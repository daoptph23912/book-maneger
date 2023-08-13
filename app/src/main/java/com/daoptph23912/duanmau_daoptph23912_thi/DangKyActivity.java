package com.daoptph23912.duanmau_daoptph23912_thi;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.ActivityDangKyBinding;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;


public class DangKyActivity extends AppCompatActivity {
    private ActivityDangKyBinding binding;
    private DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDangKyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dao = Room_DB.getInstance(this).getDAO();
        addAction();
    }

    private void addAction() {
        binding.actiDangKyBtnDangKy.setOnClickListener(v -> {
            String tenThuThu = binding.actiDangKyEdTenThuThu.getText().toString(),
                    taiKhoan = binding.actiDangKyEdTaiKhoan.getText().toString(),
                    matKhau = binding.actiDangKyEdMatKhau.getText().toString(),
                    matKhau2 = binding.actiDangKyEdMatKhau2.getText().toString();
            if(tenThuThu.isEmpty()){
                Toast.makeText(this, "Tên người dùng thiếu !", Toast.LENGTH_SHORT).show();
                return;
            }

            if(taiKhoan.isEmpty() || matKhau.isEmpty() || matKhau2.isEmpty()){
                Toast.makeText(this, "Thông tin bị thiếu !", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean check =true;
            String errr = "";
            if(tenThuThu.length()<5 ||  tenThuThu.length() > 15){
                errr += "Tên người dùng tối thiêu 5 kí tự tối đa 15 kí tự !\n" ;
                check = false;
            }
            String a = tenThuThu.substring(0,1);
            if(!a.matches("[A-Z]")){
                errr+= "Tên người dùng chữ cái đầu tiên phải viết hoa !";
                check = false;
            }
            if(!check){
                Toast.makeText(this,errr,Toast.LENGTH_SHORT).show();
                return;
            }
            if(!check)
                return;
            if(!matKhau.equals(matKhau2)){
                Toast.makeText(this, "Mật khẩu không giống nhau !", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!dao.insertOfThuThu(new ThuThu(taiKhoan,tenThuThu,matKhau))){
                Toast.makeText(this, "Tài khoản đã tồn tại !", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
        });
        binding.actiDangKyBtnHuy.setOnClickListener(v -> {
            finish();
        });
    }
}