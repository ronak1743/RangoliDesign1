package com.example.rangolidesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rangolidesign.Adepters.CatAdepter;
import com.example.rangolidesign.Models.CatModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView catagorys;
    CatAdepter adepter;
    List<CatModel>catModelList;

    TextView contect,policy;

    WebView web;
    Dialog dialog;


    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contect=findViewById(R.id.contact);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        policy=findViewById(R.id.policy);


        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                privacypolicy policy=new privacypolicy();
                policy.show(getSupportFragmentManager(),policy.getTag());
            }
        });
        contect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boromsheet bot=new boromsheet();

                bot.show(getSupportFragmentManager(),bot.getTag());
            }
        });

        getWindow().setStatusBarColor(getResources().getColor(R.color.seven));
        catagorys=findViewById(R.id.recycle);

//        catagorys.setLayoutManager(new GridLayoutManager(this,1));
        catagorys.setLayoutManager(new LinearLayoutManager(this));

        catModelList=new ArrayList<>();
        adepter=new CatAdepter(catModelList,MainActivity.this);
        catagorys.setAdapter(adepter);
        getFireBaseData();
        dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.alertdilog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Button yes=dialog.findViewById(R.id.yes);
        Button no=dialog.findViewById(R.id.no);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private void getFireBaseData() {

        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                catModelList.clear();
                for(DataSnapshot  dataSnapshot : snapshot.getChildren()){
                     String text=dataSnapshot.child("title").getValue().toString();
                     String img=dataSnapshot.child("catwall").getValue().toString();
                     String id=dataSnapshot.getKey();
                     catModelList.add(new CatModel(img,id,text));
                }
                adepter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}