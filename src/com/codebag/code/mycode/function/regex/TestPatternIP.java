package com.codebag.code.mycode.function.regex;

import java.util.regex.Pattern;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

/**
 * 用于测试ip合法性
 * 
 * @author peter
 *
 */
public class TestPatternIP extends MyCode {

	public TestPatternIP(MainActivity context) {
		super(context);
	}

	@Entry()
	public void runMatcher() {
		boolean isValid = isValidIP("192.168.1.1");
    	Log.addLog("peter", this, "isValid ip = " + isValid);
	}
	
	@Entry()
	public void runMatcher2() {
		boolean isValid = isValidIP("127.0.0.1");
		Log.addLog("peter", this, "isValid ip = " + isValid);
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
