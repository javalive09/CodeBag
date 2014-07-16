package com.codebag.code.mycode.interview.noxus;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

/**
 * onTouchEvent() 返回值的作用是：当前的view处理touch事件的反馈，返回true。说明已经处理。
 * 返回false，说明未处理，需要父容器处理。是一个反向流
 * 
 * @author peter
 *
 */
public class FirstFace extends CaseListView {

	public FirstFace(Context context) {
		super(context);
	}

	@Entry
	public void putSameHashCodeObject2HashMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("1", new SameHashCodeObj(1));
		map.put("2", new SameHashCodeObj(2));
		map.put("3", new SameHashCodeObj(3));
		Log.addLog(this, map.toString());
	}
	
	@Entry
	public void viewOnTouchReturnFalse() {
		FatherView fv = new FatherView(getContext());
		fv.setBackgroundColor(Color.BLUE);
		SonViewOne sv = new SonViewOne(getContext());
		sv.setBackgroundColor(Color.GREEN);
		fv.addView(sv);
		showView(fv);
	}
	
	@Entry
	public void viewOnTouchReturnTrue() {
		FatherView fv = new FatherView(getContext());
		fv.setBackgroundColor(Color.BLUE);
		SonViewTwo sv = new SonViewTwo(getContext());
		sv.setBackgroundColor(Color.GREEN);
		fv.addView(sv);
		showView(fv);
	}
	
	@Entry
	public void fatherViewOnTouchReturnFalse() {
		GrandFatherView gv = new GrandFatherView(getContext());
		gv.setBackgroundColor(Color.GRAY);
		FatherView fv = new FatherView(getContext());
		fv.setBackgroundColor(Color.BLUE);
		SonViewOne sv = new SonViewOne(getContext());
		sv.setBackgroundColor(Color.GREEN);
		fv.addView(sv);
		gv.addView(fv);
		showView(gv);
	}
	
	
}
