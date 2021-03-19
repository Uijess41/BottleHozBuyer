package com.maestros.bottlehoz.model;

public class CategoryModel {

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public CategoryModel(String productName, int image) {
        this.productName = productName;
        this.image = image;
    }

    String productName;
    int image;


}
