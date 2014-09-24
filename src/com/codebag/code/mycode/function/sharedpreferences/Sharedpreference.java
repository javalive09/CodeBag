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
	
	@Entry
	public void set1() {
		SharedPreferences sp = getActivity().getSharedPreferences("getSharedPreferences", Context.MODE_PRIVATE);
		sp.edit().putString("peter1", "12345").commit();
	}
	
	@Entry
	public void set2() {
		SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences("getSharedPreferences", Context.MODE_PRIVATE);
		sp.edit().putString("peter2", "12345").commit();
	}
	
	@Entry
	public void set3() {
		SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
		sp.edit().putString("peter3", "6789").commit();
	}

}
