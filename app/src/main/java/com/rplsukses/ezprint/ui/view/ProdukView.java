package com.rplsukses.ezprint.ui.view;

import com.rplsukses.ezprint.bl.network.model.Produk;

import java.util.List;

public interface ProdukView {
    void showLoading();
    void hideLoading();
    void loadItem(List<Produk> produkList);
}
