package com.codebag.code.mycode.test.activityconfigchange;

import com.codebag.code.mycode.utils.Log;

import android.R.color;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.EditText;

public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EditText et = new EditText(MyActivity.this);
		et.setBackgroundColor(color.background_dark);
		et.setHint("input ...");
		setContentView(et);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.addLog("peter", this, "onConfigurationChanged" + newConfig.toString());
	}

}
