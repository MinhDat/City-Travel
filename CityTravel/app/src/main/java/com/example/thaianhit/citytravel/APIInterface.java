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
    @GET("Taikhoan?")
    Call<Boolean> checkLogin(@Query("email") String email,@Query("password") String password);
    @GET("Taikhoan?")
    Call<Account> getAccount(@Query("email") String emal);
    @PUT("TaiKhoan/Forget?")
    Call<Boolean> Forgetpassword(@Query("email") String email);
    @PUT("TaiKhoan/ChangPassword?")
    Call<Boolean> ChangePassword(@Query("email") String email,@Query("passwordold") String password,@Query("passwordnew")String passwordnew);
    @PUT("Taikhoan")
    Call<Boolean> editProfile(@Body Account account);
}

