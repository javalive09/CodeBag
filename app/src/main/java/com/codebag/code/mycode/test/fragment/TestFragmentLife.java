
package com.codebag.code.mycode.test.fragment;

import android.content.Intent;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 测试fraemnt生命周期
 * 
 * @author peter
 *
 */
public class TestFragmentLife extends MyCode {

	public TestFragmentLife(InovkedViewActivity context) {
		super(context);
	}

	@Entry()
	public void fragmentLife() {
		startActivity(new Intent(getActivity(),MyFragmentActivity.class));
	}
	
	@Entry()
	public void fragmentForResult() {
		startActivity(new Intent(getActivity(),ViewPagerFragmentActivity.class));
	}
	
}

