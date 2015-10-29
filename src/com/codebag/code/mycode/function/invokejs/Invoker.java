package com.codebag.code.mycode.function.invokejs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
//import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.utils.Log;

@SuppressLint("JavascriptInterface") public class Invoker extends MyCode {

	WebView wv = null;
	
	public Invoker(InovkedViewActivity context) {
		super(context);
	}
	
	@Entry
	public void showWebView() {
		wv = new WebView(getActivity());
		WebSettings webSetting = wv.getSettings();
		webSetting.setJavaScriptEnabled(true);
		wv.loadUrl("file:///android_asset/js_java.html");
		wv.addJavascriptInterface(new Invoke(), "android");
		wv.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.addLog("peter", this, "shouldOverrideUrlLoading()");
				return false;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				Log.addLog("peter", this, "onPageStarted()");
				wv.addJavascriptInterface(new Invoke(), "android");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				Log.addLog("peter", this, "onPageFinished()");
			}
			
		});
		showView(wv);
	}
	
	public class Invoke{
		
//		@JavascriptInterface
		public int getLotteryCount() {
			Log.addLog("peter", this, "getLotteryCount()");
			Toast.makeText(getActivity(), "getLotteryCount()", Toast.LENGTH_SHORT).show();
			return 0;
		}

//		@JavascriptInterface
		public void setLotteryCount(int count) {
			Log.addLog("peter", this, "setLotteryCount() = " + count);
			Toast.makeText(getActivity(), "setLotteryCount() = " + count, Toast.LENGTH_SHORT).show();
		}

//		@JavascriptInterface
		public String getLotteryRecord() {
			Log.addLog("peter", this, "getLotteryRecord()");
			Toast.makeText(getActivity(), "getLotteryRecord()", Toast.LENGTH_SHORT).show();
			return null;
		}

//		@JavascriptInterface
		public void showShareWindow() {
			Log.addLog("peter", this, "showShareWindow()");
			Toast.makeText(getActivity(), "showShareWindow()", Toast.LENGTH_SHORT).show();
		}

//		@JavascriptInterface
		public int getDeviceId() {
			Log.addLog("peter", this, "getDeviceId()");
			Toast.makeText(getActivity(), "getDeviceId()", Toast.LENGTH_SHORT).show();
			return 0;
		}
	}

}
