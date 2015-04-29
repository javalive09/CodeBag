package com.codebag.code.mycode.view.drawable.layer;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 用于动画过渡
 * 
 * http://developer.android.com/guide/topics/resources/drawable-resource.html#Transition
 *
 */
public class TransitionDrawable_ extends MyCode {

	public TransitionDrawable_(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void show() {
		View v = View.inflate(getActivity(), R.layout.drawable_transition, null);
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
