package com.rplsukses.ezprint.ui.presenter;

import com.rplsukses.ezprint.bl.db.dao.ProdukDao;
import com.rplsukses.ezprint.bl.db.model.Produk;
import com.rplsukses.ezprint.ui.view.ProdukView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdukPresenter {
    private ProdukView produkView;

    public ProdukPresenter(ProdukView produkView) {
        this.produkView = produkView;
    }

    public void loadProdukByMitra(Integer id_mitra){
        produkView.showLoading();
        List<Produk> produkList = new ArrayList<>();
        try {
            produkList = ProdukDao.getProdukDao().readByMitra(id_mitra);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        produkView.loadProduk(produkList);
        produkView.hideLoading();
    }
}
