package com.example.hp.demo.Intents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.hp.demo.Adapters.EventsAdapter;
import com.example.hp.demo.R;

public class Source extends AppCompatActivity {

    Toolbar toolbar;
    WebView webView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);
        
        webView=findViewById(R.id.webview);
        toolbar=findViewById(R.id.sourceTool);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        String main_url=intent.getStringExtra(EventsAdapter.Source_Url);
        
        startWebView(main_url);
    }

    private void startWebView(final String main_url) {

        webView.setWebViewClient(new WebViewClient() {
           ProgressDialog progressDialog;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String main_url) {
                view.loadUrl(main_url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if(progressDialog==null) {

                    progressDialog=new ProgressDialog(Source.this);
                    progressDialog.setMessage("Loading..");
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                try {
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog=null;
                    }
                }
                catch (Exception exception) {
                    Toast.makeText(getApplicationContext(),""+exception,Toast.LENGTH_LONG).show();
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(main_url);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
