package com.rplsukses.ezprint.ui.presenter;

import com.rplsukses.ezprint.bl.db.dao.TransaksiDao;
import com.rplsukses.ezprint.bl.db.model.Transaksi;
import com.rplsukses.ezprint.ui.view.TransaksiView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaksiPresenter {
    private TransaksiView transaksiView;

    public TransaksiPresenter(TransaksiView transaksiView) {
        this.transaksiView = transaksiView;
    }

    public void getOrder(){
        transaksiView.showLoading();
        List<Transaksi> list = new ArrayList<>();
        try {
            list = TransaksiDao.getTransaksiDao().getOrder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        transaksiView.loadProduk(list);
        transaksiView.hideLoading();
    }

    public void getHistory(){
        transaksiView.showLoading();
        List<Transaksi> list = new ArrayList<>();
        try {
            list = TransaksiDao.getTransaksiDao().getHistory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        transaksiView.loadProduk(list);
        transaksiView.hideLoading();
    }
}
