package com.codebag.bag;

public class Log {
	
	private static StringBuffer mLog = new StringBuffer();
	
	public static void addLog(Object invoker, String msg) {
		mLog.append(invoker.getClass().getSimpleName() + msg + "\n");
		android.util.Log.i("log="+ invoker.getClass().getSimpleName(), msg);
	}
	
	public static String getLog() {
		return mLog.toString();
	}
	
	public static void clearLog() {
		mLog.setLength(0);
	}
	
}
