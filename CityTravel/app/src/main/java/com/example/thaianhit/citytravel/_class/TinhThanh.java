package com.example.thaianhit.citytravel._class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/29/2016.
 */

public class TinhThanh {
    private int id_tinhthanh;
    @SerializedName("TenTinhThanh")
    private String name_tinhthanh;

    public TinhThanh(int id_tinhthanh, String name_tinhthanh) {
        this.id_tinhthanh = id_tinhthanh;
        this.name_tinhthanh = name_tinhthanh;
    }

    public int getId_tinhthanh()
    {
        return id_tinhthanh;
    }

    public void setId_tinhthanh(int id_tinhthanh) {
        this.id_tinhthanh = id_tinhthanh;
    }

    public String getName_tinhthanh() {
        return name_tinhthanh;
    }

    public void setName_tinhthanh(String name_tinhthanh) {
        this.name_tinhthanh = name_tinhthanh;
    }
}
