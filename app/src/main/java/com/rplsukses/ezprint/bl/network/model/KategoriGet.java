package com.rplsukses.ezprint.bl.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class KategoriGet {
    @SerializedName("kategori")
    @Expose
    private List<KategoriData> kategori = new ArrayList<KategoriData>();

    public List<KategoriData> getKategori() {
        return kategori;
    }

    public void setKategori(List<KategoriData> kategori) {
        this.kategori = kategori;
    }

    public class KategoriData {

        @SerializedName("id_kategori")
        @Expose
        private Integer idKategori;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("icon")
        @Expose
        private String icon;

        public Integer getIdKategori() {
            return idKategori;
        }

        public void setIdKategori(Integer idKategori) {
            this.idKategori = idKategori;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }
}
