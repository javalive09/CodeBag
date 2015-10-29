package com.codebag.code.mycode.function.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.widget.Toast;

import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 正则查询操作
 * 
 * @author peter
 *
 */
public class TestPatternFind extends MyCode {

	public TestPatternFind(InovkedViewActivity context) {
		super(context);
	}
	
	@Entry()
	public void runMatcher() {
		String str = "1008 bytes from 61.135.169.125: icmp_seq=1 ttl=57 time=5.13 ms";
		String regx = "time=.*ms";
    	Pattern pattern = Pattern.compile(regx);
    	Matcher matcher = pattern.matcher(str);
    	if(matcher.find()) {
    		String mat = matcher.group();
    		int start = mat.indexOf("=") + 1;
    		int end = mat.indexOf(" ");
    		String time = mat.substring(start, end);
    		Double d = Double.valueOf(time);
    		Toast.makeText(getActivity(), d +" ", Toast.LENGTH_SHORT).show();
    	}else {
    		Toast.makeText(getActivity(), "not find", Toast.LENGTH_SHORT).show();
    	}
    	
	}
}
