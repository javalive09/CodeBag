package com.codebag.code.mycode.xui.progress;

import android.view.View;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void startRoteAnimation() {
		
		View view = View.inflate(getActivity(), R.layout.xui_circle_progressbarl, null);
		XuiCircularProgressBar progressBar = (XuiCircularProgressBar) view.findViewById(R.id.progress_bar);
		showView(view);
		progressBar.startAnimination();
		
	}

}
