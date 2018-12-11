package com.rplsukses.ezprint.bl.db.dao;

import com.j256.ormlite.stmt.QueryBuilder;
import com.rplsukses.ezprint.bl.db.model.Produk;

import java.sql.SQLException;
import java.util.List;

public class ProdukDao extends BaseDaoCrud<Produk, Integer> {
    private static ProdukDao produkDao;

    public static ProdukDao getProdukDao() {
        if (produkDao == null){
            produkDao = new ProdukDao();
        }
        return produkDao;
    }

    public List<Produk> readByMitra(Integer id_mitra) throws SQLException {
        QueryBuilder<Produk, Integer> qb = getDao().queryBuilder();
        qb.where().eq(Produk.ID_MITRA, id_mitra);
        return getDao().query(qb.prepare());
    }
}
