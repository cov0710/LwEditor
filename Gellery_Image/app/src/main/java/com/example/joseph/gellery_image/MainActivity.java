package com.example.joseph.gellery_image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
            Log.d("debug","주여");
//            Uri photoUri = data.getData();
//            Log.d("GALLERY", photoUri.toString());
//            String filePath = FileUtils.getPath(this, photoUri);
//            Log.d("FILE_PATH", filePath);
//            Log.d("debug","주여1");
            if (requestCode==100) {
                Log.d("debug", "주여2");
                Log.d("debug", "주여3");
                String str=data.getStringExtra("text");
                bmp=data.getParcelableExtra("image");
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
                imageView1.setImageBitmap(bmp);
                imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_1/";
                File file=new File(sdcard);
                Log.d("debugging", file + "");
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==200) {
                Log.d("debug", "주여2");
                Log.d("debug", "주여3");
                String str=data.getStringExtra("text");
                bmp=data.getParcelableExtra("image");
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
                imageView2.setImageBitmap(bmp);
                imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_2/";
                File file=new File(sdcard);
                Log.d("debugging", file + "");
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==300) {
                Log.d("debug", "주여2");
                Log.d("debug", "주여3");
                String str=data.getStringExtra("text");
                bmp=data.getParcelableExtra("image");
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
                imageView3.setImageBitmap(bmp);
                imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_3/";
                File file=new File(sdcard);
                Log.d("debugging", file + "");
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==400) {
                Log.d("debug", "주여2");
                Log.d("debug", "주여3");
                String str=data.getStringExtra("text");
                bmp=data.getParcelableExtra("image");
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
                imageView4.setImageBitmap(bmp);
                imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_4/";
                File file=new File(sdcard);
                Log.d("debugging", file + "");
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode==500) {
                Log.d("debug", "주여2");
                Log.d("debug", "주여3");
                String str=data.getStringExtra("text");
                bmp=data.getParcelableExtra("image");
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
                imageView5.setImageBitmap(bmp);
                imageView5.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard=abPath+"/Lewi/Edit/image_5/";
                File file=new File(sdcard);
                Log.d("debugging", file + "");
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
            bitmap = Bitmap.createScaledBitmap(bitmap, 500,500, true);
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