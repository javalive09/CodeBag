
package com.codebag.code.mycode.test.invokeotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.codebag.bag.Annotation;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

@Annotation("????????????????????????????????????????????????activity")
public class TestInvokeOtherAppActivity extends CaseListView {

	public TestInvokeOtherAppActivity(Context context) {
		super(context);
	}

	@Entry()
	public void runOtherActivity() {
		Intent intent = new Intent();
		ComponentName component = new ComponentName("com.lockscreen", "com.lockscreen.MainActivity");
		intent.setComponent(component);
		getContext().startActivity(intent);
	}
	
}

