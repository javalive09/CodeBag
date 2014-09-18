
package com.codebag.code.mycode.test.activityconfigchange;

import android.content.Context;
import android.content.Intent;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Invoke extends MyCode {

	public Invoke(MainActivity context) {
		super(context);
	}

	@Entry()
	public void invoke() {
		getActivity().startActivity(new Intent(getActivity(), MyActivity.class));
	}

}

