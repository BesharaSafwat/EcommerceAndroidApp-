package com.example.projectandroid;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends FragmentActivity {
    //    Handler handler = new Handler(Looper.getMainLooper());
//    private ViewPager viewPager;
    ProgressDialog dialog ;
    RecyclerView categRecycler;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryModel> categListSent= new ArrayList<CategoryModel>();

    // test
    TextView categname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categ_recycler);// Was splash_screen
        dialog = myProgressDialog();
        dialog.show();
// test
//        categname = findViewById(R.id.categname);
        getCategories();

        categRecycler = findViewById(R.id.category_recycler);
        Log.d("lifecycle","on create categ actv");
        CreateRececlyer();
    }
    private void getCategories() {
        Call<List<CategoryModel>> call = MyRetrofitclient.getInstance().getApi().getCategories();
        call.enqueue(new Callback<List<CategoryModel>>() {

            @Override
            public void onResponse(Call<List<CategoryModel>> call, Response<List<CategoryModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(CategoryActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                }else{
                    List<CategoryModel> categories = response.body();
                    for(CategoryModel categ : categories){
                        categListSent.add(categ);
//                        categname.setText(categname.getText()+"Name: "+categ.getName()+"\n");
                    }
                    categoryAdapter.notifyDataSetChanged();
                    Log.d("lifecycle","got categ");
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<CategoryModel>> call, Throwable t) {
                Log.d("lifecycle",""+t.getMessage());
                dialog.dismiss();
            }
        });
    }
    private ProgressDialog myProgressDialog(){
        ProgressDialog dialog = new ProgressDialog(this);

        dialog.setTitle("Fetching API");
        dialog.setMessage("Loading Data");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        Log.d("lifecycle","dialog create");
        return dialog;
    }
    private void CreateRececlyer(){
        categoryAdapter = new CategoryAdapter(CategoryActivity.this,categListSent);
        categRecycler.setAdapter(categoryAdapter);
        categRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
//        List<Fragment> list = new ArrayList<>();
//        list.add(new FirstViewFragment());
//        list.add(new SecondViewFragment());
//        list.add(new ThirdViewFragment());
//
//
//        viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(new MyCustomAdapter(this));
//        pagerAdapter = new PagerAdapter() {
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                Pagers pager = Pagers.values()[position];
//                LayoutInflater inflater= LayoutInflater.from(CategoryActivity.this);
//                ViewGroup layout = (ViewGroup) inflater.inflate(pager.getLayoutResId(),container,false);
//                container.addView(layout);
//                return layout;
//            }
//
//            @Override
//            public int getCount() {
//                return list.size();
//            }
//            public Fragment getItem(int position){
//                return list.get(position);
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return false;
//            }
//        };

//        ViewPager vp= (ViewPager) findViewById(R.id.view_pager);
//
//        MyPagerAdapter pagerAdapter= new MyPagerAdapter(this.getSupportFragmentManager());
//
//
//        pagerAdapter.addFragment(new FirstViewFragment());
//        pagerAdapter.addFragment(new SecondViewFragment());
//        pagerAdapter.addFragment(new ThirdViewFragment());
//        vp.setAdapter(pagerAdapter);
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }