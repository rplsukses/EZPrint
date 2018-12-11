package com.rplsukses.ezprint.ui.presenter;

import com.rplsukses.ezprint.bl.db.dao.MitraDao;
import com.rplsukses.ezprint.bl.db.model.Mitra;
import com.rplsukses.ezprint.ui.view.MitraView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MitraPresenter {
    private MitraView mitraView;

    public MitraPresenter(MitraView mitraView) {
        this.mitraView = mitraView;
    }

    public void loadData(){
        mitraView.showLoading();

        List<Mitra> mitraList = new ArrayList<>();
        try {
            mitraList = MitraDao.getMitraDao().read();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mitraView.loadMitra(mitraList);
        mitraView.hideLoading();
    }

    public Mitra getByID(Integer id){
        mitraView.showLoading();
        Mitra mitra = null;
        try {
            mitra = MitraDao.getMitraDao().getMitraByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mitra;
    }
}
