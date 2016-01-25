package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener{
    FrameLayout frameLayout1,frameLayout2;
    File file=new File("/storage/UsbDriveA/Lewi");
    @TargetApi(Build.VERSION_CODES.KITKAT)
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
                if (file.exists()) {
                    Intent intent = new Intent(this, SelectTemplate.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this,"USB인식 실패",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

}
