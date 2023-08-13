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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.daoptph23912.duanmau_daoptph23912_thi.Adapter.SachAdapter;
import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.FragmentQuanLySachBinding;
import com.daoptph23912.duanmau_daoptph23912_thi.model.Sach;
import com.daoptph23912.duanmau_daoptph23912_thi.R;

import java.util.List;

public class QuanLySachFragment extends Fragment implements SachAdapter.OnEventSachAdapter {
    private FragmentQuanLySachBinding binding;
    private SachAdapter adapter;
    private ArrayAdapter arrayAdapter;
    private List<String> listMaLoaiSach;
    private DAO dao;
    private List<Sach> list;

    public static QuanLySachFragment newInstance() {
        QuanLySachFragment fragment = new QuanLySachFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLySachBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = Room_DB.getInstance(requireContext()).getDAO();
        listMaLoaiSach = dao.getAllMaAndTenLoaiSach();
        arrayAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, listMaLoaiSach);
        handleRecycler();
        addAction();
    }

    private void addAction() {
        binding.fragQuanLySachFloatAdd.setOnClickListener(v -> {
            if (listMaLoaiSach.isEmpty()) {
                Toast.makeText(requireContext(), "Số lượng thể loại sách đang bị trống !", Toast.LENGTH_SHORT).show();
                return;
            }
            Dialog dialog = new Dialog(requireContext());
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_them_sua_sach);
            Window window = dialog.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            //
            EditText ed_tenSach = dialog.findViewById(R.id.dialogThemSuaSach_ed_tenSach),
                    ed_giaCa = dialog.findViewById(R.id.dialogThemSuaSach_ed_giaSach),
                    ed_khuyenMai = dialog.findViewById(R.id.dialogThemSuaSach_ed_khuyenMai);
            EditText ed_soluong = dialog.findViewById(R.id.dialogThemSuaSach_ed_num);
            Spinner spinner = dialog.findViewById(R.id.dialogThemSuaSach_sp_loaiSach);
            Button btn_dongY = dialog.findViewById(R.id.dialogThemSuaSach_btn_dongY),
                    btn_huy = dialog.findViewById(R.id.dialogThemSuaSach_btn_huy);
            //
            spinner.setAdapter(arrayAdapter);
            //
            btn_dongY.setOnClickListener(e -> {
                String tenSach = ed_tenSach.getText().toString(), giaCa = ed_giaCa.getText().toString();
                int quantity = Integer.parseInt(ed_soluong.getText().toString());
                if (tenSach.isEmpty() || giaCa.isEmpty() || ed_khuyenMai.getText().toString().isEmpty()) {
                    Toast.makeText(requireContext(), "Thông tin bị trống !", Toast.LENGTH_SHORT).show();
                    return;
                }
                String maLoaiSach = listMaLoaiSach.get(spinner.getSelectedItemPosition());
//                Sach obj = new Sach(Integer.parseInt(maLoaiSach.substring(0, maLoaiSach.indexOf("-"))),
//                        Integer.parseInt(giaCa), Integer.parseInt(ed_khuyenMai.getText().toString()), tenSach);
                Sach obj = new Sach(Integer.parseInt(maLoaiSach.substring(0, maLoaiSach.indexOf("-"))),
                        Integer.parseInt(giaCa), Integer.parseInt(ed_khuyenMai.getText().toString()), tenSach, quantity);


                dao.insertOfSach(obj);
                list.add(dao.getObjNewOfSach());
                adapter.notifyItemInserted(list.size() - 1);
                dialog.cancel();
                Toast.makeText(requireContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            });
            btn_huy.setOnClickListener(e -> {
                dialog.cancel();
            });

            dialog.show();
        });
    }

    private void handleRecycler() {
        list = dao.getAllOfSach();
        adapter = new SachAdapter(this);
        binding.fragQuanLYSachRc.setAdapter(adapter);
        binding.fragQuanLYSachRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setData(list);
    }

    @Override
    public void xoa(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Xác nhận xoá");
        builder.setMessage("Việc này tương ứng sẽ xoá hết các dữ liệu liên quan");
        builder.setNegativeButton("Xoá", (dialogInterface, i) -> {
            Sach obj = list.get(position);
            dao.deleteOfSach(obj);
            list.remove(position);
            adapter.notifyItemRemoved(position);
            Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
        });
        builder.setPositiveButton("Huỷ", (dialogInterface, i) -> {
        });
        builder.show();

    }

    @Override
    public void sua(int position) {
        Sach obj = list.get(position);
        Dialog dialog = new Dialog(requireContext());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_them_sua_sach);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //
        EditText ed_tenSach = dialog.findViewById(R.id.dialogThemSuaSach_ed_tenSach),
                ed_giaCa = dialog.findViewById(R.id.dialogThemSuaSach_ed_giaSach),
                ed_khuyenMai = dialog.findViewById(R.id.dialogThemSuaSach_ed_khuyenMai);
        EditText ed_soluong = dialog.findViewById(R.id.dialogThemSuaSach_ed_num);
        Spinner spinner = dialog.findViewById(R.id.dialogThemSuaSach_sp_loaiSach);
        Button btn_dongY = dialog.findViewById(R.id.dialogThemSuaSach_btn_dongY),
                btn_huy = dialog.findViewById(R.id.dialogThemSuaSach_btn_huy);
        //
        spinner.setAdapter(arrayAdapter);
        //
        int index = 0;
        for (String x : listMaLoaiSach) {
            if (obj.getMaLoai() == Integer.parseInt(x.substring(0, x.indexOf("-")))) {
                break;
            }
            index++;
        }
        spinner.setSelection(index);
        ed_giaCa.setText(String.valueOf(obj.getGiaThue()));
        ed_tenSach.setText(obj.getTenSach());
        ed_khuyenMai.setText(String.valueOf(obj.getKhuyenMai()));

        //
        btn_dongY.setOnClickListener(e -> {
            String tenSach = ed_tenSach.getText().toString(), giaCa = ed_giaCa.getText().toString();

            int quantity = Integer.parseInt(ed_soluong.getText().toString());


            if (tenSach.isEmpty() || giaCa.isEmpty() || ed_khuyenMai.getText().toString().isEmpty()) {
                Toast.makeText(requireContext(), "Thông tin bị trống !", Toast.LENGTH_SHORT).show();
                return;
            }
            String maLoaiSach = listMaLoaiSach.get(spinner.getSelectedItemPosition());
//            Sach obj2 = new Sach(Integer.parseInt(maLoaiSach.substring(0, maLoaiSach.indexOf("-"))),
//                    Integer.parseInt(giaCa), Integer.valueOf(ed_khuyenMai.getText().toString()), tenSach);


            Sach obj2 = new Sach(Integer.parseInt(maLoaiSach.substring(0, maLoaiSach.indexOf("-"))),
                    Integer.parseInt(giaCa), Integer.parseInt(ed_khuyenMai.getText().toString()), tenSach, quantity);
            obj2.setMaSach(obj.getMaSach());
            dao.updateOfSach(obj2);
            list.set(position, obj2);
            adapter.notifyItemChanged(position);
            dialog.cancel();
        });
        btn_huy.setOnClickListener(e -> {
            dialog.cancel();
        });

        dialog.show();
    }
}