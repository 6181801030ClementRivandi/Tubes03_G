package com.example.tubes03_g.model;

import android.os.Parcel;
import android.os.Parcelable;

public class IncidentModel implements Parcelable {
    int id = 0;
    String title;
    String deskripsi;
    String[] tag;
    String[] bahan;
    String[] langkahMasak;
    String[] resto;

    public IncidentModel(int id,String title, String deskripsi, String[] tag,
                         String[] bahan, String[] langkahMasak, String[] resto) {
        this.id =id;
        this.title = title;
        this.deskripsi = deskripsi;
        this.tag = tag;
        this.bahan = bahan;
        this.langkahMasak  = langkahMasak;
        this.resto = resto;
    }
    public IncidentModel(){}
    public IncidentModel(Parcel in) {
        title = in.readString();
        deskripsi = in.readString();
        tag = in.createStringArray();
        bahan = in.createStringArray();
        langkahMasak = in.createStringArray();
        resto = in.createStringArray();
    }

    public static final Creator<IncidentModel> CREATOR = new Creator<IncidentModel>() {
        @Override
        public IncidentModel createFromParcel(Parcel in) {
            return new IncidentModel(in);
        }

        @Override
        public IncidentModel[] newArray(int size) {
            return new IncidentModel[size];
        }
    };
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public void setBahan(String[] bahan) {
        this.bahan = bahan;
    }

    public void setLangkahMasak(String[] langkahMasak) {
        this.langkahMasak = langkahMasak;
    }

    public void setResto(String[] resto) {
        this.resto = resto;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTag() {
        String result = "";
        for (int i = 0; i < tag.length; i++) {
            if(i!=tag.length-1){
                result+=tag[i] +", ";
            }else{
                result+=tag[i];
            }
        }
        return result;
    }

    public String[] getTagArr(){
        return tag;
    }

    public String getBahan() {
        String result = "";
        for (int i = 0; i < bahan.length; i++) {
            result+=bahan[i] +"\n";

        }
        return result;
    }

    public String[] getBahanArr(){
        return bahan;
    }

    public String getLangkahMasak() {
        String result = "";
        for (int i = 0; i < langkahMasak.length; i++) {
            result+=langkahMasak[i] +"\n";
        }
        return result;
    }

    public String[] getLangkahMasakArr(){
        return langkahMasak;
    }

    public String getResto() {
        String result = "";
        for (int i = 0; i < resto.length; i++) {
            result+=resto[i]+"\n";

        }
        return result;
    }

    public String[] getRestoArr(){
        return resto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.deskripsi);
        dest.writeStringArray(this.tag);
        dest.writeStringArray(this.bahan);
        dest.writeStringArray(this.langkahMasak);
        dest.writeStringArray(this.resto);
    }
    //
}
