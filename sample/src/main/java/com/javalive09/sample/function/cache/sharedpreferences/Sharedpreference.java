package com.javalive09.sample.function.cache.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.javalive09.codebag.Entry;

public class Sharedpreference extends Entry {


	/**
	 * context定义的通用接口getSharedPreferences   （名字，权限）
	 */
	public void set1() {
		SharedPreferences sp = getActivity().getSharedPreferences("getSharedPreferences1", Context.MODE_PRIVATE);
		sp.edit().putString("peter1", "12345").commit();
	}
	
	/**
	 * context定义的通用接口getSharedPreferences	    （名字，权限）
	 */
	public void set2() {
		SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences("getSharedPreferences2", Context.MODE_PRIVATE);
		sp.edit().putString("peter2", "12345").commit();
	}
	
	/**
	 * xml 名字为activity类全路径名去掉包名
	 */
	public void set3() {
		SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
		String name = getActivity().getLocalClassName();
		sp.edit().putString("peter3", "6789").commit();
	}
	
	public void set4() {
	    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
	    sp.edit().putString("peter34", "0000").commit();
	}

}
