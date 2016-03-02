package com.example.joseph.gellery_image.template;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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

public class template_3 extends AppCompatActivity implements View.OnClickListener {
    ImageView temp3img_1,temp3img_2,temp3img_3,temp3img_4,temp3img_5;
    ImageView[] imageViews={temp3img_1,temp3img_2,temp3img_3,temp3img_4,temp3img_5};
    int[] imageXML={R.id.temp3img_1,R.id.temp3img_2,R.id.temp3img_3,R.id.temp3img_4,R.id.temp3img_5};
    TextView temp3text_1,temp3text_2,temp3text_3,temp3text_4,temp3text_5,temp3text_6,temp3text_7,temp3text_8,temp3text_9,temp3text_10;
    TextView[] textViews={temp3text_1,temp3text_2,temp3text_3,temp3text_4,temp3text_5,temp3text_6,temp3text_7,temp3text_8,temp3text_9,temp3text_10};
    int[] textXML={R.id.temp3text_1,R.id.temp3text_2,R.id.temp3text_3,R.id.temp3text_4,R.id.temp3text_5,R.id.temp3text_6,R.id.temp3text_7,R.id.temp3text_8,R.id.temp3text_9,R.id.temp3text_10};
    LinearLayout container;
    Button button;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_3);
        for (int i=0;i<textViews.length;i++){
            textViews[i]=(TextView)findViewById(textXML[i]);
        }
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
            imageViews[i].setOnClickListener(this);
        }
        container=(LinearLayout)findViewById(R.id.container);
        button=(Button)findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Reference1.ImageCapture(getApplicationContext(), container);
                finish();
            }
        });
    }



    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        for (int i=0;i<imageViews.length;i++){
            if (view.getId()==imageXML[i]){
                intent.setClass(this,EditActivity_1.class);
                startActivityForResult(intent,i);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data) {
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
