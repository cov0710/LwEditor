package com.example.joseph.gellery_image.multiTemplate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.joseph.gellery_image.EditActivity_2;
import com.example.joseph.gellery_image.EditActivity_3;
import com.example.joseph.gellery_image.R;
import com.example.joseph.gellery_image.reference.MultiTempMaker;
import com.example.joseph.gellery_image.reference.Reference1;

import java.util.ArrayList;

public class multiTemplate_5 extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout mtemp5vdo;                                                               //============
    RelativeLayout mtemp5view_1,mtemp5view_2,mtemp5view_3,mtemp5view_4;                                    //============
    RelativeLayout[] relativeLayouts={mtemp5view_1,mtemp5view_2,mtemp5view_3,mtemp5view_4};       //============
    int[] viewXML={R.id.mtemp5view_1,R.id.mtemp5view_2,R.id.mtemp5view_3,R.id.mtemp5view_4};                    //============

    Button saveButton,playButton;
    LinearLayout container;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    String multiTemplatePath=abPath+"/Lewi/Edit/multiTemplate/multiTemplate_5";//====수정필요

    String[][] imgArr=new String[relativeLayouts.length][];
    String[] vdoArr=null;
    String[] time=new String[relativeLayouts.length];
    ArrayList<String> list=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_template_5);
        mtemp5vdo =(RelativeLayout)findViewById(R.id.mtemp5vdo);//===========수정필요
        mtemp5vdo.setOnClickListener(this);                                   //===========
        for (int i=0;i<relativeLayouts.length;i++){
            relativeLayouts[i]=(RelativeLayout)findViewById(viewXML[i]);
            relativeLayouts[i].setOnClickListener(this);
        }

        saveButton=(Button)findViewById(R.id.save);
        saveButton.setOnClickListener(this);
        playButton=(Button)findViewById(R.id.play);
        playButton.setOnClickListener(this);
        container = (LinearLayout) findViewById(R.id.container);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String str1=data.getStringExtra("image");
            int str2=data.getIntExtra("count",1);
            int str3=data.getIntExtra("time",1);
            String[] img=data.getStringArrayExtra("images");
            String[] vdo=data.getStringArrayExtra("videos");
            for (int i=0,j=0;i<relativeLayouts.length&&j<relativeLayouts.length;i++,j++){
                if (requestCode==i){
                    MultiTempMaker.setImages(getApplicationContext(), i, str1, str2, str3, time, img, relativeLayouts, imgArr);
                }

            }
            if (requestCode==100){
                MultiTempMaker.setVideos(getApplicationContext(),str1,str2,mtemp5vdo,vdo,list,vdoArr);//===========
            }

        }
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        for (int i=0;i<relativeLayouts.length;i++){
            if (view.getId()==viewXML[i]) {
                intent.setClass(this, EditActivity_3.class);
                startActivityForResult(intent, i);
            }
        }
        if (view.getId()==R.id.mtemp5vdo){                          //==============
            intent.setClass(this, EditActivity_2.class);
            startActivityForResult(intent,100);
        }

        if (view.getId()==R.id.save){
            final AlertDialog.Builder alert = new AlertDialog.Builder(multiTemplate_5.this);//=============
            alert.setTitle("알림");
            alert.setMessage("사진의 제목을 입력하세요.");
            final EditText input = new EditText(multiTemplate_5.this);                          //=============
            alert.setView(input);
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String value = input.getText().toString();
                            value.toString();
                            Reference1.FileMaker(multiTemplatePath + "/" + value);
                            String abc = multiTemplatePath + "/" + value;
                            Reference1.FileMaker(abc+"/thumb");
                            Reference1.thumbMaker(multiTemplate_5.this,container,abc+"/thumb");//===========
                            for (int i = 0; i < imgArr.length; i++) {
                                Reference1.FileMaker(abc + "/image_" + i);
                                String pp = abc + "/image_" + i;
                                Reference1.FileMaker(pp+"/count/"+time[i]);
                                for (int j = 0; j < imgArr[i].length; j++) {
                                    Reference1.copyFile(imgArr[i][j], pp, "mtemp5img_" + j);//===============
                                }
                            }
                            Reference1.FileMaker(abc + "/video_" + 1);
                            String qq = abc + "/video_" + 1;

                            vdoArr=new String[list.size()];
                            for (int i = 0; i < vdoArr.length; i++) {
                                vdoArr = new String[list.size()];
                                vdoArr[i] = list.get(i);
                                try {
                                    Reference1.copyFile(vdoArr[i], qq, "mtemp5vdo_" + i);//===============
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
                }
            }
            Intent intent1=new Intent();
            intent1.setClass(this, com.example.joseph.gellery_image.multiTemplatePlay.multiTemplatePlay_5.class);//==========
            intent1.putExtra("image_1", imgArr[0]);                //==================
            intent1.putExtra("image_2", imgArr[1]);                 //===================image개수를 view만큼늘려야함!!!!
            intent1.putExtra("image_3", imgArr[2]);
            intent1.putExtra("image_4", imgArr[3]);
            vdoArr=new String[list.size()];
            for (int i=0;i<vdoArr.length;i++){
                vdoArr[i]=list.get(i);
            }
            intent1.putExtra("video_1",vdoArr);
            intent1.putExtra("time",time);

            startActivity(intent1);
        }
    }
}
