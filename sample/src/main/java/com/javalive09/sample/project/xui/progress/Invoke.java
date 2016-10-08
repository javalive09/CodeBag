package com.javalive09.sample.project.xui.progress;

import android.view.View;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoke extends Entry {

	public void startRoteAnimation() {
		
		View view = View.inflate(getActivity(), R.layout.xui_circle_progressbarl, null);
		final XuiCircularProgressBar progressBar = (XuiCircularProgressBar) view.findViewById(R.id.progress_bar);
		showView(view);
		view.postDelayed(new Runnable() {
			@Override
			public void run() {
				progressBar.startAnimination();
			}
		},500);

		
	}

}
