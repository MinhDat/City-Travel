package com.example.thaianhit.citytravel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by T.N on 11/20/2016.
 */

public interface APIInterface
{
    @POST("TaiKhoan")
    Call<Boolean> postAccount(@Body Account account);
    @GET("DichVu")
    Call<List<Category>> getCategory();
}

