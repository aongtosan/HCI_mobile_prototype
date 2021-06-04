package com.example.hci_project;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    CheckBox remember_me;
    Dialog message_box;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        remember_me = (CheckBox) findViewById(R.id.remember_me);
        SharedPreferences remember = getSharedPreferences("checkbox",MODE_PRIVATE);

        String chck = remember.getString("remember","");
        if(chck.equals("true")){
            Intent intent = new Intent(MainActivity.this,account_login.class);
            startActivity(intent);
        }
    }
    public void login(View v){
        String user_name_chck = "username";
        String password_chck = "12345678";
        //Intent intent = new Intent(MainActivity.this,account_login.class);
        //startActivity(intent);
       if(username.getText().toString().equals(user_name_chck)&&password.getText().toString().equals(password_chck)){
            Intent intent = new Intent(MainActivity.this,account_login.class);
            startActivity(intent);
           // Toast.makeText(this,username.getText().toString()+" "+password.getText().toString(),Toast.LENGTH_SHORT).show();
        }else if(!username.getText().toString().equals("")&&!password.getText().toString().equals("")) {
            //Toast.makeText(this,R.string.error_detail,Toast.LENGTH_SHORT).show();
            message_box = new Dialog(MainActivity.this);
            message_box.setContentView(R.layout.custom_dialog);
            message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            message_box.setCancelable(false);
            message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
            message_box.show();
            Button accept = message_box.findViewById(R.id.understanding);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    message_box.dismiss();
                }
            });

        }else if(username.getText().toString().equals("")||password.getText().toString().equals("")) {
           //Toast.makeText(this,R.string.error_detail,Toast.LENGTH_SHORT).show();
           if(username.getText().toString().equals("")&&password.getText().toString().equals("")){
               message_box = new Dialog(MainActivity.this);
               message_box.setContentView(R.layout.custom_dialog1);
               message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
               message_box.setCancelable(false);
               message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
               message_box.show();
               Button accept = message_box.findViewById(R.id.understanding);
               accept.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       message_box.dismiss();
                   }
               });
           }else if(username.getText().toString().equals("")&&!password.getText().toString().equals("")){
               message_box = new Dialog(MainActivity.this);
               message_box.setContentView(R.layout.custom_dialog1);
               message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
               message_box.setCancelable(false);
               message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
               //TextView error_user = message_box.findViewById(R.id.user_error);
               TextView error_pass = message_box.findViewById(R.id.pass_error);
               error_pass.setText("");
               message_box.show();
               Button accept = message_box.findViewById(R.id.understanding);
               accept.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       message_box.dismiss();
                   }
               });
           }else if(!username.getText().toString().equals("")&&password.getText().toString().equals("")){
               message_box = new Dialog(MainActivity.this);
               message_box.setContentView(R.layout.custom_dialog1);
               message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
               message_box.setCancelable(false);
               message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
               TextView error_user = message_box.findViewById(R.id.user_error);
               TextView error_pass = message_box.findViewById(R.id.pass_error);
               error_user.setText(error_pass.getText());
               error_pass.setText("");
               message_box.show();
               Button accept = message_box.findViewById(R.id.understanding);
               accept.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       message_box.dismiss();
                   }
               });
           }

       }

    }
    public void Remember_me(View v){
        boolean checked = ((CheckBox) v).isChecked();
        if(checked){
            SharedPreferences remember = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor edit = remember.edit();
            edit.putString("remember","true");
            edit.apply();
        }else {
            SharedPreferences remember = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor edit = remember.edit();
            edit.putString("remember","false");
            edit.apply();
        }
    }
}