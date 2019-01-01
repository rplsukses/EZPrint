package com.rplsukses.ezprint.bl.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;
import java.util.List;

public class User{
    @SerializedName("id_user")
    @Expose
    private Integer id_user;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("foto")
    @Expose
    private Object foto;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
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

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
