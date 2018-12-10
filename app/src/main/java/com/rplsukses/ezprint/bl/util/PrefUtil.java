package com.rplsukses.ezprint.bl.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.rplsukses.ezprint.bl.network.model.User;

public class PrefUtil {
    public static final String USER_SESSION = "user_session";

    public static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void putUser(Context ctx, String key, User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        putString(ctx, key, json);
    }

    public static User getUser(Context ctx, String key){
        Gson gson = new Gson();
        String json = getString(ctx, key);
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public static void putString(Context ctx, String key, String value){
        getSharedPreferences(ctx).edit().putString(key, value).apply();
    }

    public static String getString(Context ctx, String key){
        return getSharedPreferences(ctx).getString(key, null);
    }

    public static void clear(Context ctx) {
        getSharedPreferences(ctx).edit().clear().apply();
    }
}
