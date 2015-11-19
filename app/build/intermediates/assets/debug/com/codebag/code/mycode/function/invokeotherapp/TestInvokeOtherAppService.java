
package com.codebag.code.mycode.function.invokeotherapp;

import android.content.ComponentName;
import android.content.Intent;
import android.widget.Toast;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 跨应用调用其他程序的service
 * 
 * @author peter
 *
 */
public class TestInvokeOtherAppService extends MyCode {

	public TestInvokeOtherAppService(InovkedViewActivity context) {
		super(context);
	}
	
	@Entry()
	public void runOtherService() {
		Toast.makeText(getActivity(), "run_otherService", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent();
		ComponentName componentName = new ComponentName("com.lockscreen", "com.lockscreen.LockService");
		intent.setComponent(componentName);
		getActivity().startService(intent);
	}
	
}

