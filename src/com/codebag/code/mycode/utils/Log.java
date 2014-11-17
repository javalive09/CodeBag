package com.codebag.code.mycode.utils;

public class Log {
	
	public static final String CRASH_TXT_PATH = "/sdcard/codebag_crash_log.txt";
	private static final boolean DEBUG = true;
	private static StringBuffer mLog = new StringBuffer();
	private static long startTime = 0;
	
	public static void addLog(String tag, Object invoker, String msg) {
		if(DEBUG) {
			mLog.append(invoker.getClass().getSimpleName() + ":" + msg + "\n");
			android.util.Log.i(tag, "invoker = " + invoker + "name = " + invoker.getClass().getSimpleName() + ", msg =" + msg + "/n");
		}
	}
	
	public static String getLog() {
		if(DEBUG) {
			return mLog.toString();
		}
		return "";
	}
	
	public static void clearLog() {
		if(DEBUG) {
			mLog.setLength(0);
		}
	}
	
	public static void startCountTime(Object invoker, String msg) {
		if(DEBUG) {
			startTime = System.currentTimeMillis();
			Log.addLog("startCountTime", invoker, msg + " startTime=" + startTime + ">>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
	
	public static long endCountTime(Object invoker, String msg) {
		if(DEBUG) {
			long endTime = System.currentTimeMillis();
			long detaTime = endTime - startTime;
			Log.addLog("endCountTime", invoker, msg + " cost =" + detaTime + ">>>>>>>>>>>>>>>>>>>>>>>");
			return detaTime;
		}
		return 0;
	}
	
}
