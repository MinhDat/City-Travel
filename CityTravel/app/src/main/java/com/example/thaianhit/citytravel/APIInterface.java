package com.example.thaianhit.citytravel;
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
    Call<Account> checkLogin(@Query("email") String email,@Query("password") String password);
    @PUT("TaiKhoan/Forget?")
    Call<Boolean> Forgetpassword(@Query("email") String email);
    @PUT("TaiKhoan/ChangPassword?")
    Call<Boolean> ChangePassword(@Query("email") String email,@Query("passwordold") String password,@Query("passwordnew")String passwordnew);
    @PUT("TaiKhoan")
    Call<Boolean> editProfile(@Body Account account);
    @GET("TaiKhoan/Logout")
    Call<Boolean> Logout();
}

