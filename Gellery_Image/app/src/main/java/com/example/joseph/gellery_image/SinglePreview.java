package com.example.joseph.gellery_image;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SinglePreview extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView;
    Button exitButton;
    int mCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_preview);
        exitButton=(Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);
        imageView=(ImageView)findViewById(R.id.playing);
        Intent intent=getIntent();

        final String[] images=intent.getStringArrayExtra("images");
        int count=intent.getIntExtra("count",1);
        Log.d("xxxx",count+"");
        for (int i=0;i<images.length;i++){
            Log.d("fdf",images[i]);
        }
     Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageURI(Uri.parse(images[mCount]));
                        mCount++;
                        if (mCount==images.length){
                            mCount=0;
                        }
                    }
                });
            }
        };
timer.schedule(timerTask,0,count*1000);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.exitButton){

            finish();
        }
    }
}
