package com.rplsukses.ezprint.bl.network.api;

import android.content.Context;
import android.util.Log;

import com.rplsukses.ezprint.bl.db.dao.KategoriDao;
import com.rplsukses.ezprint.bl.db.dao.MitraDao;
import com.rplsukses.ezprint.bl.db.dao.ProdukDao;
import com.rplsukses.ezprint.bl.db.dao.TransaksiDao;
import com.rplsukses.ezprint.bl.db.model.Kategori;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.bl.db.model.Transaksi;
import com.rplsukses.ezprint.bl.network.model.KategoriGet;
import com.rplsukses.ezprint.bl.network.model.MitraGet;
import com.rplsukses.ezprint.bl.network.model.ProdukGet;
import com.rplsukses.ezprint.bl.network.model.TransaksiGet;
import com.rplsukses.ezprint.ui.dialog.DialogBuilder;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncWorker {
    private static SyncWorker syncWorker;

    public static SyncWorker getSyncWorker() {
        if (syncWorker == null){
            syncWorker = new SyncWorker();
        }
        return syncWorker;
    }

    public void syncMitra(final Context ctx, Call<MitraGet> mitraGetCall, final boolean isFirstRun){
        mitraGetCall.enqueue(new Callback<MitraGet>() {
            @Override
            public void onResponse(Call<MitraGet> call, Response<MitraGet> response) {
                if (response.isSuccessful()){
                    MitraGet mitraGet = response.body();
                    Log.i("MITRA_GET", response.message());
                    for (MitraGet.MitraData list :mitraGet.getMitra()) {
                        Mitra mitra = new Mitra();
                        mitra.setId_mitra(list.getIdMitra());
                        mitra.setNama(list.getNama());
                        mitra.setEmail(list.getEmail());
                        mitra.setFoto(list.getFoto());
                        mitra.setAlamat(list.getAlamat());
                        mitra.setTelepon(list.getTelepon());
                        mitra.setJam_buka(list.getJamBuka());
                        mitra.setJam_tutup(list.getJamTutup());

                        try {
                            if (isFirstRun) {
                                MitraDao.getMitraDao().add(mitra);
                            }else {
                                MitraDao.getMitraDao().save(mitra);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MitraGet> call, Throwable t) {
                DialogBuilder.showErrorDialog(ctx, t.getMessage());
                Log.i("MITRA_GET", t.getMessage());
            }
        });
    }

    public void syncProduk(final Context ctx, Call<ProdukGet> produkGetCall, final boolean isFirstRun){
        produkGetCall.enqueue(new Callback<ProdukGet>() {
            @Override
            public void onResponse(Call<ProdukGet> call, Response<ProdukGet> response) {
                if (response.isSuccessful()){
                    ProdukGet produkGet = response.body();
                    Log.i("PRODUK_GET", response.message());
                    for (ProdukGet.ProdukData data : produkGet.getProduk()) {
                        Produk produk = new Produk();
                        produk.setId_produk(data.getIdProduk());
                        produk.setId_kategori(data.getIdKategori());
                        produk.setId_mitra(data.getIdMitra());
                        produk.setWarna(data.getWarna());
                        produk.setKategori(data.getKategori());
                        produk.setBahan(data.getBahan());
                        produk.setUkuran(data.getUkuran());
                        produk.setHarga(data.getHarga());
                        produk.setIcon(data.getIcon());

                        try {
                            if (isFirstRun) {
                                ProdukDao.getProdukDao().add(produk);
                            }else {
                                ProdukDao.getProdukDao().save(produk);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProdukGet> call, Throwable t) {
                Log.i("PRODUK_GET", t.getMessage());
            }
        });
    }

    public void syncTransaksi(final Context ctx, Call<TransaksiGet> transaksiGetCall, final boolean isFirstRun){
        transaksiGetCall.enqueue(new Callback<TransaksiGet>() {
            @Override
            public void onResponse(Call<TransaksiGet> call, Response<TransaksiGet> response) {
                if (response.isSuccessful()){
                    TransaksiGet transaksiGet = response.body();
                    Log.i("TRANSAKSI_GET", response.message());
                    for (TransaksiGet.TransaksiData data : transaksiGet.getTransaksi()) {
                        Transaksi transaksi = new Transaksi();
                        transaksi.setId_transaksi(data.getIdTransaksi());
                        transaksi.setId_mitra(data.getIdMitra());
                        transaksi.setId_produk(data.getIdProduk());
                        transaksi.setFile(data.getFile());
                        transaksi.setStatus(data.getStatus());
                        transaksi.setTgl_pesan(data.getTglPesan());
                        transaksi.setTgl_selesai(data.getTglSelesai());
                        transaksi.setHarga_total(data.getHargaTotal());
                        transaksi.setJumlah(data.getJumlah());
                        transaksi.setKeterangan(data.getKeterangan());
                        try {
                            if (isFirstRun) {
                                TransaksiDao.getTransaksiDao().add(transaksi);
                            }else {
                                TransaksiDao.getTransaksiDao().save(transaksi);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TransaksiGet> call, Throwable t) {
                Log.i("TRANSAKSI_GET", t.getMessage());
            }
        });
    }

    public void syncKategori(final Context ctx, Call<KategoriGet> kategoriGetCall, final boolean isFirstRun){
        kategoriGetCall.enqueue(new Callback<KategoriGet>() {
            @Override
            public void onResponse(Call<KategoriGet> call, Response<KategoriGet> response) {
                KategoriGet kategoriGet = response.body();
                Log.i("KATEGORI_GET", response.message());
                for (KategoriGet.KategoriData list : kategoriGet.getKategori()){
                    Kategori kategori = new Kategori();
                    kategori.setId_kategori(list.getIdKategori());
                    kategori.setKategori(list.getNama());
                    kategori.setIcon(list.getIcon());

                    try {
                        if (isFirstRun) {
                            KategoriDao.getKategoriDao().add(kategori);
                        } else {
                            KategoriDao.getKategoriDao().save(kategori);
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<KategoriGet> call, Throwable t) {
                DialogBuilder.showErrorDialog(ctx, t.getMessage());
                Log.i("KATEGORI_GET", t.getMessage());
            }
        });
    }
}
