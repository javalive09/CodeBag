package com.codebag.code.mycode.test.activitylife;

import com.codebag.code.mycode.utils.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivityA extends Activity {

	/* 
	 * onCreate 是activity被启动的入口回调
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Button bt = new Button(MyActivityA.this);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MyActivityA.this, MyActivityB.class));
			}
			
		});
		bt.setText("start activityB");
		setContentView(bt);
		Log.addLog(this,"onCreate");
	}

	/* 
	 * start对应的是显示的开始
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.addLog(this, "onStart");
	}

	/* 
	 * restart对应的是再次显示
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.addLog(this, "onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.addLog(this, "onResume");
	}

	/* 
	 * onNewIntent 对应的是activity对象存在的情况下被调用，是activity被启动的另一个回调
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.addLog(this, "onNewIntent");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.addLog(this, "onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.addLog(this, "onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.addLog(this, "onDestroy");
	}

	
	
}
