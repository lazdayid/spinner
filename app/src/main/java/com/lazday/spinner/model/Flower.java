package com.lazday.spinner.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Flower {
    @SerializedName("name")
    private String name;
    @SerializedName("photo")
    private String photo;

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
}
