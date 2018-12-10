package com.rplsukses.ezprint.bl.network.api;

import android.content.Context;
import android.util.Log;

import com.rplsukses.ezprint.bl.db.dao.MitraDao;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.bl.network.model.MitraGet;
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
}
