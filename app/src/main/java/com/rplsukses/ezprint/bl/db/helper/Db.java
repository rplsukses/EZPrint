package com.rplsukses.ezprint.bl.db.helper;

import android.content.Context;

/**
 * Created by Nurul Chusna on 25/08/2016.
 */
public class Db {
    private static Db singleton;
    private DbHelper mDbHelper;

    public static Db getInstance() {
        if(singleton == null) {
            singleton = new Db();
        }
        return singleton;
    }

    public void init(Context ctx){
        mDbHelper = new DbHelper(ctx);
        mDbHelper.getWritableDatabase();
    }

    public DbHelper getDbHelper() {
        return mDbHelper;
    }
}
