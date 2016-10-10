package com.javalive09.sample.view.drawable.shapedrawable;


import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * shape使用必须关闭硬件加速
 */
public class Shape extends Entry {

	public void showLine() {
		showView(R.layout.line);//shape使用必须关闭硬件加速
	}
	
	public void showShape() {
		showView(R.layout.shape);//shape使用必须关闭硬件加速
	}
	
}
