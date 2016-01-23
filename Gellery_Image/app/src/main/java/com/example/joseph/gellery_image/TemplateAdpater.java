package com.example.joseph.gellery_image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Joseph on 2016-01-21.
 */
public class TemplateAdpater extends BaseAdapter{


    Context context=null;
    int[] imageIDs=null;

    public TemplateAdpater(Context context,int[] imageIDs){
        this.context=context;
        this.imageIDs=imageIDs;
    }


    @Override
    public int getCount() {
        return (null!=imageIDs)?imageIDs.length:0;
    }

    @Override
    public Object getItem(int position) {
        return (null!=imageIDs)?imageIDs[position]:0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView=null;
        if (null!=convertView)
            imageView=(ImageView)convertView;
        else{
            Bitmap bmp= BitmapFactory.decodeResource(context.getResources(),imageIDs[position]);
            bmp=Bitmap.createScaledBitmap(bmp,320,240,false);
            imageView=new ImageView(context);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bmp);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("debug9",position+"");
                    if (position==0){
                        context.startActivity(new Intent(context,template_1.class));
                    }
                    if (position==1){
                        context.startActivity(new Intent(context,template_2.class));
                    }
                    if (position==2){
                        context.startActivity(new Intent(context,template_3.class));
                    }
                    if (position==3){
                        context.startActivity(new Intent(context,MainActivity.class));
                    }
                }
            });
        }
        return imageView;
    }


}
