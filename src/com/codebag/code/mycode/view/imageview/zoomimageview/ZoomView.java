package com.codebag.code.mycode.view.imageview.zoomimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class ZoomView extends MyCode {

	public ZoomView(MainActivity context) {
		super(context);
	}
	
	@Entry()
	public void show1() {

		
		ZoomImageView view = new ZoomImageView(getActivity());
		view.setImageResource(R.drawable.head);
		view.setBackgroundColor(Color.BLACK);
		showView(view);
	}
	
	@Entry()
	public void show2() {
		
		MyImageView view = new MyImageView(getActivity(), 1000,1000);
		Bitmap bitMap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.head);
		view.setImageBitmap(bitMap);
		view.setBackgroundColor(Color.BLACK);
		showView(view);
	}
}
