package com.codebag.code.mycode.xui.downloading;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void animFrame() {
		View v = View.inflate(getActivity(), R.layout.anim_list, null);
		View iv = v.findViewById(R.id.anim);
		AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
		ad.start();
		showView(v);
	}

}
