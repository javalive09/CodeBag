package com.javalive09.sample.view.drawable.layer;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * 用于动画过渡
 * 
 * http://developer.android.com/guide/topics/resources/drawable-resource.html#Transition
 *
 */
public class TransitionDrawable_ extends Entry {

	public void show() {
		View v = View.inflate(getViewActivity(), R.layout.drawable_transition, null);
		ImageView iv = (ImageView) v.findViewById(R.id.image);
		final TransitionDrawable trans = (TransitionDrawable) iv.getDrawable();
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				trans.reverseTransition(2000);
			}
		});
		showView(v);
	}

}
