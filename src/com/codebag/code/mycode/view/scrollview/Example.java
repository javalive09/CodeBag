package com.codebag.code.mycode.view.scrollview;

import android.content.Context;
import android.webkit.WebView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Example extends MyCode {

	public Example(MainActivity context) {
		super(context);
	}

	@Entry()
	public void show1() {
		
		HorizontalScrollView h = new HorizontalScrollView(getActivity());
		ScrollView v = new ScrollView(getActivity());
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.pic);
		
		h.addView(iv);
		v.addView(h);
		
		showView(v);
	}
	@Entry()
	public void show2() {
		
		WebView wv = new WebView(getActivity());
		String url = "file:///android_asset/pip.jpg";
		wv.loadUrl(url);
		showView(wv);
	}
}
