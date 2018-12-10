package com.rplsukses.ezprint.bl.db.dao;

import com.j256.ormlite.dao.Dao;
import com.rplsukses.ezprint.bl.db.helper.Db;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoCrud<T, ID> {
    protected Class domain;

    public BaseDaoCrud() {
        this.domain = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Dao<T, ID> getDao() throws SQLException {
        return Db.getInstance().getDbHelper().getDao(domain);
    }

    public void save(T model) throws SQLException {
        getDao().createOrUpdate(model);
    }

    public void add(T model) throws SQLException {
        getDao().create(model);
    }

    public void delete(T model) throws SQLException {
        getDao().delete(model);
    }

    public List<T> read() throws SQLException {
        return getDao().queryForAll();
    }
}
