package com.rplsukses.ezprint.bl.network.interfaces;

import com.rplsukses.ezprint.bl.db.model.User;
import com.rplsukses.ezprint.bl.network.config.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterfaces {
    @FormUrlEncoded
    @POST(Config.API_LOGIN)
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
