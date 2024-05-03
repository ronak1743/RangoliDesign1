package com.example.rangolidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.Random;
import java.util.SimpleTimeZone;

public class Fullview extends AppCompatActivity {

    ImageView img;

    ImageView back;

    LottieAnimationView lottieAnimationView;
    Button download;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullview);
        getWindow().setStatusBarColor(getResources().getColor(R.color.six));
        back=findViewById(R.id.back);
        img=findViewById(R.id.fullimg);
        download =findViewById(R.id.Download);

        lottieAnimationView=findViewById(R.id.downloadanimation);
        Intent intent=getIntent();
        String position= intent.getStringExtra("fullimg");

        Glide.with(this).load(position).into(img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(position);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottieAnimationView.setVisibility(View.VISIBLE);
                download.setVisibility(View.INVISIBLE);
                lottieAnimationView.playAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lottieAnimationView.cancelAnimation();

                        lottieAnimationView.setVisibility(View.GONE);
                        download.setVisibility(View.VISIBLE);

                    }
                },1500);
                downloadWallpaper(position);
            }
        });
    }

    String fullString="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    String randomString(){
        String SubString="";
        Random random=new Random();
        for(int i=0;i<4;i++){
            int x=random.nextInt(fullString.length());
            SubString+=fullString.charAt(x);
        }

        return SubString;
    }



    void share(String url){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,url);
        startActivity(intent);
    }

    void downloadWallpaper(String Url){

        try {
            File file=new File(Environment.getExternalStorageDirectory()+"/Download/"+getResources().getString(R.string.app_name));
            if(!file.exists()){
                file.mkdirs();

            }
            file =new File(file+"/"+getResources().getString(R.string.app_name)+"_"+randomString()+".png");

            DownloadManager downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri=Uri.parse(Url);
            DownloadManager.Request request=new DownloadManager.Request(uri);

            request.setTitle(getResources().getString(R.string.app_name)+"_"+uri);
            request.setDescription("Downloading");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,getResources().getString(R.string.app_name)+"/"+file.getName());
            downloadManager.enqueue(request);
        }
        catch (Exception e) {
            Toast.makeText(this, "Somthing went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}