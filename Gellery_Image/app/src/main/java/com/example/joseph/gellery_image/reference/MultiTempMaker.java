package com.example.joseph.gellery_image.reference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseph.gellery_image.R;

import java.util.ArrayList;

/**
 * Created by Joseph on 2016-03-16.
 */
public class MultiTempMaker {
    public static void InfoGet(Context context,String thumb,int count,int time,String[] images,int requestCode,ImageView[] imageViews,String[][] imgArr,
                               RelativeLayout[] relativeLayouts,RelativeLayout mtempVdo,String[] videos,ArrayList<String> list,String[] vdoArr){
        View view;
        LayoutInflater layoutInflater;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.videoframe, null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView4);
        ImageView imageView1=(ImageView)view.findViewById(R.id.imageView6);
        TextView textView=(TextView)view.findViewById(R.id.textView);
        String str1=thumb;
        int str2=count;
        textView.setText(str2+"");
        textView.setTextSize(25);
        for (int i=0,j=0;i<imageViews.length&&j<imageViews.length;i++,j++){
            if (requestCode==i){
                imageView1.setVisibility(View.GONE);
                Bitmap bmp= BitmapFactory.decodeFile(str1);
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inSampleSize=4;
                Bitmap resized=Bitmap.createScaledBitmap(bmp, 300, 300, true);
                imageView.setImageBitmap(resized);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                relativeLayouts[i].addView(view);
                int str3=time;
                String[] img=images;
                imgArr[i]=img;

            }
        }
        if (requestCode==100){

            Toast.makeText(context, str2+"", Toast.LENGTH_SHORT).show();
            Bitmap bmp;
            bmp= ThumbnailUtils.createVideoThumbnail(str1, MediaStore.Video.Thumbnails.MICRO_KIND);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=4;
            Bitmap resized=Bitmap.createScaledBitmap(bmp, 300, 300, true);
            imageView.setImageBitmap(resized);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mtempVdo.addView(view);
            String[] vdo=videos;
            for (int i=0;i<vdo.length;i++){
                list.add(vdo[i]);
                vdoArr=new String[list.size()];
                vdoArr[i]=list.get(i);
            }
        }
    }
}
