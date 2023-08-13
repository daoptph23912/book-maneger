package com.daoptph23912.duanmau_daoptph23912_thi.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daoptph23912.duanmau_daoptph23912_thi.R;
import com.daoptph23912.duanmau_daoptph23912_thi.model.PhieuMuon;

import java.text.SimpleDateFormat;
import java.util.List;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.MyViewHolder>{
    private List<PhieuMuon> list;
    private OnEventPhieuMuonAdapter action;
    private View view;
    public PhieuMuonAdapter(Context context, OnEventPhieuMuonAdapter action){
        this.action = action;
    }
    public void setData(List<PhieuMuon> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_muon,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PhieuMuon obj = list.get(position);
        holder.tienThue.setText("Tiền Thuê: " + obj.getTienThue());
        holder.maPhieuMuon.setText(upMa(obj.getMaPM()));
        holder.maTV.setText("Mã Thành Viên: " + upMa(obj.getMaTV()));
        holder.maTT.setText(obj.getMaTT());
        holder.maSach.setText("Mã Sach: " + obj.getMaSach());
        holder.ngayMuon.setText("Ngày: " + new SimpleDateFormat("dd/MM/yyyy").format(obj.getNgayMuon()));
        if(obj.isTrangThai()){
            holder.trangThai.setText("Đã trả");
            holder.trangThai.setTextColor(Color.BLUE);
        }
        else
            holder.trangThai.setText("Chưa trả");
        view.setOnLongClickListener(view1 -> {
            action.xoa(holder.getAdapterPosition());
            return true;
        });
        view.setOnClickListener(view1 -> {
            action.sua(holder.getAdapterPosition());
        });

    }

    private String upMa(int ma){
        if(ma > 9)
            return String.valueOf(ma);
        return "0" + ma;
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }
    public interface OnEventPhieuMuonAdapter{
        void xoa(int position);
        void sua (int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView maPhieuMuon,trangThai,maTT,maTV,maSach,ngayMuon,tienThue;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            maPhieuMuon = itemView.findViewById(R.id.itemPhieuMuon_tv_maPhieuMuon);
            trangThai = itemView.findViewById(R.id.itemPhieuMuon_tv_trangThai);
            maTT = itemView.findViewById(R.id.itemPhieuMuon_tv_maTT);
            maTV = itemView.findViewById(R.id.itemPhieuMuon_tv_maThanhVien);
            maSach = itemView.findViewById(R.id.itemPhieuMuon_tv_maSach);
            ngayMuon = itemView.findViewById(R.id.itemPhieuMuon_tv_ngayMuon);
            tienThue = itemView.findViewById(R.id.itemPhieuMuon_tv_tienThue);
        }
    }
}
