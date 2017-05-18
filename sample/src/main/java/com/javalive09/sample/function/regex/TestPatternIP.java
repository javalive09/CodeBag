package com.javalive09.sample.function.regex;

import java.util.regex.Pattern;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

/**
 * 用于测试ip合法性
 * 
 * @author peter
 *
 */
public class TestPatternIP extends Entry {

	public void runMatcher() {
		boolean isValid = isValidIP("192.168.1.1");
		LogUtil.i( "isValid ip = " + isValid);
	}
	
	public void runMatcher2() {
		boolean isValid = isValidIP("127.0.0.1");
		LogUtil.i( "isValid ip = " + isValid);
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
