package com.daoptph23912.duanmau_daoptph23912_thi.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.daoptph23912.duanmau_daoptph23912_thi.Converters;
import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.model.LoaiSach;
import com.daoptph23912.duanmau_daoptph23912_thi.model.PhieuMuon;
import com.daoptph23912.duanmau_daoptph23912_thi.model.Sach;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThanhVien;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;

@TypeConverters({Converters.class})
@Database(entities = {ThanhVien.class, ThuThu.class, LoaiSach.class, PhieuMuon.class, Sach.class},version = 2)
public abstract class Room_DB extends RoomDatabase {
    public static final String NAME_DB = "ROOM.DB";
    public static Room_DB instance;

    public static Room_DB getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context,Room_DB.class,NAME_DB).allowMainThreadQueries().build();
        return instance;
    }
    public abstract DAO getDAO();

}
