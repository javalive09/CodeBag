
package com.codebag.code.mycode.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class TestInvokeOtherAppActivity extends CaseListView {

	public TestInvokeOtherAppActivity(Context context) {
		super(context);
	}

	@Entry()
	public void runOtherActivity() {
		Toast.makeText(getContext(), "run_otherActivity", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		ComponentName component = new ComponentName("com.lockscreen", "com.lockscreen.MainActivity");
		intent.setComponent(component);
		getContext().startActivity(intent);
	}
	
}

