package com.example.joseph.gellery_image.reference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.joseph.gellery_image.R;

import java.util.ArrayList;

/**
 * Created by Joseph on 2016-03-16.
 */
public class MultiTempMaker {

    public static void setImages(Context context,int requestCode,String thumb,int count,int time,String[] times,String[] images,RelativeLayout[] relativeLayouts,String[][] imgArr){
        View view;
        LayoutInflater layoutInflater;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.videoframe, null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView4);
        ImageView imageView1=(ImageView)view.findViewById(R.id.imageView6);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        String str1=thumb;
        int str2=count;
        int str3=time;
        textView.setText(str2 + "");
        imageView1.setVisibility(View.GONE);
        Bitmap bmp= BitmapFactory.decodeFile(str1);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=4;
        Bitmap resized=Bitmap.createScaledBitmap(bmp, 300, 300, true);
        imageView.setImageBitmap(resized);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        relativeLayouts[requestCode].addView(view);
        times[requestCode]= String.valueOf(time);
        String[] img=images;
        imgArr[requestCode]=img;



    }
    public static void setVideos(Context context,String thumb,int count,RelativeLayout relativeLayout,String[] getVideos,ArrayList<String> list,String[] vdoArr){
        View view;
        LayoutInflater layoutInflater;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.videoframe, null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView4);
        ImageView imageView1=(ImageView)view.findViewById(R.id.imageView6);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        String str1=thumb;
        int str2=count;
        textView.setText(str2 + "");
        Bitmap bmp;
        bmp= ThumbnailUtils.createVideoThumbnail(str1, MediaStore.Video.Thumbnails.MICRO_KIND);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=4;
        Bitmap resized=Bitmap.createScaledBitmap(bmp, 300, 300, true);
        imageView.setImageBitmap(resized);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        relativeLayout.addView(view);
        String[] vdo=getVideos;
        for (int i=0;i<vdo.length;i++){
            list.add(vdo[i]);
            vdoArr=new String[list.size()];
            vdoArr[i]=list.get(i);
            Log.d("wewe",vdoArr[i]);
        }
        Log.d("wewe",vdoArr.length+"");
    }
    
}
