package com.rplsukses.ezprint.bl.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Mitra.TBL_NAME)
public class Mitra {
    public static final String TBL_NAME = "mitra";
    public static final String ID_MITRA = "id_mitra";
    public static final String NAMA = "mitra";
    public static final String EMAIL = "email";

    @DatabaseField(columnName =  ID_MITRA, id = true) private Integer id_mitra;
    @DatabaseField(columnName = NAMA) private String nama;
    @DatabaseField(columnName = EMAIL) private String email;
    @DatabaseField(columnName = "alamat") private String alamat;
    @DatabaseField(columnName = "telepon") private String telepon;
    @DatabaseField(columnName = "foto") private String foto;
    @DatabaseField(columnName = "jam_buka") private String jam_buka;
    @DatabaseField(columnName = "jam_tutup") private String jam_tutup;

    public Mitra() {
    }

    public Integer getId_mitra() {
        return id_mitra;
    }

    public void setId_mitra(Integer id_mitra) {
        this.id_mitra = id_mitra;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getJam_buka() {
        return jam_buka;
    }

    public void setJam_buka(String jam_buka) {
        this.jam_buka = jam_buka;
    }

    public String getJam_tutup() {
        return jam_tutup;
    }

    public void setJam_tutup(String jam_tutup) {
        this.jam_tutup = jam_tutup;
    }
}
