package com.codebag.code.mycode.test.actiitytask;

import android.content.Intent;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class invoke extends MyCode {

	public invoke(MainActivity act) {
		super(act);
	}

	@Entry
	public void show() {
		getActivity().startActivity(new Intent(getActivity(),MyActivity.class));
	}
	
}
