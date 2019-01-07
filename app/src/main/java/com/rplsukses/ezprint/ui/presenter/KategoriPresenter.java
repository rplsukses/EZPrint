package com.rplsukses.ezprint.ui.presenter;

import com.rplsukses.ezprint.bl.db.dao.KategoriDao;
import com.rplsukses.ezprint.bl.db.model.Kategori;
import com.rplsukses.ezprint.ui.view.KategoriView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KategoriPresenter {
    KategoriView kategoriView;

    public KategoriPresenter(KategoriView kategoriView) {
        this.kategoriView = kategoriView;
    }

    public void loadData(){
        kategoriView.showLoading();
        List<Kategori> list = new ArrayList<>();
        try {
            list = KategoriDao.getKategoriDao().read();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        kategoriView.loadItem(list);
        kategoriView.hideLoading();
    }
}
