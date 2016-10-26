package com.javalive09.sample.view.imageview.bigimage;

import android.webkit.WebView;

import com.javalive09.codebag.Entry;

/**
 * 使用webview 加载显示本地或网络超大图片
 */
public class Invoke extends Entry {

	public void showBigImage() {
		
		WebView wv = new WebView(getActivity());
//		String url = "file:///android_asset/image_demo.jpg";
//		String url = "file:///android_asset/world_map.jpg";
//		String url = "file:///android_asset/satelite_map.jpg";
		String url = "http://ditucdn1.mcqyy.com/uploads/allimg/130817/1-130QGG4143T.jpg";
		wv.loadUrl(url);
		showView(wv);
	}
}
