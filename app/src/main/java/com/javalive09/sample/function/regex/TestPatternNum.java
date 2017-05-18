package com.javalive09.sample.function.regex;

import java.util.regex.Pattern;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

/**
 * 测试字符串是否是数字
 * 
 * @author peter
 *
 */
public class TestPatternNum extends Entry {

	public void runMatcher() {
		boolean isNum = isNumber("123");
		LogUtil.i("isNum = " + isNum);
	}
		
	public boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	public void testChar() {
		char a = 'A';
		LogUtil.i(" a = " + a);
	}
}
