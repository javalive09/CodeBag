package com.codebag.code.mycode.function.systemtime;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.code.mycode.utils.Log;

public class SystemTime extends MyCode {

	public SystemTime(MainActivity act) {
		super(act);
	}
	
	/**
	 * unix 时间戳的概念是 当天的00:00:00 
	 */
	@Entry
	public void currentTime() {
		long currentTime = System.currentTimeMillis() / 1000;
		
		long endTime = 1414684800;
		
		boolean currentTimeBig = currentTime > endTime;
		
		Log.addLog("peter", this, "currentTime = " + currentTime);
		Log.addLog("peter", this, "endTime = " + endTime);
		Log.addLog("peter", this, "currentTimeBig = " + currentTimeBig);
	}

}
