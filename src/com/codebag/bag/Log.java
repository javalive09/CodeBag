package com.codebag.bag;

public class Log {
	
	private static StringBuffer mLog = new StringBuffer();
	
	public static void addLog(String msg) {
		mLog.append(msg + "\n");
		android.util.Log.i("log=", msg);
	}
	
	public static String getLog() {
		return mLog.toString();
	}
	
	public static void clearLog() {
		mLog.setLength(0);
	}
	
}
