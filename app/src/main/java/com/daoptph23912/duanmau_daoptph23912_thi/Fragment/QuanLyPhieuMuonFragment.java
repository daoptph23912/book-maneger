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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.daoptph23912.duanmau_daoptph23912_thi.Adapter.PhieuMuonAdapter;
import com.daoptph23912.duanmau_daoptph23912_thi.Data.DAO;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.SharePre.Share;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.FragmentQuanLyPhieuMuonBinding;
import com.daoptph23912.duanmau_daoptph23912_thi.model.PhieuMuon;
import com.daoptph23912.duanmau_daoptph23912_thi.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuanLyPhieuMuonFragment extends Fragment implements PhieuMuonAdapter.OnEventPhieuMuonAdapter {
    private FragmentQuanLyPhieuMuonBinding binding;
    private List<PhieuMuon> list;
    private List<String> listMaAndTenTV,listMaAndTenSach;
    private List<Integer> listMaTV,listMaSach;
    private DAO dao;
    private SimpleDateFormat sdf;
    private int tienThue = 0;
    private int maSach;
    private PhieuMuonAdapter adapter;
    private ArrayAdapter arrayAdapter;
    public static QuanLyPhieuMuonFragment newInstance() {
        QuanLyPhieuMuonFragment fragment = new QuanLyPhieuMuonFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuanLyPhieuMuonBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = Room_DB.getInstance(requireContext()).getDAO();
        sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        handleLists();
        handleRecycler();
        addAction();
    }

    private void addAction() {
        binding.fragQuanLyPhieuMuonFloatAdd.setOnClickListener(v -> {
            if(listMaTV.isEmpty() || listMaSach.isEmpty()){
                Toast.makeText(requireContext(), "Số lượng thành viện hoặc số lượng sách đang trống !!", Toast.LENGTH_SHORT).show();
                return;
            }
            Dialog dialog = new Dialog(requireContext());
            dialog.setContentView(R.layout.dialog_them_sua_phieu_muon);
            dialog.setCancelable(false);
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //
            Spinner sp_maTV = dialog.findViewById(R.id.dialogThemPhieuMuon_sp_maTV),
                    sp_maSach = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_sp_maSach);
            TextView tv_ngayMuon = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_tv_ngayThue),
                    tv_tienThue = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_tv_tienThue),
                    tv_maTT = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_tv_maThuThu);
            dialog.findViewById(R.id.dialogThemSuaPhieuMuon_chk_check).setVisibility(View.GONE);
            Button btn_dongY = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_btn_dongY),
                    btn_huy = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_btn_huy);
            //
            arrayAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,listMaAndTenSach);
            sp_maSach.setAdapter(arrayAdapter);
            arrayAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,listMaAndTenTV);
            sp_maTV.setAdapter(arrayAdapter);
            //
            Date ngay = Calendar.getInstance().getTime();
            tv_ngayMuon.setText("Ngày: "  + sdf.format(ngay));
            tv_maTT.setText("Mã Thủ Thư: " + new Share(requireContext()).getMaTT());
            maSach = Integer.parseInt(listMaAndTenSach.get(0).substring(0,listMaAndTenSach.get(0).indexOf("-")));
            sp_maSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    maSach = listMaSach.get(i);
                    tienThue = dao.getObjOfSach(maSach).getGiaThue();
                    tv_tienThue.setText("Tiền Thuê: " + tienThue);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            btn_dongY.setOnClickListener(e -> {
                PhieuMuon obj = new PhieuMuon(new Share(requireContext()).getMaTT(),
                        listMaTV.get(sp_maTV.getSelectedItemPosition()),maSach,tienThue
                        ,false,
                        ngay);
                dao.insertOfPhieuMuon(obj);
                list.add(dao.getObjNewOfPhieuMuon());
                adapter.notifyItemInserted(list.size()-1);
                Toast.makeText(requireContext(), "Thêm thành công !", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            });
            btn_huy.setOnClickListener(e -> {dialog.cancel();});
            dialog.show();
        });
    }

    private void handleLists() {
        listMaAndTenSach = dao.getAllMaAndTenOfSach();
        listMaAndTenTV = dao.getAllMaAndTenOfThanhVien();
        listMaSach = dao.getAllMaOfSach();
        listMaTV = dao.getAllMaOfThanhVien();
    }

    private void handleRecycler() {
        list = dao.getAllOfPhieuMuon();
        adapter = new PhieuMuonAdapter(requireContext(),this);
        binding.fragQuanLyPhieuMuonRc.setAdapter(adapter);
        binding.fragQuanLyPhieuMuonRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setData(list);
    }


    @Override
    public void xoa(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Xác nhận xoá");
        builder.setNegativeButton("Xoá", (dialogInterface, i) -> {
            dao.deleteOfPhieuMuon(list.get(position));
            list.remove(position);
            adapter.notifyItemRemoved(position);
            Toast.makeText(requireContext(), "Xoá thành công !", Toast.LENGTH_SHORT).show();
        });
        builder.setPositiveButton("Huỷ", (dialogInterface, i) -> {
        });
        builder.show();
    }

    @Override
    public void sua(int position) {
        PhieuMuon obj = list.get(position);
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.dialog_them_sua_phieu_muon);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //
        Spinner sp_maTV = dialog.findViewById(R.id.dialogThemPhieuMuon_sp_maTV),
                sp_maSach = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_sp_maSach);
        TextView tv_ngayMuon = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_tv_ngayThue),
                tv_tienThue = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_tv_tienThue),
                tv_maTT = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_tv_maThuThu);
        CheckBox chk_check = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_chk_check);
        Button btn_dongY = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_btn_dongY),
                btn_huy = dialog.findViewById(R.id.dialogThemSuaPhieuMuon_btn_huy);
        //
        arrayAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,listMaAndTenSach);
        sp_maSach.setAdapter(arrayAdapter);
        arrayAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,listMaAndTenTV);
        sp_maTV.setAdapter(arrayAdapter);
        //
        if(obj.isTrangThai())
            chk_check.setChecked(true);
        tv_ngayMuon.setText("Ngày: "  + sdf.format(obj.getNgayMuon()));
        tv_maTT.setText("Mã Thủ Thư: " + obj.getMaTT());
        maSach = obj.getMaSach();
        sp_maSach.setSelection(listMaSach.indexOf(maSach));
        sp_maSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = listMaSach.get(i);
                tv_tienThue.setText("Tiền Thuê: " + dao.getObjOfSach(maSach).getGiaThue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        btn_dongY.setOnClickListener( e -> {
            PhieuMuon obj2 = new PhieuMuon(obj.getMaTT(),listMaTV.get(sp_maTV.getSelectedItemPosition()),maSach,
                    dao.getObjOfSach(maSach).getGiaThue()
                    ,chk_check.isChecked(),
                    obj.getNgayMuon());
            obj2.setMaPM(obj.getMaPM());
            dao.updateOfPhieuMuon(obj2);
            list.set(position,obj2);
            adapter.notifyItemChanged(position);
            Toast.makeText(requireContext(), "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
            dialog.cancel();
        });
        btn_huy.setOnClickListener(e -> {dialog.cancel();});
        dialog.show();
    }
}