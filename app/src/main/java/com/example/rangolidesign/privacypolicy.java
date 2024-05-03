package com.example.rangolidesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class privacypolicy extends BottomSheetDialogFragment {

    WebView web;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.activity_privacypolicy,container,false);

        web=view.findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://www.termsfeed.com/live/f20cfcc0-8ca2-4652-9b15-a83e2b66d244");

       return view;
    }
}