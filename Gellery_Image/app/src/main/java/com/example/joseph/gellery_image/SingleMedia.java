package com.example.joseph.gellery_image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class SingleMedia extends AppCompatActivity implements View.OnClickListener{
    final static String TAG = MainActivity.class.getSimpleName();

    static final int ADD_PHOTO_REQUEST = 2;
    static final int ADD_VIDEO_REQUEST = 3;

    private static final String CURRENT_PHOTO_SELECTION = "currentPhotoSelection";
    private static final String CURRENT_VIDEO_SELECTION = "currentVideoSelection";

    private HashMap<String, List<MediaEntityWrapper>> currentPhotoSelection;
    private HashMap<String, List<MediaEntityWrapper>> currentVideoSelection;

    ImageView imageView;
    int count=0;
    LinearLayout linearLayout;

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
        Log.d(TAG, "ImageLoaderConfig threadPoolSize: "+Runtime.getRuntime().availableProcessors());
        ImageLoader.getInstance().init(config);

        if (currentPhotoSelection == null) {
            if (savedInstanceState != null)
                currentPhotoSelection = (HashMap) savedInstanceState.getSerializable(CURRENT_PHOTO_SELECTION);
            else
                currentPhotoSelection = new HashMap<String, List<MediaEntityWrapper>>();
        }
        if (currentVideoSelection == null) {
            if (savedInstanceState != null)
                currentVideoSelection = (HashMap) savedInstanceState.getSerializable(CURRENT_VIDEO_SELECTION);
            else
                currentVideoSelection = new HashMap<String, List<MediaEntityWrapper>>();
        }
        setContentView(R.layout.activity_single_media);
        imageView=(ImageView)findViewById(R.id.imageView);
        linearLayout=(LinearLayout)findViewById(R.id.scollView);
        Button addPhoto = (Button) findViewById(R.id.submitPhotos);
        addPhoto.setOnClickListener(this);
        Button addVideo = (Button) findViewById(R.id.submitVideo);
        addVideo.setOnClickListener(this);
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
                    for(List<MediaEntityWrapper> folder : selection.values()) {
                        for(MediaEntityWrapper video : folder){
                            MediaMetadata vid = new MediaMetadata();
                            vid.setMasterId(video.getMasterId());
                            vid.setMimeType(video.getMimeType());
                            vid.setFileSize(video.getSize());
                            vid.setFilePath(video.getMasterDataPath());
                            Log.d("debug123", vid.getFilePath());
                            Bitmap bitmap;
                            bitmap= ThumbnailUtils.createVideoThumbnail(vid.getFilePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
                            imageView.setImageBitmap(bitmap);
                            vids.add(vid);
                        }
                    }

                    Toast.makeText(this, vids.size() + " video added.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case ADD_PHOTO_REQUEST: {
                if (resultCode == Activity.RESULT_OK) {
                    Bundle selectionInfo = data.getExtras();
                    HashMap<String, List<MediaEntityWrapper>> selection = (HashMap) selectionInfo.getSerializable(MultiPicker.SELECTION);
                    Log.d(TAG, "Received selection: " + selection);
                    currentPhotoSelection = selection;
                    ArrayList<MediaMetadata> pics = new ArrayList<MediaMetadata>();
                    linearLayout.removeAllViews();
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
                            count++;
                            pics.add(pic);

                        }

                        String[] imgArr = new String[list.size()];
                        for (int i=0;i<list.size();i++){
                            imgArr[i]=list.get(i);
                            Log.d("debug00", imgArr[i]);
                        }
                        for (int i=0;i<imgArr.length;i++){
                            ImageView qw=new ImageView(this);
                            Bitmap bmp;
                            bmp=PhotoHelper.getInstance().getThumb(this, imgArr[i]);
                            BitmapFactory.Options options=new BitmapFactory.Options();
                            options.inSampleSize=4;
                            //bmp=BitmapFactory.decodeFile(imgArr[i],options);
                            Bitmap resized=Bitmap.createScaledBitmap(bmp,300,300,true);
                            qw.setImageBitmap(resized);
                            qw.setScaleType(ImageView.ScaleType.FIT_XY);
                            linearLayout.addView(qw);
                        }
                        list.clear();
                        count=0;
                        Log.d("debug123", "qf");


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
        outstate.putSerializable(CURRENT_VIDEO_SELECTION, currentVideoSelection);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitPhotos:
                openPhotoGallery();
                break;
            case R.id.submitVideo:
                openVideoGallery();
                break;
            default:
                Log.d(TAG, "Unknown: " + v.getId());
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
