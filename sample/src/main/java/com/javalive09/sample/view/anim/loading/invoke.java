package com.javalive09.sample.view.anim.loading;

import com.javalive09.codebag.Entry;

public class invoke extends Entry {

	public void drawRect() {
		showView(new RectLoading(getActivity()));
	}
}
