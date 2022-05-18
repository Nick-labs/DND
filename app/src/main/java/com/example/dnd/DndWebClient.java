package com.example.dnd;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DndWebClient extends WebViewClient {
    public DndWebClient(DndSu dndSu) {
    }
    public DndWebClient(DndСlub dndClub) {
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        return false;
    }
}
