package com.javalive09.sample.function.activity.activitylife;

import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class MyActivityB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button bt = new Button(MyActivityB.this);
		bt.setGravity(Gravity.CENTER);
		bt.setText("start MyActivityA");
		setContentView(bt);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MyActivityB.this, MyActivityA.class));
			}
		});
		Log.i("onCreate");
		finish();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i("onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("onResume");
	}

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

	@Override
	public void finish() {
		setResult(Activity.RESULT_OK, new Intent());
		super.finish();
	}
	
	
}
