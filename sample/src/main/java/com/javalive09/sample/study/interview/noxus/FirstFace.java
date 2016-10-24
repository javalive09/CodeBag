package com.javalive09.sample.study.interview.noxus;

import java.util.HashMap;

import android.graphics.Color;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

/**
 * onTouchEvent() 返回值的作用是：当前的view处理touch事件的反馈，返回true。说明已经处理。
 * 返回false，说明未处理，需要父容器处理。是一个反向流
 * 
 * @author peter
 *
 */
public class FirstFace extends Entry {

	public void putSameHashCodeObject2HashMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("1", new SameHashCodeObj(1));
		map.put("2", new SameHashCodeObj(2));
		map.put("3", new SameHashCodeObj(3));
		LogUtil.i( map.toString());
	}
	
	public void viewOnTouchReturnFalse() {
		FatherView fv = new FatherView(getViewActivity());
		fv.setBackgroundColor(Color.BLUE);
		SonViewOne sv = new SonViewOne(getViewActivity());
		sv.setBackgroundColor(Color.GREEN);
		fv.addView(sv);
		showView(fv);
	}
	
	public void viewOnTouchReturnTrue() {
		FatherView fv = new FatherView(getViewActivity());
		fv.setBackgroundColor(Color.BLUE);
		SonViewTwo sv = new SonViewTwo(getViewActivity());
		sv.setBackgroundColor(Color.GREEN);
		fv.addView(sv);
		showView(fv);
	}
	
	public void fatherViewOnTouchReturnFalse() {
		GrandFatherView gv = new GrandFatherView(getViewActivity());
		gv.setBackgroundColor(Color.GRAY);
		FatherView fv = new FatherView(getViewActivity());
		fv.setBackgroundColor(Color.BLUE);
		SonViewOne sv = new SonViewOne(getViewActivity());
		sv.setBackgroundColor(Color.GREEN);
		fv.addView(sv);
		gv.addView(fv);
		showView(gv);
	}
	
	
}
