package com.example.joseph.gellery_image.template;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joseph.gellery_image.EditActivity_1;
import com.example.joseph.gellery_image.R;
import com.example.joseph.gellery_image.reference.Reference1;

public class template_9 extends AppCompatActivity implements View.OnClickListener{
    ImageView temp9img_1,temp9img_2,temp9img_3,temp9img_4,temp9img_5,temp9img_6;
    ImageView[] imageViews={temp9img_1,temp9img_2,temp9img_3,temp9img_4,temp9img_5,temp9img_6};
    int[] imageXML={R.id.temp9img_1,R.id.temp9img_2,R.id.temp9img_3,R.id.temp9img_4,R.id.temp9img_5,R.id.temp9img_6};
    TextView temp9Text_1,temp9Text_2,temp9Text_3,temp9Text_4,temp9Text_5,temp9Text_6,temp9Text_7,temp9Text_8,
            temp9Text_9,temp9Text_10,temp9Text_11,temp9Text_12;
    TextView[] textViews={temp9Text_1,temp9Text_2,temp9Text_3,temp9Text_4,temp9Text_5,temp9Text_6,temp9Text_7,temp9Text_8,
            temp9Text_9,temp9Text_10,temp9Text_11,temp9Text_12};
    int[] textXML={R.id.temp9Text_1,R.id.temp9Text_2,R.id.temp9Text_3,R.id.temp9Text_4,R.id.temp9Text_5,R.id.temp9Text_6,R.id.temp9Text_7,
            R.id.temp9Text_8,R.id.temp9Text_9,R.id.temp9Text_10,R.id.temp9Text_11,R.id.temp9Text_12};
    Button button;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_9);
        for (int i=0;i<textViews.length;i++){
            textViews[i]=(TextView)findViewById(textXML[i]);
        }
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
            imageViews[i].setOnClickListener(this);
        }
        container=(LinearLayout)findViewById(R.id.container);
        button=(Button)findViewById(R.id.button);

        //======================저장 버튼 누를 때 화면 캡쳐 저장 alpha===================
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Reference1.ImageCaptureLwtemp(getApplicationContext(), container);
                finish();
            }
        });
        //======================저장 버튼 누를 때 화면 캡쳐 저장 omega===================
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        for (int i=0;i<imageViews.length;i++){
            if (v.getId()==imageXML[i]){
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,i);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
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
