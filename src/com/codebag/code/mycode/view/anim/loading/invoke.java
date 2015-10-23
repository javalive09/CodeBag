package com.codebag.code.mycode.view.anim.loading;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class invoke extends MyCode {

	public invoke(MainActivity act) {
		super(act);
	}

	@Entry
	public void start() {
		showView(new RectLoading(getActivity()));
	}
}
