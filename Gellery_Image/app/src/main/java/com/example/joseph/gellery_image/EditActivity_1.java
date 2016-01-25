package com.example.joseph.gellery_image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.joseph.gellery_image.helper.FileUtils;
import com.example.joseph.gellery_image.helper.PhotoHelper;

public class EditActivity_1 extends AppCompatActivity implements View.OnClickListener{
    ImageView imageView;
    Bitmap bmp;
    EditText editText1,editText2;
    Button button;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    String original=null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity_1);
        imageView=(ImageView)findViewById(R.id.imageView);
        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        imageView.setOnClickListener(this);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("debug", bmp + "");
                Intent intent=getIntent();
                String str1=editText1.getText().toString();
                String str2=editText2.getText().toString();
                bmp = Bitmap.createScaledBitmap(bmp, 350, 350, true);
                intent.putExtra("image", bmp);
                intent.putExtra("text1", str1);
                intent.putExtra("text2", str2);
                intent.putExtra("filePath",original);
                Log.d("deee",original);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;

        if (Build.VERSION.SDK_INT<19){
            intent=new Intent(Intent.ACTION_GET_CONTENT);
        }else{
            intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        }
        switch (view.getId()) {
            case R.id.imageView:
                startActivityForResult(intent, 100);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultcode,Intent data) {
        if (resultcode == Activity.RESULT_OK) {
            Uri photoUri = data.getData();
            Log.d("GALLERY", photoUri.toString());
            String filePath = FileUtils.getPath(this, photoUri);
            Log.d("FILE_PATH", filePath);
            if (requestCode== 100) {
                Log.d("debug", filePath);
                imageView.setImageBitmap(null);
                bmp = PhotoHelper.getInstance().getThumb(this, filePath);
                imageView.setImageBitmap(bmp);
                Log.d("debugging", filePath + "");
                original=filePath;
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (bmp!=null){
            bmp.recycle();
            bmp=null;
        }
    }

}
