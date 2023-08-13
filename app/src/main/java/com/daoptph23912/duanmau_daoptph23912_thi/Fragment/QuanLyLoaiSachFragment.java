package com.daoptph23912.duanmau_daoptph23912_thi.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.daoptph23912.duanmau_daoptph23912_thi.Adapter.LoaiSachAdapter;
import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.FragmentQuanLyLoaiSachBinding;
import com.daoptph23912.duanmau_daoptph23912_thi.model.LoaiSach;
import com.daoptph23912.duanmau_daoptph23912_thi.R;
import java.util.List;

public class QuanLyLoaiSachFragment extends Fragment implements LoaiSachAdapter.OnEvent_LoaiSach {
    private List<LoaiSach> list;
    private FragmentQuanLyLoaiSachBinding binding;
    private LoaiSachAdapter adapter;
    private DAO dao;
    
    public static QuanLyLoaiSachFragment newInstance() {
        QuanLyLoaiSachFragment fragment = new QuanLyLoaiSachFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLyLoaiSachBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = Room_DB.getInstance(requireContext()).getDAO();
        handleRecycler();
        addAction();
    }

    private void addAction() {
        binding.fragQuanLyLoaiSachBtnThemLoaiSach.setOnClickListener(v -> {
            String tenLoai = binding.fragQuanLyLoaiSachEdTenLoaiSach.getText().toString();
            if(tenLoai == null){
                Toast.makeText(requireContext(), "Thông tin bị trống !", Toast.LENGTH_SHORT).show();
                return;
            }
            dao.insertOfLoaiSach(new LoaiSach(tenLoai));
            list.add(dao.getObjNewOfLoaiSach());
            adapter.notifyItemInserted(list.size() - 1);
            binding.fragQuanLyLoaiSachEdTenLoaiSach.setText("");
            Toast.makeText(requireContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        });
    }

    private void handleRecycler() {
        list = dao.getAllOfLoaiSach();
        adapter = new LoaiSachAdapter(this);
        binding.fragQuanLyLoaiSachRc.setAdapter(adapter);
        binding.fragQuanLyLoaiSachRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setData(list);
    }

    @Override
    public void sua(int position) {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_sua_loai_sach);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //
        EditText et_tenLoaiSach = dialog.findViewById(R.id.dialogsuaLoaiSach_ed_tenLoaiSach);
        Button btn_dongY = dialog.findViewById(R.id.dialogSuaLoaiSach_btn_dongY),
                btn_huy = dialog.findViewById(R.id.dialogSuaLoaiSach_btn_huy);
        //
        LoaiSach obj = list.get(position);
        et_tenLoaiSach.setText(obj.getTenLoai());

        //
        btn_dongY.setOnClickListener(v -> {
            String tenLoaiSach = et_tenLoaiSach.getText().toString();
            if(tenLoaiSach.isEmpty()){
                Toast.makeText(requireContext(), "Thông tin bị trống !", Toast.LENGTH_SHORT).show();
                return;
            }
            obj.setTenLoai(tenLoaiSach);
            dao.updateOfLoaiSach(obj);
            list.set(position,obj);
            adapter.notifyItemChanged(position);
            dialog.cancel();
        });
        btn_huy.setOnClickListener(v -> {
            dialog.cancel();
        });
        dialog.show();
    }

    @Override
    public void xoa(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Xác nhận xoá");
        builder.setMessage("Việc này sẽ tương ứng với các sách thuộc loại này cũng bị xoá theo");
        builder.setNegativeButton("Xoá", (dialogInterface, i) -> {
            dao.deleteOfLoaiSach(list.get(position));
            list.remove(position);
            adapter.notifyItemRemoved(position);
            Toast.makeText(requireContext(), "Xoá thành công !", Toast.LENGTH_SHORT).show();
        });
        builder.setPositiveButton("Huỷ", (dialogInterface, i) -> {
        });
        builder.show();
    }
}