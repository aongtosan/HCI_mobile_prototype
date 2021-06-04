package com.example.hci_project;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class show_history extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_history);
        SharedPreferences remember = getSharedPreferences("report_count", MODE_PRIVATE);
        String count = remember.getString("report_count", "0");
        LinearLayout problem_list = findViewById(R.id.problem_list);
        LayoutInflater inflater = LayoutInflater.from(this);
        Resources res = getResources();

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
        ImageView back = findViewById(R.id.backward);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog message_box;
                message_box = new Dialog(show_history.this);
                message_box.setContentView(R.layout.backward_show_detail);
                message_box.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                message_box.setCancelable(false);
                message_box.getWindow().getAttributes().windowAnimations = R.style.animation;
                message_box.show();
                ImageView cancel = message_box.findViewById(R.id.cancel_backward);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        message_box.dismiss();
                    }
                });
            }
        });
    }
}