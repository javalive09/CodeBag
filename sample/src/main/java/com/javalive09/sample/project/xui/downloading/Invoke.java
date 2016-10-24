package com.javalive09.sample.project.xui.downloading;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoke extends Entry {

	public void animFrame() {
		View v = View.inflate(getViewActivity(), R.layout.anim_list, null);
		View iv = v.findViewById(R.id.anim);
		AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
		ad.start();
		showView(v);
	}

}
