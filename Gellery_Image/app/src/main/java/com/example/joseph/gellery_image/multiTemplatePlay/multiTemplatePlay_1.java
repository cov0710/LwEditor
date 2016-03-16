package com.example.joseph.gellery_image.multiTemplatePlay;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.joseph.gellery_image.R;
import com.example.joseph.gellery_image.helper.VideoHelper;

import java.util.Timer;
import java.util.TimerTask;

public class multiTemplatePlay_1 extends AppCompatActivity {
    VideoHelper videoHelper;
    ImageView mtemp1img_1,mtemp1img_2;
    ImageView[] imageViews={mtemp1img_1,mtemp1img_2};
    int[] imageXML={R.id.mtemp1img_1,R.id.mtemp1img_2};
    Button exitButton;
    int count1,count2=0;
    int videoCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_template_play_1);
        Intent intent=getIntent();
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
        }
    videoHelper=new VideoHelper(this);
        videoHelper=(VideoHelper)findViewById(R.id.videoView);

        final String[] video_1=intent.getStringArrayExtra("video_1");
        videoHelper.setVideoPath(video_1[videoCount]);
        videoHelper.start();
            videoHelper.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    videoCount++;
                    if (videoCount == video_1.length) {
                        videoCount = 0;
                    }
                    videoHelper.setVideoPath(video_1[videoCount]);
                    Log.d("ttt", "t4");
                    videoHelper.start();

                }
            });

        exitButton=(Button)findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final String[] image_1=intent.getStringArrayExtra("image_1");
        final String[] image_2=intent.getStringArrayExtra("image_2");
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageViews[0].setImageURI(Uri.parse(image_1[count1]));
//                        imageViews[1].setImageURI(Uri.parse(image_2[count2]));
                        count1++;
//                        count2++;
                        if (count1==image_1.length){
                            count1=0;
                        }
//                        if (count2==image_2.length){
//                            count2=0;
//                        }
                    }
                });
            }
        };
        Timer timer2=new Timer();
        TimerTask timerTask2=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        imageViews[0].setImageURI(Uri.parse(image_1[count1]));
                        imageViews[1].setImageURI(Uri.parse(image_2[count2]));
//                        count1++;
                        count2++;
//                        if (count1==image_1.length){
//                            count1=0;
//                        }
                        if (count2==image_2.length){
                            count2=0;
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
        timer2.schedule(timerTask2, 0, 3500);
    }
}