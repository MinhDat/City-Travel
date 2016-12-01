package com.example.thaianhit.citytravel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/15/2016.
 */

public class Comment {
    @SerializedName("Ho")
    private String first_name;
    @SerializedName("Ten")
    private String last_name;
    @SerializedName("NoiDung")
    private String comment;
    @SerializedName("Hinh")
    private String avatar;
    @SerializedName("ThoiGian")
    private String date;

    public Comment(String first_name, String last_name, String comment, String avatar, String date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.comment = comment;
        this.avatar = avatar;
        this.date = date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
