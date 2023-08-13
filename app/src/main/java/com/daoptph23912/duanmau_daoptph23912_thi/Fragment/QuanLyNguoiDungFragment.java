package com.daoptph23912.duanmau_daoptph23912_thi.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.daoptph23912.duanmau_daoptph23912_thi.Adapter.NguoiDungAdapter;
import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.FragmentQuanLyNguoiDungBinding;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;

import java.util.List;


public class QuanLyNguoiDungFragment extends Fragment {
    public FragmentQuanLyNguoiDungBinding binding;
    private List<ThuThu> listThuThu;
    private DAO dao;
    NguoiDungAdapter adapter;
    public static QuanLyNguoiDungFragment newInstance() {
        QuanLyNguoiDungFragment fragment = new QuanLyNguoiDungFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLyNguoiDungBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = Room_DB.getInstance(requireContext()).getDAO();
        adapter = new NguoiDungAdapter();
        listThuThu = dao.getAllOfThuThu();
        adapter.setData(listThuThu);
        binding.fragQuanLyNguoiDungRc.setAdapter(adapter);
        binding.fragQuanLyNguoiDungRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        addAction();
    }

    private void addAction() {
        binding.fragQuanLyNguoiDungBtnSearch.setOnClickListener(v -> {
            String tt = binding.fragQuanLyNguoiDungEdSearch.getText().toString();
            if(tt.isEmpty()){
                listThuThu = dao.getAllOfThuThu();
                adapter.setData(listThuThu);
                return;
            }
            listThuThu.clear();
            ThuThu obj = dao.getObjOfThuThu(tt);
            if(obj != null){
                listThuThu.add(obj);
            }else {
                Toast.makeText(requireContext(), "Người dùng không tồn tại !", Toast.LENGTH_SHORT).show();
            }
            adapter.setData(listThuThu);
        });
    }
}