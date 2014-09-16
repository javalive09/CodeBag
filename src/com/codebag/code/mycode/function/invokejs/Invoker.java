package com.codebag.code.mycode.function.invokejs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

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
		wv.loadUrl("file:///android_asset/js_java.html");
		wv.addJavascriptInterface(new Invoke(), "android");
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
				wv.addJavascriptInterface(new Invoke(), "android");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				Log.addLog(this, "onPageFinished()");
			}
			
		});
		showView(wv);
	}
	
	public class Invoke{
		
		@JavascriptInterface
		public int getLotteryCount() {
			Log.addLog(this, "getLotteryCount()");
			Toast.makeText(getContext(), "getLotteryCount()", Toast.LENGTH_SHORT).show();
			return 0;
		}

		@JavascriptInterface
		public void setLotteryCount(int count) {
			Log.addLog(this, "setLotteryCount() = " + count);
			Toast.makeText(getContext(), "setLotteryCount() = " + count, Toast.LENGTH_SHORT).show();
		}

		@JavascriptInterface
		public String getLotteryRecord() {
			Log.addLog(this, "getLotteryRecord()");
			Toast.makeText(getContext(), "getLotteryRecord()", Toast.LENGTH_SHORT).show();
			return null;
		}

		@JavascriptInterface
		public void showShareWindow() {
			Log.addLog(this, "showShareWindow()");
			Toast.makeText(getContext(), "showShareWindow()", Toast.LENGTH_SHORT).show();
		}

		@JavascriptInterface
		public int getDeviceId() {
			Log.addLog(this, "getDeviceId()");
			Toast.makeText(getContext(), "getDeviceId()", Toast.LENGTH_SHORT).show();
			return 0;
		}
	}

}
