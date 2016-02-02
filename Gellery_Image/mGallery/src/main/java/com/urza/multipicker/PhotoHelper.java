package com.urza.multipicker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.DisplayMetrics;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Joseph on 2015-10-27.
 */
public class PhotoHelper {
    private static PhotoHelper current;
    public static PhotoHelper getInstance(){
        if (current==null){
            current=new PhotoHelper();
        }
        return current;
    }
    public static void freeInstance(){
        current=null;
    }
    public PhotoHelper(){
        super();
    }
    public String getNewPhotoPath(){
        Calendar c=Calendar.getInstance();
        int yy=c.get(Calendar.YEAR);
        int mm=c.get(Calendar.MONTH)+1;
        int dd=c.get(Calendar.DAY_OF_MONTH);
        int hh=c.get(Calendar.HOUR_OF_DAY);
        int mi=c.get(Calendar.MINUTE);
        int ss=c.get(Calendar.SECOND);

        String fileName=String.format("%04d-%02d-%02d %02d;02d;02d.jpg", yy, mm, dd, hh, mi, ss);
        File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (!dir.exists()){
            dir.mkdirs();
        }
        return dir.getAbsolutePath()+"/"+fileName;
    }
    public Bitmap getThumb(Activity activity,String path) {
        Bitmap bmp = null;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        int maxScale = deviceWidth;
        if (maxScale < deviceHeight) {
            maxScale = deviceHeight;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int fscale = options.outHeight;
        if (options.outWidth > options.outHeight) {
            fscale = options.outWidth;

        }
        if (maxScale < fscale) {
            int sampleSize = fscale / maxScale;
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = sampleSize;
            bmp = BitmapFactory.decodeFile(path, options2);
        } else {
            bmp = BitmapFactory.decodeFile(path);
        }
        return bmp;
    }

}
