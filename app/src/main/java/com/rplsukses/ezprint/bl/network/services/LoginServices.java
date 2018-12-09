package com.rplsukses.ezprint.bl.network.services;

import android.content.Context;

import com.rplsukses.ezprint.bl.network.config.RetrofitBuilder;
import com.rplsukses.ezprint.bl.network.interfaces.LoginInterfaces;

import retrofit2.Callback;

public class LoginServices {
    private LoginInterfaces loginInterfaces;

    public LoginServices(Context ctx) {
        this.loginInterfaces = RetrofitBuilder.builder(ctx).create(LoginInterfaces.class);
    }

    public void doLogin(String email, String password, Callback callback){
        loginInterfaces.login(email, password).enqueue(callback);
    }
}
