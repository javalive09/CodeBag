package com.codebag.code.mycode.view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
	
	Bitmap temp = null;
	
	@Entry
	public void test() {
		
			for(int i = 0; i<500; i++) {
				dodd();
			}
		
	}
	
	private void dodd() {
		iv.buildDrawingCache(true);
		Bitmap map = iv.getDrawingCache();
		iv.setBackgroundDrawable(new BitmapDrawable(map));
		
		iv.destroyDrawingCache();
	}

}
