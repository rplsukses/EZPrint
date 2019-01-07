package com.rplsukses.ezprint.bl.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Kategori.TBL_NAME)
public class Kategori {
    public static final String TBL_NAME = "kategori";
    public static final String ID_KATEGORI = "id_kategori";
    public static final String KATEGORI = "kategori";
    public static final String ICON = "icon";

    @DatabaseField(columnName =  ID_KATEGORI, id = true) private Integer id_kategori;
    @DatabaseField(columnName = KATEGORI) private String kategori;
    @DatabaseField(columnName = ICON) private String icon;

    public Kategori() {
    }

    public Integer getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(Integer id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
