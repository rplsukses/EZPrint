package com.rplsukses.ezprint.bl.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.stmt.query.In;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdukGet {
    @SerializedName("produk")
    @Expose
    private List<ProdukData> produk = new ArrayList<ProdukData>();

    public List<ProdukData> getProduk() {
        return produk;
    }

    public void setProduk(List<ProdukData> produk) {
        this.produk = produk;
    }

    public class ProdukData {

        @SerializedName("id_produk")
        @Expose
        private Integer idProduk;
        @SerializedName("id_mitra")
        @Expose
        private Integer idMitra;
        @SerializedName("id_kategori")
        @Expose
        private Integer idKategori;
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
        private BigDecimal harga;
        @SerializedName("kategori")
        @Expose
        private String kategori;
        @SerializedName("icon")
        @Expose
        private String icon;

        public Integer getIdProduk() {
            return idProduk;
        }

        public void setIdProduk(Integer idProduk) {
            this.idProduk = idProduk;
        }

        public Integer getIdMitra() {
            return idMitra;
        }

        public void setIdMitra(Integer idMitra) {
            this.idMitra = idMitra;
        }

        public Integer getIdKategori() {
            return idKategori;
        }

        public void setIdKategori(Integer idKategori) {
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

        public BigDecimal getHarga() {
            return harga;
        }

        public void setHarga(BigDecimal harga) {
            this.harga = harga;
        }

        public String getKategori() {
            return kategori;
        }

        public void setKategori(String kategori) {
            this.kategori = kategori;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }
}
