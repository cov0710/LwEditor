package com.example.joseph.gellery_image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseph.gellery_image.template.MainActivity;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.urza.multipicker.FolderListActivityFragmented;
import com.urza.multipicker.MediaEntityWrapper;
import com.urza.multipicker.MultiPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditActivity_2 extends AppCompatActivity implements View.OnClickListener{
    final static String TAG = MainActivity.class.getSimpleName();
    static final int ADD_VIDEO_REQUEST = 3;
    private static final String CURRENT_VIDEO_SELECTION = "currentVideoSelection";
    private HashMap<String, List<MediaEntityWrapper>> currentVideoSelection;
    ImageView imageView;
    int count;
    LinearLayout linearLayout;
    Button button1,button2;
    String thumb;
    String[] imgArr
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
        if (currentVideoSelection == null) {
            if (savedInstanceState != null)
                currentVideoSelection = (HashMap) savedInstanceState.getSerializable(CURRENT_VIDEO_SELECTION);
            else
                currentVideoSelection = new HashMap<String, List<MediaEntityWrapper>>();
        }
        setContentView(R.layout.activity_edit_activity_2);
        imageView=(ImageView)findViewById(R.id.imageView);
        linearLayout=(LinearLayout)findViewById(R.id.scrollView1);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);




    }
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<String> list = new ArrayList<String>();
        Log.d(TAG, "Received result with code " + requestCode);
        switch (requestCode) {
            case ADD_VIDEO_REQUEST: {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle selectionInfo = data.getExtras();
                    HashMap<String, List<MediaEntityWrapper>> selection = (HashMap) selectionInfo.getSerializable(MultiPicker.SELECTION);
                    Log.d(TAG, "Received selection: " + selection);
                    currentVideoSelection = selection;
                    ArrayList<MediaMetadata> vids = new ArrayList<MediaMetadata>();
                    linearLayout.removeAllViews();
                    for(List<MediaEntityWrapper> folder : selection.values()) {
                        for(MediaEntityWrapper video : folder){
                            MediaMetadata vid = new MediaMetadata();
                            vid.setMasterId(video.getMasterId());
                            vid.setMimeType(video.getMimeType());
                            vid.setFileSize(video.getSize());
                            vid.setFilePath(video.getMasterDataPath());
                            Log.d("debug123", vid.getFilePath());
                            list.add(vid.filePath);
                            vids.add(vid);
                        }
                        String[] imgArr = new String[list.size()];
                        count=0;
                        for (int i=0;i<list.size();i++){
                            imgArr[i]=list.get(i);
                            Log.d("debug00", imgArr[i]);
                            count++;
                        }
                        for (int i=0;i<imgArr.length;i++){
                            View view;
                            LayoutInflater layoutInflater;
                            layoutInflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            view=layoutInflater.inflate(R.layout.videoframe, null);
                            ImageView imageView=(ImageView)view.findViewById(R.id.imageView4);
                            ImageView imageView1=(ImageView)view.findViewById(R.id.imageView6);
                            TextView textView=(TextView)view.findViewById(R.id.textView);
                            textView.setVisibility(View.GONE);
                            Bitmap bmp;
                            bmp= ThumbnailUtils.createVideoThumbnail(imgArr[i],MediaStore.Video.Thumbnails.MICRO_KIND);
                            BitmapFactory.Options options=new BitmapFactory.Options();
                            options.inSampleSize=4;
                            Bitmap resized=Bitmap.createScaledBitmap(bmp,300,300,true);
                            imageView.setImageBitmap(resized);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            linearLayout.addView(view);
                            if (i==0){
                                thumb=imgArr[0];
                            }
                        }
                        list.clear();
                    }
                    selection.clear();
                    Toast.makeText(this, vids.size() + " video added.", Toast.LENGTH_SHORT).show();
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
        outstate.putSerializable(CURRENT_VIDEO_SELECTION, currentVideoSelection);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                openVideoGallery();
                break;
            case R.id.button2:
                Intent intent=getIntent();
                intent.putExtra("image",thumb);
                Log.d("qaa", count + "");
                intent.putExtra("count",count);
                intent.putExtra("videos",)
                setResult(RESULT_OK, intent);
                finish();
                break;
        }

    }

    public void openVideoGallery(){

        //TODO dialog choose - capture video or choose from video-gallery
        //TODO implement option to select only one or more videos

        Log.d(TAG, "Started MultiPicker for result with requestCode: " + ADD_VIDEO_REQUEST);
        Intent intent = new Intent(this, FolderListActivityFragmented.class);
        intent.putExtra(MultiPicker.MEDIATYPE_CHOICE, MultiPicker.VIDEO_LOADER);
        Bundle currentSelection = new Bundle();
        currentSelection.putSerializable(MultiPicker.SELECTION, currentVideoSelection);
        Log.d(TAG, "to Intent - Adding selection data: " + currentVideoSelection);
        intent.putExtras(currentSelection);

        startActivityForResult(intent, ADD_VIDEO_REQUEST);
    }


}
