package com.codebag.code.mycode.cache.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Sharedpreference extends MyCode {

	public Sharedpreference(InovkedViewActivity act) {
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
		String name = getActivity().getLocalClassName();
		sp.edit().putString("peter3", "6789").commit();
	}
	
	@Entry
	public void set4() {
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
	    sp.edit().putString("peter34", "0000").commit();
	}

}
