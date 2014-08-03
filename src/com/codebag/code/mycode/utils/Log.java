package com.codebag.code.mycode.utils;

public class Log {
	
	private static StringBuffer mLog = new StringBuffer();
	private static long startTime = 0;
	
	public static void addLog(Object invoker, String msg) {
		mLog.append(invoker.getClass().getSimpleName() + ":" + msg + "\n");
		android.util.Log.i("log="+ invoker.getClass().getSimpleName(), msg);
	}
	
	public static String getLog() {
		return mLog.toString();
	}
	
	public static void showSystemLog(Object invoker, String msg) {
		android.util.Log.i("log="+ invoker.getClass().getSimpleName(), msg);
	}
	
	public static void clearLog() {
		mLog.setLength(0);
	}
	
	public static void startCountTime(Object invoker, String msg) {
		startTime = System.currentTimeMillis();
		Log.addLog(invoker, msg + " startTime=" + startTime + ">>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	public static long endCountTime(Object invoker, String msg) {
		long endTime = System.currentTimeMillis();
		long detaTime = endTime - startTime;
		Log.addLog(invoker, msg + " cost =" + detaTime + ">>>>>>>>>>>>>>>>>>>>>>>");
		return detaTime;
	}
	
}
