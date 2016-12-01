package com.example.thaianhit.citytravel._class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/29/2016.
 */

public class Phuong {
    private int id_phuong;
    @SerializedName("TenPhuong")
    private String name_phuong;

    public Phuong(int id_phuong, String name_phuong) {
        this.id_phuong = id_phuong;
        this.name_phuong = name_phuong;
    }

    public int getId_phuong() {
        return id_phuong;
    }

    public void setId_phuong(int id_phuong) {
        this.id_phuong = id_phuong;
    }

    public String getName_phuong() {
        return name_phuong;
    }

    public void setName_phuong(String name_phuong) {
        this.name_phuong = name_phuong;
    }
}
