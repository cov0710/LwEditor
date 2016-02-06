package com.example.joseph.gellery_image.reference;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Joseph on 2016-01-22.
 */
public class Reference1 {

     public static int refTemp1;

     public static  void copyFile(String copyPath,String pastePath,String fileName){
          File copyFile=new File(copyPath);
          File pasteFile=new File(pastePath+"/"+fileName);
          Log.d("debug1", copyFile + "");
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
               Log.d("debug3","성공");
          }
     }


     public static void BoxMaker(String Path){

     }





}
