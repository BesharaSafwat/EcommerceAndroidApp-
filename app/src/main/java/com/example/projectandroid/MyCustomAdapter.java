package com.example.projectandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import com.example.projectandroid.Pagers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MyCustomAdapter extends PagerAdapter {
    Context ctx;

    public MyCustomAdapter(Context ctx ) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater= (LayoutInflater) ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        View view  =  inflater.inflate(R.layout.first_view, container, false);

        ImageView img = view.findViewById(R.id.img1);
        TextView txt1 = view.findViewById(R.id.txt1);
        TextView txt2 = view.findViewById(R.id.txt2);
        TextView txt3 = view.findViewById(R.id.txt3);
        ImageButton backbtn = view.findViewById(R.id.backbtn1);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,FirstScreen.class);
                ctx.startActivity(intent);
            }
        });
        switch (position){
            case 0 :
                img.setImageResource(R.drawable.image_model);
                txt1.setText("20% Discount");
                txt2.setText("New Arrival Product");
                txt3.setText("Take the chance and buy now ");
                break;
            case 1:
                img.setImageResource(R.drawable.image_model);
                txt1.setText("Take Advantage");
                txt2.setText("Of The Offer Shopping");
                txt3.setText("Take the chance and buy now ");
                break;
            case 2:
                img.setImageResource(R.drawable.image_model);
                txt1.setText("All Types Offers ");
                txt2.setText("Within Your Reach");
                txt3.setText("Take the chance and buy now ");
                break;
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return 3;
//        return Pagers.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;

    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        Pagers custompager = Pagers.values()[position];
//        return ctx.getString(custompager.getTitleResId());
//    }
}