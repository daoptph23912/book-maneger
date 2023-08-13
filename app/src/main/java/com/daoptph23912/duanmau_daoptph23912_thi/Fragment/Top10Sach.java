package com.daoptph23912.duanmau_daoptph23912_thi.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.daoptph23912.duanmau_daoptph23912_thi.Adapter.SachAdapter;
import com.daoptph23912.duanmau_daoptph23912_thi.RoomDB.Room_DB;
import com.daoptph23912.duanmau_daoptph23912_thi.databinding.FragmentTop10SachBinding;


public class Top10Sach extends Fragment implements SachAdapter.OnEventSachAdapter {
    private FragmentTop10SachBinding binding;
    public static Top10Sach newInstance() {
        Top10Sach fragment = new Top10Sach();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTop10SachBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SachAdapter adapter = new SachAdapter(this);
        binding.fragTop10SachRc.setAdapter(adapter);
        binding.fragTop10SachRc.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setData(Room_DB.getInstance(requireContext()).getDAO().getTop10SachMuonNhieuNhat());
    }

    @Override
    public void xoa(int position) {

    }

    @Override
    public void sua(int position) {

    }
}