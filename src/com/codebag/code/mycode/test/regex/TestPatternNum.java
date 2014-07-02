package com.codebag.code.mycode.test.regex;

import java.util.regex.Pattern;

import android.content.Context;

import com.codebag.bag.Annotation;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

@Annotation("???????????????????????????????????????????????????")
public class TestPatternNum extends CaseListView {

	public TestPatternNum(Context context) {
		super(context);
	}

	@Entry()
	public void runMatcher() {
		boolean isNum = isNumber("123");
		Log.addLog("isNum = "  + isNum);
	}
		
	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
}
