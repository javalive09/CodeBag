package com.codebag.code.mycode.function.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Sharedpreference extends MyCode {

	public Sharedpreference(MainActivity act) {
		super(act);
	}
	
	/**
	 * context定义的通用接口getSharedPreferences   （名字，权限）
	 */
	@Entry
	public void set1() {
		SharedPreferences sp = getActivity().getSharedPreferences("getSharedPreferences1", Context.MODE_PRIVATE);
		sp.edit().putString("peter1", "12345").commit();
	}
	
	/**
	 * context定义的通用接口getSharedPreferences	    （名字，权限）
	 */
	@Entry
	public void set2() {
		SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences("getSharedPreferences2", Context.MODE_PRIVATE);
		sp.edit().putString("peter2", "12345").commit();
	}
	
	/**
	 * xml 名字为activity类全路径名去掉包名
	 */
	@Entry
	public void set3() {
		SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
		sp.edit().putString("peter3", "6789").commit();
	}
	

}
