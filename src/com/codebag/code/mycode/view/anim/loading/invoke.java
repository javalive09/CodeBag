package com.codebag.code.mycode.view.anim.loading;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class invoke extends MyCode {

	public invoke(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void start() {
		showView(new RectLoading(getActivity()));
	}
}
