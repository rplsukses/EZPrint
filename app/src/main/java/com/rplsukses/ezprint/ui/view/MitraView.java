package com.rplsukses.ezprint.ui.view;

import com.rplsukses.ezprint.bl.db.model.Mitra;

import java.util.List;

public interface MitraView {
    void showLoading();
    void hideLoading();
    void loadItem(List<Mitra> mitraList);
}
