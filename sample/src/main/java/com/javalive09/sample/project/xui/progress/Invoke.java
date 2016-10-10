package com.javalive09.sample.project.xui.progress;

import android.view.View;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoke extends Entry {

	public void startRoteAnimation() {
		
		showView(R.layout.xui_circle_progressbarl);
		final XuiCircularProgressBar progressBar = (XuiCircularProgressBar) findViewById(R.id.progress_bar);
		progressBar.postDelayed(new Runnable() {
			@Override
			public void run() {
				progressBar.startAnimination();
			}
		},500);
		
	}

}
