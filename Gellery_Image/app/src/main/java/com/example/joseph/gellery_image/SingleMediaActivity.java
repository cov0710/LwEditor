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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.urza.multipicker.PhotoHelper;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Joseph on 2016-02-06.
 */
public class SingleMediaActivity extends AppCompatActivity{
    GridView gridView;
    ArrayList<String> list,list1,list2;
    String[] imgArr;
    String[] countArr;
    String[] nameArr;
    String singleMediaPath= Environment.getExternalStorageDirectory().getPath()+"/Lewi/Edit/singleMedia";
    LinearLayout linearLayout;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singlemedia);
        gridView=(GridView)findViewById(R.id.gridView);
        linearLayout=(LinearLayout)findViewById(R.id.container);
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
        Log.d("defe",list.size()+"");
//        imgArr[imgArr.length]=list.get(list.size());
        imgArr=new String[list.size()];
        countArr=new String[list1.size()];
        nameArr=new String[list2.size()];
        for (int i=0;i<list.size();i++){
            imgArr[i]=list.get(i);
            Log.d("deubg00", imgArr[i]);
            countArr[i]=list1.get(i);
            nameArr[i]=list2.get(i);
            Log.d("qqq",nameArr[i]);
        }

        final CustomAdapter customAdapter=new CustomAdapter(this,R.layout.low,imgArr,countArr,nameArr);
        gridView.setAdapter(customAdapter);
        gridView.setFastScrollEnabled(true);
        Log.d("debug123", list.size() + "/" + imgArr.length);
        for (int i=0;i<countArr.length;i++){
            Log.d("debug123",countArr[i]);
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                linearLayout.removeAllViews();
                if (position != imgArr.length-1) {
                    String imageAllPath = singleMediaPath + "/" + nameArr[position];
                    File file = new File(imageAllPath);
                    String[] imageList = file.list();
                    for (int i = 1; i < imageList.length; i++) {
                        ImageView imageView = new ImageView(SingleMediaActivity.this);
                        Bitmap bmp;
                        bmp = PhotoHelper.getInstance().getThumb(SingleMediaActivity.this, imageAllPath + "/" + imageList[i]);
                        Bitmap resized = Bitmap.createScaledBitmap(bmp, 300, 300, true);
                        imageView.setImageBitmap(resized);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        linearLayout.addView(imageView);

                    }
                } else {
                    Toast.makeText(SingleMediaActivity.this,"하나님 사랑해요",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SingleMediaActivity.this,SingleMedia.class);
                    startActivity(intent);
                }
            }
        });
        list.clear();
        list1.clear();
        list2.clear();
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
            if (convertView==null){
                if (img[position]!="add") {
                    convertView = inflater.inflate(layout, null);
                    ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
                    TextView textView = (TextView) convertView.findViewById(R.id.textView2);
                    TextView textView1 = (TextView) convertView.findViewById(R.id.textView3);
                    textView1.setText(name[position]);
                    textView.setText(count[position]);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;

                    Bitmap bmp = BitmapFactory.decodeFile(img[position], options);
                    bmp = Bitmap.createScaledBitmap(bmp, 320, 240, false);
                    imageView.setImageBitmap(bmp);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
                else {
                    convertView=inflater.inflate(R.layout.addlayout,null);
                    ImageView imageView1=(ImageView)convertView.findViewById(R.id.imageView3);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    Bitmap bmp=BitmapFactory.decodeResource(getResources(),R.drawable.ic_action_add);
                    bmp=Bitmap.createScaledBitmap(bmp,320,240,false);
                    imageView1.setImageBitmap(bmp);
                    imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
                }

            }
            return convertView;
        }
    }
}
