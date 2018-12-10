package com.rplsukses.ezprint.bl.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MitraGet {
    @Expose
    private List<MitraData> mitra = new ArrayList<MitraData>();

    public List<MitraData> getMitra() {
        return mitra;
    }

    public void setMitra(List<MitraData> mitra) {
        this.mitra = mitra;
    }

    public class MitraData{
        @SerializedName("id_mitra")
        @Expose
        private Integer idMitra;
        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("telepon")
        @Expose
        private String telepon;
        @SerializedName("foto")
        @Expose
        private String foto;
        @SerializedName("jam_buka")
        @Expose
        private String jamBuka;
        @SerializedName("jam_tutup")
        @Expose
        private String jamTutup;

        public Integer getIdMitra() {
            return idMitra;
        }

        public void setIdMitra(Integer idMitra) {
            this.idMitra = idMitra;
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

        public String getJamBuka() {
            return jamBuka;
        }

        public void setJamBuka(String jamBuka) {
            this.jamBuka = jamBuka;
        }

        public String getJamTutup() {
            return jamTutup;
        }

        public void setJamTutup(String jamTutup) {
            this.jamTutup = jamTutup;
        }
    }
}
