package com.daoptph23912.duanmau_daoptph23912_thi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, com.daoptph23912.duanmau_daoptph23912_thi.MainActivity.class));
            finish();
        },2000);
    }
}