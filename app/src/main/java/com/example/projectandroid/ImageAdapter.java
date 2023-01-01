package com.example.projectandroid;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        Context cntxt;
        List<String> ImgArrList ;
public ImageAdapter (Context cntxt , List<String> ImgArrList){
        this.cntxt = cntxt;
        this.ImgArrList = ImgArrList;
        Log.d("lifecycle","created Img adapter");
        }
@NonNull
@Override
public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View imgRecycler = inflater.inflate(R.layout.img_cell,parent,false);
        ImageViewHolder viewHolder = new ImageViewHolder(imgRecycler);
        Log.d("lifecycle","create img view holder");

        return viewHolder;
        }

@Override
public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        DrawableCrossFadeFactory factory = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        Glide.with(cntxt).load(ImgArrList.get(position))
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(holder.img);
        }

@Override
public int getItemCount() {
        return ImgArrList.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder{

    ImageView img;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.product_img);
        Log.d("lifecycle","object image viewholder");
    }
}
}
