
package com.example.hci_project;

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

import static android.widget.Toast.LENGTH_SHORT;

public class report_problem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView mimageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_problem);
        Spinner spinner_b = (Spinner) findViewById(R.id.building_list);
        ArrayAdapter<CharSequence> adapter_b = ArrayAdapter.createFromResource(this, R.array.building_list, android.R.layout.simple_spinner_item);
        adapter_b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_b.setAdapter(adapter_b);
        spinner_b.setOnItemSelectedListener(this);
       AutoCompleteTextView spinner_r = (AutoCompleteTextView) findViewById(R.id.room_of_building_list);
        ArrayAdapter<CharSequence> adapter_r = ArrayAdapter.createFromResource(this, R.array.room_list, android.R.layout.simple_list_item_1);
        spinner_r.setAdapter(adapter_r);
        spinner_r.setOnItemSelectedListener(this);
        CheckBox electro_problem = findViewById(R.id.Problem_electro);
        LinearLayout electro_detail = findViewById(R.id.Problem_electro_detail);
        electro_problem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) electro_detail.setVisibility(View.VISIBLE);
                else electro_detail.setVisibility(View.GONE);
            }

        });
        CheckBox plumb_problem = findViewById(R.id.Problem_plumb);
        LinearLayout plumb_detail = findViewById(R.id.Problem_plumb_detail);
        plumb_problem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) plumb_detail.setVisibility(View.VISIBLE);
                else  plumb_detail.setVisibility(View.GONE);
            }

        });
        CheckBox building_problem = findViewById(R.id.Problem_build);
        LinearLayout building_detail = findViewById(R.id.Problem_build_detail);
        building_problem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) building_detail.setVisibility(View.VISIBLE);
                else building_detail.setVisibility(View.GONE);
            }

        });
        CheckBox etc_problem = findViewById(R.id.Problem_ect);
        LinearLayout ect_detail = findViewById(R.id.Problem_ect_detail);
        etc_problem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) ect_detail.setVisibility(View.VISIBLE);
                else ect_detail.setVisibility(View.GONE);
            }

        });
        CheckBox add_pic = findViewById(R.id.select_pic_from_gallery);
        LinearLayout add_pic_detail = findViewById(R.id.show_gallery);
        add_pic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) add_pic_detail.setVisibility(View.VISIBLE);
                else add_pic_detail.setVisibility(View.GONE);
            }
        });
        CheckBox take_pic = findViewById(R.id.take_picture);
        LinearLayout take_pic_detail = findViewById(R.id.show_camera);
        take_pic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) take_pic_detail.setVisibility(View.VISIBLE);
                else take_pic_detail.setVisibility(View.GONE);
            }
        });

    }

    public void backward(View view) {
        Dialog message_box;
        message_box = new Dialog(report_problem.this);
        message_box.setContentView(R.layout.backward_show_detail);
        message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        message_box.setCancelable(false);
        message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
        message_box.show();
        ImageView cancel = message_box.findViewById(R.id.cancel_backward);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                message_box.dismiss();
            }
        });
    }

    public void goDashboard(View view) {
        Intent intent = new Intent(report_problem.this,dashboard.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void cal_camera(View view) {
        mimageView = findViewById(R.id.temp_pic);
        Intent imageTakenintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (ContextCompat.checkSelfPermission(report_problem.this, Manifest.permission.CAMERA )!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(report_problem.this,new String[]{
                    Manifest.permission.CAMERA
            },0);
        }
        if(imageTakenintent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(imageTakenintent, 0);
        }

    }

    public void upload_picture(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent,
                    "Select photo from"), 1);
        }
    }

    public void submit_report(View view) {
        Dialog message_box;
        message_box = new Dialog(report_problem.this);
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
                Intent intent = new Intent(report_problem.this,dashboard.class);
                SharedPreferences remember = getSharedPreferences("report_count",MODE_PRIVATE);
                SharedPreferences.Editor edit = remember.edit();
                String count = remember.getString("report_count","0");
                edit.putString("report_count",(Integer.parseInt(count)+1)+"");
                edit.apply();
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== 0 && resultCode == Activity.RESULT_OK) {
            try {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ImageView problem = mimageView;
                problem.setImageBitmap(imageBitmap);
                problem.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                Log.e("Log", "Error from Camera Activity");
            }
        }
        if (requestCode== 1 && resultCode == Activity.RESULT_OK && data!=null) {
                Uri uri = data.getData();
                    int pic_ea =0;
                    //ImageView imageView = (ImageView) findViewById(R.id.problem_report_pic);
                   // imageView.getLayoutParams().height = 400;
                    List<Bitmap> bitmaps= new ArrayList<>();
                    ClipData clipData = data.getClipData();
                    if (clipData != null) {
                        for (pic_ea = 0; pic_ea < clipData.getItemCount(); pic_ea++) {
                            Uri imageuri = clipData.getItemAt(pic_ea).getUri();
                            try{
                                InputStream input = getContentResolver().openInputStream(imageuri);
                                Bitmap bitmap = BitmapFactory.decodeStream(input);
                                bitmaps.add(bitmap);
                            }catch(FileNotFoundException e){
                                e.printStackTrace();
                            }
                        }
                    }else{
                        Uri imageuri = data.getData();
                        try{
                            InputStream input = getContentResolver().openInputStream(imageuri);
                            Bitmap bitmap = BitmapFactory.decodeStream(input);
                            bitmaps.add(bitmap);
                        }catch(FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                    LinearLayout pic_list = findViewById(R.id.pic_list);
                    LayoutInflater inflater = LayoutInflater.from(this);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for(Bitmap b: bitmaps){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        View view = inflater.inflate(R.layout.item,pic_list,false);
                                        ImageView problem_src = view.findViewById(R.id.problem_src);
                                        problem_src.setImageBitmap(b);
                                        pic_list.addView(view);
                                    }
                                });
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
            HorizontalScrollView show = findViewById(R.id.wrap_pic_list);
            show.setVisibility(View.VISIBLE);

            }
        }
    }