package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class template_2 extends AppCompatActivity implements View.OnClickListener{
    ImageView temp2img_1,temp2img_2,temp2img_3,temp2img_4,temp2img_5,temp2img_6,temp2img_7,temp2img_8;
    TextView temp2Text_1,temp2Text_2,temp2Text_3,temp2Text_4,temp2Text_5,temp2Text_6,temp2Text_7,temp2Text_8,
            temp2Text_9,temp2Text_10,temp2Text_11,temp2Text_12,temp2Text_13,temp2Text_14,temp2Text_15,temp2Text_16;

    Button button;
    LinearLayout container;

    String usbPath="/storage/UsbDriveA";
    String filePath1=null;
    String filePath2=null;
    String filePath3=null;
    String filePath4=null;
    String filePath5=null;
    String filePath6=null;
    String filePath7=null;
    String filePath8=null;


    String abPath= Environment.getExternalStorageDirectory().getPath();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_2);
        temp2img_1=(ImageView)findViewById(R.id.temp2img_1);
        temp2img_2=(ImageView)findViewById(R.id.temp2img_2);
        temp2img_3=(ImageView)findViewById(R.id.temp2img_3);
        temp2img_4=(ImageView)findViewById(R.id.temp2img_4);
        temp2img_5=(ImageView)findViewById(R.id.temp2img_5);
        temp2img_6=(ImageView)findViewById(R.id.temp2img_6);
        temp2img_7=(ImageView)findViewById(R.id.temp2img_7);
        temp2img_8=(ImageView)findViewById(R.id.temp2img_8);
        temp2Text_1=(TextView)findViewById(R.id.temp2Text_1);
        temp2Text_2=(TextView)findViewById(R.id.temp2Text_2);
        temp2Text_3=(TextView)findViewById(R.id.temp2Text_3);
        temp2Text_4=(TextView)findViewById(R.id.temp2Text_4);
        temp2Text_5=(TextView)findViewById(R.id.temp2Text_5);
        temp2Text_6=(TextView)findViewById(R.id.temp2Text_6);
        temp2Text_7=(TextView)findViewById(R.id.temp2Text_7);
        temp2Text_8=(TextView)findViewById(R.id.temp2Text_8);
        temp2Text_9=(TextView)findViewById(R.id.temp2Text_9);
        temp2Text_10=(TextView)findViewById(R.id.temp2Text_10);
        temp2Text_11=(TextView)findViewById(R.id.temp2Text_11);
        temp2Text_12=(TextView)findViewById(R.id.temp2Text_12);
        temp2Text_13=(TextView)findViewById(R.id.temp2Text_13);
        temp2Text_14=(TextView)findViewById(R.id.temp2Text_14);
        temp2Text_15=(TextView)findViewById(R.id.temp2Text_15);
        temp2Text_16=(TextView)findViewById(R.id.temp2Text_16);

        temp2img_1.setOnClickListener(this);
        temp2img_2.setOnClickListener(this);
        temp2img_3.setOnClickListener(this);
        temp2img_4.setOnClickListener(this);
        temp2img_5.setOnClickListener(this);
        temp2img_6.setOnClickListener(this);
        temp2img_7.setOnClickListener(this);
        temp2img_8.setOnClickListener(this);

        container=(LinearLayout)findViewById(R.id.container);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(abPath+"/Lewi/Edit/capture/temp2capture.jpg");
                    captureView.compress(Bitmap.CompressFormat.PNG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                copyFile(filePath1,abPath+"/Lewi/Edit/temp/temp2","temp2img_1.jpg");
                copyFile(filePath2,abPath+"/Lewi/Edit/temp/temp2","temp2img_2.jpg");
                copyFile(filePath3,abPath+"/Lewi/Edit/temp/temp2","temp2img_3.jpg");
                copyFile(filePath4,abPath+"/Lewi/Edit/temp/temp2","temp2img_4.jpg");
                copyFile(filePath5,abPath+"/Lewi/Edit/temp/temp2","temp2img_5.jpg");
                copyFile(filePath6,abPath+"/Lewi/Edit/temp/temp2","temp2img_6.jpg");
                copyFile(filePath7,abPath+"/Lewi/Edit/temp/temp2","temp2img_7.jpg");
                copyFile(filePath8,abPath+"/Lewi/Edit/temp/temp2","temp2img_8.jpg");

                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }



    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.temp2img_1:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,100);
                break;
            case R.id.temp2img_2:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent,200);
                break;
            case R.id.temp2img_3:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,300);
                break;
            case R.id.temp2img_4:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,400);
                break;
            case R.id.temp2img_5:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,500);
                break;
            case R.id.temp2img_6:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,600);
                break;
            case R.id.temp2img_7:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,700);
                break;
            case R.id.temp2img_8:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,800);
                break;
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (resultcode == RESULT_OK) {
            if (requestCode == 100) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath1=str3;
                temp2Text_1.setText(str1);
                temp2Text_2.setText(str2);
                temp2img_1.setImageURI(Uri.parse(filePath1));
                temp2img_1.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            if (requestCode == 200) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath2=str3;
                temp2Text_3.setText(str1);
                temp2Text_4.setText(str2);
                temp2img_2.setImageURI(Uri.parse(filePath2));
                temp2img_2.setScaleType(ImageView.ScaleType.FIT_XY);


            }
            if (requestCode == 300) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath3=str3;
                temp2Text_5.setText(str1);
                temp2Text_6.setText(str2);
                temp2img_3.setImageURI(Uri.parse(filePath3));
                temp2img_3.setScaleType(ImageView.ScaleType.FIT_XY);


            }
            if (requestCode == 400) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath4=str3;
                temp2Text_7.setText(str1);
                temp2Text_8.setText(str2);
                temp2img_4.setImageURI(Uri.parse(filePath4));
                temp2img_4.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode == 500) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath5=str3;
                temp2Text_9.setText(str1);
                temp2Text_10.setText(str2);
                temp2img_5.setImageURI(Uri.parse(filePath5));
                temp2img_5.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode == 600) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath6=str3;
                temp2Text_11.setText(str1);
                temp2Text_12.setText(str2);
                temp2img_6.setImageURI(Uri.parse(filePath6));
                temp2img_6.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode == 700) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath7=str3;
                temp2Text_13.setText(str1);
                temp2Text_14.setText(str2);
                temp2img_7.setImageURI(Uri.parse(filePath7));
                temp2img_7.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode == 800) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String str3=data.getStringExtra("filePath");
                filePath8=str3;
                temp2Text_15.setText(str1);
                temp2Text_16.setText(str2);
                temp2img_8.setImageURI(Uri.parse(filePath8));
                temp2img_8.setScaleType(ImageView.ScaleType.FIT_XY);

            }
        }
    }

    public void copyFile(String copyPath,String pastePath,String fileName){
        File copyFile=new File(copyPath);
        File pasteFile=new File(pastePath+"/"+fileName);
        if (copyFile!=pasteFile) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(copyFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(pasteFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileChannel fcin = inputStream.getChannel();
            FileChannel fcout = outputStream.getChannel();
            long size = 0;
            try {
                size = fcin.size();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fcin.transferTo(0, size, fcout);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fcout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fcin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
