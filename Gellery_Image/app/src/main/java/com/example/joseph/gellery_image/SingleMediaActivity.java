package com.example.joseph.gellery_image;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.urza.multipicker.PhotoHelper;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Joseph on 2016-02-06.
 */
public class SingleMediaActivity extends AppCompatActivity implements View.OnClickListener{
    GridView gridView;
    ArrayList<String> list,list1,list2;
    String[] imgArr;
    String[] countArr;
    String[] nameArr;
    String singleMediaPath= Environment.getExternalStorageDirectory().getPath()+"/Lewi/Edit/singleMedia";
    LinearLayout linearLayout;
    LinearLayout preview;
    ImageButton exitButton;
    Button button;
    String[] previewImages=null;
    ArrayList<String> pre=new ArrayList<String>();

    int countValue;
    private Animation mTranslateUp;
    private Animation mTranslateDown;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlemedia);

        mTranslateUp= AnimationUtils.loadAnimation(this,R.anim.translate_right);
        mTranslateDown=AnimationUtils.loadAnimation(this,R.anim.translate_left);
        PageAnimationListener animationListener=new PageAnimationListener();
        mTranslateUp.setAnimationListener(animationListener);
        mTranslateDown.setAnimationListener(animationListener);

        gridView=(GridView)findViewById(R.id.gridView);
        linearLayout=(LinearLayout)findViewById(R.id.container);
        preview=(LinearLayout)findViewById(R.id.preview);
        exitButton=(ImageButton)findViewById(R.id.exitButton);
        button=(Button)findViewById(R.id.button4);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview.setVisibility(View.GONE);
            }
        });
        list=new ArrayList<String>();
        list1=new ArrayList<String>();
        list2=new ArrayList<String>();
        File file=new File(singleMediaPath);
        String[] children=file.list();
        for (int i=0;i<children.length;i++){
            String filename=children[i];
            File thumbFile=new File(singleMediaPath+"/"+filename);
            String[] thumb=thumbFile.list();
            list.add(singleMediaPath+"/"+filename+"/"+thumb[1]); //thumb1해주는 이유는 count폴더가 thumb[0]이기때문에!
            list1.add(String.valueOf(thumb.length-1));
            list2.add(filename);
        }
        list.add("add");
        list1.add("1");
        list2.add("1");
        for (int i=0;i<list.size();i++){
            Log.d("defe",list.get(i));
        }
        imgArr=new String[list.size()];
        countArr=new String[list1.size()];
        nameArr=new String[list2.size()];
        for (int i=0;i<list.size();i++){
            imgArr[i]=list.get(i);
            countArr[i]=list1.get(i);
            nameArr[i]=list2.get(i);
        }

        final CustomAdapter customAdapter=new CustomAdapter(this,R.layout.low,imgArr,countArr,nameArr);
        gridView.setAdapter(customAdapter);
        gridView.setFastScrollEnabled(true);
        button.setOnClickListener(this);
        Log.d("debug123", list.size() + "/" + imgArr.length);
        for (int i=0;i<countArr.length;i++){
            Log.d("debug123",countArr[i]);
    }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pre.clear();
                linearLayout.removeAllViews();
                if (position != imgArr.length - 1) {
                    preview.setVisibility(View.VISIBLE);
                    preview.startAnimation(mTranslateDown);
                    String imageAllPath = singleMediaPath + "/" + nameArr[position];
                    File file = new File(imageAllPath);
                    String[] imageList = file.list();

                    File countFile=new File(imageAllPath+"/"+imageList[0]);
                    String[] count=countFile.list();
                    countValue= Integer.parseInt(count[0]);
                    Log.d("efff",count[0]);
                    for (int q = 0; q < imageList.length; q++) {
                        Log.d("rrr", imageList[q]);
                    }
                    for (int i = 1; i < imageList.length; i++) {
                        ImageView imageView = new ImageView(SingleMediaActivity.this);
                        Bitmap bmp;
                        bmp = PhotoHelper.getInstance().getThumb(SingleMediaActivity.this, imageAllPath + "/" + imageList[i]);
                        Bitmap resized = Bitmap.createScaledBitmap(bmp, 300, 300, true);
                        imageView.setImageBitmap(resized);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        linearLayout.addView(imageView);
                        pre.add(imageAllPath + "/" + imageList[i]);

                    }
                    previewImages=new String[pre.size()];
                    for (int i=0;i<pre.size();i++){
                        previewImages[i]=pre.get(i);
                        Log.d("err",previewImages[i]);
                    }

                } else {
                    Intent intent = new Intent(SingleMediaActivity.this, SingleMedia.class);
                    startActivityForResult(intent, 100);
                }
            }
        });
        list.clear();
        list1.clear();
        list2.clear();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            if (requestCode==100){

            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button4){
        Intent intent=new Intent(this,SinglePreview.class);
        Log.d("qwe", "qwe");
//        for (int i = 0; i < previewImages.length; i++) {
//            previewImages=new String[pre.size()];
//            previewImages[i]=pre.get(i);
//            Log.d("ferf", previewImages[i]);
//        }
        intent.putExtra("images", previewImages);
            intent.putExtra("count",countValue);
            startActivity(intent);
        pre.clear();
        }
    }

    class CustomAdapter extends BaseAdapter {
        Context context;
        int layout;
        String img[];
        String count[];
        String name[];
        LayoutInflater inflater;

        public CustomAdapter(Context context,int layout,String[] img,String[] count,String[] name){
            this.context=context;
            this.layout=layout;
            this.img=img;
            this.count=count;
            this.name=name;
            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int position) {
            return img[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView==null){
                if (img[position]!="add") {
                    holder=new ViewHolder();
                    convertView = inflater.inflate(layout, null);
                    holder.view1=(ImageView)convertView.findViewById(R.id.imageView2);
                    holder.view2=(TextView)convertView.findViewById(R.id.textView3);
//                    ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
//                    TextView textView1 = (TextView) convertView.findViewById(R.id.textView3);
                    holder.view2.setText(name[position] + " (" + count[position] + ")");
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;

                    Bitmap bmp = BitmapFactory.decodeFile(img[position], options);
                    bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);
                    holder.view1.setImageBitmap(bmp);
                    holder.view1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
                else {
                    holder=new ViewHolder();
                    convertView=inflater.inflate(R.layout.low,null);
                    holder.view1=(ImageView)convertView.findViewById(R.id.imageView2);
                    holder.view2=(TextView)convertView.findViewById(R.id.textView3);
//                    ImageView imageView1=(ImageView)convertView.findViewById(R.id.imageView2);
//                    TextView textView1=(TextView)convertView.findViewById(R.id.textView3);
                    holder.view2.setVisibility(View.INVISIBLE);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bmp=BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_add);
                    bmp=Bitmap.createScaledBitmap(bmp,320,240,false);
                    holder.view1.setImageBitmap(bmp);
                    holder.view1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }

            }
            return convertView;
        }
    }
    static class ViewHolder{
        public ImageView view1;
        public TextView view2;
    }
}
class PageAnimationListener implements Animation.AnimationListener{

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}