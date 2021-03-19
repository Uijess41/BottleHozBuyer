package com.maestros.bottlehoz.model;

public class MoreModel {

    String price;
    String soldCoun;
    String rating;

    public MoreModel(String price, String soldCoun, String rating, String name, int image) {
        this.price = price;
        this.soldCoun = soldCoun;
        this.rating = rating;
        this.name = name;
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    int image;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSoldCoun() {
        return soldCoun;
    }

    public void setSoldCoun(String soldCoun) {
        this.soldCoun = soldCoun;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
