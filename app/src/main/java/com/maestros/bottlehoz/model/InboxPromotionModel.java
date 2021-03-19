package com.maestros.bottlehoz.model;

public class InboxPromotionModel {

    String title;
    String description;
    String msg;
    String  day;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public InboxPromotionModel(String title, String description, String msg, String day) {
        this.title = title;
        this.description = description;
        this.msg = msg;
        this.day = day;
    }
}
