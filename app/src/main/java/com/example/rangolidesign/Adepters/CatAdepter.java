package com.example.rangolidesign.Adepters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rangolidesign.Models.CatModel;
import com.example.rangolidesign.R;
import com.example.rangolidesign.imagelist;

import java.util.List;

public class CatAdepter extends RecyclerView.Adapter<CatAdepter.Myview> {
    List<CatModel> catModelList;
    Context context;

    public CatAdepter(List<CatModel> catModelList, Context context) {
        this.catModelList = catModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CatAdepter.Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_desing,parent,false);
        return new Myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatAdepter.Myview holder, int position) {
            CatModel model=catModelList.get(position);

            holder.text.setText(model.getTitle());
        Glide
                .with(context)
                .load(model.getImageurl())
                .placeholder(R.drawable.loder)
                .centerInside()
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, imagelist.class);
                intent.putExtra("catagory",model.getId());
                intent.putExtra("title",model.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catModelList.size();
    }
    public class Myview extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;
        public Myview(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.catimage);
            text=itemView.findViewById(R.id.cattitle);

        }
    }
}
