package com.codebag.code.mycode.view.zoomimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class ZoomView extends CaseListView {

	public ZoomView(Context context) {
		super(context);
	}
	
	@Entry()
	public void show1() {

		
		ZoomImageView view = new ZoomImageView(getContext());
		view.setImageResource(R.drawable.head);
		view.setBackgroundColor(Color.BLACK);
		
		showView(view);
	}
	
	@Entry()
	public void show2() {
		
		MyImageView view = new MyImageView(getContext(), 1000,1000);
		Bitmap bitMap = BitmapFactory.decodeResource(getResources(), R.drawable.head);
		view.setImageBitmap(bitMap);
		view.setBackgroundColor(Color.BLACK);
		
		showView(view);
	}
}
