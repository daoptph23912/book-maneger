//package com.daoptph23912.duanmau_daoptph23912_thi.model;
//
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//@Entity
//public class Sach {
//    @PrimaryKey(autoGenerate = true)
//    private int maSach;
//
//    private int maLoai, giaThue;
//    private String tenSach;
//    private int khuyenMai;
//
//
//    public Sach(int maLoai, int giaThue, int khuyenMai, String tenSach) {
//        this.maLoai = maLoai;
//        this.giaThue = giaThue;
//        this.tenSach = tenSach;
//        this.khuyenMai = khuyenMai;
//    }
//
//    public Sach() {
//    }
//
//    public int getKhuyenMai() {
//        return khuyenMai;
//    }
//
//    public void setKhuyenMai(int khuyenMai) {
//        this.khuyenMai = khuyenMai;
//    }
//
//    public int getMaSach() {
//        return maSach;
//    }
//
//    public void setMaSach(int maSach) {
//        this.maSach = maSach;
//    }
//
//    public int getMaLoai() {
//        return maLoai;
//    }
//
//    public void setMaLoai(int maLoai) {
//        this.maLoai = maLoai;
//    }
//
//    public int getGiaThue() {
//        return giaThue;
//    }
//
//    public void setGiaThue(int giaThue) {
//        this.giaThue = giaThue;
//    }
//
//    public String getTenSach() {
//        return tenSach;
//    }
//
//    public void setTenSach(String tenSach) {
//        this.tenSach = tenSach;
//    }
//}

package com.daoptph23912.duanmau_daoptph23912_thi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sach {
    @PrimaryKey(autoGenerate = true)
    private int maSach;

    private int maLoai, giaThue;
    private String tenSach;
    private int khuyenMai;
    private int quantity;

    public Sach(int maLoai, int giaThue, int khuyenMai, String tenSach, int quantity) {
        this.maLoai = maLoai;
        this.giaThue = giaThue;
        this.tenSach = tenSach;
        this.khuyenMai = khuyenMai;
        this.quantity = quantity;
    }

    public Sach() {
    }

    public int getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
