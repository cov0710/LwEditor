package com.example.joseph.gellery_image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.joseph.gellery_image.template.MainActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.urza.multipicker.FolderListActivityFragmented;
import com.urza.multipicker.MediaEntityWrapper;
import com.urza.multipicker.MultiPicker;
import com.urza.multipicker.PhotoHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditActivity_3 extends AppCompatActivity implements View.OnClickListener {

    final static String TAG = MainActivity.class.getSimpleName();
    static final int ADD_PHOTO_REQUEST = 2;
    private static final String CURRENT_PHOTO_SELECTION = "currentPhotoSelection";
    private HashMap<String, List<MediaEntityWrapper>> currentPhotoSelection;
    String filePath=null;
    ImageView imageView;
    int count;
    HorizontalScrollView horizontalScrollView;
    LinearLayout linearLayout;
    Button button1,button2;
    String thumb;
    RelativeLayout relativeLayout;
    String savePath= Environment.getExternalStorageDirectory().getPath()+"/Lewi/Edit/singleMedia";
    String[] imgArr=null;
    String[] imgArr1=null;
    ArrayList<String> list1 = new ArrayList<String>();



    NumberPicker numberPicker,numberPicker2;
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
        setContentView(R.layout.activity_edit_activity_3);
        imageView=(ImageView)findViewById(R.id.imageView);
        linearLayout=(LinearLayout)findViewById(R.id.scrollView);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        imageView.setOnClickListener(this);
        horizontalScrollView=(HorizontalScrollView)findViewById(R.id.horizontalScrollView);
        relativeLayout=(RelativeLayout)findViewById(R.id.container);
        relativeLayout.setOnClickListener(this);

        numberPicker=(NumberPicker)findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(59);
        numberPicker2=(NumberPicker)findViewById(R.id.numberPicker2);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(59);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> list = new ArrayList<String>();
        Log.d(TAG, "Received result with code " + requestCode);
        switch (requestCode) {
            case ADD_PHOTO_REQUEST: {

                if (resultCode == Activity.RESULT_OK) {
                    imageView.setVisibility(View.GONE);
                    Bundle selectionInfo = data.getExtras();
                    HashMap<String, List<MediaEntityWrapper>> selection = (HashMap) selectionInfo.getSerializable(MultiPicker.SELECTION);
                    Log.d(TAG, "Received selection: " + selection);
                    currentPhotoSelection = selection;
                    ArrayList<MediaMetadata> pics = new ArrayList<MediaMetadata>();
                    linearLayout.removeAllViews();
                    count=0;
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

                            list.add(pic.filePath);
                            list1.add(pic.filePath);
                            count++;
                            pics.add(pic);
                        }
                        imgArr = new String[list.size()];
                        imgArr1=new String[list1.size()];
                        for (int i=0;i<list.size();i++){
                            imgArr[i]=list.get(i);
                            imgArr1[i]=list.get(i);
                            Log.d("debug00", imgArr[i]);
                        }
                        for (int i=0;i<imgArr.length;i++){
                            ImageView qw=new ImageView(this);
                            Bitmap bmp;
                            bmp=PhotoHelper.getInstance().getThumb(this, imgArr[i]);
                            BitmapFactory.Options options=new BitmapFactory.Options();
                            options.inSampleSize=4;
                            Bitmap resized=Bitmap.createScaledBitmap(bmp,300,300,true);
                            qw.setImageBitmap(resized);
                            qw.setScaleType(ImageView.ScaleType.FIT_XY);
                            linearLayout.addView(qw);
                            if (i==0){
                                thumb=imgArr[0];
                            }
                        }
                        list.clear();
                    }
                    selection.clear();
                    Toast.makeText(this, pics.size() + " photos added.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default: {
                Log.d(TAG, "Got result from unexpected requestCode: " + requestCode);
            }
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                openPhotoGallery();
                break;
            case R.id.button2:
                Intent intent=getIntent();
                intent.putExtra("image",thumb);
                intent.putExtra("count",count);
                int i = numberPicker.getValue();
                int j = numberPicker2.getValue() * 60;
                int sum = i + j;
                intent.putExtra("time",sum);
                for (int q=0;q<imgArr.length;q++){
                    Log.d("dddd", imgArr[q]);
                }
                intent.putExtra("images",imgArr);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
    public void openPhotoGallery(){
        Log.d(TAG, "Started MultiPicker for result with requestCode: " + ADD_PHOTO_REQUEST);
        Intent intent = new Intent(this, FolderListActivityFragmented.class);
        intent.putExtra(MultiPicker.MEDIATYPE_CHOICE, MultiPicker.IMAGE_LOADER);
        Bundle currentSelection = new Bundle();
        currentSelection.putSerializable(MultiPicker.SELECTION, currentPhotoSelection);
        Log.d(TAG, "to Intent - Adding selection data: " + currentPhotoSelection);
        intent.putExtras(currentSelection);
        startActivityForResult(intent, ADD_PHOTO_REQUEST);
    }
}