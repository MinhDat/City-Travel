package com.example.thaianhit.citytravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuuBao on 27-Nov-16.
 */

public class HistoryLikeDAO {

    SQLiteDatabase database;
    DatabaseHistoryHelper databaseHistoryHelper;
    String[] columHistory = {DatabaseHistoryHelper.HISTORY_ID, DatabaseHistoryHelper.HISTORY_TENDIADIEM,
    DatabaseHistoryHelper.HISTORY_TENDICHVU,DatabaseHistoryHelper.HISTORY_DIACHI};

    public HistoryLikeDAO(Context context) {
        databaseHistoryHelper = new DatabaseHistoryHelper(context);
    }

    public void open() {
        database = databaseHistoryHelper.getWritableDatabase();
    }

    public void close() {
        databaseHistoryHelper.close();
    }

    public boolean AddHistoryLike(HistoryLikeDTO history) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHistoryHelper.TABLE_HISTORY);//id nhân viên tự tăng
        contentValues.put(DatabaseHistoryHelper.HISTORY_TENDIADIEM, history.getTenDiaDiem().toString());
        contentValues.put(DatabaseHistoryHelper.HISTORY_TENDICHVU, history.getTenDichVu().toString());
        contentValues.put(DatabaseHistoryHelper.HISTORY_DIACHI, history.getTenDiaChi().toString());

        long id_diadiem = database.insert(DatabaseHistoryHelper.TABLE_HISTORY, null, contentValues);

        if (id_diadiem != 0) {
            return true;
        }else {
            return false;
        }
    }

    public List<HistoryLikeDTO> GetListHistoryLike(){
        List<HistoryLikeDTO> dsHistoryLike = new ArrayList<HistoryLikeDTO>();
//        Cursor cursor = database.query(DatabaseHistoryHelper.TABLE_HISTORY,columHistory
//                ,null,null,null,null,null);

        String query = "SELECT * FROM " + DatabaseHistoryHelper.TABLE_HISTORY;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            //Lấy giá trị dữ liệu trong bảng CSDL
            int id_history = cursor.getInt(cursor.getColumnIndex(DatabaseHistoryHelper.HISTORY_ID));
            String tendiadiem_history = cursor.getString(cursor.getColumnIndex(DatabaseHistoryHelper.HISTORY_TENDIADIEM));
            String tendichvu_history = cursor.getString(cursor.getColumnIndex(DatabaseHistoryHelper.HISTORY_TENDICHVU));
            String tendiachi_history = cursor.getString(cursor.getColumnIndex(DatabaseHistoryHelper.HISTORY_DIACHI));

            //Tạo đối tượng
            HistoryLikeDTO historyLikeDTO = new HistoryLikeDTO();

            //Gán thuộc tính cho các đối tượng
            historyLikeDTO.set_id(id_history);
            historyLikeDTO.setTenDiaDiem(tendiadiem_history);
            historyLikeDTO.setTenDiaDiem(tendichvu_history);
            historyLikeDTO.setTenDiaDiem(tendiachi_history);

            //Đổ dữ liệu vào DS lịch sử
            dsHistoryLike.add(historyLikeDTO);

            //di chuyển con trỏ đến vị trí tiếp theo để đọc dữ liệu
            cursor.moveToNext();
        }

        return dsHistoryLike;
    }

    public boolean DeleteItemHistoryLike(HistoryLikeDTO history){
        int kt = database.delete(DatabaseHistoryHelper.TABLE_HISTORY, DatabaseHistoryHelper.HISTORY_ID + " = " + history.get_id(),null);
        if(kt != 0){
            return true;
        }else{
            return false;
        }
    }

}
