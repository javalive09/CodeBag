
package com.codebag.code.mycode.test.fragmentlife;

import android.content.Context;
import android.content.Intent;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

/**
 * 测试fraemnt生命周期
 * 
 * @author peter
 *
 */
public class TestFragmentLife extends CaseListView {

	public TestFragmentLife(Context context) {
		super(context);
	}

	@Entry()
	public void runFragmentActivity() {
		getContext().startActivity(new Intent(getContext(),MyFragmentActivity.class));
	}
	
	@Entry()
	public void runViewPagerFragmentAct() {
		getContext().startActivity(new Intent(getContext(),ViewPagerFragmentActivity.class));
	}
	
}

