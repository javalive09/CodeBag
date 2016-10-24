
package com.javalive09.sample.function.app.invokeotherapp;

import android.content.ComponentName;
import android.content.Intent;

import com.javalive09.codebag.Entry;

/**
 * 跨应用调用其他activity的两种方式: componentName, action
 *
 * 注意：被调用组件必须，android:exported="true" 
 *
 * @author peter
 *
 */
public class TestInvokeOtherAppActivity extends Entry {

	public void runOtherActivity() {
		Intent intent = new Intent();
		ComponentName component = new ComponentName("com.peter.appmanager", "com.peter.appmanager.SettingActivity");
		intent.setComponent(component);
		getViewActivity().startActivity(intent);
	}

	public void runOtherActivity2() {
		Intent intent = new Intent();
		intent.setAction("com.peter.foo");
		getViewActivity().startActivity(intent);
	}

}

