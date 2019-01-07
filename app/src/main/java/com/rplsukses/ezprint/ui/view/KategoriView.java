package com.rplsukses.ezprint.ui.view;

import com.rplsukses.ezprint.bl.db.model.Kategori;
import com.rplsukses.ezprint.bl.network.model.KategoriGet;

import java.util.List;

public interface KategoriView {
    void showLoading();
    void hideLoading();
    void loadItem(List<Kategori> kategoriList);
}
