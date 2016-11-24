package com.example.thaianhit.citytravel;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by T.N on 11/20/2016.
 */

public class Account {
    @SerializedName("Email")
    private String email;
    @SerializedName("PassWord")
    private String password;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("FirtName")
    private String firsrName;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Sex")
    private int gender;
    @SerializedName("Birth")
    private Date birth;
    @SerializedName("Address")
    private String address;
    @SerializedName("Picture")
    private String picture;

    public Account(String email, String picture, String address, Date birth, int gender, String phone, String firsrName, String lastName, String password) {
        this.email = email;
        this.picture = picture;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.firsrName = firsrName;
        this.lastName = lastName;
        this.password = password;
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

    public String getFirsrName() {
        return firsrName;
    }

    public void setFirsrName(String firsrName) {
        this.firsrName = firsrName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
