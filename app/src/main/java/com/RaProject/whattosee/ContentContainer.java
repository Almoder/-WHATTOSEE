package com.RaProject.whattosee;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ContentContainer {
    private String name;
    private Bitmap itemRes;
    private String name1;
    private String year;
    private String country;
    private String genres;
    private String duration;
    private String ratingDev;
    private String ratingKinoPoisk;
    private String producer;
    private String description;
    private String cast;


    public ContentContainer(String name, String name1, String year, String country,
                            String genres, String duration, String ratingDev, String ratingKinoPoisk,
                            String producer, String description, String cast, Bitmap itemRes)
    {
        this.name = name;
        itemRes = itemRes;
        this.name1 = name1;
        this.year = year;
        this.country = country;
        this.genres = genres;
        this.duration = duration;
        this.ratingDev = ratingDev;
        this.ratingKinoPoisk = ratingKinoPoisk;
        this.producer = producer;
        this.description = description;
        this.cast = cast;
    }

    public ContentContainer(Cursor cursor, Cursor genres){

        cursor.moveToFirst();
        genres.moveToFirst();
        this.name = cursor.getString(2);
        byte[] blob = cursor.getBlob(1);
        itemRes = BitmapFactory.decodeByteArray(blob , 0, blob .length);
        this.name1 = cursor.getString(2);
        this.year = cursor.getString(3);
        this.country = cursor.getString(4);
        this.genres = "";
        for(int i = 1; i < 20; i++)
            if(genres.getString(i) != null) this.genres += genres.getString(i);
        this.duration = cursor.getString(5);
        this.ratingDev = cursor.getString(6);
        this.ratingKinoPoisk = cursor.getString(7);
        this.producer = cursor.getString(8);
        this.description = cursor.getString(10);
        this.cast = cursor.getString(9);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDiscription(String description) {
        this.description = description;
    }

    public Bitmap getItemRes() {
        return this.itemRes;
    }

    public void setItemRes(Bitmap itemRes) {
        this.itemRes = itemRes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRatingDev() {
        return ratingDev;
    }

    public void setRatingDev(String ratingDev) {
        this.ratingDev = ratingDev;
    }

    public String getRatingKinoPoisk() {
        return ratingKinoPoisk;
    }

    public void setRatingKinoPoisk(String ratingKinoPoisk) {
        this.ratingKinoPoisk = ratingKinoPoisk;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
