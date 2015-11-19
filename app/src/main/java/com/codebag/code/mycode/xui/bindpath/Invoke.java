package com.codebag.code.mycode.xui.bindpath;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void show() {
		showView(new RectPathView(getActivity()));
	}
	
	@Entry
	public void showBtn() {
		ColorButton end = new ColorButton(getActivity()); 
		end.setRippleColor(Color.parseColor("#6096e9"), 1.0f);
		showView(end);
	}
	
	@Entry
	public void showBtnc() {
		final ButtonContainer btnc = new ButtonContainer(getActivity());
		showView(btnc);
		btnc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				btnc.anim();
			}
		});
	}
	
	@Entry
	public void showAll() {
		ButtonLineComponent bc = new ButtonLineComponent(getActivity());
		showView(bc);
	}
	
}
