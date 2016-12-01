package com.example.thaianhit.citytravel._class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/29/2016.
 */

public class Category {
    private int id_category;
    @SerializedName("Name")
    private String name_category;
    @SerializedName("Hinh")
    private String photo_category;

    public Category(int id_category, String photo_category, String name_category) {
        this.id_category = id_category;
        this.photo_category = photo_category;
        this.name_category = name_category;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }

    public String getPhoto_category() {
        return photo_category;
    }

    public void setPhoto_category(String photo_category) {
        this.photo_category = photo_category;
    }
}
