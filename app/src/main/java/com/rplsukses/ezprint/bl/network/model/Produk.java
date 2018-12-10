package com.rplsukses.ezprint.bl.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Produk {
    @SerializedName("id_produk")
    @Expose
    private String idProduk;
    @SerializedName("id_mitra")
    @Expose
    private String idMitra;
    @SerializedName("id_kategori")
    @Expose
    private String idKategori;
    @SerializedName("ukuran")
    @Expose
    private String ukuran;
    @SerializedName("bahan")
    @Expose
    private String bahan;
    @SerializedName("warna")
    @Expose
    private String warna;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("kategori")
    @Expose
    private Object kategori;
    @SerializedName("icon")
    @Expose
    private Object icon;

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getIdMitra() {
        return idMitra;
    }

    public void setIdMitra(String idMitra) {
        this.idMitra = idMitra;
    }

    public String getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(String idKategori) {
        this.idKategori = idKategori;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public Object getKategori() {
        return kategori;
    }

    public void setKategori(Object kategori) {
        this.kategori = kategori;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }
}
