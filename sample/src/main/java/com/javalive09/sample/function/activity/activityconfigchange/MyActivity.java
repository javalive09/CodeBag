package com.javalive09.sample.function.activity.activityconfigchange;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import com.javalive09.codebag.LogUtil;

/**
 * activity manifest 配置 onConfigurationChanged 的各个参数
 *
 *
 */
public class MyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EditText et = new EditText(MyActivity.this);
		et.setBackgroundColor(Color.DKGRAY);
		et.setHint("input ...");
		setContentView(et);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LogUtil.i( "onConfigurationChanged" + newConfig.toString());
	}

}