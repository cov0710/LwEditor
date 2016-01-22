package com.example.joseph.gellery_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView=null;
        if (null!=convertView)
            imageView=(ImageView)convertView;
        else{
            Bitmap bmp= BitmapFactory.decodeResource(context.getResources(),imageIDs[position]);
            bmp=Bitmap.createScaledBitmap(bmp,320,240,false);
            imageView=new ImageView(context);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(bmp);

            ImageClickListener imageClickListener=new ImageClickListener(context,position);
            imageView.setOnClickListener(imageClickListener);
        }
        return imageView;
    }


}
