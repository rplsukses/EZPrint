package com.rplsukses.ezprint.bl.db.dao;

import com.rplsukses.ezprint.bl.db.model.Mitra;

import java.sql.SQLException;
import java.util.List;

public class MitraDao extends BaseDaoCrud<Mitra, Integer> {
    private static MitraDao mitraDao;

    public static MitraDao getMitraDao(){
        if (mitraDao == null){
            mitraDao = new MitraDao();
        }
        return mitraDao;
    }

    public Mitra getMitraByID(Integer id) throws SQLException{
        return getDao().queryForId(id);
    }
}
