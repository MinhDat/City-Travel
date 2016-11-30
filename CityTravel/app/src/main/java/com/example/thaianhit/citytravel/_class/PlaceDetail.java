package com.example.thaianhit.citytravel._class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by T.N on 11/29/2016.
 */

public class PlaceDetail
{   @SerializedName("ten")
    private Place place;
    @SerializedName("dichvu")
    private Category category;
    @SerializedName("phuong")
    private Phuong phuong;
    @SerializedName("quanhuyen")
    private QuanHuyen quanhuyen;
    @SerializedName("tinhthanh")
    private TinhThanh tinhthanh;
    @SerializedName("MaDuLieu")
    private int id_placedetail;
    @SerializedName("SoNha")
    private String sonha;
    @SerializedName("KinhDo")
    private double kinhdo;
    @SerializedName("ViDo")
    private double vido;
    @SerializedName("ChuThich")
    private String chuthich;
    @SerializedName("DanhGia")
    private float danhgia;
    @SerializedName("duong")
    private Duong duong;
    public PlaceDetail(String duong,float danhgia, String chuthich, double vido, double kinhdo, String sonha, int id_placedetail, String tinhthanh, String quanhuyen, String phuong, String category,String photo_category, String place) {
        this.duong.setName_duong(duong);
        this.danhgia = danhgia;

        this.chuthich = chuthich;
        this.vido = vido;
        this.kinhdo = kinhdo;
        this.sonha = sonha;
        this.id_placedetail = id_placedetail;
        this.tinhthanh.setName_tinhthanh(tinhthanh);
        this.quanhuyen.setName_quanhuyen(quanhuyen);
        this.phuong.setName_phuong(phuong);
        this.category.setName_category(category);
        this.category.setPhoto_category(photo_category);
        this.place.setName_place(place);
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Phuong getPhuong() {
        return phuong;
    }

    public void setPhuong(Phuong phuong) {
        this.phuong = phuong;
    }

    public QuanHuyen getQuanhuyen() {
        return quanhuyen;
    }

    public void setQuanhuyen(QuanHuyen quanhuyen) {
        this.quanhuyen = quanhuyen;
    }

    public TinhThanh getTinhthanh() {
        return tinhthanh;
    }

    public void setTinhthanh(TinhThanh tinhthanh) {
        this.tinhthanh = tinhthanh;
    }

    public int getId_placedetail() {
        return id_placedetail;
    }

    public void setId_placedetail(int id_placedetail) {
        this.id_placedetail = id_placedetail;
    }

    public String getSonha() {
        return sonha;
    }

    public void setSonha(String sonha) {
        this.sonha = sonha;
    }

    public double getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(double kinhdo) {
        this.kinhdo = kinhdo;
    }

    public double getVido() {
        return vido;
    }

    public void setVido(double vido) {
        this.vido = vido;
    }

    public String getChuthich() {
        return chuthich;
    }

    public void setChuthich(String chuthich) {
        this.chuthich = chuthich;
    }

    public float getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(float danhgia) {
        this.danhgia = danhgia;
    }

    public Duong getDuong() {
        return duong;
    }

    public void setDuong(Duong duong) {
        this.duong = duong;
    }
}
