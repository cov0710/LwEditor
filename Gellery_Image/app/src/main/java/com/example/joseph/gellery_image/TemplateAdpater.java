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

import com.example.joseph.gellery_image.template.MainActivity;
import com.example.joseph.gellery_image.template.template_1;
import com.example.joseph.gellery_image.template.template_10;
import com.example.joseph.gellery_image.template.template_2;
import com.example.joseph.gellery_image.template.template_3;
import com.example.joseph.gellery_image.template.template_5;
import com.example.joseph.gellery_image.template.template_6;
import com.example.joseph.gellery_image.template.template_7;
import com.example.joseph.gellery_image.template.template_8;
import com.example.joseph.gellery_image.template.template_9;

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
            bmp=Bitmap.createScaledBitmap(bmp,240,240,false);
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
                    if (position==4){
                        context.startActivity(new Intent(context, template_5.class));
                    }
                    if (position==5){
                        context.startActivity(new Intent(context, template_6.class));
                    }
                    if (position==6){
                        context.startActivity(new Intent(context, template_7.class));
                    }
                    if (position==7){
                        context.startActivity(new Intent(context, template_8.class));
                    }
                    if (position==8){
                        context.startActivity(new Intent(context, template_9.class));
                    }if (position==9){
                        context.startActivity(new Intent(context, template_10.class));
                    }
                }
            });
        }
        return imageView;
    }


}
