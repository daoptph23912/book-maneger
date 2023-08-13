package com.daoptph23912.duanmau_daoptph23912_thi;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.Fragment.DoanhThuFragment;
import com.daoptph23912.duanmau_daoptph23912_thi.Fragment.QuanLyLoaiSachFragment;
import com.daoptph23912.duanmau_daoptph23912_thi.Fragment.QuanLyNguoiDungFragment;
import com.daoptph23912.duanmau_daoptph23912_thi.Fragment.QuanLyPhieuMuonFragment;
import com.daoptph23912.duanmau_daoptph23912_thi.Fragment.QuanLySachFragment;
import com.daoptph23912.duanmau_daoptph23912_thi.Fragment.QuanLyThanhVienFragment;
import com.daoptph23912.duanmau_daoptph23912_thi.Fragment.Top10Sach;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.SharePre.Share;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.ActivityMainBinding;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FragmentTransaction transaction;
//    import com.google.android.material.navigation.NavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handlerNaviAndToolbar();
        addFragment();
    }

    private void addFragment() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.actiMain_layout_linear, QuanLyPhieuMuonFragment.newInstance());
        transaction.commit();
    }

    private void handlerNaviAndToolbar() {
        setSupportActionBar(binding.actiMainTb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.actiMainDrawerLayout,binding.actiMainTb,
                R.string.open_drawer,R.string.close_drawer);
        binding.actiMainDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.actiMainNv.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_qlpm) {
                chuyenFragment(QuanLyPhieuMuonFragment.newInstance());
            } else if (itemId == R.id.nav_qlls) {
                chuyenFragment(QuanLyLoaiSachFragment.newInstance());
            } else if (itemId == R.id.nav_qls) {
                chuyenFragment(QuanLySachFragment.newInstance());
            } else if (itemId == R.id.nav_qltv) {
                chuyenFragment(QuanLyThanhVienFragment.newInstance());
            } else if (itemId == R.id.nav_qlnd) {
                chuyenFragment(QuanLyNguoiDungFragment.newInstance());
            } else if (itemId == R.id.nav_top10Sach) {
                chuyenFragment(Top10Sach.newInstance());
            } else if (itemId == R.id.nav_doanhThu) {
                chuyenFragment(DoanhThuFragment.newInstance());
            } else if (itemId == R.id.nav_doiMatKhau) {
                handlerDoiMatKhau();
            } else {
                startActivity(new Intent(this, DangNhapActivity.class));
                finish();
            }

            binding.actiMainDrawerLayout.closeDrawer(binding.actiMainNv);
            return true;
        });

        TextView name_admin = binding.actiMainNv.getHeaderView(0).findViewById(R.id.layoutHeader_tv_name);
        name_admin.setText("" + new Share(this).getName());
    }

    private void handlerDoiMatKhau() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_doi_mat_khau);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //
        EditText ed_mkMoi = dialog.findViewById(R.id.dialogDoiMatKhau_ed_matKhauMoi),ed_mkCu = dialog.findViewById(R.id.dialogDoiMatKhau_ed_matKhauCu);
        Button btn_dongy = dialog.findViewById(R.id.dialogDoiMatKhau_btn_dongY),btn_huy = dialog.findViewById(R.id.dialogDoiMatKhau_btn_huy);
        //
        btn_dongy.setOnClickListener(v -> {
            DAO dao = Room_DB.getInstance(MainActivity.this).getDAO();
            ThuThu obj = dao.getObjOfThuThu(new Share(MainActivity.this).getMaTT());
            if(obj.getMatKhau().equals(ed_mkCu.getText().toString())){
                obj.setMatKhau(ed_mkMoi.getText().toString());
                dao.updateOfThuThu(obj);
                Toast.makeText(this, "Đổi thành công", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }else{
                Toast.makeText(this, "Mật khẩu không đúng !", Toast.LENGTH_SHORT).show();
            }
        });
        btn_huy.setOnClickListener(v -> {
            dialog.cancel();
        });

        dialog.show();
    }

    private void chuyenFragment(Fragment fragment){
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.actiMain_layout_linear,fragment);
        transaction.commit();
    }

}