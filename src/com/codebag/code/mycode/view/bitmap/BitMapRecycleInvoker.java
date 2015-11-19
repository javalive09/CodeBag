package com.codebag.code.mycode.view.bitmap;

import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class BitMapRecycleInvoker extends MyCode {

	public BitMapRecycleInvoker(InovkedViewActivity context) {
		super(context);
	}
	
	ImageView iv;

	@Entry
	public void show() {
		iv = new ImageView(getActivity());
		iv.setBackgroundResource(R.drawable.card_danager_memory);
		showView(iv);
	}
	
}
