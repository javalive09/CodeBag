package com.codebag.code.mycode.test.activitylife;

import com.codebag.R;
import com.codebag.code.mycode.utils.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MyActivityB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Button bt = new Button(MyActivityB.this);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//startActivity(new Intent(MyActivityB.this, MyActivityA.class));
//				new Thread(new Runnable() {
//
//					@Override
//					public void run() {
//						
//					}
//					
//				}).start();
				Message msg = Message.obtain();
				msg.what = 0;
				msg.obj = MyActivityB.this;
				new MyHandler().sendMessageDelayed(msg, 1000);
			}
			
		});
		bt.setText("///////");
		setContentView(R.layout.activity_root);
		FrameLayout fl = (FrameLayout) findViewById(R.id.container);
		fl.addView(bt);
		Log.addLog("peter", this, "onCreate");
	}
	
	public static class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			Log.addLog("peter", this, "msg =" + msg.what + "; obj = "+msg.obj);
			msg = Message.obtain(msg);
			msg.what++;
			sendMessageDelayed(msg, 1000);
		}
	}

//	private MyHandler mHandler = new MyHandler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//			Log.addLog(this, "msg =" + msg.what + "; obj = "+MyActivityB.this);
//			sendEmptyMessageDelayed(msg.what++, 1000);
//		}
//		
//	};
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.addLog("peter", this, "onStart");
	}

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

	@Override
	public void finish() {
//		setResult(Activity.RESULT_OK, new Intent()); 
		super.finish();
	}

	
	
}
