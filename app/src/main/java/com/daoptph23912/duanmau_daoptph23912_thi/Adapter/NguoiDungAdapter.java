package com.daoptph23912.duanmau_daoptph23912_thi.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.daoptph23912.duanmau_daoptph23912_thi.R;
import com.daoptph23912.duanmau_daoptph23912_thi.model.ThuThu;

import java.util.List;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.MyViewHolder>{
    private List<ThuThu> list;


    public void setData(List<ThuThu> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nguoi_dung,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ThuThu obj = list.get(position);
        holder.id.setText(String.valueOf(holder.getAdapterPosition()));
        holder.ma.setText(obj.getMaTT());
        holder.ten.setText(obj.getTen());
        int color = Color.GREEN;
        if(position % 2 != 0){
            color = Color.RED;
        }
        holder.id.setTextColor(color);
        holder.ten.setTextColor(color);
        holder.ma.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,ten,ma;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.itemNguoiDung_tv_maLoai);
            ten = itemView.findViewById(R.id.itemNguoiDung_tv_tenLoai);
            ma = itemView.findViewById(R.id.itemNguoiDung_tv_maNguoiDung);
        }
    }
}
