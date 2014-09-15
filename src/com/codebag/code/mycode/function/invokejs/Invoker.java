package com.codebag.code.mycode.function.invokejs;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

public class Invoker extends CaseListView {

	WebView wv = null;
	
	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void showWebView() {
		wv = new WebView(getContext());
		WebSettings webSetting = wv.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setSupportZoom(true);
		wv.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.addLog(this, "shouldOverrideUrlLoading()");
				return false;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				Log.addLog(this, "onPageStarted()");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				Log.addLog(this, "onPageFinished()");
				view.addJavascriptInterface(invokeAndroid, "invokeAndroid");
			}
			
		});
		
		wv.loadUrl("http://www.csdn.net/");
		showView(wv);
		wv.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				if(event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
					if(wv.canGoBack()) {
						wv.goBack();
						return true;
					}
				}
				return false;
			}
		});
	}
	
	
	
	private InvokeAndroid invokeAndroid = new InvokeAndroid() {

		@Override
		public int getLotteryCount() {
			Log.addLog(this, "getLotteryCount()");
			return 0;
		}

		@Override
		public void setLotteryCount(int count) {
			Log.addLog(this, "setLotteryCount()");
		}

		@Override
		public String getLotteryRecord() {
			Log.addLog(this, "getLotteryRecord()");
			return null;
		}

		@Override
		public void showShareWindow() {
			Log.addLog(this, "showShareWindow()");
		}

		@Override
		public int getDeviceId() {
			Log.addLog(this, "getDeviceId()");
			return 0;
		}
		
	};
	
	private interface InvokeAndroid{
		int getLotteryCount();
		void setLotteryCount(int count);
		String getLotteryRecord();
		void showShareWindow();
		int getDeviceId();
	}

}
