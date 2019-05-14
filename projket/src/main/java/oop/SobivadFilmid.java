package oop;

import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SobivadFilmid{
    private Zanrid filmid;
    private List<String> Filmid;
    private List<String> Zanrid;
    private String otsitav;
    private List<String> soovitud = new ArrayList<>();


    //konstruktor
    public SobivadFilmid(String otsitav) throws Exception {
        this.filmid = new Zanrid();
        filmid.pealkirjad();
        this.Filmid = filmid.getPealkirjade_list();
        filmid.zanrid();
        this.Zanrid = filmid.getZanrite_list();
        this.otsitav = otsitav;
    }//konstruktor



    public void sobivFilm() {
        int a = 0;
        System.out.println(Filmid.size());
        System.out.println(otsitav + " zanri filmid on: ");

        for (int i = 0; i < Zanrid.size(); i++) {
            if (Zanrid.get(i).toLowerCase().indexOf(otsitav.toLowerCase()) != -1) {
                soovitud.add(Filmid.get(i));
                System.out.println(Filmid.get(i));
            } else {
                a++;
            }
        }
        if (a == Zanrid.size()) throw new SisendiErind("Ei leidu!");

    }

    public oop.Zanrid getFilmid() {
        return filmid;
    }

    public List<String> getSoovitud() {
        return soovitud;
    }

    // Random filmi valik
    public void juhuslikuFilmiGeneraator(List<String> list){

        int indeks = (int)(Math.random()*list.size());
        String juhuslik_film = list.get(indeks);
        System.out.println("\nJuhuslik film, mida võid soovi korral vaatama minna on: " + juhuslik_film);
    }
}

