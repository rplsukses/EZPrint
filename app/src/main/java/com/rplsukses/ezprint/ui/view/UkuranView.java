package com.rplsukses.ezprint.ui.view;

import com.rplsukses.ezprint.bl.network.model.Ukuran;

import java.util.List;

public interface UkuranView {
    void showLoading();
    void hideLoading();
    void loadItem(List<Ukuran> ukuranList);
}
