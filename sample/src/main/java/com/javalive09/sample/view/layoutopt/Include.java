package com.javalive09.sample.view.layoutopt;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Include extends Entry {

	/**
	 * include 主要应用场景是：减少layout的冗余。复用layout。
	 */
	public void showInclude() {
		View view = showView(R.layout.include);

		View top = view.findViewById(R.id.top);
		ImageView topImg = (ImageView) top.findViewById(R.id.iv);
		topImg.setBackgroundResource(R.drawable.card_danager_privacy);

		View center = view.findViewById(R.id.center);
		ImageView centerImg = (ImageView) center.findViewById(R.id.iv);
		centerImg.setBackgroundResource(R.drawable.card_danager_storage);
		
	}

}
