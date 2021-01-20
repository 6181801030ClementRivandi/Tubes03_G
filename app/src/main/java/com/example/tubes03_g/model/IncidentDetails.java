package com.example.tubes03_g.model;

import android.os.Parcel;
import android.os.Parcelable;

public class IncidentDetails implements Parcelable {

    int id = 0;
    String title;
    String description;
    String address;


    public IncidentDetails(int id,String title, String description, String address) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
    }

    public IncidentDetails(){}

    public IncidentDetails(Parcel in) {
        title = in.readString();
        description = in.readString();
        address = in.readString();

    }

    public static final Creator<IncidentDetails> CREATOR = new Creator<IncidentDetails>() {
        @Override
        public IncidentDetails createFromParcel(Parcel in) {
            return new IncidentDetails(in);
        }

        @Override
        public IncidentDetails[] newArray(int size) {
            return new IncidentDetails[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.address);
    }
}