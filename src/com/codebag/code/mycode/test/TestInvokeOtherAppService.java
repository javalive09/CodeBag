
package com.codebag.code.mycode.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class TestInvokeOtherAppService extends CaseListView {

	public TestInvokeOtherAppService(Context context) {
		super(context);
	}
	
	@Entry()
	public void runOtherService() {
		Toast.makeText(getContext(), "run_otherService", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		ComponentName componentName = new ComponentName("com.lockscreen", "com.lockscreen.LockService");
		intent.setComponent(componentName);
		getContext().startService(intent);
	}
	
}

