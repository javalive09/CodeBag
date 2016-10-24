
package com.javalive09.sample.function.activity.activitylife;

import android.content.Intent;
import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

	public void actionA() {
		getViewActivity().startActivity(new Intent(getViewActivity(),MyActivityA.class));
	}

}

