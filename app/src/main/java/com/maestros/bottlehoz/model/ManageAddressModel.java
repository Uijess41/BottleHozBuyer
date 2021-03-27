package com.maestros.bottlehoz.model;

public class ManageAddressModel {


    String name;
    String address;
    String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ManageAddressModel(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }
}
