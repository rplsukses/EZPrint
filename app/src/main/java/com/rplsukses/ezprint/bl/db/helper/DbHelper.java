package com.rplsukses.ezprint.bl.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rplsukses.ezprint.bl.db.model.Mitra;

import java.sql.SQLException;

/**
 * Created by Nurul Chusna on 25/08/2016.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    private static final int DBVER = 1;
    public static final String DBNAME = "ezprint.db";

    public DbHelper(Context ctx){
        super(ctx, DBNAME, null, DBVER);
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Mitra.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {}

    @Override
    public ConnectionSource getConnectionSource() {
        return super.getConnectionSource();
    }

}
