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
import java.util.concurrent.ConcurrentHashMap;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategViewholder> {

    Context ctx;
    ArrayList<CategoryModel> categList ;

    public CategoryAdapter (Context ctx , ArrayList<CategoryModel> categList){
        this.ctx = ctx;
        this.categList = categList;
        Log.d("lifecycle","obj categ adapter");
    }

    @NonNull
    @Override
    public CategViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater viewinflater = LayoutInflater.from(parent.getContext());
        View categ_recycler = viewinflater.inflate(R.layout.categ_cell,parent,false);
        CategViewholder viewholder = new CategViewholder(categ_recycler);
        Log.d("lifecycle","create categ view holder");
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategViewholder holder, int position) {
        holder.categ_name.setText(categList.get(position).getName());
        Glide.with(ctx).load(categList.get(position).getImage()).into(holder.categ_img);
        Log.d("lifecycle","bind categ view holder");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,ProdbyCategView.class);
                intent.putExtra("category",categList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("categ",holder.categ_name.getText());
                ctx.startActivity(intent);
                Log.d("lifecycle","clicked categ");
                Log.d("lifecycle",holder.categ_name.getText()+" and "+categList.get(holder.getAdapterPosition()).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categList.size();
    }

    public class CategViewholder extends RecyclerView.ViewHolder{

        TextView categ_name;
        ImageView categ_img;
        public CategViewholder(@NonNull View itemView) {
            super(itemView);
            categ_name = itemView.findViewById(R.id.categ_name);
            categ_img = itemView.findViewById(R.id.categ_img);
            Log.d("lifecycle","obj categ view holder");
        }
    }
}


