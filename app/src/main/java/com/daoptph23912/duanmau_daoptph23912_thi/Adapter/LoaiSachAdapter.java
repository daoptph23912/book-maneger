package com.daoptph23912.duanmau_daoptph23912_thi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.daoptph23912.duanmau_daoptph23912_thi.model.LoaiSach;
import com.daoptph23912.duanmau_daoptph23912_thi.R;
import java.util.List;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.MyViewHolder> {
    private List<LoaiSach> list;
    private View view;
    private OnEvent_LoaiSach action;

    public LoaiSachAdapter(OnEvent_LoaiSach action){
        this.action = action;
    }
    public void setData(List<LoaiSach> list){
        this.list = list;
        this.notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_sach,parent,false);
        return new MyViewHolder(view);
    }
    public interface OnEvent_LoaiSach{
        void sua(int position);
        void xoa(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LoaiSach obj = list.get(position);
        int i = obj.getMaLoai();
        if(i < 10)
            holder.maLoai.setText("0" + i);
        else
            holder.maLoai.setText(String.valueOf(i));
        holder.tenLoai.setText(obj.getTenLoai());
        view.setOnClickListener(v -> {
            action.sua(holder.getAdapterPosition());
        });
        view.setOnLongClickListener(v -> {
            action.xoa(holder.getAdapterPosition());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        if(list == null)
            return 0;
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView maLoai,tenLoai;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            maLoai = itemView.findViewById(R.id.itemLoaiSach_tv_maLoai);
            tenLoai = itemView.findViewById(R.id.itemLoaiSach_tv_tenLoai);
        }
    }
}
