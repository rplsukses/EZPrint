package com.rplsukses.ezprint.bl.db.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.rplsukses.ezprint.bl.db.model.Transaksi;

import java.sql.SQLException;
import java.util.List;

public class TransaksiDao extends BaseDaoCrud<Transaksi, Integer>{
    private static TransaksiDao transaksiDao;

    public static TransaksiDao getTransaksiDao(){
        if (transaksiDao == null){
            transaksiDao = new TransaksiDao();
        }
        return transaksiDao;
    }

    public Transaksi getTransaksiByID(Integer id) throws SQLException {
        return getDao().queryForId(id);
    }

    public List<Transaksi> getOrder() throws SQLException{
        QueryBuilder<Transaksi, Integer> qb = getDao().queryBuilder();
        qb.where().eq(Transaksi.STATUS, "0").or().eq(Transaksi.STATUS, "1");
        return getDao().query(qb.prepare());
    }

    public List<Transaksi> getHistory() throws SQLException{
        QueryBuilder<Transaksi, Integer> qb = getDao().queryBuilder();
        qb.where().eq(Transaksi.STATUS, "3").or().eq(Transaksi.STATUS, "2");
        return getDao().query(qb.prepare());
    }
}
