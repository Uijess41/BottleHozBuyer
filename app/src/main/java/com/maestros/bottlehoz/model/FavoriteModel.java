package com.maestros.bottlehoz.model;

public class FavoriteModel {

    String productName;
    String brandName;
    String  catName;
    int image;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public FavoriteModel(String productName, String brandName, String catName, int image) {
        this.productName = productName;
        this.brandName = brandName;
        this.catName = catName;
        this.image = image;
    }
}
