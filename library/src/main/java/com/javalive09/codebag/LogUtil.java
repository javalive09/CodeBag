package com.javalive09.codebag;


import android.text.TextUtils;

/**
 * log util for auto print invoker class and method name
 * Created by peter on 16/9/21.
 */
public class LogUtil {

    private static String PACKAGE_NAME = "com.javalive09.codebag";
    private static String CLASS_NAME = LogUtil.class.getSimpleName();
    private static final boolean DEBUG = true;
    private static StringBuffer mLog = new StringBuffer();
    private static long startTime = 0;

    public static void i(String msg) {
        if (DEBUG) {
            CallerInfo info = CallerInfo.get(PACKAGE_NAME, CLASS_NAME, "i");
            if(info != null) {
                mLog.append(info.className).append(" > ").append(info.methodName).append(": ").append(msg).append("\n");
                android.util.Log.i(info.className, info.methodName + ": " + msg);
            }
        }
    }

    public static void w(String msg) {
        if (DEBUG) {
            CallerInfo info = CallerInfo.get(PACKAGE_NAME, CLASS_NAME, "w");
            if(info != null) {
                mLog.append(info.className).append(" > ").append(info.methodName).append(": ").append(msg).append("\n");
                android.util.Log.w(info.className, info.methodName + ": " + msg);
            }
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            CallerInfo info = CallerInfo.get(PACKAGE_NAME, CLASS_NAME, "e");
            if(info != null) {
                mLog.append(info.className).append(" > ").append(info.methodName).append(": ").append(msg).append("\n");
                android.util.Log.e(info.className, info.methodName + ": " + msg);
            }
        }
    }

    static String getLog() {
        if (DEBUG) {
            return mLog.toString();
        }
        return "";
    }

    static void clearLog() {
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

    private static class CallerInfo {

        String className;
        String methodName;

        CallerInfo(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }

        /**
         *
         * @param packageName  your package name
         * @param currentClassName your class name
         * @param currentMethodName you current method name
         * @return CallerInfo contain invoker's method name and class name
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
