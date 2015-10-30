package com.codebag.bag.main;

import com.codebag.R;
import com.codebag.bag.CodeBag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		IdleHandler handler = new IdleHandler() {

			@Override
			public boolean queueIdle() {
				CodeBag app = (CodeBag) getApplication();
				app.init();
				CodeBag codeBag = (CodeBag) getApplication();
				Intent intent = new Intent(SplashActivity.this, FileNodeActivity.class);
				intent.putExtra("node", codeBag.getRootNode());
				startActivity(intent);
				finish();
				return false;
			}

		};
		Looper.myQueue().addIdleHandler(handler);
	}
	
	
}
