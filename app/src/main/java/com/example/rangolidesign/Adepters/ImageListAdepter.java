package com.example.rangolidesign.Adepters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.rangolidesign.Fullview;
import com.example.rangolidesign.Models.ImagelListModel;
import com.example.rangolidesign.R;

import java.util.List;

public class ImageListAdepter extends RecyclerView.Adapter<ImageListAdepter.MyView> {
    Context context;
    List<ImagelListModel>imagelListModelList;

    public ImageListAdepter(Context context, List<ImagelListModel> imagelListModelList) {
        this.context = context;
        this.imagelListModelList = imagelListModelList;
    }

    @NonNull
    @Override
    public ImageListAdepter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.imglist,parent,false);

        return new MyView(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        ImagelListModel model=imagelListModelList.get(position);
//        LottieAnimationView animationView=holder.itemView.findViewById(R.id.loader);
//
//        holder.animationView.setVisibility(View.VISIBLE);
//        holder.animationView.playAnimation();
        Glide
                .with(context)
                .load(model.getImg())
                .placeholder(R.raw.imgload)
                .centerCrop()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        holder.animationView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        holder.animationView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Fullview.class);
                intent.putExtra("fullimg",model.getImg());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagelListModelList.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        ImageView img;
        LottieAnimationView animationView;
        public MyView(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.listofimg);
            animationView=itemView.findViewById(R.id.loader);
        }
    }
}
