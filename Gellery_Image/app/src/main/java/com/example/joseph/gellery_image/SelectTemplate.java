package com.example.joseph.gellery_image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class SelectTemplate extends AppCompatActivity {
    GridView gridView;
    int[] imageIDs=new int[]{
            R.drawable.template_1,R.drawable.template_2,R.drawable.template_3,R.drawable.template_4,R.drawable.template_5,R.drawable.template_6,
            R.drawable.template_7,R.drawable.template_8,R.drawable.template_9,R.drawable.template_10
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
