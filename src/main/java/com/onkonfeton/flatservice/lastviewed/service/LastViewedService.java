package com.onkonfeton.flatservice.lastviewed.service;

import com.onkonfeton.flatservice.flat.model.Flat;
import com.onkonfeton.flatservice.lastviewed.model.LastViewed;
import com.onkonfeton.flatservice.lastviewed.model.LastViewedKey;
import com.onkonfeton.flatservice.lastviewed.repository.LastViewedRepository;
import com.onkonfeton.flatservice.user.model.User;
import com.onkonfeton.flatservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LastViewedService {
    private final LastViewedRepository lastViewedRepository;
    private final UserService userService;

    public List<Flat> getLastViewed(Principal principal){
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        List<LastViewed> byUser = lastViewedRepository.findByUserOrderByTimestampDesc(user);
        return byUser.stream()
                .map(LastViewed::getFlat)
                .collect(Collectors.toList());

    }

    public void save (Principal principal, Flat flat){
        User user = userService.findByEmail(principal.getName()).orElseThrow();
        LastViewedKey id = new LastViewedKey(user.getId(), flat.getId());


        Optional<LastViewed> lastSeen = lastViewedRepository.findById(id);
        LastViewed lastViewed;
        if (lastSeen.isPresent()){
            lastViewed = lastSeen.get();
            lastViewed.setTimestamp(LocalDateTime.now());
        }else {
            lastViewed = new LastViewed(
                    id,
                    user,
                    flat,
                    LocalDateTime.now()
            );
        }
        lastViewedRepository.save(lastViewed);


    }


}
