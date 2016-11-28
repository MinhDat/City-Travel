package com.example.thaianhit.citytravel;

/**
 * Created by T.N on 11/15/2016.
 */

public class Comment {
    private String name,comment,avatar;
    private String date;

    public Comment(String name, String date, String avatar, String comment) {
        this.name = name;
        this.date = date;
        this.avatar = avatar;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
