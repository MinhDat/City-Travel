package com.example.thaianhit.citytravel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HuuBao on 27-Nov-16.
 */

public class DatabaseHistoryHelper extends SQLiteOpenHelper {

    public static final String DBHistoryLike = "HistoryLike";
    public static final int DBVersion = 1;
    public static final String TABLE_HISTORY = "HISTORYLIKE";
    public static final String HISTORY_ID = "_id";
    public static final String HISTORY_TENDIADIEM = "TenDiaDiem";
    public static final String HISTORY_TENDICHVU = "TenDichVu";
    public static final String HISTORY_THOIGIANLIKE = "thoiGianLike";
    public static final String HISTORY_DIACHI = "TenDiaChi";
    public static final String HISTORY_HINHANHDICHVU = "HinhAnhDichVu";
    public static final String HISTORY_DIEMDANHGIA = "DiemDanhGia";

    public DatabaseHistoryHelper(Context context) {
        super(context, DBHistoryLike, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ TABLE_HISTORY + " (" + HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HISTORY_TENDIADIEM + " TEXT, " + HISTORY_TENDICHVU + " TEXT, " + HISTORY_THOIGIANLIKE + " TEXT, " + HISTORY_DIACHI + " TEXT, " + HISTORY_HINHANHDICHVU + " TEXT, " + HISTORY_DIEMDANHGIA +" TEXT);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String querySQL = "DROP TABLE IF EXISTS " + TABLE_HISTORY;
        sqLiteDatabase.execSQL(querySQL);
        onCreate(sqLiteDatabase);
    }
}
