package com.example.projectandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProdDetailsActivity extends AppCompatActivity {

    TextView prodTitle,prodDesc,prodPrice,trash ;
    ImageView prodThumb;
    RecyclerView imgRecycler;
    ImageAdapter imageAdapter;
    ImageButton addOne,minusOne;
    TextView amount;

    boolean isSelected = false;
    boolean isSelected2 =false;
    boolean isSelected3 = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_view);
        prodTitle = findViewById(R.id.prod_title);
        prodDesc = findViewById(R.id.prod_desc);
        prodPrice = findViewById(R.id.prod_price);

        imgRecycler = findViewById(R.id.img_recycler);
//        trash = findViewById(R.id.thumb);
        amount =findViewById(R.id.txtamount);
        addOne =findViewById(R.id.btnplus);
        minusOne = findViewById(R.id.btnminus);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        prodTitle.setText(bundle.getString("Title"));
        prodDesc.setText(bundle.getString("Desc"));
        prodPrice.setText(String.valueOf(bundle.getFloat("Price"))+" $");

        String [] imgArray = bundle.getStringArray("Images");
        List<String> ImgArrList = new ArrayList<String>(Arrays.asList(imgArray));

        imageAdapter = new ImageAdapter(ProdDetailsActivity.this,ImgArrList);

        imgRecycler.setAdapter(imageAdapter);
        imgRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(bundle.getInt("prodStock")>
//                        Integer.parseInt(String.valueOf(amount.getText())))
                    amount.setText(String.valueOf(Integer.parseInt(String.valueOf(amount.getText())) + 1));
            }
        });
        minusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(String.valueOf(amount.getText()))>0)
                    amount.setText(String.valueOf(Integer.parseInt(String.valueOf(amount.getText()))-1));
            }
        });
        // code to select color from image buttons
        //private
        ImageButton img;
        ImageButton img2;
        ImageButton img3;
        // private

        final GradientDrawable unselected = new GradientDrawable();
        unselected.setShape(GradientDrawable.OVAL);
        unselected.setColor(Color.parseColor("#e91e63"));
        unselected.setSize(144, 144);

        final GradientDrawable selected = new GradientDrawable();
        selected.setShape(GradientDrawable.OVAL);
        selected.setColor(Color.parseColor("#E91E63"));
        selected.setSize(144, 144);
        selected.setStroke(15, Color.parseColor("#605F5F"));


        final GradientDrawable unselected2 = new GradientDrawable();
        unselected2.setShape(GradientDrawable.OVAL);
        unselected2.setColor(Color.parseColor("#AFA24E"));
        unselected2.setSize(144, 144);

        final GradientDrawable selected2 = new GradientDrawable();
        selected2.setShape(GradientDrawable.OVAL);
        selected2.setColor(Color.parseColor("#AFA24E"));
        selected2.setSize(144, 144);
        selected2.setStroke(15, Color.parseColor("#605F5F"));

        final GradientDrawable unselected3 = new GradientDrawable();
        unselected3.setShape(GradientDrawable.OVAL);
        unselected3.setColor(Color.parseColor("#AFA24E"));
        unselected3.setSize(144, 144);

        final GradientDrawable selected3 = new GradientDrawable();
        selected3.setShape(GradientDrawable.OVAL);
        selected3.setColor(Color.parseColor("#AFA24E"));
        selected3.setSize(144, 144);
        selected3.setStroke(15, Color.parseColor("#605F5F"));

        img = (ImageButton) findViewById(R.id.clrslct1);
        img2 = (ImageButton) findViewById(R.id.clrslct2);
        img3 = (ImageButton) findViewById(R.id.clrslct3);

        img.setBackground(unselected);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in));
                if (isSelected) {
                    isSelected = false;
                    img.setBackground(unselected);
                } else {
                    isSelected = true;
                    img.setBackground(selected);
                    img2.setBackground(unselected2);
                    img3.setBackground(unselected3);

                }
            }
        });

        img2.setBackground(unselected2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in));
                if (isSelected2) {
                    isSelected2 = false;
                    img2.setBackground(unselected2);
                } else {
                    isSelected2 = true;
                    img2.setBackground(selected2);
                    img.setBackground(unselected);
                    img3.setBackground(unselected3);

                }
            }
        });

        img3.setBackground(unselected3);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_in));
                if (isSelected3) {
                    isSelected3 = false;
                    img3.setBackground(unselected3);
                } else {
                    isSelected3 = true;
                    img3.setBackground(selected3);
                    img2.setBackground(unselected2);
                    img.setBackground(unselected);
                }
            }
        });
    }
}