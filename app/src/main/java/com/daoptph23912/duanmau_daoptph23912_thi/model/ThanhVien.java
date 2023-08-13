package com.daoptph23912.duanmau_daoptph23912_thi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class ThanhVien {
    @PrimaryKey (autoGenerate = true)
    private int maTV;
    @NotNull
    private String hoTen,namSinh;

    public ThanhVien(String hoTen, String namSinh) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public ThanhVien() {
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
