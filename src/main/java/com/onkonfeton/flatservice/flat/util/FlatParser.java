package com.onkonfeton.flatservice.flat.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FlatParser {
    public AdditionalInfo parse(String url) throws IOException, IndexOutOfBoundsException {
        Document document = Jsoup.connect(url).get();
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setSellerName(document.select("#apartment-phones > div:nth-child(3) > a").text());
        additionalInfo.setSellerPhone(document.select("#apartment-phones > div.apartment-info__sub-line.apartment-info__sub-line_extended-bottom_condensed-alter > ul > li.apartment-info__item.apartment-info__item_secondary > a")
                .text()
                .substring(0, 17));
        additionalInfo.setDescription(document.select("#container > div > div.l-gradient-wrapper > div > div > div.arenda-apartment > div.apartment-info > div:nth-child(4) > div > div.apartment-info__cell.apartment-info__cell_66 > div.apartment-info__sub-line.apartment-info__sub-line_extended-bottom").text());
        String[] info = document.select("#container > div > div.l-gradient-wrapper > div > div > div.arenda-apartment > div.apartment-info > div:nth-child(1) > div > div.apartment-info__cell.apartment-info__cell_66 > div.apartment-info__column.apartment-info__column_50.apartment-info__column_shifted > ul > li:nth-child(2)")
                .text()
                .split(" ");
        additionalInfo.setWalling(info[0]);
        additionalInfo.setYear(Integer.parseInt(info[2]));
        return additionalInfo;
    }
}
