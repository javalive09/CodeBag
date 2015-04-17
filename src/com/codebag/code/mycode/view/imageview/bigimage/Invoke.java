package com.codebag.code.mycode.view.imageview.bigimage;

import android.webkit.WebView;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Invoke extends MyCode {

	public Invoke(MainActivity context) {
		super(context);
	}

	@Entry()
	public void showBigImage() {
		
		WebView wv = new WebView(getActivity());
		String url = "file:///android_asset/image_demo.jpg";
		wv.loadUrl(url);
		showView(wv);
	}
}
