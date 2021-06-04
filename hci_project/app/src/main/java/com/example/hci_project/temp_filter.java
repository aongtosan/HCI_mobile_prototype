package com.example.hci_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class temp_filter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_filter);
        Intent intent = new Intent(temp_filter.this,dashboard.class);
        SharedPreferences status = getSharedPreferences("filter_state",MODE_PRIVATE);
        SharedPreferences.Editor edit = status.edit();
        String status_filter= getIntent().getStringExtra("status");
        edit.putString("state",status_filter);
        edit.apply();
        startActivity(intent);
    }
}