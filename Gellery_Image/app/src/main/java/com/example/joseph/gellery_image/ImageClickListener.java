package com.example.joseph.gellery_image;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * Created by Joseph on 2016-01-22.
 */
public class ImageClickListener implements View.OnClickListener {
    Context context;
    int imageID;

    public ImageClickListener(Context context,int imageID){
        this.context=context;
        this.imageID=imageID;
    }
    @Override
    public void onClick(View v) {
        Log.d("debug9", imageID + "");
        Intent intent=new Intent(context,template_1.class);




    }
}
