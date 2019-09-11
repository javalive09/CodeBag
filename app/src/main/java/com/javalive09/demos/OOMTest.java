package com.javalive09.demos;

import android.util.Log;
import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;
import java.util.HashMap;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class OOMTest {

    HashMap<Integer, Timer> timerHashMap = new HashMap<>();

    @Run
    public void oom(CodeActivity codeActivity) {

        for (int i = 0; i < Integer.MAX_VALUE; i++){
            Timer timer = new Timer();
            timer.purge();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
//                    while (true) {
                        Log.i("peter", "------");
//                    }
                }
            }, 60000);
            timerHashMap.put(i, timer);
            Log.i("peter", "getTimerQueueSize = " + getTimerQueueSize(timer));
        }

    }

    private int getTimerQueueSize(Timer timer) {
        try {
            Class clazz = Class.forName("java.util.Timer");
            Field field = clazz.getDeclaredField("queue");
            field.setAccessible(true);
            Object queue = field.get(timer);
            Class clazzQueue = field.getType();
            Field FiledSize = clazzQueue.getDeclaredField("size");
            FiledSize.setAccessible(true);
            return (int) FiledSize.get(queue);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
