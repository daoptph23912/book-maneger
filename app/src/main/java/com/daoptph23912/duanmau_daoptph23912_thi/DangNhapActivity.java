package com.daoptph23912.duanmau_daoptph23912_thi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.SharePre.Share;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.ActivityDangNhapBinding;

import java.util.Objects;

public class DangNhapActivity extends AppCompatActivity {
    private ActivityDangNhapBinding binding;
    private DAO dao;
    private Share share;
    private ThuThu obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangNhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dao = Room_DB.getInstance(this).getDAO();
        dao.insertOfThuThu(new ThuThu("admin","Phạm Thành Đạo","123456"));
        handlerThongTinShare();
        addAction();
    }
    private void handlerThongTinShare() {
        share = new Share(this);
        obj = share.getAccount();
        if(obj != null){
            binding.actiDangNhapEdTaiKhoan.setText(obj.getMaTT());
            binding.actiDangNhapEdMatKhau.setText(obj.getMatKhau());
            binding.actiDangNhapChkLuuThongTin.setChecked(true);
        }
    }
    private void addAction() {
        binding.actiDangNhapBtnDangNhap.setOnClickListener(v -> {
            obj = dao.getObjOfThuThu(Objects.requireNonNull(binding.actiDangNhapEdTaiKhoan.getText()).toString());
            if(obj != null && obj.getMatKhau().equals(Objects.requireNonNull(binding.actiDangNhapEdMatKhau.getText()).toString())){
                boolean check = binding.actiDangNhapChkLuuThongTin.isChecked();
                share.putAccount(obj,check);
                Intent intent = new Intent(this, com.daoptph23912.duanmau_daoptph23912_thi.MainActivity.class);
                Toast.makeText(this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
                return;
            }
            Toast.makeText(this, "Tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
        });
        binding.actiDangNhapBtnDangKy.setOnClickListener(v -> {
            Intent intent = new Intent(this,DangKyActivity.class);
            startActivity(intent);
        });
    }
}