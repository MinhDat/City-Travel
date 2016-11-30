package com.example.thaianhit.citytravel._class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/29/2016.
 */

public class Place {
    @SerializedName("MaTenDiaDiem")
    int id_place;
    @SerializedName("TenDiaDiem1")
    String name_place;

    public String getName_place() {
        return name_place;
    }

    public void setName_place(String name_place) {
        this.name_place = name_place;
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public Place(int id_place, String name_place) {
        this.id_place = id_place;
        this.name_place = name_place;
    }
}
