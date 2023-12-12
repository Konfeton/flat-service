package com.onkonfeton.flatservice.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onkonfeton.flatservice.converter.ApartmentConverter;
import com.onkonfeton.flatservice.dto.onliner.ApartmentDTO;
import com.onkonfeton.flatservice.dto.onliner.ApartmentsDTO;
import com.onkonfeton.flatservice.flat.service.FlatService;
import com.onkonfeton.flatservice.model.Flat;
import com.onkonfeton.flatservice.model.User;
import com.onkonfeton.flatservice.model.enums.Role;
import com.onkonfeton.flatservice.model.enums.Walling;
import com.onkonfeton.flatservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.NoSuchElementException;

@Component
@Slf4j
public class DataLoader {
    private final FlatService flatService;
    private final UserService userService;
    private final ApartmentConverter apartmentConverter;

    public DataLoader(FlatService flatService, UserService userService, ApartmentConverter apartmentConverter) {
        this.flatService = flatService;
        this.userService = userService;
        this.apartmentConverter = apartmentConverter;
    }
//TODO rework method
    @Scheduled(fixedDelayString = "${dataRefreshRate}")
    public void load() throws IOException, InterruptedException, URISyntaxException {
        HttpResponse<String> response = sendGetRequest("https://r.onliner.by/sdapi/pk.api/search/apartments?order=created_at%3Adesc");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        ApartmentsDTO apartmentsDTO = gson.fromJson(response.body(), ApartmentsDTO.class);
        int counter = 0;
        boolean isAdded = false;
        for (ApartmentDTO apartment : apartmentsDTO.getApartments()) {
            if (counter >= 5) {
                break;
            }
            try {
                flatService.findById(apartment.getId());
                continue;
            } catch (NoSuchElementException ignored) {
            }
            counter++;

            Document doc = null;
            try {
                doc = Jsoup.connect("https://r.onliner.by/pk/apartments/" + apartment.getId()).get();
            } catch (IOException e) {
                log.error("no such flat: {}", apartment.getId());
                continue;
            }

            User user = assembleUser(doc, apartment.getAuthorId());
            Flat flat = assembleFlat(doc, apartment, user);

            userService.save(user);
            log.debug("User added: {}", user);
            flatService.save(flat);
            log.debug("Flat added: {}", flat);
            isAdded = true;
        }
        if (isAdded)
            log.info("Data Loaded");
        else
            log.info("Nothing to add");
    }

    private HttpResponse<String> sendGetRequest(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(uri))
                .header("Accept", "*/*")
                .build();
        HttpClient client = HttpClient.newHttpClient();
        return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    private Flat assembleFlat(Document doc, ApartmentDTO apartment, User user) {
        Flat flat = apartmentConverter.fromApartmentToFlat(apartment);
        Elements description = doc.getElementsByClass("apartment-info__sub-line apartment-info__sub-line_extended-bottom");
        Elements flatDescription = doc.getElementsByClass("apartment-options apartment-options_simplified");

        String[] info = flatDescription.first().child(1).text().split(" ");
        String walling = info[0];
        String year = null;
        try {
            year = info[2];
        } catch (Exception e) {
            year = "0";
        }

        try {
            flat.setDescription(description.get(0).text());
        } catch (Exception e) {
            flat.setDescription("");
        }
        flat.setWalling(Walling.convertFromString(walling));
        flat.setYear(Integer.parseInt(year));
        flat.setUser(user);

        return flat;
    }

    private User assembleUser(Document doc, Long author_id) {
        Elements contacts = doc.getElementsByClass("apartment-info__item apartment-info__item_secondary");
        Elements userInfo = doc.getElementsByClass("apartment-info__sub-line apartment-info__sub-line_extended");
        String phoneNumber = contacts.get(0).text();
        String name = userInfo.get(0).text();

        User user = new User();
        user.setId(author_id);

        user.setPhone(phoneNumber);
        user.setName(name);
        user.setRoles(new HashSet<>() {{
            add(Role.USER);
        }});
        user.setActive(true);
        return user;
    }
}
