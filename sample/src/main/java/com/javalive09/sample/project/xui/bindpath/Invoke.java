package com.javalive09.sample.project.xui.bindpath;

import android.view.View;
import android.view.View.OnClickListener;

import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

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
	
	public void showAll() {
		ButtonLineComponent bc = new ButtonLineComponent(getActivity());
		showView(bc);
	}
	
}
