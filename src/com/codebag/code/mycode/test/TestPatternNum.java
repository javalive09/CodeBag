package com.codebag.code.mycode.test;

import java.util.regex.Pattern;

import android.content.Context;

import com.codebag.bag.Annotation;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

@Annotation("正则表达式验证验证字符串是否是数字")
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
