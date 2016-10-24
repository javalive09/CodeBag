package com.javalive09.sample.function.webview.invokejs;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

@SuppressLint("JavascriptInterface") public class Invoker extends Entry {

	WebView wv = null;
	

	@SuppressLint("SetJavaScriptEnabled")
	public void showWebView() {
		wv = new WebView(getViewActivity());
		WebSettings webSetting = wv.getSettings();
		webSetting.setJavaScriptEnabled(true);
		wv.loadUrl("file:///android_asset/js_java.html");
		wv.addJavascriptInterface(new Invoke(), "android");
		wv.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				LogUtil.i(    "shouldOverrideUrlLoading()");
				return false;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				LogUtil.i(    "onPageStarted()");
				wv.addJavascriptInterface(new Invoke(), "android");
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				LogUtil.i(    "onPageFinished()");
			}
			
		});
		showView(wv);
	}
	
	public class Invoke{
		
//		@JavascriptInterface
		public int getLotteryCount() {
			LogUtil.i(    "getLotteryCount()");
			Toast.makeText(getViewActivity(), "getLotteryCount()", Toast.LENGTH_SHORT).show();
			return 0;
		}

//		@JavascriptInterface
		public void setLotteryCount(int count) {
			LogUtil.i(    "setLotteryCount() = " + count);
			Toast.makeText(getViewActivity(), "setLotteryCount() = " + count, Toast.LENGTH_SHORT).show();
		}

//		@JavascriptInterface
		public String getLotteryRecord() {
			LogUtil.i(    "getLotteryRecord()");
			Toast.makeText(getViewActivity(), "getLotteryRecord()", Toast.LENGTH_SHORT).show();
			return null;
		}

//		@JavascriptInterface
		public void showShareWindow() {
			LogUtil.i(    "showShareWindow()");
			Toast.makeText(getViewActivity(), "showShareWindow()", Toast.LENGTH_SHORT).show();
		}

//		@JavascriptInterface
		public int getDeviceId() {
			LogUtil.i(    "getDeviceId()");
			Toast.makeText(getViewActivity(), "getDeviceId()", Toast.LENGTH_SHORT).show();
			return 0;
		}
	}

}
