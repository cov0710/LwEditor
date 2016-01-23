package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class template_3 extends AppCompatActivity implements View.OnClickListener {
    ImageView temp3img_1,temp3img_2,temp3img_3,temp3img_4,temp3img_5;
    TextView temp3text_1,temp3text_2,temp3text_3,temp3text_4,temp3text_5,temp3text_6,temp3text_7,temp3text_8,temp3text_9,temp3text_10;
    LinearLayout container;
    Button button;
    Bitmap bmp=null;
    String abPath=Environment.getExternalStorageDirectory().getPath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_3);
        temp3img_1=(ImageView)findViewById(R.id.temp3img_1);
        temp3img_2=(ImageView)findViewById(R.id.temp3img_2);
        temp3img_3=(ImageView)findViewById(R.id.temp3img_3);
        temp3img_4=(ImageView)findViewById(R.id.temp3img_4);
        temp3img_5=(ImageView)findViewById(R.id.temp3img_5);

        temp3text_1=(TextView)findViewById(R.id.temp3text_1);
        temp3text_2=(TextView)findViewById(R.id.temp3text_2);
        temp3text_3=(TextView)findViewById(R.id.temp3text_3);
        temp3text_4=(TextView)findViewById(R.id.temp3text_4);
        temp3text_5=(TextView)findViewById(R.id.temp3text_5);
        temp3text_6=(TextView)findViewById(R.id.temp3text_6);
        temp3text_7=(TextView)findViewById(R.id.temp3text_7);
        temp3text_8=(TextView)findViewById(R.id.temp3text_8);
        temp3text_9=(TextView)findViewById(R.id.temp3text_9);
        temp3text_10=(TextView)findViewById(R.id.temp3text_10);

        temp3img_1.setOnClickListener(this);
        temp3img_2.setOnClickListener(this);
        temp3img_3.setOnClickListener(this);
        temp3img_4.setOnClickListener(this);
        temp3img_5.setOnClickListener(this);

        container=(LinearLayout)findViewById(R.id.container);
        button=(Button)findViewById(R.id.button);


        /*=======================================================
        저장 버튼 누를 때 화면 캡쳐 후 지금 지정위치로 파일 저장
        현재는 abPath 로 돼있음
         */
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    File[] file=getExternalFilesDirs(null);
                    String capturePath= String.valueOf(file[1]);
                    fos = new FileOutputStream(capturePath+"/Lewi/Edit/capture/temp3capture.jpg");
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        //=========================================================
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.temp3img_1:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,100);
                break;
            case R.id.temp3img_2:
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent,200);
                break;
            case R.id.temp3img_3:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,300);
                break;
            case R.id.temp3img_4:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,400);
                break;
            case R.id.temp3img_5:
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,500);
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
                temp3text_1.setText(str1);
                temp3text_2.setText(str2);
                temp3img_1.setImageURI(Uri.parse(filePath));
                temp3img_1.setScaleType(ImageView.ScaleType.FIT_XY);

            }
            if (requestCode == 200) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp3text_3.setText(str1);
                temp3text_4.setText(str2);
                temp3img_2.setImageBitmap(bmp);
                temp3img_2.setScaleType(ImageView.ScaleType.FIT_XY);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 300) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp3text_5.setText(str1);
                temp3text_6.setText(str2);
                temp3img_3.setImageBitmap(bmp);
                temp3img_3.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 400) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp3text_7.setText(str1);
                temp3text_8.setText(str2);
                temp3img_4.setImageBitmap(bmp);
                temp3img_4.setScaleType(ImageView.ScaleType.FIT_CENTER);
                String sdcard = abPath + "/Lewi/Edit/image_1/";
                createThumbnail(bmp, sdcard, 1 + ".png");

            }
            if (requestCode == 500) {
                String str1 = data.getStringExtra("text1");
                String str2 = data.getStringExtra("text2");
                bmp = data.getParcelableExtra("image");
                temp3text_9.setText(str1);
                temp3text_10.setText(str2);
                temp3img_5.setImageBitmap(bmp);
                temp3img_5.setScaleType(ImageView.ScaleType.FIT_CENTER);
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
