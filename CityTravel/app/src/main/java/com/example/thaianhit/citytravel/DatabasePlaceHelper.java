package com.example.thaianhit.citytravel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HuuBao on 27-Nov-16.
 */

public class DatabasePlaceHelper extends SQLiteOpenHelper {

    public static final String DBPlaceLike = "PlaceLike";
    public static final int DBVersion = 1;
    public static final String TABLE_PLACE = "PLACELIKE";
    public static final String PLACE_ID = "_id";
    public static final String PLACE_TENDIADIEM = "TenDiaDiem";
    public static final String PLACE_TENDICHVU = "TenDichVu";
    public static final String PLACE_THOIGIANLIKE = "thoiGianLike";
    public static final String PLACE_DIACHI = "TenDiaChi";
    public static final String PLACE_HINHANHDICHVU = "HinhAnhDichVu";
    public static final String PLACE_DIEMDANHGIA = "DiemDanhGia";

    public DatabasePlaceHelper(Context context) {
        super(context, DBPlaceLike, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ TABLE_PLACE + " (" + PLACE_ID + " INTEGER PRIMARY KEY, " + PLACE_TENDIADIEM + " TEXT, " + PLACE_TENDICHVU + " TEXT, " + PLACE_THOIGIANLIKE + " TEXT, " + PLACE_DIACHI + " TEXT, " + PLACE_HINHANHDICHVU + " TEXT, " + PLACE_DIEMDANHGIA +" TEXT);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String querySQL = "DROP TABLE IF EXISTS " + TABLE_PLACE;
        sqLiteDatabase.execSQL(querySQL);
        onCreate(sqLiteDatabase);
    }
}
