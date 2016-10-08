package com.javalive09.sample.view.drawable.shapedrawable;


import android.view.View;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * shape使用必须关闭硬件加速
 */
public class Shape extends Entry {

	public void showLine() {
		showView(View.inflate(getActivity(), R.layout.line,null));//shape使用必须关闭硬件加速
	}
	
	public void showShape() {
		showView(View.inflate(getActivity(),R.layout.shape,null));//shape使用必须关闭硬件加速
	}
	
	
	
}
