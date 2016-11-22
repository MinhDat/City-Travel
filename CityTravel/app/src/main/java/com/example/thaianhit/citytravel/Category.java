package com.example.thaianhit.citytravel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/21/2016.
 */

public class Category {
    @SerializedName("Name")
    private  String name;
    @SerializedName("Picture")
    private  String picture;

    public Category(String id, String name, String picture) {

        this.name = name;
        this.picture = picture;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
