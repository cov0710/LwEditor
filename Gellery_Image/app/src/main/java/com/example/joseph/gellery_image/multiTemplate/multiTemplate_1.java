package com.example.joseph.gellery_image.multiTemplate;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.joseph.gellery_image.EditActivity_2;
import com.example.joseph.gellery_image.EditActivity_3;
import com.example.joseph.gellery_image.R;
import com.example.joseph.gellery_image.reference.MultiTempMaker;
import com.example.joseph.gellery_image.reference.Reference1;

import java.util.ArrayList;

public class multiTemplate_1 extends AppCompatActivity implements View.OnClickListener{
    RelativeLayout mtemp1vdo;                                                               //============
    RelativeLayout mtemp1view_1,mtemp1view_2;                                    //============
    RelativeLayout[] relativeLayouts={mtemp1view_1,mtemp1view_2};       //============
    int[] viewXML={R.id.mtemp1view_1,R.id.mtemp1view_2};                    //============

    Button saveButton,playButton;
    LinearLayout container;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    String multiTemplatePath=abPath+"/Lewi/Edit/multiTemplate/multiTemplate_1";//====수정필요

    String[][] imgArr=new String[relativeLayouts.length][];
    String[] vdoArr=null;
    String[] time=new String[relativeLayouts.length];
    ArrayList<String> list=new ArrayList<String>();

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_template_1);
            mtemp1vdo =(RelativeLayout)findViewById(R.id.mtemp1vdo);//===========수정필요
            mtemp1vdo.setOnClickListener(this);                                   //===========
            for (int i=0;i<relativeLayouts.length;i++){
                relativeLayouts[i]=(RelativeLayout)findViewById(viewXML[i]);
                relativeLayouts[i].setOnClickListener(this);
            }

            saveButton=(Button)findViewById(R.id.save);
            saveButton.setOnClickListener(this);
            playButton=(Button)findViewById(R.id.play);
            playButton.setOnClickListener(this);
            container = (LinearLayout) findViewById(R.id.container);
            SharedPreferences sf=getSharedPreferences("ㄱㄱㄱ.m1", 0);
            String str=sf.getString("images_0-0","123");
            Log.d("ttt",sf.getString("images_0-1","123"));
            Log.d("ttt", sf.getString("images_0-2", "123"));
            Log.d("ttt",sf.getString("images_1-0","123"));
            Log.d("ttt", sf.getString("images_1-1", "123"));
            Log.d("ttt",sf.getString("videos_0","123"));
            Log.d("ttt",sf.getString("videos_1","123"));
            Log.d("ttt",sf.getString("count_0","123"));
            Log.d("ttt",sf.getString("count_1","123"));
            Log.d("ttt", sf.getString("thumb", "123"));

            Intent intent=getIntent();
            String stt=intent.getStringExtra("inf");
            Log.d("fff",stt);


            Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String str1=data.getStringExtra("image");
            int str2=data.getIntExtra("count",1);
            int str3=data.getIntExtra("time",1);
            String[] img=data.getStringArrayExtra("images");
            String[] vdo=data.getStringArrayExtra("videos");
            for (int i=0,j=0;i<relativeLayouts.length&&j<relativeLayouts.length;i++,j++){
                if (requestCode==i){
                    MultiTempMaker.setImages(getApplicationContext(), i, str1, str2, str3,time, img, relativeLayouts, imgArr);
                }

            }
            if (requestCode==100){
                MultiTempMaker.setVideos(getApplicationContext(),str1,str2,mtemp1vdo,vdo,list,vdoArr);//===========
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
        if (view.getId()==R.id.mtemp1vdo){                          //==============
            intent.setClass(this, EditActivity_2.class);
            startActivityForResult(intent,100);
        }

        if (view.getId()==R.id.save){
            final AlertDialog.Builder alert = new AlertDialog.Builder(multiTemplate_1.this);//=============
            alert.setTitle("알림");
            alert.setMessage("사진의 제목을 입력하세요.");
            final EditText input = new EditText(multiTemplate_1.this);//=============
            alert.setView(input);
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String value = input.getText().toString();
                    value.toString();
                    SharedPref(value, "name", value);
                    Reference1.FileMaker(multiTemplatePath + "/" + value);
                    String abc = multiTemplatePath + "/" + value;
                    Reference1.FileMaker(abc+"/thumb");
                    Reference1.thumbMaker(multiTemplate_1.this, container, abc + "/thumb");//===========
                    SharedPref(value,"thumb",abc+"/thumb");
                    for (int i = 0; i < imgArr.length; i++) {
                        Reference1.FileMaker(abc + "/image_" + i);
                        String pp = abc + "/image_" + i;
                        Reference1.FileMaker(pp+"/count/"+time[i]);
                        SharedPref(value,"count_"+i,time[i]);
                        for (int j = 0; j < imgArr[i].length; j++) {
                            Reference1.copyFile(imgArr[i][j], pp, "mtemp1img_" + j);//===============
                            SharedPref(value,"images_"+i+"-"+j,imgArr[i][j]);
                        }
                    }
                    Reference1.FileMaker(abc + "/video_" + 1);
                    String qq = abc + "/video_" + 1;

                    vdoArr=new String[list.size()];
                    for (int i = 0; i < vdoArr.length; i++) {
                        vdoArr = new String[list.size()];
                        vdoArr[i] = list.get(i);
                        try {
                            Reference1.copyFile(vdoArr[i], qq, "mtemp1vdo_" + i);//===============
                            SharedPref(value,"videos_"+i,vdoArr[i]);
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
            intent1.setClass(this, com.example.joseph.gellery_image.multiTemplatePlay.multiTemplatePlay_1.class);//==========
            intent1.putExtra("image_1", imgArr[0]);                //==================
            intent1.putExtra("image_2", imgArr[1]);                 //===================image개수를 view만큼늘려야함!!!!
            vdoArr=new String[list.size()];
            for (int i=0;i<vdoArr.length;i++){
                vdoArr[i]=list.get(i);
            }
            intent1.putExtra("video_1",vdoArr);
            intent1.putExtra("time",time);

            startActivity(intent1);
        }
        }
public void SharedPref(String fileName,String key,String value){
    SharedPreferences sf = getSharedPreferences(fileName+".m1",0);
    SharedPreferences.Editor editor=sf.edit();
    editor.putString(key,value);
    editor.commit();
}


}
