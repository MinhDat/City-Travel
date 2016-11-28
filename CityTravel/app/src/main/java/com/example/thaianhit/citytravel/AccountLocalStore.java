package com.example.thaianhit.citytravel;

import android.content.Context;
import android.content.SharedPreferences;

public class AccountLocalStore {
    public static final String SP_NAME = "userDetail";
    SharedPreferences userLocalDatabase;
    public AccountLocalStore(Context context)
    {
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }
   public void StoreUserData(Account account)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("email",account.getEmail());
        spEditor.putString("picture",account.getPicture());
        spEditor.putString("address",account.getAddress());
        spEditor.putString("birth",account.getBirth());
        spEditor.putInt("gender",account.getGender());
        spEditor.putString("phone",account.getPhone());
        spEditor.putString("firstname",account.getFirstName());
        spEditor.putString("lastname",account.getLastName());
        spEditor.putString("password",account.getPassword());
        spEditor.putString("role",account.getRole());
        spEditor.commit();
    }
    public  Account GetLoggedInUser()
    {
        String email = userLocalDatabase.getString("email","");
        String picture = userLocalDatabase.getString("picture","");
        String address = userLocalDatabase.getString("address","");
        String birth = userLocalDatabase.getString("birth","");
        int gender = userLocalDatabase.getInt("gender",-1);
        String phone = userLocalDatabase.getString("phone","");
        String firstname = userLocalDatabase.getString("firstname","");
        String lastname = userLocalDatabase.getString("lastname","");
        String password = userLocalDatabase.getString("password","");
        String role = userLocalDatabase.getString("role","");
        Account storeAccount = new Account(email,picture,address,birth,gender,phone,firstname,lastname,password,role);
        return storeAccount;
    }
    public void SetUserLoggedIn(boolean loggedIn)
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }
    public boolean GetUserLoogedIn()
    {
        if(userLocalDatabase.getBoolean("loggedIn",false) == true)
        {
            return true;
        }
        return false;
    }
    public void ClearUser()
    {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
