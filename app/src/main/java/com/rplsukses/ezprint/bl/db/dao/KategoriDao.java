package com.rplsukses.ezprint.bl.db.dao;

import com.rplsukses.ezprint.bl.db.model.Kategori;

import java.sql.SQLException;

public class KategoriDao extends BaseDaoCrud<Kategori, Integer> {
    private static KategoriDao kategoriDao;

    public static KategoriDao getKategoriDao(){
        if (kategoriDao == null){
            kategoriDao = new KategoriDao();
        }
        return kategoriDao;
    }

    public Kategori getByID(Integer id)throws SQLException{
        return getDao().queryForId(id);
    }
}
