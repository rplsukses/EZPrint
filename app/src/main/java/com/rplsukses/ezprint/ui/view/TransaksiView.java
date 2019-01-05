package com.rplsukses.ezprint.ui.view;

import com.rplsukses.ezprint.bl.db.model.Transaksi;

import java.util.List;

public interface TransaksiView {
    void showLoading();
    void hideLoading();
    void loadProduk(List<Transaksi> transaksiList);
}
