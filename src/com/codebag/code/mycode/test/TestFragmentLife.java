
package com.codebag.code.mycode.test;

import android.content.Context;
import android.content.Intent;

import com.codebag.bag.CaseListView;
import com.codebag.code.mlib.FragmentActivity;

public class TestFragmentLife extends CaseListView {

	public TestFragmentLife(Context context) {
		super(context);
	}

	public void run_FragmentActivity() {
		getContext().startActivity(new Intent(getContext(),FragmentActivity.class));
	}
	
}

