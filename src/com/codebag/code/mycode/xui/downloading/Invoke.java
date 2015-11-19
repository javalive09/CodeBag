package com.codebag.code.mycode.xui.downloading;

import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void animFrame() {
		View v = View.inflate(getActivity(), R.layout.anim_list, null);
		View iv = v.findViewById(R.id.anim);
		AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
		ad.start();
		match_parent.gravity = Gravity.CENTER;
		showView(v, match_parent);
	}

}
