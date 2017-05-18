package com.javalive09.sample.function.systemtime;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

public class SystemTime extends Entry {

	/**
	 * unix 时间戳的概念是 当天的00:00:00 
	 */
	public void currentTime() {
		long currentTime = System.currentTimeMillis() / 1000;
		
		long endTime = 1414684800;
		
		boolean currentTimeBig = currentTime > endTime;
		
		LogUtil.i(  "currentTime = " + currentTime);
		LogUtil.i(  "endTime = " + endTime);
		LogUtil.i(  "currentTimeBig = " + currentTimeBig);
	}

}
