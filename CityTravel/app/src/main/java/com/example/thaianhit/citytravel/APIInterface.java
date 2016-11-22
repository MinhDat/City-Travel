package com.example.thaianhit.citytravel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by T.N on 11/20/2016.
 */

public interface APIInterface
{
    @POST("TaiKhoan")
    Call<Boolean> postAccount(@Body Account account);
    @PUT("TaiKhoan/Forget?")
    Call<Boolean> Forgetpassword(@Query("email") String email);
    @GET("DichVu")
    Call<List<Category>> getCategory();
}

