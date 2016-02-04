package com.example.joseph.gellery_image;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Joseph on 2016-01-21.
 */
public class SinglePlayAdapter extends BaseAdapter{

    String usbPath="/storage/UsbDriveA";
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
            Log.d("debug123", imageIDs[position]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"전송 완료",Toast.LENGTH_SHORT).show();

                }
            });

        }
        return imageView;
    }

    public void copyFile(String copyPath,String pastePath,String fileName){
        File copyFile=new File(copyPath);
        File pasteFile=new File(pastePath+"/"+fileName);
        Log.d("debug1",copyFile+"");
        Log.d("debug2",pasteFile+"");
        if (copyFile!=pasteFile) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(copyFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(pasteFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileChannel fcin = inputStream.getChannel();
            FileChannel fcout = outputStream.getChannel();
            long size = 0;
            try {
                size = fcin.size();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fcin.transferTo(0, size, fcout);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fcout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fcin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("debug3", "성공");
        }
    }
}
