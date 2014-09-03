
package com.codebag.code.mycode.function.invokeotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

/**
 * 跨应用调用其他activity的两种方式: componentName, action
 * 
 * 注意：被调用组件必须，android:exported="true" 
 * 
 * @author peter
 *
 */
public class TestInvokeOtherAppActivity extends CaseListView {

	public TestInvokeOtherAppActivity(Context context) {
		super(context);
	}

	@Entry()
	public void runOtherActivity() {
		Intent intent = new Intent();
		ComponentName component = new ComponentName("com.peter.appmanager", "com.peter.appmanager.SettingActivity");
		intent.setComponent(component);
		getContext().startActivity(intent);
	}
	
	@Entry
	public void runOtherActivity2() {
		Intent intent = new Intent();
		intent.setAction("com.peter.foo");
		getContext().startActivity(intent);
	}
	
}

