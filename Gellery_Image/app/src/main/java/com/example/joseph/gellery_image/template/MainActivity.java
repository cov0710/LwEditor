package com.example.joseph.gellery_image.template;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joseph.gellery_image.EditActivity_1;
import com.example.joseph.gellery_image.R;
import com.example.joseph.gellery_image.reference.Reference1;




//======================template_4입니다============================================
//******************************이름만 MainActivity*****************************************************

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView temp4img_1,temp4img_2,temp4img_3,temp4img_4,temp4img_5;
    ImageView[] imageViews={temp4img_1,temp4img_2,temp4img_3,temp4img_4,temp4img_5};
    int[] imageXML={R.id.temp4img_1,R.id.temp4img_2,R.id.temp4img_3,R.id.temp4img_4,R.id.temp4img_5};
    TextView temp4Text_1,temp4Text_2,temp4Text_3,temp4Text_4,temp4Text_5,temp4Text_6,temp4Text_7,
            temp4Text_8,temp4Text_9,temp4Text_10;
    TextView[] textViews={temp4Text_1,temp4Text_2,temp4Text_3,temp4Text_4,temp4Text_5,temp4Text_6,temp4Text_7,
            temp4Text_8,temp4Text_9,temp4Text_10};
//    int[] textXML={R.id.temp4Text_1,R.id.temp4Text_2,R.id.temp4Text_3,R.id.temp4Text_4,R.id.temp4Text_5,R.id.temp4Text_6,R.id.temp4Text_7,
//            R.id.temp4Text_8,R.id.temp4Text_9,R.id.temp4Text_10};
    Button button;
    LinearLayout container;
    String abPath= Environment.getExternalStorageDirectory().getPath();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        for (int i=0;i<textViews.length;i++){
//            textViews[i]=(TextView)findViewById(textXML[i]);
//        }
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
            imageViews[i].setOnClickListener(this);
        }
        container=(LinearLayout)findViewById(R.id.container);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reference1.ImageCapture(getApplicationContext(),container);
                finish();
            }
        });
    }

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

    @Override
    public void onActivityResult(int requestCode, int resultcode,Intent data) {
        if (resultcode==RESULT_OK){
            for (int i=0,j=0;i<imageViews.length&&j<imageViews.length;i++,j++){
                if (requestCode==i){
//                    String str1=data.getStringExtra("text1");
//                    String str2=data.getStringExtra("text2");
                    String str3=data.getStringExtra("filePath");
//                    textViews[i+j].setText(str1);
//                    textViews[i+j+1].setText(str2);
                    imageViews[i].setImageURI(Uri.parse(str3));
                    imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        }
    }
}