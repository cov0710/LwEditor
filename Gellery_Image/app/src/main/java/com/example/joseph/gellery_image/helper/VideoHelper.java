package com.example.joseph.gellery_image.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by Joseph on 2016-03-11.
 */
public class VideoHelper extends VideoView {
    private int mVideoWidth;
    private int mVideoHeight;
    public VideoHelper(Context context){
        super(context);

    }

    public VideoHelper(Context context,AttributeSet attrs) {
        super(context, attrs);
    }
    public VideoHelper(Context context,AttributeSet attrs,int detStyle){
        super(context,attrs,detStyle);

    }
    public void setVideoSize(int width,int height){
        mVideoHeight=width;
        mVideoHeight=height;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }



}
