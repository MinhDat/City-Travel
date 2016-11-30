package com.example.thaianhit.citytravel;


import com.google.gson.annotations.SerializedName;



/**
 * Created by T.N on 11/20/2016.
 */

public class Account {
    @SerializedName("IdUser")
    private int id;
    @SerializedName("Email")
    private String email;
    @SerializedName("PassWord")
    private String password;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("FirtName")
    private String firstName;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Sex")
    private int gender;
    @SerializedName("Birth")
    private String birth;
    @SerializedName("Address")
    private String address;
    @SerializedName("Picture")
    private String picture;
    @SerializedName("Role")
    private String role;





    public Account(int id,String email, String picture, String address, String birth, int gender, String phone, String firsrName, String lastName, String password, String role) {
        this.id = id;
        this.email = email;
        this.picture = picture;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.firstName = firsrName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }
    public Account(String email, String picture, String address, String birth, int gender, String phone, String firsrName, String lastName, String password, String role) {

        this.email = email;
        this.picture = picture;
        this.address = address;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.firstName = firsrName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
