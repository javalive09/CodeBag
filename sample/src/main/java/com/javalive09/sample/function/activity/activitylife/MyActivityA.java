package com.javalive09.sample.function.activity.activitylife;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

public class MyActivityA extends Activity {

	/* 
	 * onCreate 是activity被启动的入口回调
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button bt = new Button(this);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MyActivityA.this, MyActivityB.class), 0);
				LogUtil.i("onCreate()");
			}
			
		});
		bt.setText("start MyActivityB");
		setContentView(bt);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i("onActivityResult");
	}

	/* 
	 * start对应的是显示的开始
	 */
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("onStart");
	}

	/* 
	 * restart对应的是再次显示
	 */
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("onResume");
	}

	/* 
	 * onNewIntent 对应的是activity对象存在的情况下被调用，是activity被启动的另一个回调
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Log.i("onNewIntent");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("onDestroy");
	}

	
	
}
