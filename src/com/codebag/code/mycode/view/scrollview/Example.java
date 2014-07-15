package com.codebag.code.mycode.view.scrollview;

import android.content.Context;
import android.webkit.WebView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Example extends CaseListView {

	public Example(Context context) {
		super(context);
	}

	@Entry()
	public void show1() {
		
		HorizontalScrollView h = new HorizontalScrollView(getContext());
		ScrollView v = new ScrollView(getContext());
		ImageView iv = new ImageView(getContext());
		iv.setImageResource(R.drawable.pic);
		
		h.addView(iv);
		v.addView(h);
		
		showView(v);
	}
	@Entry()
	public void show2() {
		
		WebView wv = new WebView(getContext());
		String url = "file:///android_asset/pip.jpg";
		wv.loadUrl(url);
		showView(wv);
	}
}
