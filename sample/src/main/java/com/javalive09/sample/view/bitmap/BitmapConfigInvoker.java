package com.javalive09.sample.view.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class BitmapConfigInvoker extends Entry {

	
	
	public void alpha_8() {
		Bitmap mBitmap = BitmapFactory.decodeResource(getViewActivity().getResources(), R.drawable.sysclear_card_anim_fan);
		mBitmap = mBitmap.copy(Config.ALPHA_8, false);
		ImageView view = new ImageView(getViewActivity());
		view.setImageBitmap(mBitmap);
		showView(view);
	}
	
	public void rgb_565() {
		Bitmap mBitmap = BitmapFactory.decodeResource(getViewActivity().getResources(), R.drawable.sysclear_card_anim_fan);
		mBitmap = mBitmap.copy(Config.RGB_565, false);
		ImageView view = new ImageView(getViewActivity());
		view.setImageBitmap(mBitmap);
		showView(view);
	}
	
	public void argb_4444() {
		Bitmap mBitmap = BitmapFactory.decodeResource(getViewActivity().getResources(), R.drawable.sysclear_card_anim_fan);
		mBitmap = mBitmap.copy(Config.ARGB_4444, false);
		ImageView view = new ImageView(getViewActivity());
		view.setImageBitmap(mBitmap);
		showView(view);
	}
	
	public void argb_8888() {
		Bitmap mBitmap = BitmapFactory.decodeResource(getViewActivity().getResources(), R.drawable.sysclear_card_anim_fan);
		mBitmap = mBitmap.copy(Config.ARGB_8888, false);
		ImageView view = new ImageView(getViewActivity());
		view.setImageBitmap(mBitmap);
		showView(view);
	}
	
}
