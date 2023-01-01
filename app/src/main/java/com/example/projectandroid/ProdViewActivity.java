package com.example.projectandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdViewActivity extends AppCompatActivity {
    static ArrayList<ProductModel> prodarrListSent ;
    static ArrayList<ProductModel> prodListSent ;
    ProgressDialog dialog;
    SearchView searchbtn;
    ProductAdapter productAdapter;
    RecyclerView prodrecycler;
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_recycler);
        searchbtn =findViewById(R.id.searchview);
        btn =findViewById(R.id.btncateg);
        dialog = myProgressDialog();
        dialog.show();
        prodarrListSent = new ArrayList<ProductModel>();
        prodListSent = new ArrayList<ProductModel>();
        getData();
        prodrecycler = findViewById(R.id.prod_recycler);

        CreateProdRecycler();
        String searchvalue = searchbtn.getQuery().toString();
        searchthis(searchvalue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scintent = new Intent(ProdViewActivity.this,CategoryActivity.class);
                startActivity(scintent);
            }
        });
        Log.d("lifecycle","create prodview by categ ");
    }

    public void searchthis (String searchvalue) {
        prodListSent = getDataSearch(searchvalue);
        CreatesearchProdRecycler(prodListSent);
    }
    public ArrayList<ProductModel> getDataSearch(String searchvalue){
        Call<List<ProductModel>> call = MyRetrofitclient.getInstance().getApi().getProducts();
        call.enqueue(new Callback<List<ProductModel>>() {

            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProdViewActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<ProductModel> prodlist = response.body();
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();

                    for(ProductModel prod : prodlist){
                        if (searchvalue.equals(prod.getTitle()))
                            prodListSent.add(prod);
                    }
                    Log.d("lifecycle", "getting Products");
                    productAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                Log.d("lifecycle",""+t.getMessage());
                dialog.dismiss();
            }
        });
        return prodListSent;
    }
    public void getData() {
        Call<List<ProductModel>> call = MyRetrofitclient.getInstance().getApi().getProducts();
        call.enqueue(new Callback<List<ProductModel>>() {

            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProdViewActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<ProductModel> prodlist = response.body();
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();

                    for(ProductModel prod : prodlist){
                            prodarrListSent.add(prod);
                    }
                    Log.d("lifecycle", "getting Products");
                    productAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
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

        return dialog;
    }
    private void CreateProdRecycler(){
        productAdapter = new ProductAdapter(ProdViewActivity.this,prodarrListSent);

        prodrecycler.setAdapter(productAdapter);
        prodrecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
    }
    private void CreatesearchProdRecycler(ArrayList<ProductModel> prodListSent){
        productAdapter = new ProductAdapter(ProdViewActivity.this,prodListSent);

        prodrecycler.setAdapter(productAdapter);
        prodrecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
    }
}

////    TextView txt;
//    RecyclerView prodrecycler ;
//    static ArrayList<ProductModel> productModels = new ArrayList<>();
//    ProgressDialog myProgDialog;
////    SearchView searchbtn;
//    ProductAdapter productAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.grid_recycler);
//        myProgDialog = makeprogdialog();
//        myProgDialog.show();
//        getProductsOfApi();
//        Log.d("lifecycle","got products");
//
//        productAdapter = new ProductAdapter(ProdbyCategView.this,productModels);
//
//        prodrecycler.setAdapter(productAdapter);
//        prodrecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
//    }
//    private void getProductsOfApi() {
//        Call<List<ProductModel>> call = MyRetrofitclient.getInstance().getApi().getProducts();
//        call.enqueue(new Callback<List<ProductModel>>() {
//
//            @Override
//            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(ProdbyCategView.this, "" + response.code(), Toast.LENGTH_SHORT).show();
//                } else {
//
//                    List<ProductModel> prod = response.body();
//                    for (ProductModel product : prod) {
//                        productModels.add(product);
//                    }
//                    Log.d("lifecycle", "got categ_products");
//                }
//                myProgDialog.dismiss();
//            }
//            @Override
//            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
//            }
//        });
//    }
//    private ProgressDialog makeprogdialog(){
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setTitle("Fetching Results");
//        dialog.setMessage("Loading");
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setCancelable(false);
//
//        return dialog;
//    }
//}
