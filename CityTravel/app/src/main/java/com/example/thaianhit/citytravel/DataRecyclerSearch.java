package com.example.thaianhit.citytravel;

/**
 * Created by NGUYEN TUAN ANH on 11/9/2016.
 */

public class DataRecyclerSearch {
        public String name;
        // Phần mô tả cho danh mục đó
        public String Description;
        public String rating;
        // Khởi tạo dữ liệu cho danh mục
        public DataRecyclerSearch(String name, String Description,String rating) {
            this.name = name;
            this.Description=Description;
            this.rating=rating;
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