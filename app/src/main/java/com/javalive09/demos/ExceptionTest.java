package com.javalive09.demos;

import android.os.Handler;
import android.os.Looper;

import com.javalive09.codebag.Tester;
import com.javalive09.codebag.CodeBag;
import com.javalive09.codebag.Test;

/**
 * Created by peter on 2018/1/10.
 */

@Tester(name = "Exception异常")
public class ExceptionTest {

    /**
     * 在子线程中直接new 无参Handler 会抛出异常。
     * java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
     */
    @Test(name = "在子线程中直接new无参Handler")
    public void newNoParamHandlerInThread() {
        new Thread(() -> {
            Handler handler = new Handler();
            Handler handler1 = new Handler();
            Looper looper = handler.getLooper();
            Looper looper1 = handler1.getLooper();
            Looper mainLooper = Looper.getMainLooper();
            CodeBag.context().addText("looper" + looper.toString());
            CodeBag.context().addText("looper1" + looper1.toString());
            CodeBag.context().addText("mainLooper" + mainLooper.toString());
            CodeBag.context().showAddedText();
        }).start();
    }


}
