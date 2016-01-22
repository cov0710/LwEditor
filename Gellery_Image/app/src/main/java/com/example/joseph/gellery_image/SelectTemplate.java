package com.example.joseph.gellery_image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class SelectTemplate extends AppCompatActivity {
    GridView gridView;
    int[] imageIDs=new int[]{
            R.drawable.wallpaper3,R.drawable.wallpaper4,R.drawable.wallpaper5,R.drawable.wallpaper6,R.drawable.wallpaper7
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_template);
        gridView=(GridView)findViewById(R.id.gridView);
        TemplateAdpater templateAdpater=new TemplateAdpater(this,imageIDs);
        gridView.setAdapter(templateAdpater);
        gridView.setFastScrollEnabled(true);


    }
}
