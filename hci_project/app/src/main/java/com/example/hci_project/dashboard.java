package com.example.hci_project;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class dashboard extends AppCompatActivity  {
    private  ImageView menu;
    private  ImageView profile;
    private  Button request_notify;
    private  PopupMenu popupMenu;
    private  String filter_state;
    private Button filter_menu;
    private EditText subj;
    private SharedPreferences filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_home);
        filter = getSharedPreferences("subj_list", MODE_PRIVATE);
        filter_state = filter.getString("subject", "");
        LinearLayout reg_list = findViewById(R.id.reg_list);
        LayoutInflater inflater = LayoutInflater.from(this);
        SharedPreferences remember = getSharedPreferences("checkbox",MODE_PRIVATE);
        String chck = remember.getString("remember","");
        Resources res = getResources();
        subj = findViewById(R.id.subj);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("complete",res.getResourceName(R.string.reporting_success),NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        if(!filter_state.equals(""))
            for (String i : filter_state.split(",")) {
                View view = inflater.inflate(R.layout.problem_template, reg_list, false);
                TextView status = view.findViewById(R.id.problem_report_detail);
                status.setText(i);
                reg_list.addView(view);
            }
        //filter_menu = findViewById(R.id.filter_button);
        /*if(filter_state.equals("default")) {
            filter_menu.setText(res.getText(R.string.default_value));
            for (int i = 0; i < Integer.parseInt(count); i++) {
            View view = inflater.inflate(R.layout.problem_template, problem_list, false);
            TextView status = view.findViewById(R.id.problem_report_status);
            int status_chance = new Random().nextInt() % 2;
            if (status_chance == 1) {
                status.setText(res.getText(R.string.report_status_success));
                status.setTextColor(res.getColor(R.color.green));
                status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
            } else {
                status.setText(res.getText(R.string.report_status_onworking));
                status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
            }
            problem_list.addView(view);

            }
        }else if(filter_state.equals("location")){
            filter_menu.setText(res.getText(R.string.location));
            int lc2 =  new Random().nextInt()%Integer.parseInt(count);
            View view = inflater.inflate(R.layout.building2, problem_list, false);
            problem_list.addView(view);
            int flag = 0;
            for (int i = 0; i < lc2; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            int lc3 = new Random().nextInt()%(Integer.parseInt(count)-lc2);
            view = inflater.inflate(R.layout.building3, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < lc3; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            int lc4 = new Random().nextInt()%(Integer.parseInt(count)-lc2-lc3);
            view = inflater.inflate(R.layout.building4, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < lc4; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            int lc5 = new Random().nextInt()%(Integer.parseInt(count)-lc2-lc3-lc4);
            view = inflater.inflate(R.layout.building5, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < lc5; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }


        }else if(filter_state.equals("date")){
            filter_menu.setText(res.getText(R.string.date));
            int success =  new Random().nextInt()%Integer.parseInt(count);
            View view = inflater.inflate(R.layout.today, problem_list, false);
            problem_list.addView(view);
            int flag = 0;
            for (int i = 0; i < success; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            view = inflater.inflate(R.layout.other_day, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < Integer.parseInt(count) - success; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
        }else if(filter_state.equals("report_status")){
            filter_menu.setText(res.getText(R.string.status));
            int success =  new Random().nextInt()%Integer.parseInt(count);
            View view = inflater.inflate(R.layout.sucess, problem_list, false);
            problem_list.addView(view);
            int flag = 0;
            for (int i = 0; i < success; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                status.setText(res.getText(R.string.report_status_success));
                status.setTextColor(res.getColor(R.color.green));
                status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            view = inflater.inflate(R.layout.onworking, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < Integer.parseInt(count) - success; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                status.setText(res.getText(R.string.report_status_onworking));
                status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
        }else if(filter_state.equals("catalogue")){
            filter_menu.setText(res.getText(R.string.problem_type));
            int lc2 =  new Random().nextInt()%Integer.parseInt(count);
            View view = inflater.inflate(R.layout.electro_cat, problem_list, false);
            problem_list.addView(view);
            int flag = 0;
            for (int i = 0; i < lc2; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            int lc3 = new Random().nextInt()%(Integer.parseInt(count)-lc2);
            view = inflater.inflate(R.layout.plumbing_cat, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < lc3; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            int lc4 = new Random().nextInt()%(Integer.parseInt(count)-lc2-lc3);
            view = inflater.inflate(R.layout.building_cat, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < lc4; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
            flag = 0;
            int lc5 = new Random().nextInt()%(Integer.parseInt(count)-lc2-lc3-lc4);
            view = inflater.inflate(R.layout.ect_cat, problem_list, false);
            problem_list.addView(view);
            for (int i = 0; i < lc5; i++) {
                view = inflater.inflate(R.layout.problem_template, problem_list, false);
                TextView status = view.findViewById(R.id.problem_report_status);
                int status_chance = new Random().nextInt() % 2;
                if (status_chance == 1) {
                    status.setText(res.getText(R.string.report_status_success));
                    status.setTextColor(res.getColor(R.color.green));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                } else {
                    status.setText(res.getText(R.string.report_status_onworking));
                    status.setTextColor(res.getColor(R.color.Thammsart_yellow));
                    status.setCompoundDrawablesWithIntrinsicBounds(R.drawable.construction, 0, 0, 0);
                }
                problem_list.addView(view);
                flag++;
            }
            if(flag==0){
                view = inflater.inflate(R.layout.none_thing, problem_list, false);
                problem_list.addView(view);
            }
        }

        menu =findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              menu.setImageResource(R.drawable.ic_menu_open);
              startMenu(v);
            }
        });

         */
        profile = findViewById(R.id.profile_icon);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Dialog message_box;
                message_box = new Dialog(dashboard.this);
                message_box.setContentView(R.layout.profile_infrom);
                message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                message_box.getWindow().setGravity(Gravity.TOP);
                message_box.setCancelable(false);
                message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
                message_box.show();
                ImageView cancel = message_box.findViewById(R.id.cancel_logout);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        message_box.dismiss();
                    }
                });
            }
        });
      /*  filter_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu = new PopupMenu(dashboard.this,v,Gravity.START);
                //filter_menu.setImageResource(R.drawable.ic_baseline_filter_after);
                filter_menu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_filter_after, 0, 0, 0);
                popupMenu.inflate(R.menu.filter_menu);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true);
                }
                popupMenu.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Intent intent = new Intent(dashboard.this,temp_filter.class);
                                if(item.getItemId()==R.id.date){
                                    intent.putExtra("status", "date");
                                    startActivity(intent);
                                }else  if(item.getItemId()==R.id.location){
                                    intent.putExtra("status", "location");
                                    startActivity(intent);
                                }else  if(item.getItemId()==R.id.status){
                                    intent.putExtra("status", "report_status");
                                    startActivity(intent);
                                }
                                else  if(item.getItemId()==R.id.problem_catelogue){
                                    intent.putExtra("status", "catalogue");
                                    startActivity(intent);
                                } if(item.getItemId()==R.id.default_value){
                                    intent.putExtra("status", "default");
                                    startActivity(intent);
                                }
                                return true;
                            }

                        }

                );
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu popupMenu) {
                        //filter_menu.setImageResource(R.drawable.ic_filter);
                        filter_menu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_filter, 0, 0, 0);
                    }
                });
                popupMenu.show();
            }
        });
      /*  LinearLayout phone_call = problem_list.findViewById(R.id.problem_block);
        phone_call.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               // Toast.makeText(dashboard.this,"Holding on: "+phone_call.getId(),Toast.LENGTH_SHORT).show();
                popupMenu = new PopupMenu(dashboard.this,v,Gravity.START);
                //filter_menu.setImageResource(R.drawable.ic_baseline_filter_after);
                //filter_menu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_filter_after, 0, 0, 0);
                popupMenu.inflate(R.menu.report_option);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    popupMenu.setForceShowIcon(true);
                }
                popupMenu.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                //Intent intent = new Intent(dashboard.this,temp_filter.class);
                                if(item.getItemId()==R.id.call_technician){


                                }else  if(item.getItemId()==R.id.Report_gen){

                                }
                                return true;
                            }

                        }

                );
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu popupMenu) {
                        //filter_menu.setImageResource(R.drawable.ic_filter);
                        //filter_menu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_filter, 0, 0, 0);
                    }
                });
                popupMenu.show();
                return false;
            }
        });*/
        /*  */
    }

    public void logout(){
        Dialog message_box;
        message_box = new Dialog(dashboard.this);
        message_box.setContentView(R.layout.custom_dialog_logout);
        message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        message_box.setCancelable(false);
        message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
        message_box.show();
        Button logout = message_box.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(dashboard.this,MainActivity.class);
                SharedPreferences remember = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor edit = remember.edit();
                edit.putString("remember","false");
                edit.apply();
                startActivity(intent);
            }
        });
        ImageView cancel = message_box.findViewById(R.id.cancel_logout);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                message_box.dismiss();
            }
        });
    }

    public void startMenu(View v){
        popupMenu = new PopupMenu(this,v,Gravity.START);
        popupMenu.inflate(R.menu.menu_user);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true);
        }
        popupMenu.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.Report_his){
                            show_Report_History();
                        }else  if(item.getItemId()==R.id.logout){
                            logout();
                        }else  if(item.getItemId()==R.id.problem_report){
                            Report_Problem();
                        }
                        return true;
                    }

                }

        );
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu popupMenu) {
                menu.setImageResource(R.drawable.ic_menu);
            }
        });
        popupMenu.show();
    }

    public void show_Report_History(){
        Intent intent = new Intent(dashboard.this,show_history.class);
        startActivity(intent);
    }
    public void Report_Problem(){
        Intent intent = new Intent(dashboard.this,report_problem.class);
        startActivity(intent);
    }

    public void  tell_me(View v){
        if(!subj.getText().toString().matches("CS[1-4][0-9][0-9]")){
            Dialog message_box;
            message_box = new Dialog(dashboard.this);
            message_box.setContentView(R.layout.custom_dialog_alert2);
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
        }else if(subj.getText().toString().equals("")){
            Dialog message_box;
            message_box = new Dialog(dashboard.this);
            message_box.setContentView(R.layout.custom_dialog_alert);
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
        } else{
            Dialog message_box;
            message_box = new Dialog(dashboard.this);
            message_box.setContentView(R.layout.comfirm_popup);
            message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            message_box.setCancelable(false);
            message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
            message_box.show();
            Button cancel = message_box.findViewById(R.id.decline);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    message_box.dismiss();
                }
            });
            Button approve = message_box.findViewById(R.id.accept);
            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    message_box.dismiss();
                    Toast toast=Toast.makeText(getApplicationContext(),"ทำรายการเสร็จสิ้น",Toast.LENGTH_SHORT);
                    toast.show();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(dashboard.this, "complete")
                            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                            .setContentTitle(getText(R.string.report_status_success))
                            .setContentText(getText(R.string.profile_name).toString()+getText(R.string.status)+subj.getText()+getText(R.string.date))
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getText(R.string.profile_name).toString()+getText(R.string.status)+subj.getText()+getText(R.string.date)));
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(dashboard.this);

// notificationId is a unique int for each notification that you must define
                    notificationManager.notify(1, builder.build());

                    SharedPreferences.Editor edt = filter.edit();
                    edt.putString("subject",filter_state+subj.getText().toString()+",");
                    edt.commit();
                    Intent intent = new Intent(dashboard.this,refresh_subject.class);
                    startActivity(intent);
                }
            });
        }

    }

}