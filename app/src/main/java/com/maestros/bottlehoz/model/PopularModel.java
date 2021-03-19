package com.maestros.bottlehoz.model;

public class PopularModel {

    String price,soldCoun;
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

    public PopularModel(String price, String soldCoun, int image) {
        this.price = price;
        this.soldCoun = soldCoun;
        this.image = image;
    }
}
