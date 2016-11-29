package com.example.thaianhit.citytravel;

/**
 * Created by NGUYEN TUAN ANH on 11/9/2016.
 */

public class DataRecyclerSearch {
    private String name;
    // Phần mô tả cho danh mục đó
    private String photo;
    private String  category;
    private float nearme;
    private String address;
    private String rating;

    // Khởi tạo dữ liệu cho danh mục

    public DataRecyclerSearch(String rating, String address, float nearme, String category, String photo, String name) {
        this.rating = rating;
        this.address = address;
        this.nearme = nearme;
        this.category = category;
        this.photo = photo;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getNearme() {
        return nearme;
    }

    public void setNearme(float nearme) {
        this.nearme = nearme;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}