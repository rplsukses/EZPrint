package com.rplsukses.ezprint.bl.network.model;

import java.math.BigDecimal;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.stmt.query.In;

public class TransaksiGet {

    @SerializedName("transaksi")
    @Expose
    private List<TransaksiData> transaksi = null;

    public List<TransaksiData> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(List<TransaksiData> transaksi) {
        this.transaksi = transaksi;
    }

    public class TransaksiData {

        @SerializedName("id_transaksi")
        @Expose
        private Integer idTransaksi;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("tgl_pesan")
        @Expose
        private String tglPesan;
        @SerializedName("tgl_selesai")
        @Expose
        private String tglSelesai;
        @SerializedName("jumlah")
        @Expose
        private Integer jumlah;
        @SerializedName("harga_total")
        @Expose
        private BigDecimal hargaTotal;
        @SerializedName("keterangan")
        @Expose
        private String keterangan;
        @SerializedName("id_mitra")
        @Expose
        private Integer idMitra;
        @SerializedName("id_produk")
        @Expose
        private Integer idProduk;
        @SerializedName("file")
        @Expose
        private String file;

        public Integer getIdTransaksi() {
            return idTransaksi;
        }

        public void setIdTransaksi(Integer idTransaksi) {
            this.idTransaksi = idTransaksi;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTglPesan() {
            return tglPesan;
        }

        public void setTglPesan(String tglPesan) {
            this.tglPesan = tglPesan;
        }

        public String getTglSelesai() {
            return tglSelesai;
        }

        public void setTglSelesai(String tglSelesai) {
            this.tglSelesai = tglSelesai;
        }

        public Integer getJumlah() {
            return jumlah;
        }

        public void setJumlah(Integer jumlah) {
            this.jumlah = jumlah;
        }

        public BigDecimal getHargaTotal() {
            return hargaTotal;
        }

        public void setHargaTotal(BigDecimal hargaTotal) {
            this.hargaTotal = hargaTotal;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public Integer getIdMitra() {
            return idMitra;
        }

        public void setIdMitra(Integer idMitra) {
            this.idMitra = idMitra;
        }

        public Integer getIdProduk() {
            return idProduk;
        }

        public void setIdProduk(Integer idProduk) {
            this.idProduk = idProduk;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }
}