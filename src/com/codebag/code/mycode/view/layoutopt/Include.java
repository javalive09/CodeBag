package com.codebag.code.mycode.view.layoutopt;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Include extends CaseListView {

	public Include(Context context) {
		super(context);
	}
	
	/**
	 * include 主要应用场景是：减少layout的冗余。复用layout。
	 */
	@Entry
	public void showInclude() {
		showView(R.layout.include);
		
		View c = getContainer();
		View top = c.findViewById(R.id.top);
		ImageView topImg = (ImageView) top.findViewById(R.id.iv);
		topImg.setBackgroundResource(R.drawable.card_danager_privacy);
		
		View center = c.findViewById(R.id.center);
		ImageView centerImg = (ImageView) center.findViewById(R.id.iv);
		centerImg.setBackgroundResource(R.drawable.card_danager_storage);
		
	}

}
