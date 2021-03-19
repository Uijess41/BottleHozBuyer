package com.maestros.bottlehoz.model;

public class DiscountPercentModel {

    String percent;
    int image;

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public DiscountPercentModel(String percent, int image) {
        this.percent = percent;
        this.image = image;
    }
}
