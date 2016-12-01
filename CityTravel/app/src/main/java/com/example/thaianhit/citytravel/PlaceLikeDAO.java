package com.example.thaianhit.citytravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamin on 01-Dec-16.
 */

public class PlaceLikeDAO {
    SQLiteDatabase database;
    DatabasePlaceHelper databasePlaceHelper;
//    String[] columPlace = {DatabasePlaceHelper.HISTORY_ID, DatabasePlaceHelper.HISTORY_TENDIADIEM
//            , DatabasePlaceHelper.HISTORY_TENDICHVU, DatabasePlaceHelper.HISTORY_THOIGIANLIKE
//            , DatabasePlaceHelper.HISTORY_DIACHI, DatabasePlaceHelper.HISTORY_HINHANHDICHVU
//            , DatabasePlaceHelper.HISTORY_DIEMDANHGIA};

    public PlaceLikeDAO(Context context) {
        databasePlaceHelper = new DatabasePlaceHelper(context);
    }

    public void open() {
        database = databasePlaceHelper.getWritableDatabase();
    }

    public void close() {
        databasePlaceHelper.close();
    }

    public boolean AddPlaceLike(PlaceLikeDTO place) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabasePlaceHelper.TABLE_PLACE);//id nhân viên tự tăng
        contentValues.put(DatabasePlaceHelper.PLACE_ID, place.get_id());
        contentValues.put(DatabasePlaceHelper.PLACE_TENDIADIEM, place.getTenDiaDiem().toString());
        contentValues.put(DatabasePlaceHelper.PLACE_TENDICHVU, place.getTenDichVu().toString());
        contentValues.put(DatabasePlaceHelper.PLACE_THOIGIANLIKE, place.getThoiGianLike().toString());
        contentValues.put(DatabasePlaceHelper.PLACE_DIACHI, place.getTenDiaChi().toString());
        contentValues.put(DatabasePlaceHelper.PLACE_HINHANHDICHVU, place.getHinhAnhDichVu().toString());
        contentValues.put(DatabasePlaceHelper.PLACE_DIEMDANHGIA, place.getDiemDanhGia().toString());

        long id_diadiem = database.insert(DatabasePlaceHelper.TABLE_PLACE, null, contentValues);

        if (id_diadiem != 0) {
            return true;
        }else {
            return false;
        }
    }

    public List<PlaceLikeDTO> GetListPlaceLike(){
        List<PlaceLikeDTO> dsPlaceLike = new ArrayList<PlaceLikeDTO>();
//        Cursor cursor = database.query(DatabasePlaceHelper.TABLE_HISTORY,columPlace
//                ,null,null,null,null,null);

        String query = "SELECT * FROM " + DatabasePlaceHelper.TABLE_PLACE;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            //Lấy giá trị dữ liệu trong bảng CSDL
            int id_place = cursor.getInt(cursor.getColumnIndex(DatabasePlaceHelper.PLACE_ID));
            String tendiadiem_place = cursor.getString(cursor.getColumnIndex(DatabasePlaceHelper.PLACE_TENDIADIEM));
            String tendichvu_place = cursor.getString(cursor.getColumnIndex(DatabasePlaceHelper.PLACE_TENDICHVU));
            String thoigianlike_place = cursor.getString(cursor.getColumnIndex(DatabasePlaceHelper.PLACE_THOIGIANLIKE));
            String tendiachi_place = cursor.getString(cursor.getColumnIndex(DatabasePlaceHelper.PLACE_DIACHI));
            String hinhanhdichvu_place = cursor.getString(cursor.getColumnIndex(DatabasePlaceHelper.PLACE_HINHANHDICHVU));
            String diemdanhgia_place = cursor.getString(cursor.getColumnIndex(DatabasePlaceHelper.PLACE_DIEMDANHGIA));

            //Tạo đối tượng
            PlaceLikeDTO placeLikeDTO = new PlaceLikeDTO();

            //Gán thuộc tính cho các đối tượng
            placeLikeDTO.set_id(id_place);
            placeLikeDTO.setTenDiaDiem(tendiadiem_place);
            placeLikeDTO.setTenDichVu(tendichvu_place);
            placeLikeDTO.setThoiGianLike(thoigianlike_place);
            placeLikeDTO.setTenDiaChi(tendiachi_place);
            placeLikeDTO.setHinhAnhDichVu(hinhanhdichvu_place);
            placeLikeDTO.setDiemDanhGia(diemdanhgia_place);

            //Đổ dữ liệu vào DS lịch sử
            dsPlaceLike.add(placeLikeDTO);

            //di chuyển con trỏ đến vị trí tiếp theo để đọc dữ liệu
            cursor.moveToNext();
        }

        return dsPlaceLike;
    }

    public boolean DeleteItemPlaceLike(PlaceLikeDTO place){
        int kt = database.delete(DatabasePlaceHelper.TABLE_PLACE, DatabasePlaceHelper.PLACE_ID + " = " + place.get_id(),null);
        if(kt != 0){
            return true;
        }else{
            return false;
        }
    }
}
