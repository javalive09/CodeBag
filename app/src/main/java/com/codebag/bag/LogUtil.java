package com.codebag.bag;


public class LogUtil {
	
	private static final boolean DEBUG = true;
	private static StringBuffer mLog = new StringBuffer();
	private static long startTime = 0;

	public static void addLog(String msg) {
		if(DEBUG) {
			String[] invoker = getCaller("LogUtil");
			mLog.append(invoker[0] + ">" + invoker[1] + "()" + ":" + msg + "\n");
			android.util.Log.i(invoker[0], invoker[1] + "()" + ":" + msg);
		}
	}

	public static void addLog() {
		if(DEBUG) {
			String[] invoker = getCaller("LogUtil");
			mLog.append(invoker[0] + ">" + invoker[1] + "()" + "\n");
			android.util.Log.i(invoker[0], invoker[1] + "()");
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
	
	public static void startCountTime(String msg) {
		if(DEBUG) {
			startTime = System.currentTimeMillis();
			LogUtil.addLog("startTime=" + startTime + ">>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
	
	public static long endCountTime(String msg) {
		if(DEBUG) {
			long endTime = System.currentTimeMillis();
			long detaTime = endTime - startTime;
			LogUtil.addLog(" cost =" + detaTime + ">>>>>>>>>>>>>>>>>>>>>>>");
			return detaTime;
		}
		return 0;
	}

	public static String[] getCaller(String utilsClassName){
		StackTraceElement stack[] = Thread.currentThread().getStackTrace();
		for(int i = 0; i < stack.length; i++) {
			if(stack[i].getClassName().indexOf(utilsClassName) != -1) {
				StackTraceElement invoker = stack[i+1];
				String javaFileName = invoker.getClassName(); //Caller.java
				String[] name = javaFileName.split("\\.");
				String className = name[name.length - 1];
				String methodName = invoker.getMethodName(); //showCallerInfo
				return new String[] {className, methodName};
			}
		}
		return null;
	}
	
}
