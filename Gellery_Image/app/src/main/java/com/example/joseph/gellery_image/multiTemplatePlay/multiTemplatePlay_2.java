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

public class multiTemplatePlay_2 extends AppCompatActivity {
    VideoHelper videoHelper;
    ImageView mtemp2img_1,mtemp2img_2,mtemp2img_3,mtemp2img_4;
    ImageView[] imageViews={mtemp2img_1,mtemp2img_2,mtemp2img_3,mtemp2img_4};
    int[] imageXML={R.id.mtemp2img_1,R.id.mtemp2img_2,R.id.mtemp2img_3,R.id.mtemp2img_4};
    Button exitButton;
    int count1,count2,count3,count4=0;
    int videoCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_template_play_2);
        Intent intent=getIntent();
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
        }
        videoHelper=new VideoHelper(this);
        videoHelper=(VideoHelper)findViewById(R.id.mtemp2vdo);
        final String[] video=intent.getStringArrayExtra("video_1");
        videoHelper.setVideoPath(video[videoCount]);
        videoHelper.start();
        videoHelper.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoCount++;
                if (videoCount == video.length) {
                    videoCount = 0;
                }
                videoHelper.setVideoPath(video[videoCount]);
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
        final String[] image_3=intent.getStringArrayExtra("image_3");
        final String[] image_4=intent.getStringArrayExtra("image_4");
        for (int i=0;i<image_3.length;i++){
            Log.d("rgrg",image_3[i]);
        }
        String[] time=intent.getStringArrayExtra("time");
        for (int i=0;i<time.length;i++){
            Log.d("rgrg",time[i]);
        }
        Timer timer1=new Timer();
        TimerTask timerTask1=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageViews[0].setImageURI(Uri.parse(image_1[count1]));
                        count1++;
                        if (count1==image_1.length){
                            count1=0;
                        }
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
                        imageViews[1].setImageURI(Uri.parse(image_2[count2]));
                        count2++;
                        if (count2==image_2.length){
                            count2=0;
                        }
                    }
                });
            }
        };
        Timer timer3=new Timer();
        TimerTask timerTask3=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageViews[2].setImageURI(Uri.parse(image_3[count3]));
                        count3++;
                        if (count3==image_3.length){
                            count3=0;
                        }
                    }
                });
            }
        };
        Timer timer4=new Timer();
        TimerTask timerTask4=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageViews[3].setImageURI(Uri.parse(image_4[count4]));
                        count4++;
                        if (count4==image_4.length){
                            count4=0;
                        }
                    }
                });
            }
        };
        timer1.schedule(timerTask1,0, Long.parseLong(time[0])*1000);
        timer2.schedule(timerTask2,0, Long.parseLong(time[1])*1000);
        timer3.schedule(timerTask3,0, Long.parseLong(time[2])*1000);
        timer4.schedule(timerTask4,0, Long.parseLong(time[3])*1000);
    }
}
