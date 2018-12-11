package com.rplsukses.ezprint.ui.view;

import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.bl.network.model.ProdukGet;

import java.util.List;

public interface ProdukView {
    void showLoading();
    void hideLoading();
    void loadProduk(List<Produk> produkList);
}
