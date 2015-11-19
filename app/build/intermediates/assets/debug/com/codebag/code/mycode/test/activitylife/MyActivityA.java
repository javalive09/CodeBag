package com.codebag.code.mycode.test.activitylife;

import com.codebag.R;
import com.codebag.bag.CodeBag;
import com.codebag.code.mycode.utils.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

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
//				startActivity(new Intent(MyActivityA.this, MyActivityB.class));
				startActivityForResult(new Intent(MyActivityA.this, MyActivityB.class), 0);
				Log.addLog("peter", this, "class name = " + MyActivityA.class.getName());
			}
			
		});
		bt.setText("start activityB");
		setContentView(R.layout.activity_root);
		FrameLayout fl = (FrameLayout) findViewById(R.id.container);
		fl.addView(bt);
		Log.addLog("peter", this,"onCreate");
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.addLog("peter", this, "onActivityResult()");
	}



	/* 
	 * start对应的是显示的开始
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.addLog("peter", this, "onStart");
	}

	/* 
	 * restart对应的是再次显示
	 */
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.addLog("peter", this, "onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.addLog("peter", this, "onResume");
	}

	/* 
	 * onNewIntent 对应的是activity对象存在的情况下被调用，是activity被启动的另一个回调
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		Log.addLog("peter", this, "onNewIntent");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.addLog("peter", this, "onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.addLog("peter", this, "onStop");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.addLog("peter", this, "onDestroy");
	}

	
	
}
