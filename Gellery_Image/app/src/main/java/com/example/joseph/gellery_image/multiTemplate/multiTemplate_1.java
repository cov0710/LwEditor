package com.example.joseph.gellery_image.multiTemplate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.joseph.gellery_image.EditActivity_2;
import com.example.joseph.gellery_image.EditActivity_3;
import com.example.joseph.gellery_image.R;
import com.example.joseph.gellery_image.reference.MultiTempMaker;
import com.example.joseph.gellery_image.reference.Reference1;

import java.util.ArrayList;

public class multiTemplate_1 extends AppCompatActivity implements View.OnClickListener{
    ImageView mtemp1img_1,mtemp1img_2;
    ImageView[] imageViews={mtemp1img_1,mtemp1img_2};
    int[] imageXML={R.id.mtemp1img_1,R.id.mtemp1img_2};
    ImageView mtemp1vdo_1;
    Button button;
    LinearLayout container;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    String multiTemplatePath=abPath+"/Lewi/Edit/multiTemplate/multiTemplate_1";
    RelativeLayout mtemp1vdo;

    RelativeLayout mtemp1view_1,mtemp1view_2;
    RelativeLayout[] relativeLayouts={mtemp1view_1,mtemp1view_2};
    int[] viewXML={R.id.mtemp1view_1,R.id.mtemp1view_2};

   String[][] imgArr=new String[imageViews.length][];
    String[] vdoArr;
    ArrayList<String> list=new ArrayList<String>();

    Button playButton;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_template_1);
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
            imageViews[i].setOnClickListener(this);
        }
        mtemp1vdo_1=(ImageView)findViewById(R.id.mtemp1vdo_1);
        mtemp1vdo_1.setOnClickListener(this);
        container = (LinearLayout) findViewById(R.id.linearLayout);
        button = (Button) findViewById(R.id.save);
        mtemp1vdo =(RelativeLayout)findViewById(R.id.mtemp1vdo);
        for (int i=0;i<relativeLayouts.length;i++){
            relativeLayouts[i]=(RelativeLayout)findViewById(viewXML[i]);
        }

        button.setOnClickListener(this);

            playButton=(Button)findViewById(R.id.play);
           playButton.setOnClickListener(this);


    }


    @Override
    public void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (resultcode == RESULT_OK) {
            String str1=data.getStringExtra("image");
            int str2=data.getIntExtra("count",1);
            int str3=data.getIntExtra("time",1);
            String[] img=data.getStringArrayExtra("images");
            String[] vdo=data.getStringArrayExtra("videos");
            MultiTempMaker.InfoGet(getApplicationContext(),str1,str2,str3,img,requestCode,imageViews,imgArr,relativeLayouts,mtemp1vdo,vdo,list,vdoArr);

        }
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        for (int i=0;i<imageViews.length;i++){
            if (view.getId()==imageXML[i]) {
                intent.setClass(this, EditActivity_3.class);
                startActivityForResult(intent, i);
            }
        }
        if (view.getId()==R.id.mtemp1vdo_1){
            intent.setClass(this, EditActivity_2.class);
            startActivityForResult(intent,100);
        }



        if (view.getId()==R.id.save){
            Reference1.ImageCapture(multiTemplate_1.this, container);
            final AlertDialog.Builder alert = new AlertDialog.Builder(multiTemplate_1.this);
            alert.setTitle("알림");
            alert.setMessage("사진의 제목을 입력하세요.");

            // Set an EditText view to get user input
            final EditText input = new EditText(multiTemplate_1.this);
            alert.setView(input);
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    String value = input.getText().toString();
                    value.toString();
                    Reference1.FileMaker(multiTemplatePath + "/" + value);
                    String abc = multiTemplatePath + "/" + value;
                    for (int i = 0; i < imgArr.length; i++) {
                        Reference1.FileMaker(abc + "/image_" + i);
                        String pp = abc + "/image_" + i;
                        for (int j = 0; j < imgArr[i].length; j++) {
                            Reference1.copyFile(imgArr[i][j], pp, "mtemp1img_" + j);

                        }
                    }
                    Reference1.FileMaker(abc + "/video_" + 1);
                    String qq = abc + "/video_" + 1;

                    for (int i = 0; i < vdoArr.length; i++) {
                        vdoArr = new String[list.size()];
                        vdoArr[i] = list.get(i);
                        try {
                            Reference1.copyFile(vdoArr[i], qq, "mtemp1vdo_" + i);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        }
                        finish();

                    }
                }

                );
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener()

                        {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        }

                );
                alert.show();

            }
        if (view.getId()==R.id.play){

            for (int i=0;i<imgArr.length;i++){
                for (int j=0;j<imgArr[i].length;j++){
                    Log.d("ccc",imgArr[i][j]);
                }
            }
            Intent intent1=new Intent();
            intent1.setClass(this,com.example.joseph.gellery_image.multiTemplatePlay.multiTemplatePlay_1.class);
            Log.d("qwe", "Qew");
            intent1.putExtra("image_1", imgArr[0]);
            Log.d("qwe", "Qew");
            intent1.putExtra("image_2",imgArr[1]);
            vdoArr=new String[list.size()];
            for (int i=0;i<vdoArr.length;i++){
                vdoArr[i]=list.get(i);
                Log.d("rrrr",vdoArr[i]);
            }
            intent1.putExtra("video_1",vdoArr);
//            Log.d("qwe",vdoArr[0]);

            startActivity(intent1);
        }
        }
}
