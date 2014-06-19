
package com.codebag.code.mycode.test;

import android.content.Context;
import android.content.Intent;

import com.codebag.bag.Annotation;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mlib.FragmentActivity;

@Annotation("用于调试fragment生命周期")
public class TestFragmentLife extends CaseListView {

	public TestFragmentLife(Context context) {
		super(context);
	}

	@Entry()
	public void runFragmentActivity() {
		getContext().startActivity(new Intent(getContext(),FragmentActivity.class));
	}
	
}

