package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

@TargetApi(Build.VERSION_CODES.KITKAT)
public class template_1 extends AppCompatActivity implements View.OnClickListener{
    ImageView temp1img_1,temp1img_2,temp1img_3;
    TextView temp1Text_1,temp1Text_2,temp1Text_3,temp1Text_4,temp1Text_5,temp1Text_6;
    Button button;
    LinearLayout container;
    Bitmap bmp=null;
    String usbPath="/storage/UsbDriveA";
    String abPath= Environment.getExternalStorageDirectory().getPath();
    String filePath1=null;
    String filePath2=null;
    String filePath3=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_1);
        temp1img_1=(ImageView)findViewById(R.id.temp1img_1);
        temp1img_2=(ImageView)findViewById(R.id.temp1img_2);
        temp1img_3=(ImageView)findViewById(R.id.temp1img_3);
        temp1Text_1=(TextView)findViewById(R.id.temp1Text_1);
        temp1Text_2=(TextView)findViewById(R.id.temp4Text_2);
        temp1Text_3=(TextView)findViewById(R.id.temp1Text_3);
        temp1Text_4=(TextView)findViewById(R.id.temp4Text_4);
        temp1Text_5=(TextView)findViewById(R.id.temp1Text_5);
        temp1Text_6=(TextView)findViewById(R.id.temp4Text_6);

        temp1img_1.setOnClickListener(this);
        temp1img_2.setOnClickListener(this);
        temp1img_3.setOnClickListener(this);

        container=(LinearLayout)findViewById(R.id.container);

        button=(Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(abPath+"/Lewi/Edit/capture/temp1capture.jpg");
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                copyFile(filePath1, abPath + "/Lewi/Edit/temp/temp1", "temp1img_1.jpg");
                copyFile(filePath2, abPath + "/Lewi/Edit/temp/temp1", "temp1img_2.jpg");
                copyFile(filePath3, abPath + "/Lewi/Edit/temp/temp1", "temp1img_3.jpg");

//                String signalPath=usbPath+"/Lewi/Edit/signal/";
//                File signalFile=new File(signalPath);
//                String[] children=signalFile.list();
//                final int len=signalFile.list().length;
//                for (int i=0;i<len;i++){
//                    String filename=children[i];
//                    File f=new File(signalPath+filename);
//                    f.delete();
//                }
//                File file=new File(signalPath+"signal1");
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
            case R.id.temp1img_1:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.temp1img_2:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 200);
                break;
            case R.id.temp1img_3:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 300);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultcode,Intent data) {
        if (resultcode == RESULT_OK) {
            if (requestCode == 100) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath1=str3;
                temp1Text_1.setText(str1);
                temp1Text_2.setText(str2);
                temp1img_1.setImageBitmap(bmp);
                temp1img_1.setScaleType(ImageView.ScaleType.FIT_CENTER);



            }
            if (requestCode == 200) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath2=str3;
                temp1Text_3.setText(str1);
                temp1Text_4.setText(str2);
                temp1img_2.setImageBitmap(bmp);
                temp1img_2.setScaleType(ImageView.ScaleType.FIT_CENTER);

            }
            if (requestCode == 300) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                String str3=data.getStringExtra("filePath");
                filePath3=str3;
                temp1Text_5.setText(str1);
                temp1Text_6.setText(str2);
                temp1img_3.setImageBitmap(bmp);
                temp1img_3.setScaleType(ImageView.ScaleType.FIT_CENTER);


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
//    public void createThumbnail(Bitmap bitmap, String strFilePath, String filename){
//        File file=new File(strFilePath);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        File fileCacheItem=new File(strFilePath+filename);
//        OutputStream out=null;
//
//        try {
//            fileCacheItem.createNewFile();
//            out=new FileOutputStream(fileCacheItem);
//            bitmap = Bitmap.createScaledBitmap(bitmap, 1920,1080, true);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                out.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
    public void copyFile(String copyPath,String pastePath,String fileName){
        File copyFile=new File(copyPath);
        File pasteFile=new File(pastePath+"/"+fileName);
        Log.d("debug1",copyFile+"");
        Log.d("debug2",pasteFile+"");
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
            Log.d("debug3","성공");
        }
    }
}
