package com.codebag.code.mycode.test.regex;

import java.util.regex.Pattern;

import android.content.Context;

import com.codebag.bag.Annotation;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

@Annotation("?????????????????????ip?????????")
public class TestPatternIP extends CaseListView {

	public TestPatternIP(Context context) {
		super(context);
	}

	@Entry()
	public void runMatcher() {
		boolean isValid = isValidIP("192.168.1.1");
    	Log.addLog(this, "isValid ip = " + isValid);
	}
	
	public boolean isValidIP(String str) {
		
		String regex = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."+
				"(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."+
				"(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\."+
				"(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
	
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(str).matches();
	}
}
