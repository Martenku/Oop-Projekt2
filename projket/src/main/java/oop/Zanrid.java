package oop;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Zanrid {
    Document document;
    public List<String> zanrite_list = new ArrayList<String>();
    public List<String> pealkirjade_list = new ArrayList<String>();


    public Zanrid() throws IOException {
        this.document = Jsoup.connect("https://www.forumcinemas.ee/Movies/NowInTheatres/").get();

    }

    // Koguda kokku koik zanrid
    public List<String> zanrid() {

        for (Element row : document.select("div.results tr")) {
            final String zanr = row.select(".small_txt").text(); // zanrid
            int alug = zanr.indexOf("Žanr: ");
            if (alug > 0) {
                String filmi_zanr = zanr.substring(alug + 5);
                zanrite_list.add(filmi_zanr);
            }//if
        }//for
        this.pealkirjade_list = pealkirjade_list;
        return zanrite_list;
    }//zanrid

    // Koguda kokku koik filmide pealkirjad
    public List<String> pealkirjad() {
        for (Element row : document.select("div.results tr")) {
            final String title = row.select("span.result_h").text(); // filmi pealkirjad
            if (title != null && !title.isEmpty()) pealkirjade_list.add(title);
        }//for

        return pealkirjade_list;
    }//pealkirjad



    public List<String> getZanrite_list() {
        return zanrite_list;
    }

    public void setZanrite_list(List<String> zanrite_list) {
        this.zanrite_list = zanrite_list;
    }

    public List<String> getPealkirjade_list() {
        return pealkirjade_list;
    }

    public void setPealkirjade_list(List<String> pealkirjade_list) {
        this.pealkirjade_list = pealkirjade_list;
    }

}