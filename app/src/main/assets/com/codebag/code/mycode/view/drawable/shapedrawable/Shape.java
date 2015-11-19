package com.codebag.code.mycode.view.drawable.shapedrawable;


import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Shape extends MyCode {

	public Shape(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void showLine() {
		showView(R.layout.line);//shape使用必须关闭硬件加速
	}
	
	@Entry
	public void showShape() {
		showView(R.layout.shape);//shape使用必须关闭硬件加速
	}
	
	
	
}
