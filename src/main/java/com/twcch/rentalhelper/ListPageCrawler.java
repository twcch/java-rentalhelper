package com.twcch.rentalhelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListPageCrawler {

    private int regionId;
    private int sectionId;
    private Document doc;

    public ListPageCrawler(int regionId, int sectionId) throws IOException {
        this.regionId = regionId;
        this.sectionId = sectionId;
        String url = "https://rent.591.com.tw/?region=" + regionId + "&section=" + sectionId;
        this.doc = Jsoup.connect(url).get();
    }

//    public List<String> getDetailUrls() {
//        List<String> result = new ArrayList<>();
//
//
//    }

}
