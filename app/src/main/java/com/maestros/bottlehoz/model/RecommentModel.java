package com.maestros.bottlehoz.model;

public class RecommentModel {

    String productName;
    String discription;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public RecommentModel(String productName, String discription, String rating, int image) {
        this.productName = productName;
        this.discription = discription;
        this.rating = rating;
        this.image = image;
    }

    String rating;
    int image;

}
