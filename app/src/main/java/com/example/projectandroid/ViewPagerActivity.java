package com.example.projectandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends FragmentActivity {
                //    Handler handler = new Handler(Looper.getMainLooper());
        private ViewPager viewPager ;
        MyCustomAdapter adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_pager);// Was splash_screen
            viewPager = findViewById(R.id.view_pager);
            adapter = new MyCustomAdapter(this);
            viewPager.setAdapter(adapter);
            
//            List<Fragment> list = new ArrayList<>();
//            list.add(new FirstViewFragment());
//            list.add(new SecondViewFragment());
//            list.add(new ThirdViewFragment());

//            viewPager = (ViewPager) findViewById(R.id.view_pager);
//            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                }
//                @Override
//                public void onPageSelected(int position) {
//                }
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                }
//            });
//            viewPager.setu
//            viewPager.setOffscreenPageLimit(3);
//            viewPager.setAdapter(new MyCustomAdapter(ViewPagerActivity.this));

        }
}
