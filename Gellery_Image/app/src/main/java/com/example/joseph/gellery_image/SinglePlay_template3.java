package com.example.joseph.gellery_image;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class SinglePlay_template3 extends AppCompatActivity  {

    GridView gridView;
    String Path= Environment.getExternalStorageDirectory().getPath()+"/Lewi/Edit/capture";
    String[] imagePath=new String[]{Path+"/temp1capture.jpg",Path+"/temp2capture.jpg",Path+"/temp4capture.jpg",Path+"/temp3capture.jpg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_play_template3);
        gridView=(GridView)findViewById(R.id.gridView);
        SinglePlayAdapter singlePlayAdapter=new SinglePlayAdapter(this,imagePath);
        gridView.setAdapter(singlePlayAdapter);
        gridView.setFastScrollEnabled(true);



    }
}
