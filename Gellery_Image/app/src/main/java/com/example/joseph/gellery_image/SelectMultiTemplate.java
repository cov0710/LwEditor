package com.example.joseph.gellery_image;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.joseph.gellery_image.multiTemplate.multiTemplate_1;
import com.example.joseph.gellery_image.multiTemplate.multiTemplate_2;
import com.example.joseph.gellery_image.multiTemplate.multiTemplate_3;
import com.example.joseph.gellery_image.multiTemplate.multiTemplate_4;
import com.example.joseph.gellery_image.multiTemplate.multiTemplate_5;

public class SelectMultiTemplate extends AppCompatActivity {
    GridView gridView;
    int[] imageIDs=new int[]{
            R.drawable.template_1,R.drawable.template_3,R.drawable.template_6,R.drawable.template_8,R.drawable.template_10
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_multi_template);
        gridView=(GridView)findViewById(R.id.gridView);
        MultiTemplateAdapter multiTemplateAdapter=new MultiTemplateAdapter(this,imageIDs);
        gridView.setAdapter(multiTemplateAdapter);
        gridView.setFastScrollEnabled(true);

    }

    class MultiTemplateAdapter extends BaseAdapter {
        Context context=null;
        int[] imageIDs=null;

        public MultiTemplateAdapter(Context context,int[] imageIDs){
            this.context=context;
            this.imageIDs=imageIDs;
        }
        @Override
        public int getCount() {
            return (null!=imageIDs)?imageIDs.length:0;
        }

        @Override
        public Object getItem(int position) {
            return (null!=imageIDs)?imageIDs[position]:0;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, final View convertView, ViewGroup parent) {
            ImageView imageView=null;
            if (null!=convertView)
                imageView=(ImageView)convertView;
            else{
                imageView=new ImageView(context);
//                imageView.setAdjustViewBounds(true);
                Glide.with(context).load(imageIDs[position]).override(200,200).into(imageView);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position==0){
                            context.startActivity(new Intent(context,multiTemplate_1.class));
                        }
                        if (position==1){
                            context.startActivity(new Intent(context,multiTemplate_2.class));
                        }
                        if (position==2){
                            context.startActivity(new Intent(context,multiTemplate_3.class));
                        }
                        if (position==3){
                            context.startActivity(new Intent(context,multiTemplate_4.class));
                        }
                        if (position==4){
                            context.startActivity(new Intent(context,multiTemplate_5.class));
                        }

                    }
                });
            }
            return imageView;
        }
    }
}
