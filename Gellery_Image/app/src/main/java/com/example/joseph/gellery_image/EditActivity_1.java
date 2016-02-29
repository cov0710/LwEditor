package com.example.joseph.gellery_image;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class EditActivity_1 extends AppCompatActivity implements View.OnClickListener{

    final static String TAG = MainActivity.class.getSimpleName();
    private static final String CURRENT_PHOTO_SELECTION = "currentPhotoSelection";
    private HashMap<String, List<MediaEntityWrapper>> currentPhotoSelection;
    String filePath=null;

    ImageView imageView;
    EditText editText1,editText2;
    Button button;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_edit_activity_1);
        imageView=(ImageView)findViewById(R.id.imageView);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        imageView.setOnClickListener(this);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        Bundle currentSelection = new Bundle();
        switch (view.getId()) {
            case R.id.imageView:
                openPhotoGallery(intent,currentSelection);
                startActivityForResult(intent,100);
                break;
            case R.id.button:
                Intent intent1=getIntent();
                String str1=editText1.getText().toString();
                String str2=editText2.getText().toString();
                intent1.putExtra("text1", str1);
                intent1.putExtra("text2", str2);
                intent1.putExtra("filePath", filePath);
                setResult(RESULT_OK, intent1);
                finish();
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
                        imageView.setImageBitmap(bmp);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        pics.add(pic);
                        filePath = pic.getFilePath();
                    }
                }
                selection.clear();
                Toast.makeText(this, pics.size() + " photos added.", Toast.LENGTH_SHORT).show();
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

    public void openPhotoGallery(Intent intent,Bundle currentSelection){
        intent.setClass(this,FolderListActivityFragmented.class);
        intent.putExtra(MultiPicker.MEDIATYPE_CHOICE, MultiPicker.IMAGE_LOADER);
        Log.d("de",123+"");
        currentSelection.putSerializable(MultiPicker.SELECTION, currentPhotoSelection);
        Log.d(TAG, "to Intent - Adding selection data: " + currentPhotoSelection);
        intent.putExtras(currentSelection);
    }
}
