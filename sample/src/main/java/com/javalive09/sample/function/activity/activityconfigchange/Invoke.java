package com.javalive09.sample.function.activity.activityconfigchange;

import android.content.Intent;
import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

	public void invoke() {
		getActivity().startActivity(new Intent(getActivity(), MyActivity.class));
	}

}
