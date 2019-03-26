package com.example.appetizer;

public class Restaurant {

    int restId;
    String restName;
    String restImage;

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestImage() {
        return restImage;
    }

    public void setRestImage(String restImage) {
        this.restImage = restImage;
    }

    public Restaurant(int restId, String restName, String restImage) {
        this.setRestId(restId);
        this.setRestName(restName);
        this.setRestImage(restImage);
    }
    public Restaurant(String restName, String restImage) {
        //this.setRestId(restId);
        this.setRestName(restName);
        this.setRestImage(restImage);
    }
}
