package com.daoptph23912.duanmau_daoptph23912_thi.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class PhieuMuon {
    @PrimaryKey(autoGenerate = true)
    private int maPM;
    private int maTV,maSach;
    private int tienThue;
    private boolean trangThai;
    private String maTT;
    private Date ngayMuon;

    public PhieuMuon(String maTT, int maTV, int maSach, int tienThue, boolean trangThai, Date ngayMuon) {
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.trangThai = trangThai;
        this.maTT = maTT;
        this.ngayMuon = ngayMuon;
    }

    public PhieuMuon() {
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    @Override
    public String toString() {
        return
                "maPM=" + maPM +
                ", maTV=" + maTV +
                ", maSach=" + maSach +
                ", tienThue=" + tienThue +
                ", trangThai=" + trangThai +
                ", maTT='" + maTT + '\'' +
                ", ngayMuon='" + ngayMuon;
    }
}
