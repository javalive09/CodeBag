package com.codebag.code.mycode.test;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.utils.DisplayUtil;
import com.codebag.code.mycode.utils.Log;

public class DPITest extends MyCode {

	public DPITest(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void showPX() {
		int px = DisplayUtil.dip2px(getActivity(), 110);
		Log.addLog("DPITest", this, "88px = " + px);
	}

}
