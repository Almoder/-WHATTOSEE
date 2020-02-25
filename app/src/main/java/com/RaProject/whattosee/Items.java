package com.RaProject.whattosee;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Items {
    private int key;
    private Bitmap itemRes;
    private String name;
    private String genres;
    private String year;

    public Items(int key,
                 Bitmap image,
                 String title,
                 String year){

        this.key = key;
        this.itemRes = image;
        this.name = title;
        this.year = year;

    }

    public Items(int key,
                 byte[] blob,
                 String title,
                 String year,
                 String genres){

        this.key = key;
        this.itemRes = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        this.name = title;
        this.year = year;
        this.genres = genres;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return this.genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Bitmap getItemRes() {
        return this.itemRes;
    }

    public void setItemRes(Bitmap itemRes) {
        this.itemRes = itemRes;
    }

    public int getKey(){
        return this.key;
    }

    public void setKys(int key) {
        this.key = key;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

