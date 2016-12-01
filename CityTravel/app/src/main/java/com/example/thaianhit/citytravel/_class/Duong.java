package com.example.thaianhit.citytravel._class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/29/2016.
 */

public class Duong {
    private int id_duong;
    @SerializedName("TenDuong")
    private String name_duong;

    public int getId_duong() {
        return id_duong;
    }

    public void setId_duong(int id_duong) {
        this.id_duong = id_duong;
    }

    public String getName_duong() {
        return name_duong;
    }

    public void setName_duong(String name_duong) {
        this.name_duong = name_duong;
    }

    public Duong(int id_duong, String name_duong) {
        this.id_duong = id_duong;
        this.name_duong = name_duong;
    }
}
