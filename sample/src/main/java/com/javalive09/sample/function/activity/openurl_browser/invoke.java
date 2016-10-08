package com.javalive09.sample.function.activity.openurl_browser;

import android.content.Intent;
import com.javalive09.codebag.Entry;

public class invoke extends Entry {

	public void show() {
		getActivity().startActivity(new Intent(getActivity(),MyActivity.class));
	}
	
}
