package com.RaProject.whattosee;

public class ContentContainer {
    private String name;
    private int ItemResource;
    private String name1;
    private String year;
    private String country;
    private String genres;
    private String Duration;
    private String RatingIMDB;
    private String RatingKinoPoisk;
    private String producer;
    private String discription;
    private String Cast;


    public ContentContainer(String name, String name1, String year, String country,
                            String genres, String duration, String ratingIMDB, String ratingKinoPoisk,
                            String producer, String discription, String cast, int itemResource)
    {
        this.name = name;
        ItemResource = itemResource;
        this.name1 = name1;
        this.year = year;
        this.country = country;
        this.genres = genres;
        Duration = duration;
        RatingIMDB = ratingIMDB;
        RatingKinoPoisk = ratingKinoPoisk;
        this.producer = producer;
        this.discription = discription;
        Cast = cast;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDiscription() {
        return this.discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getItemResource() {
        return this.ItemResource;
    }

    public void setItemResource(int ItemResource) {
        this.ItemResource = ItemResource;
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

    public String getRatingIMDB() {
        return RatingIMDB;
    }

    public void setRatingIMDB(String ratingIMDB) {
        RatingIMDB = ratingIMDB;
    }

    public String getRatingKinoPoisk() {
        return RatingKinoPoisk;
    }

    public void setRatingKinoPoisk(String ratingKinoPoisk) {
        RatingKinoPoisk = ratingKinoPoisk;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
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
        return Cast;
    }

    public void setCast(String cast) {
        Cast = cast;
    }
}
