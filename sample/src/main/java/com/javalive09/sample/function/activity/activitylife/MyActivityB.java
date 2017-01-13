package com.javalive09.sample.function.activity.activitylife;

import com.javalive09.codebag.LogUtil;

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
		LogUtil.i("");
		finish();
	}

	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.i("");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.i("");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.i("");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		LogUtil.i("");
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.i("");
	}

	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.i("");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.i("");
	}

	@Override
	public void finish() {
		setResult(Activity.RESULT_OK, new Intent());
		super.finish();
	}
	
	
}
