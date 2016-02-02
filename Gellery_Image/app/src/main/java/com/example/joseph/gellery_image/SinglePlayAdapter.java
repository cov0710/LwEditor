package com.example.joseph.gellery_image;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Joseph on 2016-01-21.
 */
public class SinglePlayAdapter extends BaseAdapter{


    Context context=null;
    String[] imageIDs=null;

    public SinglePlayAdapter(Context context, String[] imageIDs){
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

            imageView=new ImageView(context);
            imageView.setAdjustViewBounds(true);
            imageView.setImageURI(Uri.parse(imageIDs[position]));
            Log.d("debug123",imageIDs[position]);

        }
        return imageView;
    }


}
