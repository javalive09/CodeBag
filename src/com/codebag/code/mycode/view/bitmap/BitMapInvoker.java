package com.codebag.code.mycode.view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class BitMapInvoker extends CaseListView {

	public BitMapInvoker(Context context) {
		super(context);
	}
	
	ImageView iv;

	@Entry
	public void show() {
		iv = new ImageView(getContext());
		iv.setBackgroundResource(R.drawable.card_danager_memory);
		showView(iv);
	}
	
	Bitmap temp = null;
	
	@Entry
	public void test() {
			
			
			for(int i = 0; i<5000; i++) {
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
