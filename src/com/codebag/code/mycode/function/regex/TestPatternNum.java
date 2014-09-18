package com.codebag.code.mycode.function.regex;

import java.util.regex.Pattern;

import android.content.Context;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

/**
 * 测试字符串是否是数字
 * 
 * @author peter
 *
 */
public class TestPatternNum extends MyCode {

	public TestPatternNum(MainActivity context) {
		super(context);
	}

	@Entry()
	public void runMatcher() {
		boolean isNum = isNumber("123");
		Log.addLog(this, "isNum = "  + isNum);
	}
		
	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	@Entry
	public void testChar() {
		char a = 'A';
		Log.addLog(this, " a = " + a);
	}
}
