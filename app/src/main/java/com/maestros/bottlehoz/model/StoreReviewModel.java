package com.maestros.bottlehoz.model;

public class StoreReviewModel {


    String userName;
    String comment;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public StoreReviewModel(String userName, String comment, String rating) {
        this.userName = userName;
        this.comment = comment;
        this.rating = rating;
    }

    String rating;


}
