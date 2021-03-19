package com.maestros.bottlehoz.model;

public class ProductSimilarModel {
    String productName;
    int image;

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

    public ProductSimilarModel(String productName, int image) {
        this.productName = productName;
        this.image = image;
    }
}
