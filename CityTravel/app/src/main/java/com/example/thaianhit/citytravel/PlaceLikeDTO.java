package com.example.thaianhit.citytravel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HuuBao on 27-Nov-16.
 */

public class PlaceLikeDTO implements Parcelable {

    int _id;
    String tenDiaDiem;
    String tenDichVu;
    String thoiGianLike;
    String tenDiaChi;
    String hinhAnhDichVu;
    String diemDanhGia;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getThoiGianLike() {
        return thoiGianLike;
    }

    public void setThoiGianLike(String thoiGianLike) {
        this.thoiGianLike = thoiGianLike;
    }

    public String getTenDiaChi() {
        return tenDiaChi;
    }

    public void setTenDiaChi(String tenDiaChi) {
        this.tenDiaChi = tenDiaChi;
    }

    public String getHinhAnhDichVu() {
        return hinhAnhDichVu;
    }

    public void setHinhAnhDichVu(String hinhAnhDichVu) {
        this.hinhAnhDichVu = hinhAnhDichVu;
    }

    public String getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(String diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this.tenDiaDiem);
        dest.writeString(this.tenDichVu);
        dest.writeString(this.thoiGianLike);
        dest.writeString(this.tenDiaChi);
        dest.writeString(this.hinhAnhDichVu);
        dest.writeString(this.diemDanhGia);
    }

    public PlaceLikeDTO() {
    }

    protected PlaceLikeDTO(Parcel in) {
        this._id = in.readInt();
        this.tenDiaDiem = in.readString();
        this.tenDichVu = in.readString();
        this.thoiGianLike = in.readString();
        this.tenDiaChi = in.readString();
        this.hinhAnhDichVu = in.readString();
        this.diemDanhGia = in.readString();
    }

    public static final Parcelable.Creator<PlaceLikeDTO> CREATOR = new Parcelable.Creator<PlaceLikeDTO>() {
        @Override
        public PlaceLikeDTO createFromParcel(Parcel source) {
            return new PlaceLikeDTO(source);
        }

        @Override
        public PlaceLikeDTO[] newArray(int size) {
            return new PlaceLikeDTO[size];
        }
    };

}
