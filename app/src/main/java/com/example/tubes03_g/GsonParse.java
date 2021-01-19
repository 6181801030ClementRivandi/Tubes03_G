package com.example.tubes03_g;

import com.google.gson.annotations.SerializedName;

public class GsonParse {
    @SerializedName("id")
    private int id;
    public GsonParse(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
