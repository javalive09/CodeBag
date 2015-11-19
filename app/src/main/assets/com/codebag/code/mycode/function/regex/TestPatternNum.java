package com.codebag.code.mycode.function.regex;

import java.util.regex.Pattern;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.utils.Log;

/**
 * 测试字符串是否是数字
 * 
 * @author peter
 *
 */
public class TestPatternNum extends MyCode {

	public TestPatternNum(InovkedViewActivity context) {
		super(context);
	}

	@Entry()
	public void runMatcher() {
		boolean isNum = isNumber("123");
		Log.addLog("peter", this, "isNum = "  + isNum);
	}
		
	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	@Entry
	public void testChar() {
		char a = 'A';
		Log.addLog("peter", this, " a = " + a);
	}
}
