package com.example.thaianhit.citytravel;
import com.example.thaianhit.citytravel._class.Place;
import com.example.thaianhit.citytravel._class.PlaceDetail;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIInterface
{
    @POST("TaiKhoan")
    Call<Boolean> postAccount(@Body Account account);
    @GET("DichVu")
    Call<List<Category>> getCategory();
    @GET("TaiKhoan?")
    Call<Account> checkLogin(@Query("email") String email,@Query("password") String password, @Query("provider") String provider);
    @PUT("TaiKhoan/Forget?")
    Call<Boolean> Forgetpassword(@Query("email") String email);
    @PUT("TaiKhoan/ChangPassword?")
    Call<Boolean> ChangePassword(@Query("idUser") int id,@Query("passwordold") String password,@Query("passwordnew")String passwordnew);
    @PUT("TaiKhoan")
    Call<Boolean> editProfile(@Body Account account);
    @GET("TaiKhoan/Logout")
    Call<Boolean> Logout();
    @GET("DiaDiem?")
    Call<PlaceDetail> getPlace(@Query("ma_dulieu") int id);
    @GET("DiaDiem?")
    Call<List<PlaceDetail>> searchPlace(@Query("str") String str);

}

