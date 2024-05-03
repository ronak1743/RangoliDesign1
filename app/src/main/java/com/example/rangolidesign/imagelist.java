package com.example.rangolidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rangolidesign.Adepters.ImageListAdepter;
import com.example.rangolidesign.Models.ImagelListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class imagelist extends AppCompatActivity {
    RecyclerView imagelist;
    ImageListAdepter adepter;

    ImageView img;

    TextView text;
    List<ImagelListModel> imageListModelList;
    String position;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagelist);

        getWindow().setStatusBarColor(getResources().getColor(R.color.seven));

        imagelist=findViewById(R.id.imagelist);
        text=findViewById(R.id.statustitle);


        imageListModelList=new ArrayList<>();
        adepter=new ImageListAdepter(this,imageListModelList);

        Intent intent=getIntent();
        position=intent.getStringExtra("title");

        text.setText(position);
        img=findViewById(R.id.backimg);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imagelist.setLayoutManager(new GridLayoutManager(this,2));

        imagelist.setAdapter(adepter);

        getFireBaseData();


    }

    private void getFireBaseData() {

        FirebaseDatabase.getInstance().getReference().child(getIntent().getStringExtra("catagory")).child("imges").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imageListModelList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String img=dataSnapshot.child("img").getValue().toString();
                    imageListModelList.add(new ImagelListModel(img,dataSnapshot.getKey()));
                }
//                Toast.makeText(imagelist.this, ""+imageListModelList.size(), Toast.LENGTH_SHORT).show();
                adepter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}