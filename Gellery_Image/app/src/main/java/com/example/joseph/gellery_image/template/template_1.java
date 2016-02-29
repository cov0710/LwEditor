package com.example.joseph.gellery_image.template;

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

import com.example.joseph.gellery_image.EditActivity_1;
import com.example.joseph.gellery_image.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class template_1 extends AppCompatActivity implements View.OnClickListener {
    ImageView temp1img_1, temp1img_2, temp1img_3;
    ImageView[] imageViews={temp1img_1, temp1img_2, temp1img_3};
    int[] imageXML={R.id.temp1img_1,R.id.temp1img_2,R.id.temp1img_3};
    TextView temp1Text_1, temp1Text_2, temp1Text_3, temp1Text_4, temp1Text_5, temp1Text_6;
    TextView[] textViews={ temp1Text_1, temp1Text_2, temp1Text_3, temp1Text_4, temp1Text_5, temp1Text_6};
    int[] textXML={R.id.temp1Text_1,R.id.temp4Text_2,R.id.temp1Text_3,R.id.temp4Text_4,R.id.temp1Text_5,R.id.temp4Text_6};
    Button button;
    LinearLayout container;
    String abPath = Environment.getExternalStorageDirectory().getPath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_1);
        for (int i=0;i<textViews.length;i++){
            textViews[i]=(TextView)findViewById(textXML[i]);
        }
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
            imageViews[i].setOnClickListener(this);
        }

        container = (LinearLayout) findViewById(R.id.container);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(abPath + "/Lewi/Edit/capture/temp1capture.png");
                    Log.d("ew","we");
                    captureView.compress(Bitmap.CompressFormat.PNG, 100, fos);
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
        Intent intent = new Intent();
        for (int i=0;i<imageViews.length;i++){
            if (view.getId()==imageXML[i]) {
                intent.setClass(this, EditActivity_1.class);
                startActivityForResult(intent, i);
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (resultcode == RESULT_OK) {
            for (int i=0,j=0;i<imageViews.length&&j<imageViews.length;i++,j++){
                if (requestCode==i){
                    String str1=data.getStringExtra("text1");
                    String str2=data.getStringExtra("text2");
                    String str3=data.getStringExtra("filePath");
                    textViews[i+j].setText(str1);
                    textViews[i+j+1].setText(str2);
                    imageViews[i].setImageURI(Uri.parse(str3));
                    imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }

        }
    }


}
