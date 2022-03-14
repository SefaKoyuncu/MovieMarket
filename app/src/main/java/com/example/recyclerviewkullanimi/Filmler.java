package com.example.recyclerviewkullanimi;

import java.io.Serializable;

public class Filmler implements Serializable
{
    private int film_id;
    private String film_ad;
    private String film_resim_ad;
    private double film_fiyat;
    private String film_yonetmen;
    private String film_yapim_yili;
    private int film_adet;




    public Filmler()
    {
    }



    public Filmler(int film_id, String film_ad, String film_resim_ad, double film_fiyat, String film_yonetmen, String film_yapim_yili, int film_adet) {
        this.film_id = film_id;
        this.film_ad = film_ad;
        this.film_resim_ad = film_resim_ad;
        this.film_fiyat = film_fiyat;
        this.film_yonetmen = film_yonetmen;
        this.film_yapim_yili=film_yapim_yili;
        this.film_adet=film_adet;




    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getFilm_ad() {
        return film_ad;
    }

    public void setFilm_ad(String film_ad) {
        this.film_ad = film_ad;
    }

    public String getFilm_resim_ad() {
        return film_resim_ad;
    }

    public void setFilm_resim_ad(String film_resim_ad) {
        this.film_resim_ad = film_resim_ad;
    }

    public double getFilm_fiyat() {
        return film_fiyat;
    }

    public void setFilm_fiyat(double film_fiyat) {
        this.film_fiyat = film_fiyat;
    }

    public String getFilm_yonetmen() {
        return film_yonetmen;
    }

    public void setFilm_yonetmen(String film_yonetmen) {
        this.film_yonetmen = film_yonetmen;
    }

    public String getFilm_yapim_yili() {
        return film_yapim_yili;
    }

    public void setFilm_yapim_yili(String film_yapim_yili) {
        this.film_yapim_yili = film_yapim_yili;
    }

    public int getFilm_adet() {
        return film_adet;
    }

    public void setFilm_adet(int film_adet)
    {
        this.film_adet = film_adet;

    }
}
