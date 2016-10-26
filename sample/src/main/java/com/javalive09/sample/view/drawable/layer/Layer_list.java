package com.javalive09.sample.view.drawable.layer;

import android.widget.TextView;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * layerlist 用于图片.png的叠加
 * 
 * LayerDrawable.java
 * 
 * http://developer.android.com/guide/topics/resources/drawable-resource.html#LayerList
 */
public class Layer_list extends Entry {

	public void show() {
		TextView tv = new TextView(getActivity());
		tv.setText("kajd;fkaj;dfkj");
		tv.setBackgroundResource(R.drawable.layer_list);
		showView(tv);
	}

}
