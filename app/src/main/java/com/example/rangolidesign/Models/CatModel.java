package com.example.rangolidesign.Models;

public class CatModel {
    String imageurl;
    String id;
    String title;

    public CatModel(String imageurl, String id, String title) {

        this.imageurl = imageurl;
        this.id = id;
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }


}
