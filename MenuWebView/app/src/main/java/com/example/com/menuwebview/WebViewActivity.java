package com.example.com.menuwebview;

/**
 * Created by Karunakaran on 3/25/2017.
 */
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://www.google.com");
        webView.loadUrl("file:///android_asset/menu.html");
    }

}
