package com.example.joseph.gellery_image.multiTemplate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseph.gellery_image.EditActivity_2;
import com.example.joseph.gellery_image.EditActivity_3;
import com.example.joseph.gellery_image.R;
import com.example.joseph.gellery_image.reference.Reference1;

import java.util.ArrayList;

public class multiTemplate_1 extends AppCompatActivity implements View.OnClickListener{
    ImageView mtemp1img_1,mtemp1img_2;
    ImageView[] imageViews={mtemp1img_1,mtemp1img_2};
    int[] imageXML={R.id.mtemp1img_1,R.id.mtemp1img_2};
    ImageView mtemp1vdo_1;
    Button button;
    LinearLayout container;
    String abPath= Environment.getExternalStorageDirectory().getPath();
    String multiTemplatePath=abPath+"/Lewi/Edit/multiTemplate/multiTemplate_1";
    RelativeLayout mtemp1vdo;

    RelativeLayout mtemp1view_1,mtemp1view_2;
    RelativeLayout[] relativeLayouts={mtemp1view_1,mtemp1view_2};
    int[] viewXML={R.id.mtemp1view_1,R.id.mtemp1view_2};

   String[][] imgArr=new String[imageViews.length][];
    String[] vdoArr=null;
    ArrayList<String> list=new ArrayList<String>();
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_template_1);
        for (int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(imageXML[i]);
            imageViews[i].setOnClickListener(this);
        }
        mtemp1vdo_1=(ImageView)findViewById(R.id.mtemp1vdo_1);
        mtemp1vdo_1.setOnClickListener(this);
        container = (LinearLayout) findViewById(R.id.container);
        button = (Button) findViewById(R.id.save);
        mtemp1vdo =(RelativeLayout)findViewById(R.id.mtemp1vdo);
        for (int i=0;i<relativeLayouts.length;i++){
            relativeLayouts[i]=(RelativeLayout)findViewById(viewXML[i]);
        }

        button.setOnClickListener(this);

    }


    @Override
    public void onActivityResult(int requestCode, int resultcode, Intent data) {
        if (resultcode == RESULT_OK) {
            View view;
            LayoutInflater layoutInflater;
            layoutInflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.videoframe, null);
            ImageView imageView=(ImageView)view.findViewById(R.id.imageView4);
            ImageView imageView1=(ImageView)view.findViewById(R.id.imageView6);
            TextView textView=(TextView)view.findViewById(R.id.textView);
            String str1=data.getStringExtra("image");
            int str2=data.getIntExtra("count",1);
            textView.setText(str2+"");
            for (int i=0,j=0;i<imageViews.length&&j<imageViews.length;i++,j++){
                if (requestCode==i){
                    imageView1.setVisibility(View.GONE);
                    Bitmap bmp=BitmapFactory.decodeFile(str1);
                    BitmapFactory.Options options=new BitmapFactory.Options();
                    options.inSampleSize=4;
                    Bitmap resized=Bitmap.createScaledBitmap(bmp, 300, 300, true);
                    imageView.setImageBitmap(resized);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                    relativeLayouts[i].addView(view);
                    int str3=data.getIntExtra("time",1);
                    String[] img=data.getStringArrayExtra("images");
                    imgArr[i]=img;

                    Toast.makeText(this,str3+"",Toast.LENGTH_SHORT).show();
                }
            }
            if (requestCode==100){

                Toast.makeText(this, str2+"", Toast.LENGTH_SHORT).show();
                Bitmap bmp;
                bmp= ThumbnailUtils.createVideoThumbnail(str1, MediaStore.Video.Thumbnails.MICRO_KIND);
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inSampleSize=4;
                Bitmap resized=Bitmap.createScaledBitmap(bmp, 300, 300, true);
                imageView.setImageBitmap(resized);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                mtemp1vdo.addView(view);
                String[] vdo=data.getStringArrayExtra("videos");
                for (int i=0;i<vdo.length;i++){
                    list.add(vdo[i]);
                    vdoArr=new String[list.size()];
                    vdoArr[i]=list.get(i);
                    Log.d("zzzz",vdoArr[i]);
                }


            }
        }
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        for (int i=0;i<imageViews.length;i++){
            if (view.getId()==imageXML[i]) {
                intent.setClass(this, EditActivity_3.class);
                startActivityForResult(intent, i);
            }
        }
        if (view.getId()==R.id.mtemp1vdo_1){
            intent.setClass(this, EditActivity_2.class);
            startActivityForResult(intent,100);
        }



        if (view.getId()==R.id.save){
            final AlertDialog.Builder alert = new AlertDialog.Builder(multiTemplate_1.this);
            alert.setTitle("알림");
            alert.setMessage("사진의 제목을 입력하세요.");

            // Set an EditText view to get user input
            final EditText input = new EditText(multiTemplate_1.this);
            alert.setView(input);
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    String value = input.getText().toString();
                    value.toString();
                    Reference1.FileMaker(multiTemplatePath + "/" + value);
                    String abc = multiTemplatePath + "/" + value;
                    for (int i = 0; i < imgArr.length; i++) {
                        Reference1.FileMaker(abc + "/image_" + i);
                        String pp = abc + "/image_" + i;
                        for (int j = 0; j < imgArr[i].length; j++) {
                            Log.d("ffff", imgArr[i][j]);
                            Reference1.copyFile(imgArr[i][j], pp, "mtemp1img_" + j);

                        }
                    }
                    Reference1.FileMaker(abc + "/video_" + 1);
                    String qq = abc + "/video_" + 1;

                    for (int i = 0; i < vdoArr.length; i++) {
                        vdoArr = new String[list.size()];
                        vdoArr[i] = list.get(i);
                        Log.d("zzzz", vdoArr[i]);
                        try {
                            Reference1.copyFile(vdoArr[i], qq, "mtemp1vdo_" + i);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        }
                        finish();

                    }
                }

                );
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener()

                        {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        }

                );
                alert.show();

            }
        }
}
