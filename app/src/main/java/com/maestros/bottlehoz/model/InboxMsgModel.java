package com.maestros.bottlehoz.model;

public class InboxMsgModel {

    String userName;
    String description;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public InboxMsgModel(String userName, String description, String time, int image) {
        this.userName = userName;
        this.description = description;
        this.time = time;
        this.image = image;
    }

    String  time;
    int image;


}
