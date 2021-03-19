package com.maestros.bottlehoz.model;

public class AllListingModel {

    String productName;
    String price;
    int image;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public AllListingModel(String productName, String price, int image) {
        this.productName = productName;
        this.price = price;
        this.image = image;
    }
}
