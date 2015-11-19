
package com.codebag.code.mycode.test.activitylife;

import android.content.Intent;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity context) {
		super(context);
	}

	@Entry()
	public void actionA() {
		getActivity().startActivity(new Intent(getActivity(),MyActivityA.class));
	}
	
}

