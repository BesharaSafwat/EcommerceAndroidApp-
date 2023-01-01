package com.example.projectandroid;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProdViewholder>{

    Context ctx ;
    ArrayList<ProductModel> prodList ;

    public ProductAdapter(Context ctx , ArrayList<ProductModel> prodList){
        this.ctx =ctx;
        this.prodList = prodList;
        Log.d("lifecycle","created prod adapter");
    }
//    public void filterList(ArrayList<ProductModel> filterlist) {
//        // below line is to add our filtered
//        // list in our course array list.
//        prodList = filterlist;
//        // below line is to notify our adapter
//        // as change in recycler view data.
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public ProdViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater viewinflater = LayoutInflater.from(parent.getContext());
        View prod_recycler = viewinflater.inflate(R.layout.prod_cell,parent,false);
        ProdViewholder vholder = new ProdViewholder(prod_recycler);
        Log.d("lifecycle","create prod view holder");
        return vholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdViewholder holder, int position) {
        holder.prodTitle.setText(prodList.get(position).getTitle());
        // get categ instead of desc
        holder.proddesc.setText(prodList.get(position).getCategory().getName());
        holder.prodPrice.setText(String.valueOf(prodList.get(position).getPrice()));
        Glide.with(ctx).load(prodList.get(position).getImages()[0]).centerCrop()
                .into(holder.prodThumb);
//        holder.prodThumb.setImageResource(prodList.get(position).getImages()[0]);
        //add rest specs
        Log.d("lifecycle","bind view holder");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, ProdDetailsActivity.class);
                intent.putExtra("Title",prodList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("Price",prodList.get(holder.getAdapterPosition()).getPrice());
                intent.putExtra("Desc",prodList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Category",prodList.get(holder.getAdapterPosition()).getCategory().getName());
                intent.putExtra("Images",prodList.get(holder.getAdapterPosition()).getImages());

                ctx.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return prodList.size();
    }

    public class ProdViewholder extends RecyclerView.ViewHolder{
        TextView prodTitle,proddesc,prodPrice;
        ImageView prodThumb;
        public ProdViewholder(@NonNull View itemView) {
            super(itemView);
            prodTitle = itemView.findViewById(R.id.product_title);
            proddesc = itemView.findViewById(R.id.product_desc);
            prodPrice = itemView.findViewById(R.id.product_price);
            prodThumb = itemView.findViewById(R.id.product_img);
            // add rest specs
        }
    }
}
