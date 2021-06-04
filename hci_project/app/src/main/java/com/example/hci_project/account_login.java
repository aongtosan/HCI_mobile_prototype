package com.example.hci_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class account_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_login);
    }

    public void use_another_account(View view) {
        Intent intent = new Intent(account_login.this,MainActivity.class);
        startActivity(intent);
        SharedPreferences remember = getSharedPreferences("checkbox",MODE_PRIVATE);
        SharedPreferences.Editor edit = remember.edit();
        edit.putString("remember","false");
        edit.apply();
    }

    public void toDashboard(View view) {
        Intent intent = new Intent(account_login.this,dashboard.class);
        startActivity(intent);
    }
}