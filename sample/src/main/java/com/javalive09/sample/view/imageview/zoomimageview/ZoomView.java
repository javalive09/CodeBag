package com.javalive09.sample.view.imageview.zoomimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class ZoomView extends Entry {

	public void show1() {

		
		ZoomImageView view = new ZoomImageView(getActivity());
		view.setImageResource(R.drawable.ic_launcher);
		view.setBackgroundColor(Color.BLACK);
		showView(view);
	}
	
	public void show2() {
		
		MyImageView view = new MyImageView(getActivity(), 1000,1000);
		Bitmap bitMap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher);
		view.setImageBitmap(bitMap);
		view.setBackgroundColor(Color.BLACK);
		showView(view);
	}
}
