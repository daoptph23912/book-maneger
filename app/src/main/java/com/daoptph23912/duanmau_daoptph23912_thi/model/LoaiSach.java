package com.daoptph23912.duanmau_daoptph23912_thi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoaiSach {
    @PrimaryKey(autoGenerate = true)
    private int maLoai;

    private String tenLoai;

    public LoaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiSach() {
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
