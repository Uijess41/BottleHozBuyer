package com.maestros.bottlehoz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signup_detail_dto {

    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("age_validation")
    @Expose
    private String ageValidation;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("otp")
    @Expose
    private String otp;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("longi")
    @Expose
    private String longi;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("path")
    @Expose
    private String path;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgeValidation() {
        return ageValidation;
    }

    public void setAgeValidation(String ageValidation) {
        this.ageValidation = ageValidation;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

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

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
