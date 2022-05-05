package com.example.dnd;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DndSuClient extends WebViewClient {
    public DndSuClient(DndSu dndSu) {
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        return false;
    }
}
