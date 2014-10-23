package com.bmob.im.demo.lock;


import com.bmob.im.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends Activity {
	private WebView mWebView; 
	private ProgressBar pb;
	private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(1);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_activity);
		findViewById();
		initView();
	}
	private void findViewById(){
		mWebView = (WebView) findViewById(R.id.web_view);
		pb = (ProgressBar) findViewById(R.id.pd);  
	    
	}
	private void initView(){
		pb.setMax(100);  
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);       
		mWebView.setWebChromeClient(new MyWebViewClient());
		mWebView.setWebViewClient(new WebViewClient(){
			public boolean shouldOverrideUrlLoading(WebView view, String url) {      
				pb.setVisibility(View.VISIBLE);  
	            view.loadUrl(url);       
	            return true;       
	        } 
		});
        mWebView.loadUrl("http://m.baidu.com"); 
	}
	private class MyWebViewClient extends WebChromeClient {  
	    public void onProgressChanged(WebView view, int newProgress) {  
	        pb.setProgress(newProgress);  
	        if(newProgress==100){  
	            pb.setVisibility(View.GONE);  
	        }  
	        super.onProgressChanged(view, newProgress);  
	    }  
	} 

}
