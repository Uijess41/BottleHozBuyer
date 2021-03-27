package com.maestros.bottlehoz.model;

public class CartProductModel {
    String name,description,price,deliveryFee,cartAmount;
    int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(String cartAmount) {
        this.cartAmount = cartAmount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public CartProductModel(String name, String description, String price, String deliveryFee, String cartAmount, int image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.cartAmount = cartAmount;
        this.image = image;
    }
}
