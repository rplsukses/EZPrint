package com.rplsukses.ezprint.bl.network.config;

public class Config {
    public static final String BASE_URL = "http://ezprint.tech"; // Your Local IP Address or Localhost (http://10.0.2.2/)

    public static final String API_URL = BASE_URL + "/admin/api";

    public static final String API_LOGIN = API_URL + "/user/login.php";
    public static final String API_REGISTER = API_URL + "/user/register.php";
    public static final String API_UKURAN = API_URL + "/produk/readUkuran.php";
    public static final String API_MITRA = API_URL + "/mitra/read.php";
    public static final String API_KATEGORI = API_URL + "/kategori/read.php";
    public static final String API_PRODUK = API_URL + "/produk/read.php";
    public static final String API_UPLOAD_TRANS = API_URL + "/transaksi/upload.php?apicall=upload";
}
