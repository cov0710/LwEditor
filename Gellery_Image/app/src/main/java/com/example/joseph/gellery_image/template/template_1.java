package com.example.joseph.gellery_image.template;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseph.gellery_image.EditActivity_1;
import com.example.joseph.gellery_image.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class template_1 extends AppCompatActivity implements View.OnClickListener{
    ImageView temp1img_1,temp1img_2,temp1img_3;
    TextView temp1Text_1,temp1Text_2,temp1Text_3,temp1Text_4,temp1Text_5,temp1Text_6;
    Button button;
    LinearLayout container;
    Bitmap bmp=null;
    String abPath=Environment.getExternalStorageDirectory().getPath();
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
                    fos = new FileOutputStream(Environment.getExternalStorageDirectory().toString()+"/temp1capture.jpeg");
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
        Log.d("debug","나온네이");
        Intent intent=new Intent();
        switch (view.getId()){
            case R.id.temp1img_1:
                Log.d("debug","나온네이");
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
            Log.d("debug","나온네이");
            if (requestCode == 100) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp1Text_1.setText(str1);
                temp1Text_2.setText(str2);
                temp1img_1.setImageBitmap(bmp);
                temp1img_1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 200) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp1Text_3.setText(str1);
                temp1Text_4.setText(str2);
                temp1img_2.setImageBitmap(bmp);
                temp1img_2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_2/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 300) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp1Text_5.setText(str1);
                temp1Text_6.setText(str2);
                temp1img_3.setImageBitmap(bmp);
                temp1img_3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_3/";
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
