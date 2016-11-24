package com.example.thaianhit.citytravel;

/**
 * Created by NGUYEN TUAN ANH on 11/8/2016.
 */

public class DataRecyclerHome {
    // Tên của danh mục tìm kiếm: Bệnh viện, Cửa hàng,...

    public String name;
    // Phần mô tả cho danh mục đó
    public String Description;
    // Khởi tạo dữ liệu cho danh mục
    public DataRecyclerHome(String name, String Description) {
        this.name = name;
        this.Description=Description;
    }
    // Lấy tên danh mục
    public String getName() {
        return name;
    }
    // Gán tên danh mục
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        this.Description= description;
    }
}
