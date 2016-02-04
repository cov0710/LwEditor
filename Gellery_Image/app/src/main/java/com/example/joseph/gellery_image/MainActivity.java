package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
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
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView temp4img_1,temp4img_2,temp4img_3,temp4img_4,temp4img_5;
    Button button;
    Bitmap bmp=null;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    LinearLayout container;
    TextView temp4Text_1,temp4Text_2,temp4Text_3,temp4Text_4,temp4Text_5,temp4Text_6,temp4Text_7,
            temp4Text_8,temp4Text_9,temp4Text_10;
    String usbPath="/storage/UsbDriveA";
    String filePath1=null;
    String filePath2=null;
    String filePath3=null;
    String filePath4=null;
    String filePath5=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        temp4img_1 =(ImageView)findViewById(R.id.temp4img_1);
        temp4img_2=(ImageView)findViewById(R.id.temp4img_2);
        temp4img_3=(ImageView)findViewById(R.id.temp4img_3);
        temp4img_4=(ImageView)findViewById(R.id.temp4img_4);
        temp4img_5=(ImageView)findViewById(R.id.temp4img_5);
        temp4Text_1=(TextView)findViewById(R.id. temp4Text_1);
        temp4Text_2=(TextView)findViewById(R.id.temp4Text_2);
        temp4Text_3=(TextView)findViewById(R.id. temp4Text_3);
        temp4Text_4=(TextView)findViewById(R.id.temp4Text_4);
        temp4Text_5=(TextView)findViewById(R.id. temp4Text_5);
        temp4Text_6=(TextView)findViewById(R.id.temp4Text_6);
        temp4Text_7=(TextView)findViewById(R.id. temp4Text_7);
        temp4Text_8=(TextView)findViewById(R.id.temp4Text_8);
        temp4Text_9=(TextView)findViewById(R.id. temp4Text_9);
        temp4Text_10=(TextView)findViewById(R.id.temp4Text_10);

        button=(Button)findViewById(R.id.button);
        container=(LinearLayout)findViewById(R.id.container);
        temp4img_1.setOnClickListener(this);
        temp4img_2.setOnClickListener(this);
        temp4img_3.setOnClickListener(this);
        temp4img_4.setOnClickListener(this);
        temp4img_5.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(abPath+"/Lewi/Edit/capture/temp4capture.jpg");
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                copyFile(filePath1,abPath+"/Lewi/Edit/temp/temp4","temp4img_1.jpg");
                copyFile(filePath2,abPath+"/Lewi/Edit/temp/temp4","temp4img_2.jpg");
                copyFile(filePath3,abPath+"/Lewi/Edit/temp/temp4","temp4img_3.jpg");
                copyFile(filePath4,abPath+"/Lewi/Edit/temp/temp4","temp4img_4.jpg");
                copyFile(filePath5,abPath+"/Lewi/Edit/temp/temp4","temp4img_5.jpg");

//                String signalPath=usbPath+"/Lewi/Edit/signal/";
//                File signalFile=new File(signalPath);
//                String[] children=signalFile.list();
//                final int len=signalFile.list().length;
//                for (int i=0;i<len;i++){
//                    String filename=children[i];
//                    File f=new File(signalPath+filename);
//                    f.delete();
//                }
//                File file=new File(signalPath+"signal4");
//                file.mkdirs();

                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.temp4img_1:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.temp4img_2:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 200);
                break;
            case R.id.temp4img_3:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 300);
                break;
            case R.id.temp4img_4:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 400);
                break;
            case R.id.temp4img_5:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 500);
                break;


        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultcode,Intent data) {
        if (resultcode==RESULT_OK) {
            if (requestCode==100) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath1=str3;
                temp4Text_1.setText(str1);
                temp4Text_2.setText(str2);
                temp4img_1.setImageBitmap(bmp);
                temp4img_1.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode==200) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath2=str3;
                temp4Text_3.setText(str1);
                temp4Text_4.setText(str2);
                temp4img_2.setImageBitmap(bmp);
                temp4img_2.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode==300) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath3=str3;
                temp4Text_5.setText(str1);
                temp4Text_6.setText(str2);
                temp4img_3.setImageBitmap(bmp);
                temp4img_3.setScaleType(ImageView.ScaleType.FIT_XY);


            }
            if (requestCode==400) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath4=str3;
                temp4Text_7.setText(str1);
                temp4Text_8.setText(str2);
                temp4img_4.setImageBitmap(bmp);
                temp4img_4.setScaleType(ImageView.ScaleType.FIT_XY);


            }
            if (requestCode==500) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath5=str3;
                temp4Text_9.setText(str1);
                temp4Text_10.setText(str2);
                temp4img_5.setImageBitmap(bmp);
                temp4img_5.setScaleType(ImageView.ScaleType.FIT_XY);

            }

        }

    }


@Override
public void onDestroy(){
    super.onDestroy();
    if (bmp!=null){
        bmp.recycle();
        bmp=null;
    }
}

    public void createThumbnail(Bitmap bitmap, String strFilePath, String filename){
        File file=new File(strFilePath);
        if (!file.exists()){
            file.mkdirs();
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }
        File fileCacheItem=new File(strFilePath+filename);
        OutputStream out=null;

        try {
            fileCacheItem.createNewFile();
            out=new FileOutputStream(fileCacheItem);
            bitmap = Bitmap.createScaledBitmap(bitmap, 1920,1080, true);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            }catch (Exception e){
                e.printStackTrace();
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