package com.daoptph23912.duanmau_daoptph23912_thi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daoptph23912.duanmau_daoptph23912_thi.R;
import com.daoptph23912.duanmau_daoptph23912_thi.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.MyViewHolder> {
    private List<Sach> list;
    private OnEventSachAdapter action;
    private View view;

    public SachAdapter(OnEventSachAdapter action) {
        this.action = action;
    }

    public void setData(List<Sach> list) {
        this.list = list;
        this.notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
//        holder.itemView.setOnClickListener(v -> {
//            action.sua(holder.getAdapterPosition());
//        });
//        holder.itemView.setOnLongClickListener(v -> {
//            action.xoa(holder.getAdapterPosition());
//            return true;
//        });


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Sach obj = list.get(position);
        holder.giaThue.setText("Giá Thuê: " + obj.getGiaThue());
        holder.maLoai.setText("Mã Loại: " + upMa(obj.getMaLoai()));
        holder.tenSach.setText(obj.getTenSach());
        holder.maSach.setText(upMa(obj.getMaSach()));
        holder.khuyenMai.setText("Giá Khuyến Mãi: " + obj.getKhuyenMai());
        holder.quantity.setText("So luong" + obj.getQuantity());
//     EDIT and DELETE
//        view.setOnClickListener(v -> {
//            action.sua(holder.getAdapterPosition());
//        });
//        view.setOnLongClickListener(v -> {
//            action.xoa(holder.getAdapterPosition());
//            return true;
//        });
    }

    private String upMa(int ma) {
        if (ma > 9)
            return String.valueOf(ma);
        return "0" + ma;
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }


    public interface OnEventSachAdapter {
        void xoa(int position);

        void sua(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView maSach, tenSach, maLoai, giaThue, khuyenMai, quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            maSach = itemView.findViewById(R.id.itemSach_tv_maSach);
            tenSach = itemView.findViewById(R.id.itemSach_tv_tenSach);
            maLoai = itemView.findViewById(R.id.itemSach_tv_maLoai);
            giaThue = itemView.findViewById(R.id.itemSach_tv_giaThue);
            khuyenMai = itemView.findViewById(R.id.itemSach_tv_khuyenMai);
            quantity = itemView.findViewById(R.id.itemSach_tv_soluong);
        }
    }

    //tìm kiếm theo tên
    public void searchByName(String query) {
        List<Sach> searchResults = new ArrayList<>();
        for (Sach sach : list) {
            if (sach.getTenSach().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(sach);
            }
        }
        setData(searchResults);
    }

}
