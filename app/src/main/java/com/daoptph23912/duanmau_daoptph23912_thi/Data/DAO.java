package com.daoptph23912.duanmau_daoptph23912_thi.Data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daoptph23912.duanmau_daoptph23912_thi.model.LoaiSach;
import com.daoptph23912.duanmau_daoptph23912_thi.model.PhieuMuon;
import com.daoptph23912.duanmau_daoptph23912_thi.model.Sach;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThanhVien;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Dao
public abstract class DAO {
    @Insert
    public abstract void insertOfPhieuMuon(PhieuMuon...obj);
    @Query("SELECT * FROM phieumuon")
    public abstract List<PhieuMuon> getAllOfPhieuMuon();

    @Update
    public abstract void updateOfPhieuMuon(PhieuMuon obj);
    @Delete
    public abstract void deleteOfPhieuMuon(PhieuMuon obj);

    @Query("DELETE FROM phieumuon WHERE maSach = :maSach")
    public abstract void deletePhieuMuonWithMaSach(int maSach);

    @Query("SELECT * FROM phieumuon ORDER BY maPM DESC LIMIT 1")
    public abstract PhieuMuon getObjNewOfPhieuMuon();

    @Query("SELECT * FROM sach WHERE maSach in (SELECT maSach FROM phieumuon GROUP BY maSach ORDER BY maSach DESC LIMIT 10)")
    public abstract List<Sach> getTop10SachMuonNhieuNhat();

    @Query("SELECT Count(*) FROM phieumuon WHERE ngayMuon BETWEEN :day1 AND :day2 GROUP BY maSach ORDER BY maSach DESC")
    public abstract List<Integer> getSoLuongMaSachTrongKhoanThoiGian(Date day1,Date day2);

    @Query("SELECT maSach FROM phieumuon WHERE ngayMuon BETWEEN :day1 AND :day2 GROUP BY maSach ORDER BY maSach DESC")
    public abstract List<Integer> getMaSachTrongKhoanThoiGian (Date day1,Date day2);

    @Query("SELECT giaThue FROM sach WHERE maSach = :maSach")
    public abstract int getTienWithMaSach(int maSach);

    public int getTienTrongKhoangThoiGian(Date day1, Date day2){
        int sum = 0;
        List<Integer> listMaSach = getMaSachTrongKhoanThoiGian(day1,day2);
        int index = 0;
        for(int x : getSoLuongMaSachTrongKhoanThoiGian(day1,day2)){
            sum += x * getTienWithMaSach(listMaSach.get(index));
            index ++;
        }
        return sum;
    }
    private String upMa(int ma){
        if(ma > 10)
            return String.valueOf(ma);
        return "0" + ma;
    }

    @Insert
    public abstract void  insertOfLoaiSach(LoaiSach...obj);
    @Query("SELECT * FROM loaisach")
    public abstract List<LoaiSach> getAllOfLoaiSach();

    public List<String> getAllMaAndTenLoaiSach(){
        List<String> list = new ArrayList<>();
        for(LoaiSach x : getAllOfLoaiSach()){
            list.add(upMa(x.getMaLoai()) + "-" + x.getTenLoai());
        }
        return list;
    }

    @Query("SELECT * FROM loaisach ORDER BY maLoai DESC LIMIT 1")
    public abstract LoaiSach getObjNewOfLoaiSach();
    @Update
    public abstract void updateOfLoaiSach(LoaiSach obj);
    @Delete
    public abstract void deleteObjOfLoaiSach(LoaiSach obj);

    public void deleteOfLoaiSach(LoaiSach obj){
        deleteObjOfLoaiSach(obj);
        deleteMaLoaiSach(obj.getMaLoai());
    }

    @Insert
    public abstract void insertOfSach(Sach ...obj);

    @Query("SELECT * FROM sach")
    public abstract List<Sach> getAllOfSach();

    @Query("SELECT * FROM sach ORDER BY maSach DESC LIMIT 1")
    public abstract Sach getObjNewOfSach();

    @Query("SELECT maSach FROM sach")
    public abstract List<Integer> getAllMaOfSach();

    public List<String> getAllMaAndTenOfSach(){
        List<String> list = new ArrayList<>();
        for(Sach x : getAllOfSach()){
            list.add(upMa(x.getMaSach()) + "-" + x.getTenSach());
        }
        return list;
    }
    @Query("SELECT * FROM sach WHERE maSach = :maSach")
    public abstract Sach getObjOfSach(int maSach);
    @Update
    public abstract void updateOfSach(Sach obj);
    @Delete
    public abstract void deleteObjOfSach(Sach obj);

    public void deleteOfSach(Sach obj){
        deleteObjOfSach(obj);
        deletePhieuMuonWithMaSach(obj.getMaSach());
    }

    @Query("SELECT maSach FROM sach WHERE maLoai = :maLoaiSach")
    public abstract List<Integer> getMaWithMaLoaiSachOfSach(int maLoaiSach);


    public void deleteMaLoaiSach(int maLoaiSach){
        for(int x : getMaWithMaLoaiSachOfSach(maLoaiSach)){
            deleteOfSach(getObjOfSach(x));
        }
    };

    @Insert
    public abstract void insertOfThanhVien(ThanhVien...obj);

    @Query("SELECT * FROM thanhvien")
    public abstract List<ThanhVien> getAllOfThanhVien();

    @Query("SELECT * FROM thanhvien ORDER BY maTV DESC LIMIT 1")
    public abstract ThanhVien getObjNewOfThanhVien();

    @Query("SELECT maTV FROM thanhvien")
    public abstract List<Integer> getAllMaOfThanhVien();

    public List<String> getAllMaAndTenOfThanhVien(){
        List<String> list = new ArrayList<>();
        for(ThanhVien x : getAllOfThanhVien()){
            list.add(upMa(x.getMaTV()) + "-" + x.getHoTen());
        }
        return list;
    }

    @Update
    public abstract void updateOfThanhVien(ThanhVien obj);

    @Delete
    public abstract void deleteOfThanhVien(ThanhVien obj);

    @Insert
    abstract void insertObjOfThuThu(ThuThu...obj);

    public boolean insertOfThuThu(ThuThu obj){
        if(getObjOfThuThu(obj.getMaTT()) == null){
            insertObjOfThuThu(obj);
            return true;
        }
        return false;
    }

    @Query("SELECT * FROM thuthu")
    public abstract List<ThuThu> getAllOfThuThu();

    @Query("SELECT * FROM thuthu WHERE maTT = :maTT")
    public abstract ThuThu getObjOfThuThu(String maTT);


    @Update
    public abstract void updateOfThuThu(ThuThu obj);

    @Delete
    public abstract void deleteOfThuThu(ThuThu obj);
}
