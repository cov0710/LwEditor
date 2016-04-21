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

public class template_8 extends AppCompatActivity   implements View.OnClickListener {
    ImageView temp8img_1,temp8img_2,temp8img_3,temp8img_4;
    ImageView[] imageViews={temp8img_1,temp8img_2,temp8img_3,temp8img_4};
    int[] imageXML={R.id.temp8img_1,R.id.temp8img_2,R.id.temp8img_3,R.id.temp8img_4};
    TextView temp8Text_1,temp8Text_2,temp8Text_3,temp8Text_4,temp8Text_5,temp8Text_6,temp8Text_7,temp8Text_8;
    TextView[] textViews={temp8Text_1,temp8Text_2,temp8Text_3,temp8Text_4,temp8Text_5,temp8Text_6,temp8Text_7,temp8Text_8};
    int[] textXML={R.id.temp8Text_1,R.id.temp8Text_2,R.id.temp8Text_3,R.id.temp8Text_4,R.id.temp8Text_5,R.id.temp8Text_6,
            R.id.temp8Text_7,R.id.temp8Text_8};
    Button button;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_8);
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
