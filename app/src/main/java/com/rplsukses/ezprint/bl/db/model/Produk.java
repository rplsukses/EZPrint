package com.rplsukses.ezprint.bl.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable(tableName = Produk.TBL_NAME)
public class Produk {
    public static final String TBL_NAME = "produk";
    public static final String ID_PRODUK = "id_produk";
    public static final String ID_MITRA = "id_mitra";
    public static final String ID_KATEGORI = "id_kategori";
    public static final String WARNA = "warna";
    public static final String BAHAN = "bahan";
    public static final String UKURAN = "ukuran";
    public static final String KATEGORI = "kategori";
    public static final String ICON = "icon";
    public static final String HARGA = "harga";

    @DatabaseField(columnName = ID_PRODUK, id = true) private Integer id_produk;
    @DatabaseField(columnName = ID_KATEGORI) private Integer id_kategori;
    @DatabaseField(columnName = ID_MITRA) private Integer id_mitra;
    @DatabaseField(columnName = BAHAN) private String bahan;
    @DatabaseField(columnName = WARNA) private String warna;
    @DatabaseField(columnName = UKURAN) private String ukuran;
    @DatabaseField(columnName = HARGA) private BigDecimal harga;
    @DatabaseField(columnName = KATEGORI) private String kategori;
    @DatabaseField(columnName = ICON) private String icon;

    public Produk() {
    }

    public Integer getId_produk() {
        return id_produk;
    }

    public void setId_produk(Integer id_produk) {
        this.id_produk = id_produk;
    }

    public Integer getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(Integer id_kategori) {
        this.id_kategori = id_kategori;
    }

    public Integer getId_mitra() {
        return id_mitra;
    }

    public void setId_mitra(Integer id_mitra) {
        this.id_mitra = id_mitra;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
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

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }
}
