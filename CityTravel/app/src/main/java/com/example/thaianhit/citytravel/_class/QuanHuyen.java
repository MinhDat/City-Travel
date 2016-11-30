package com.example.thaianhit.citytravel._class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/29/2016.
 */

public class QuanHuyen {
    @SerializedName("MaQuanHuyen")
    private int id_quanhuyen;
    @SerializedName("TenQuanHuyen")
    private  String name_quanhuyen;

    public QuanHuyen(int id_quanhuyen, String name_quanhuyen) {
        this.id_quanhuyen = id_quanhuyen;
        this.name_quanhuyen = name_quanhuyen;
    }

    public int getId_quanhuyen()
    {
        return id_quanhuyen;
    }

    public void setId_quanhuyen(int id_quanhuyen) {
        this.id_quanhuyen = id_quanhuyen;
    }

    public String getName_quanhuyen() {
        return name_quanhuyen;
    }

    public void setName_quanhuyen(String name_quanhuyen) {
        this.name_quanhuyen = name_quanhuyen;
    }
}
