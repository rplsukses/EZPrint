package com.rplsukses.ezprint.bl.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;

@DatabaseTable(tableName = Transaksi.TBL_NAME)
public class Transaksi {
    public static final String TBL_NAME = "transaksi";
    public static final String ID_TRANSAKSI = "id_transaksi";
    public static final String ID_PRODUK = "id_produk";
    public static final String ID_MITRA = "id_mitra";
    public static final String FILE = "file";
    public static final String HARGA_TOTAL = "harga_total";
    public static final String JUMLAH = "jumlah";
    public static final String STATUS = "status";
    public static final String TGL_PESAN = "tgl_mulai";
    public static final String TGL_SELESAI = "tgl_selesai";
    public static final String KETERANGAN = "keterangan";

    @DatabaseField(columnName = ID_TRANSAKSI, id = true) private Integer id_transaksi;
    @DatabaseField(columnName = ID_MITRA) private Integer id_mitra;
    @DatabaseField(columnName = ID_PRODUK) private Integer id_produk;
    @DatabaseField(columnName = FILE) private String file;
    @DatabaseField(columnName = STATUS) private String status;
    @DatabaseField(columnName = HARGA_TOTAL) private BigDecimal harga_total;
    @DatabaseField(columnName = JUMLAH) private Integer jumlah;
    @DatabaseField(columnName = TGL_PESAN) private String tgl_pesan;
    @DatabaseField(columnName = TGL_SELESAI) private String tgl_selesai;
    @DatabaseField(columnName = KETERANGAN) private String keterangan;

    public Transaksi() {
    }

    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getId_mitra() {
        return id_mitra;
    }

    public void setId_mitra(Integer id_mitra) {
        this.id_mitra = id_mitra;
    }

    public Integer getId_produk() {
        return id_produk;
    }

    public void setId_produk(Integer id_produk) {
        this.id_produk = id_produk;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getHarga_total() {
        return harga_total;
    }

    public void setHarga_total(BigDecimal harga_total) {
        this.harga_total = harga_total;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getTgl_pesan() {
        return tgl_pesan;
    }

    public void setTgl_pesan(String tgl_pesan) {
        this.tgl_pesan = tgl_pesan;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public void setTgl_selesai(String tgl_selesai) {
        this.tgl_selesai = tgl_selesai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
