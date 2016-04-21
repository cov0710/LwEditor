package com.example.joseph.gellery_image;

import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class MyContents extends AppCompatActivity {
    GridView gridView;
    String[] thumbArr,nameArr;
    ArrayList<String> list,nameList;
    LinearLayout linearLayout;
    String multiTemplatePath= Environment.getExternalStorageDirectory().getPath()+"/Lewi/Edit/multiTemplate";
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contents);
        gridView=(GridView)findViewById(R.id.gridView);
        linearLayout=(LinearLayout)findViewById(R.id.container);
        list=new ArrayList<String>();
        nameList=new ArrayList<String>();
        File file=new File(multiTemplatePath);
        String[] multi1=file.list();

        for (int i=0;i<multi1.length;i++){
            String multi2=multi1[i];
            File multi3=new File(multiTemplatePath+"/"+multi2);
            String[] multi4=multi3.list();
            for (int j=0;j<multi4.length;j++){
                String thumb=multi3+"/"+multi4[j]+"/thumb";
                File thumbFile=new File(thumb);
                String[] lists=thumbFile.list();
                list.add(thumb+"/"+lists[0]);
                nameList.add(multi4[j]);
            }

        }
        thumbArr=new String[list.size()];
        for (int i=0;i<list.size();i++){
            thumbArr[i]=list.get(i);
        }
        nameArr=new String[nameList.size()];
        for (int i=0;i<nameList.size();i++){
            nameArr[i]=nameList.get(i);//
        }
        CustomAdapter customAdapter=new CustomAdapter(this,R.layout.low,thumbArr,nameArr);
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyContents.this, position + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                if (thumbArr[position].contains("multiTemplate_1")) {
                    intent.setClass(MyContents.this, com.example.joseph.gellery_image.multiTemplate.multiTemplate_1.class);
                    intent.putExtra("inf", nameArr[position] + ".m1");
                    Log.d("yyy",nameArr[position]+".m1");
                    startActivity(intent);
                }
                if (thumbArr[position].contains("multiTemplate_2")) {
                    intent.setClass(MyContents.this, com.example.joseph.gellery_image.multiTemplate.multiTemplate_2.class);
                    startActivity(intent);
                }
                if (thumbArr[position].contains("multiTemplate_3")) {
                    intent.setClass(MyContents.this, com.example.joseph.gellery_image.multiTemplate.multiTemplate_3.class);
                    startActivity(intent);
                }
                if (thumbArr[position].contains("multiTemplate_4")) {
                    intent.setClass(MyContents.this, com.example.joseph.gellery_image.multiTemplate.multiTemplate_4.class);
                    startActivity(intent);
                }
                if (thumbArr[position].contains("multiTemplate_5")) {
                    intent.setClass(MyContents.this, com.example.joseph.gellery_image.multiTemplate.multiTemplate_5.class);
                    startActivity(intent);
                }


            }
        });

    }
    class CustomAdapter extends BaseAdapter{
        Context context;
        int layout;
        String[] img;
        String[] name;
        LayoutInflater inflater;

        public CustomAdapter(Context context,int layout,String[] img,String[] name){
            this.context=context;
            this.layout=layout;
            this.img=img;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            viewHolder=new ViewHolder();
            if (convertView==null){
                convertView=inflater.inflate(layout,null);
                viewHolder.view1=(ImageView)convertView.findViewById(R.id.imageView2);
                viewHolder.view2=(TextView)convertView.findViewById(R.id.textView3);
                viewHolder.view2.setPadding(0,10,0,0);
                convertView.setTag(viewHolder);
                Glide.with(context).load(img[position]).override(200, 200).into(viewHolder.view1);
                viewHolder.view2.setText(nameArr[position]);


            }
            return convertView;

        }

    }
    static class ViewHolder{
        public ImageView view1;
        public TextView view2;
    }
}
