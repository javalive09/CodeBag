package com.codebag.bag;


import android.text.TextUtils;

/**
 * log 工具类 能自动打印调用类和调用方法
 */
public class LogUtil {

    private static final boolean DEBUG = true;
    private static StringBuffer mLog = new StringBuffer();
    private static long startTime = 0;

    public static void i(String msg) {
        if (DEBUG) {
            CallerInfo info = CallerInfo.get(CodeBag.instance().getPackageName(), "LogUtil", "i");
            mLog.append(info.className + " > " + info.methodName + ": " + msg + "\n");
            android.util.Log.i(info.className, info.methodName + ": " + msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            CallerInfo info = CallerInfo.get(CodeBag.instance().getPackageName(), "LogUtil", "w");
            mLog.append(info.className + " > " + info.methodName + ": " + msg + "\n");
            android.util.Log.w(info.className, info.methodName + ": " + msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            CallerInfo info = CallerInfo.get(CodeBag.instance().getPackageName(), "LogUtil", "e");
            mLog.append(info.className + " > " + info.methodName + ": " + msg + "\n");
            android.util.Log.e(info.className, info.methodName + ": " + msg);
        }
    }

    public static String getLog() {
        if (DEBUG) {
            return mLog.toString();
        }
        return "";
    }

    public static void clearLog() {
        if (DEBUG) {
            mLog.setLength(0);
        }
    }

    public static void startCountTime(String msg) {
        if (DEBUG) {
            startTime = System.currentTimeMillis();
            LogUtil.i("startTime=" + startTime + ">>>>>>>>>>>>>>>>>>>>>>>");
        }
    }

    public static long endCountTime(String msg) {
        if (DEBUG) {
            long endTime = System.currentTimeMillis();
            long detaTime = endTime - startTime;
            LogUtil.i(" cost =" + detaTime + ">>>>>>>>>>>>>>>>>>>>>>>");
            return detaTime;
        }
        return 0;
    }

    public static class CallerInfo {

        String className;
        String methodName;

        public CallerInfo(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }

        /**
         * @param currentClassName
         * @param currentMethodName
         * @return
         */
        public static CallerInfo get(String packageName, String currentClassName, String currentMethodName) {
            StackTraceElement stack[] = Thread.currentThread().getStackTrace();
            for (int i = 0; i < stack.length; i++) {
                String className = stack[i].getClassName() + "." + stack[i].getMethodName();
                String methodSign = packageName + "." + currentClassName + "." + currentMethodName;
                if (!TextUtils.isEmpty(className)
                        && !TextUtils.isEmpty(methodSign)
                        && className.contains(methodSign)) {
                    StackTraceElement invoker = stack[i + 1];
                    String javaFileName = invoker.getClassName(); //Caller.java
                    String[] name = javaFileName.split("\\.");
                    String callerClassName = name[name.length - 1] + ".java";
                    String callerMethodName = invoker.getMethodName() + "()"; //showCallerInfo
                    return new CallerInfo(callerClassName, callerMethodName);
                }
            }
            return null;
        }
    }

}
