package com.codebag.code.mycode.view.textview.shader;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class InvokerShader extends MyCode {

	public InvokerShader(MainActivity context) {
		super(context);
	}
	
	@Entry
	public void showShader_r0() {
		TextView tv = new TextView(getActivity());
		tv.setText("123456 张瑞");
		tv.setTextSize(50);
		tv.setTextColor(Color.GRAY);
		tv.setBackgroundColor(Color.WHITE);
		tv.setShadowLayer(0f, 10, 10, Color.BLUE);//radius 是羽化半径
		tv.setGravity(Gravity.CENTER);
		showView(tv);
	}
	
	@Entry
	public void showShader_r0_5() {
		TextView tv = new TextView(getActivity());
		tv.setText("123456 张瑞");
		tv.setTextSize(50);
		tv.setTextColor(Color.GRAY);
		tv.setBackgroundColor(Color.WHITE);
		tv.setShadowLayer(0.5f, 0, 10, Color.BLUE);
		tv.setGravity(Gravity.CENTER);
		showView(tv);
	}
	
	@Entry
	public void showShader_r0_1() {
		TextView tv = new TextView(getActivity());
		tv.setText("123456 张瑞");
		tv.setTextSize(50);
		tv.setTextColor(Color.GRAY);
		tv.setBackgroundColor(Color.WHITE);
		tv.setShadowLayer(0.1f, 10, 0, Color.BLUE);
		tv.setGravity(Gravity.CENTER);
		showView(tv);
	}
	
	@Entry
	public void showShader_r1() {
		TextView tv = new TextView(getActivity());
		tv.setText("123456 张瑞");
		tv.setTextSize(50);
		tv.setTextColor(Color.GRAY);
		tv.setBackgroundColor(Color.WHITE);
		tv.setShadowLayer(1, 10, 10, Color.BLUE);
		tv.setGravity(Gravity.CENTER);
		showView(tv);
	}
	
	@Entry
	public void showShader_r5() {
		TextView tv = new TextView(getActivity());
		tv.setText("123456 张瑞");
		tv.setTextSize(50);
		tv.setTextColor(Color.GRAY);
		tv.setBackgroundColor(Color.WHITE);
		tv.setShadowLayer(5, 10, 10, Color.BLUE);
		tv.setGravity(Gravity.CENTER);
		showView(tv);
	}
	
	

}
