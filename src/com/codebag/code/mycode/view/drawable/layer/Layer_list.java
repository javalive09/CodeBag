package com.codebag.code.mycode.view.drawable.layer;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * layerlist 用于图片的叠加
 * 
 * LayerDrawable.java
 * 
 * http://developer.android.com/guide/topics/resources/drawable-resource.html#LayerList
 */
public class Layer_list extends MyCode {

	public Layer_list(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void show() {
		TextView tv = new TextView(getActivity());
		tv.setText("kajd;fkaj;dfkj");
		tv.setBackgroundResource(R.drawable.layer_list);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
		showView(tv, params);
	}

}
