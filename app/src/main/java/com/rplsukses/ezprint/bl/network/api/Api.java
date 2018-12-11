package com.rplsukses.ezprint.bl.network.api;

import com.rplsukses.ezprint.bl.network.model.MitraGet;
import com.rplsukses.ezprint.bl.network.model.ProdukGet;
import com.rplsukses.ezprint.bl.network.model.User;
import com.rplsukses.ezprint.bl.network.config.Config;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST(Config.API_LOGIN)
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST(Config.API_REGISTER)
    Call<User> register(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET(Config.API_MITRA)
    Call<MitraGet> getMitra();

    @GET(Config.API_PRODUK)
    Call<ProdukGet> getProduk();

}
