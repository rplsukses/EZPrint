package com.rplsukses.ezprint.bl.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("id_kategori")
    @Expose
    private String idKategori;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("icon")
    @Expose
    private Object icon;

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }
}
