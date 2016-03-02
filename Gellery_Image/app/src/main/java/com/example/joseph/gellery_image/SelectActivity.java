package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout select_1,select_2,select_3,select_4;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        select_1=(RelativeLayout)findViewById(R.id.select_1);
        select_2=(RelativeLayout)findViewById(R.id.select_2);
        select_3=(RelativeLayout)findViewById(R.id.select_3);
        select_4=(RelativeLayout)findViewById(R.id.select_4);
        select_1.setOnClickListener(this);
        select_2.setOnClickListener(this);
        select_3.setOnClickListener(this);
        select_4.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_1:
                    Intent intent = new Intent(this,SingleMediaActivity.class);
                    startActivity(intent);
                break;
            case R.id.select_3:
                    Intent intent1 = new Intent(this, SelectTemplate.class);
                    startActivity(intent1);
                break;
            case R.id.select_2:
                Intent intent2=new Intent();
                intent2.setClassName(this,"com.example.joseph.gellery_image.multiTemplate.multiTemplate_1");
                startActivity(intent2);


        }

    }

}
