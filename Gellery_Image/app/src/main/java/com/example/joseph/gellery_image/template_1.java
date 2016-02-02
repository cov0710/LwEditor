package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.urza.multipicker.FolderListActivityFragmented;
import com.urza.multipicker.MediaEntityWrapper;
import com.urza.multipicker.MultiPicker;
import com.urza.multipicker.PhotoHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class template_1 extends AppCompatActivity implements View.OnClickListener {
    ImageView temp1img_1, temp1img_2, temp1img_3;
    TextView temp1Text_1, temp1Text_2, temp1Text_3, temp1Text_4, temp1Text_5, temp1Text_6;
    Button button;
    LinearLayout container;
    Bitmap bmp = null;
    String usbPath = "/storage/UsbDriveA";
    String abPath = Environment.getExternalStorageDirectory().getPath();
    String filePath1 = null;
    String filePath2 = null;
    String filePath3 = null;


    final static String TAG = MainActivity.class.getSimpleName();
    // static final int ADD_PHOTO_REQUEST = 2;
    private static final String CURRENT_PHOTO_SELECTION = "currentPhotoSelection";
    private HashMap<String, List<MediaEntityWrapper>> currentPhotoSelection;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(Runtime.getRuntime().availableProcessors())
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        //.writeDebugLogs()
                .build();
        Log.d(TAG, "ImageLoaderConfig threadPoolSize: " + Runtime.getRuntime().availableProcessors());
        ImageLoader.getInstance().init(config);
        if (currentPhotoSelection == null) {
            if (savedInstanceState != null)
                currentPhotoSelection = (HashMap) savedInstanceState.getSerializable(CURRENT_PHOTO_SELECTION);
            else
                currentPhotoSelection = new HashMap<String, List<MediaEntityWrapper>>();
        }
        setContentView(R.layout.activity_template_1);
        temp1img_1 = (ImageView) findViewById(R.id.temp1img_1);
        temp1img_2 = (ImageView) findViewById(R.id.temp1img_2);
        temp1img_3 = (ImageView) findViewById(R.id.temp1img_3);
        temp1Text_1 = (TextView) findViewById(R.id.temp1Text_1);
        temp1Text_2 = (TextView) findViewById(R.id.temp4Text_2);
        temp1Text_3 = (TextView) findViewById(R.id.temp1Text_3);
        temp1Text_4 = (TextView) findViewById(R.id.temp4Text_4);
        temp1Text_5 = (TextView) findViewById(R.id.temp1Text_5);
        temp1Text_6 = (TextView) findViewById(R.id.temp4Text_6);

        temp1img_1.setOnClickListener(this);
        temp1img_2.setOnClickListener(this);
        temp1img_3.setOnClickListener(this);

        container = (LinearLayout) findViewById(R.id.container);

        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.buildDrawingCache();
                Bitmap captureView = container.getDrawingCache();
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(abPath + "/Lewi/Edit/capture/temp1capture.jpg");
                    captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                copyFile(filePath1, abPath + "/Lewi/Edit/temp/temp1", "temp1img_1.jpg");
                copyFile(filePath2, abPath + "/Lewi/Edit/temp/temp1", "temp1img_2.jpg");
                copyFile(filePath3, abPath + "/Lewi/Edit/temp/temp1", "temp1img_3.jpg");

//                String signalPath=usbPath+"/Lewi/Edit/signal/";
//                File signalFile=new File(signalPath);
//                String[] children=signalFile.list();
//                final int len=signalFile.list().length;
//                for (int i=0;i<len;i++){
//                    String filename=children[i];
//                    File f=new File(signalPath+filename);
//                    f.delete();
//                }
//                File file=new File(signalPath+"signal1");
//                file.mkdirs();
                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        Bundle currentSelection = new Bundle();
        switch (view.getId()) {
            case R.id.temp1img_1:
                openPhotoGallery(intent, currentSelection);
                startActivityForResult(intent, 100);
                break;
            case R.id.temp1img_2:
                openPhotoGallery(intent, currentSelection);
                startActivityForResult(intent, 200);
                break;
            case R.id.temp1img_3:
                openPhotoGallery(intent, currentSelection);
                startActivityForResult(intent, 300);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (resultcode == RESULT_OK) {
            if (requestCode == 100) {
                Bundle selectionInfo = data.getExtras();
                HashMap<String, List<MediaEntityWrapper>> selection = (HashMap) selectionInfo.getSerializable(MultiPicker.SELECTION);
                Log.d(TAG, "Received selection: " + selection);
                currentPhotoSelection = selection;
                ArrayList<MediaMetadata> pics = new ArrayList<MediaMetadata>();
                for (List<MediaEntityWrapper> folder : selection.values()) {
                    for (MediaEntityWrapper photo : folder) {
                        MediaMetadata pic = new MediaMetadata();
                        pic.setMasterId(photo.getMasterId());
                        pic.setMimeType(photo.getMimeType());
                        pic.setFileSize(photo.getSize());
                        pic.setFilePath(photo.getMasterDataPath());
                        Log.d("debug123", pic.getFilePath());
                        Bitmap bmp;
                        bmp = PhotoHelper.getInstance().getThumb(this, pic.getFilePath());
                        Log.d("FILE_PATH", bmp + "");
                        temp1img_1.setImageBitmap(bmp);
                        temp1img_1.setScaleType(ImageView.ScaleType.FIT_XY);
                        pics.add(pic);
                        filePath1=pic.getFilePath();
                    }
                }
                selection.clear();
                Toast.makeText(this, pics.size() + " photos added.", Toast.LENGTH_SHORT).show();
            }


        }
        if (requestCode == 200) {
            Bundle selectionInfo = data.getExtras();
            HashMap<String, List<MediaEntityWrapper>> selection = (HashMap) selectionInfo.getSerializable(MultiPicker.SELECTION);
            Log.d(TAG, "Received selection: " + selection);
            currentPhotoSelection = selection;
            ArrayList<MediaMetadata> pics = new ArrayList<MediaMetadata>();
            for (List<MediaEntityWrapper> folder : selection.values()) {
                for (MediaEntityWrapper photo : folder) {
                    MediaMetadata pic = new MediaMetadata();
                    pic.setMasterId(photo.getMasterId());
                    pic.setMimeType(photo.getMimeType());
                    pic.setFileSize(photo.getSize());
                    pic.setFilePath(photo.getMasterDataPath());
                    Log.d("debug123", pic.getFilePath());
                    Bitmap bmp;
                    bmp = PhotoHelper.getInstance().getThumb(this, pic.getFilePath());
                    Log.d("FILE_PATH", bmp + "");
                    temp1img_2.setImageBitmap(bmp);
                    temp1img_2.setScaleType(ImageView.ScaleType.FIT_XY);
                    pics.add(pic);
                    filePath2=pic.getFilePath();
                }
            }
            selection.clear();
            Toast.makeText(this, pics.size() + " photos added.", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == 300) {
            Bundle selectionInfo = data.getExtras();
            HashMap<String, List<MediaEntityWrapper>> selection = (HashMap) selectionInfo.getSerializable(MultiPicker.SELECTION);
            Log.d(TAG, "Received selection: " + selection);
            currentPhotoSelection = selection;
            ArrayList<MediaMetadata> pics = new ArrayList<MediaMetadata>();
            for (List<MediaEntityWrapper> folder : selection.values()) {
                for(MediaEntityWrapper photo : folder){
                    MediaMetadata pic = new MediaMetadata();
                    pic.setMasterId(photo.getMasterId());
                    pic.setMimeType(photo.getMimeType());
                    pic.setFileSize(photo.getSize());
                    pic.setFilePath(photo.getMasterDataPath());
                    Log.d("debug123", pic.getFilePath());
                    Bitmap bmp;
                    bmp= PhotoHelper.getInstance().getThumb(this,pic.getFilePath());
                    Log.d("FILE_PATH", bmp + "");
                    temp1img_3.setImageBitmap(bmp);
                    temp1img_3.setScaleType(ImageView.ScaleType.FIT_XY);
                    pics.add(pic);
                    filePath3=pic.getFilePath();
                }
            }
            selection.clear();
            Toast.makeText(this, pics.size() + " photos added.", Toast.LENGTH_SHORT).show();
        }
        }





    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        Log.d(TAG, "onSaveInstanceState()");
        outstate.putSerializable(CURRENT_PHOTO_SELECTION, currentPhotoSelection);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
//    public void createThumbnail(Bitmap bitmap, String strFilePath, String filename){
//        File file=new File(strFilePath);
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        File fileCacheItem=new File(strFilePath+filename);
//        OutputStream out=null;
//
//        try {
//            fileCacheItem.createNewFile();
//            out=new FileOutputStream(fileCacheItem);
//            bitmap = Bitmap.createScaledBitmap(bitmap, 1920,1080, true);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            try {
//                out.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
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
            Log.d("debug3","성공");
        }
    }
public void openPhotoGallery(Intent intent,Bundle currentSelection){
    intent.setClass(this,FolderListActivityFragmented.class);
    intent.putExtra(MultiPicker.MEDIATYPE_CHOICE, MultiPicker.IMAGE_LOADER);
    Log.d("de",123+"");
    currentSelection.putSerializable(MultiPicker.SELECTION, currentPhotoSelection);
    Log.d(TAG, "to Intent - Adding selection data: " + currentPhotoSelection);
    intent.putExtras(currentSelection);
}
}
