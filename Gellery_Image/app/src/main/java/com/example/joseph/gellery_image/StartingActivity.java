package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class StartingActivity extends AppCompatActivity {
    String abPath= Environment.getExternalStorageDirectory().getPath();
    String usbPath="/storage/UsbDriveA";
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1200);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }





                    String tempPath = abPath + "/Lewi/Edit/temp/";
                    File file1 = new File(abPath + "/Lewi/Edit/temp");
                    File file2 = new File(abPath + "/Lewi/Edit/template");
                    File file3 = new File(abPath + "/Lewi/Edit/capture");
                    File file4=new File(abPath+"/Lewi/Edit/signal");
                    File temp1 = new File(tempPath + "temp1");
                    File temp2 = new File(tempPath + "temp2");
                    File temp3 = new File(tempPath + "temp3");
                    File temp4 = new File(tempPath + "temp4");
                    File temp5 = new File(tempPath + "temp5");
                    if (!file1.exists()) {
                        file1.mkdirs();
                    }
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (!file3.exists()) {
                        file3.mkdirs();
                    }
                    if (!file4.exists()){
                        file4.mkdirs();
                    }
                    if (!temp1.exists()) {
                        temp1.mkdirs();
                    }
                    if (!temp2.exists()) {
                        temp2.mkdirs();
                    }
                    if (!temp3.exists()) {
                        temp3.mkdirs();
                    }
                    if (!temp4.exists()) {
                        temp4.mkdirs();
                    }
                    if (!temp5.exists()) {
                        temp5.mkdirs();
                    }


                Intent intent=new Intent(getApplication(),SelectActivity.class);
                startActivity(intent);

                finish();
            }
        }).start();
    }





}
