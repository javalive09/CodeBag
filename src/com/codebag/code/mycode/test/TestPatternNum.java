package com.codebag.code.mycode.test;

import java.util.regex.Pattern;

import android.content.Context;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Log;

public class TestPatternNum extends CaseListView {

	public TestPatternNum(Context context) {
		super(context);
	}

	public void run_matcher() {
		boolean isNum = isNumber("123");
		Log.addLog("isNum = "  + isNum);
	}
		
	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
