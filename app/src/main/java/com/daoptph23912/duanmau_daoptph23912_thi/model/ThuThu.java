package com.daoptph23912.duanmau_daoptph23912_thi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class ThuThu {
    @PrimaryKey
    @NotNull
    private String  maTT;
    @NotNull
    private String ten,matKhau;

    public ThuThu(@NotNull String maTT, @NotNull String ten, @NotNull String matKhau) {
        this.maTT = maTT;
        this.ten = ten;
        this.matKhau = matKhau;
    }


    @NotNull
    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(@NotNull String maTT) {
        this.maTT = maTT;
    }

    @NotNull
    public String getTen() {
        return ten;
    }

    public void setTen(@NotNull String ten) {
        this.ten = ten;
    }

    @NotNull
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(@NotNull String matKhau) {
        this.matKhau = matKhau;
    }
}
