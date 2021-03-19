package com.maestros.bottlehoz.model;

public class FeatureRecommentModel {

    String productTitle;
    String discription;

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
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

    public FeatureRecommentModel(String productTitle, String discription, String rating, int image) {
        this.productTitle = productTitle;
        this.discription = discription;
        this.rating = rating;
        this.image = image;
    }

    String rating;
    int image;


}
