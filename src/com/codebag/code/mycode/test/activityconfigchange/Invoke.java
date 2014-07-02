
package com.codebag.code.mycode.test.activityconfigchange;

import android.content.Context;
import android.content.Intent;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoke extends CaseListView {

	public Invoke(Context context) {
		super(context);
	}

	@Entry()
	public void invoke() {
		getContext().startActivity(new Intent(getContext(), MyActivity.class));
	}

}

