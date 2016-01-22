package com.example.joseph.gellery_image.template;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

import com.example.joseph.gellery_image.EditActivity_1;
import com.example.joseph.gellery_image.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class template_2 extends AppCompatActivity implements View.OnClickListener{
    ImageView temp2img_1,temp2img_2,temp2img_3,temp2img_4,temp2img_5,temp2img_6,temp2img_7,temp2img_8;
    TextView temp2Text_1,temp2Text_2,temp2Text_3,temp2Text_4,temp2Text_5,temp2Text_6,temp2Text_7,temp2Text_8,
            temp2Text_9,temp2Text_10,temp2Text_11,temp2Text_12,temp2Text_13,temp2Text_14,temp2Text_15,temp2Text_16;

    Button button;
    LinearLayout container;
    Bitmap bmp=null;
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
            @Override
            public void onClick(View v) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/temp2capture.jpeg");
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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
            Log.d("debug", "나온네이");
            if (requestCode == 100) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                String filePath=data.getStringExtra("filePath");
                Log.d("debb",filePath);
                bmp = data.getParcelableExtra("image");
                temp2Text_1.setText(str1);
                temp2Text_2.setText(str2);
                temp2img_1.setImageURI(Uri.parse(filePath));
                temp2img_1.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode == 200) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp2Text_3.setText(str1);
                temp2Text_4.setText(str2);
                temp2img_2.setImageBitmap(bmp);
                temp2img_2.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 300) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp2Text_5.setText(str1);
                temp2Text_6.setText(str2);
                temp2img_3.setImageBitmap(bmp);
                temp2img_3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 400) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp2Text_7.setText(str1);
                temp2Text_8.setText(str2);
                temp2img_4.setImageBitmap(bmp);
                temp2img_4.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 500) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp2Text_9.setText(str1);
                temp2Text_10.setText(str2);
                temp2img_5.setImageBitmap(bmp);
                temp2img_5.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 600) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp2Text_11.setText(str1);
                temp2Text_12.setText(str2);
                temp2img_6.setImageBitmap(bmp);
                temp2img_6.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 700) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp2Text_13.setText(str1);
                temp2Text_14.setText(str2);
                temp2img_7.setImageBitmap(bmp);
                temp2img_7.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 800) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp2Text_15.setText(str1);
                temp2Text_16.setText(str2);
                temp2img_8.setImageBitmap(bmp);
                temp2img_8.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
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
        }
        File fileCacheItem=new File(strFilePath+filename);
        OutputStream out=null;

        try {
            fileCacheItem.createNewFile();
            out=new FileOutputStream(fileCacheItem);
            bitmap = Bitmap.createScaledBitmap(bitmap, 1920,1080, true);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100, out);

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
