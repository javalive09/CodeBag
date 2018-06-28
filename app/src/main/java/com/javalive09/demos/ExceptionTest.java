package com.javalive09.demos;

import android.os.Handler;
import android.os.Looper;

import com.javalive09.annotation.Code;
import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;

/**
 * Created by peter on 2018/1/10.
 */

@Code(name = "Exception异常")
public class ExceptionTest {

    /**
     * 在子线程中直接new 无参Handler 会抛出异常。
     * java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
     */
    @Run(name = "在子线程中直接new无参Handler")
    public void newNoParamHandlerInThread(CodeActivity activity) {
        new Thread(() -> {
            Handler handler = new Handler();
            Handler handler1 = new Handler();
            Looper looper = handler.getLooper();
            Looper looper1 = handler1.getLooper();
            Looper mainLooper = Looper.getMainLooper();
            String str = "";
            str += "looper" + looper.toString();
            str += "looper1" + looper1.toString();
            str += "mainLooper" + mainLooper.toString();
            activity.showText(str);
        }).start();
    }


}
