package com.example.joseph.gellery_image;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener{
    FrameLayout frameLayout1,frameLayout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        frameLayout1=(FrameLayout)findViewById(R.id.newSlide);
        frameLayout2=(FrameLayout)findViewById(R.id.mySlide);
        frameLayout1.setOnClickListener(this);
        frameLayout2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newSlide:
                Intent intent=new Intent(this,template_2.class);
                startActivity(intent);

        }

    }
}
