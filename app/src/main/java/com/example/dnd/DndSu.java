package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DndSu extends AppCompatActivity {
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dnd_su);
        wb =findViewById(R.id.webBrowser);
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        DndWebClient wbc = new DndWebClient(this);
        wb.setWebViewClient(wbc);
        wb.loadUrl("https://dnd.su/");
    }
}