package com.daoptph23912.duanmau_daoptph23912_thi.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.FragmentDoanhThuBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DoanhThuFragment extends Fragment {
    private FragmentDoanhThuBinding binding;
    private DAO dao;
    private DatePickerDialog pickerDialog;
    public static DoanhThuFragment newInstance() {
        DoanhThuFragment fragment = new DoanhThuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoanhThuBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = Room_DB.getInstance(requireContext()).getDAO();
        addAction();
    }

    private void addAction() {
        Calendar now = Calendar.getInstance();
        binding.fragDoanhThuBtnTinhDoanhThu.setOnClickListener(v -> {
            String ngay1 = binding.fragDoangThuEdTuNgay.getText().toString(),
                    ngay2 = binding.fragDoangThuEdDenNgay.getText().toString();
            if(ngay1.isEmpty() || ngay2.isEmpty()){
                Toast.makeText(requireContext(), "Dữ liệu thiếu !", Toast.LENGTH_SHORT).show();
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            try {
                binding.fragDoangThuTvTongDoanhThu.setText(String.valueOf(dao.getTienTrongKhoangThoiGian(sdf.parse(ngay1),
                        sdf.parse(ngay2))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        binding.fragDoangThuEdDenNgay.setOnClickListener(v -> {
            pickerDialog = new DatePickerDialog(requireContext(), (datePicker, i, i1, i2) ->
                    binding.fragDoangThuEdDenNgay.setText(i2 + "/" + (i1 + 1) + "/" + i),
                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
            pickerDialog.show();
        });
        binding.fragDoangThuEdTuNgay.setOnClickListener(v -> {
            pickerDialog = new DatePickerDialog(requireContext(), (datePicker, i, i1, i2) ->
                    binding.fragDoangThuEdTuNgay.setText(i2 + "/" + (i1 + 1) + "/" + i),
                    now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE));
            pickerDialog.show();
        });
    }
}