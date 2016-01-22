package com.example.joseph.gellery_image;

import android.content.Intent;
import android.graphics.Bitmap;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
    Button button;
    Bitmap bmp=null;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    LinearLayout container;
    TextView insertText1,insertText2,insertText3,insertText4,insertText5,insertText6,insertText7,insertText8,insertText9,insertText10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String basePath=abPath+"/Lewi/Edit";
        File dir=new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        imageView1 =(ImageView)findViewById(R.id.imageView1);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        insertText1=(TextView)findViewById(R.id.insertText1);
        insertText2=(TextView)findViewById(R.id.insertText2);
        insertText3=(TextView)findViewById(R.id.insertText3);
        insertText4=(TextView)findViewById(R.id.insertText4);
        insertText5=(TextView)findViewById(R.id.insertText5);
        insertText6=(TextView)findViewById(R.id.insertText6);
        insertText7=(TextView)findViewById(R.id.insertText7);
        insertText8=(TextView)findViewById(R.id.insertText8);
        insertText9=(TextView)findViewById(R.id.insertText9);
        insertText10=(TextView)findViewById(R.id.insertText10);

        button=(Button)findViewById(R.id.button);
        container=(LinearLayout)findViewById(R.id.container);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/capture.jpeg");
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.imageView1:

                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.imageView2:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 200);
                break;
            case R.id.imageView3:

                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 300);
                break;
            case R.id.imageView4:

                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, 400);
                break;
            case R.id.imageView5:

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
                insertText1.setText(str1);
                insertText2.setText(str2);
                imageView1.setImageBitmap(bmp);
                imageView1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard=abPath+"/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==200) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                insertText3.setText(str1);
                insertText4.setText(str2);
                imageView2.setImageBitmap(bmp);
                imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_2/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==300) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                insertText5.setText(str1);
                insertText6.setText(str2);
                imageView3.setImageBitmap(bmp);
                imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_3/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==400) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                insertText7.setText(str1);
                insertText8.setText(str2);
                imageView4.setImageBitmap(bmp);
                imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_4/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==500) {
                String str1=data.getStringExtra("text1");
                String str2=data.getStringExtra("text2");
                bmp=data.getParcelableExtra("image");
                insertText9.setText(str1);
                insertText10.setText(str2);
                imageView5.setImageBitmap(bmp);
                imageView5.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_5/";
                createThumbnail(bmp, sdcard, 1 + ".png");

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



}