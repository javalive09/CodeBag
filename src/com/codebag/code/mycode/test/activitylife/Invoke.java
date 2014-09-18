
package com.codebag.code.mycode.test.activitylife;

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
	public void actionA() {
		getActivity().startActivity(new Intent(getActivity(),MyActivityA.class));
	}
	
}

