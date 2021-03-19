package com.maestros.bottlehoz.model;

public class InboxCouponModel {

    String userName;
    String price;
    String  time;
    String  date;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public InboxCouponModel(String userName, String price, String time, String date, String over, int image) {
        this.userName = userName;
        this.price = price;
        this.time = time;
        this.date = date;
        this.over = over;
        this.image = image;
    }

    String  over;
    int image;


}
