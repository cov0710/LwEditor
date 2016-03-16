package com.example.joseph.gellery_image.reference;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Joseph on 2016-01-22.
 */
public class Reference1 {

     public static int refTemp1;

     //===================파일 복사========================
     public static  void copyFile(String copyPath,String pastePath,String fileName){
          File copyFile=new File(copyPath);
          File pasteFile=new File(pastePath+"/"+fileName);
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
          }
     }

     //================해당 경로에 폴더 없으면 폴더 생성================
     public static void FileMaker(String Path){
          File file=new File(Path);
          if (!file.exists()){
               file.mkdirs();
          }
     }

     //=============LwEditor Template 편집 전용 경로 캡쳐=================
public static void ImageCapture(Context context,View container){
     String path= Environment.getExternalStorageDirectory().getPath()+"/Lewi/Edit/LwTemp";
     container.buildDrawingCache();
     Bitmap captureView=container.getDrawingCache();
     FileOutputStream fos;
     SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
     Date currentTime=new Date();
     String mTime=mSimpleDateFormat.format(currentTime);
     try {
          fos=new FileOutputStream(path+"/temp_"+mTime+".png");
          captureView.compress(Bitmap.CompressFormat.PNG,100,fos);
     }catch (FileNotFoundException e){
          e.printStackTrace();
     }
     context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+path + "/temp_" + mTime + ".png")));
     Toast.makeText(context,"저장 완료",Toast.LENGTH_SHORT).show();
}




}
