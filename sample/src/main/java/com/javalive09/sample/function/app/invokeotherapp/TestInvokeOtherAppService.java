
package com.javalive09.sample.function.app.invokeotherapp;

import android.content.ComponentName;
import android.content.Intent;
import android.widget.Toast;
import com.javalive09.codebag.Entry;

/**
 * 跨应用调用其他程序的service
 *
 * @author peter
 *
 */
public class TestInvokeOtherAppService extends Entry {

	public void runOtherService() {
		Toast.makeText(getViewActivity(), "run_otherService", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		ComponentName componentName = new ComponentName("com.lockscreen", "com.lockscreen.LockService");
		intent.setComponent(componentName);
		getViewActivity().startService(intent);
	}

}

