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

public class template_2 extends AppCompatActivity implements View.OnClickListener{
    //==================이미지뷰 텍스트뷰 배열생성 alpha======================
    ImageView temp2img_1,temp2img_2,temp2img_3,temp2img_4,temp2img_5,temp2img_6,temp2img_7,temp2img_8;
    ImageView[] imageViews={temp2img_1,temp2img_2,temp2img_3,temp2img_4,temp2img_5,temp2img_6,temp2img_7,temp2img_8};
    int[] imageXML={R.id.temp2img_1,R.id.temp2img_2,R.id.temp2img_3,R.id.temp2img_4,R.id.temp2img_5,R.id.temp2img_6,R.id.temp2img_7,R.id.temp2img_8};
    TextView temp2Text_1,temp2Text_2,temp2Text_3,temp2Text_4,temp2Text_5,temp2Text_6,temp2Text_7,temp2Text_8,
            temp2Text_9,temp2Text_10,temp2Text_11,temp2Text_12,temp2Text_13,temp2Text_14,temp2Text_15,temp2Text_16;
    TextView[] textViews={ temp2Text_1,temp2Text_2,temp2Text_3,temp2Text_4,temp2Text_5,temp2Text_6,temp2Text_7,temp2Text_8,
            temp2Text_9,temp2Text_10,temp2Text_11,temp2Text_12,temp2Text_13,temp2Text_14,temp2Text_15,temp2Text_16};
    int[] textXML={R.id.temp2Text_1,R.id.temp2Text_2,R.id.temp2Text_3,R.id.temp2Text_4,R.id.temp2Text_5,R.id.temp2Text_6,R.id.temp2Text_7,R.id.temp2Text_8,
            R.id.temp2Text_9,R.id.temp2Text_10,R.id.temp2Text_11,R.id.temp2Text_12,R.id.temp2Text_13,R.id.temp2Text_14,R.id.temp2Text_15,R.id.temp2Text_16};
  //===================이미지뷰 텍스트뷰 배열생성 omega=======================

    Button button;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_2);
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
                Reference1.ImageCapture(getApplicationContext(), container);
                finish();
            }
        });
        //======================저장 버튼 누를 때 화면 캡쳐 저장 omega===================
    }


//===============view 마다 requestcode 보내기 alpha===========
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        for (int i=0;i<imageViews.length;i++){
            if (view.getId()==imageXML[i]){
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,i);
            }
        }

    }
    //===============view 마다 requestcode 보내기 omega===========


    //==================intent 각 view 마다 text1,2랑 이미지경로 받아오기 alpha==========
    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (resultcode==RESULT_OK){
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
//==================intent 각 view 마다 text1,2랑 이미지경로 받아오기 omega==========



}
