package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;
import android.util.Log;

/**
 * Created by peter on 2019-06-10
 */
public class TryWithResource {

    @Run
    public void tryWithResource(CodeActivity codeActivity) {
        try (Resource resource = new Resource()){
            resource.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run
    public void tryWithResource2(CodeActivity codeActivity) {
        try (Resource2 resource = new Resource2()){
            resource.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    class Resource implements AutoCloseable{

        @Override
        public void close() throws Exception {
            Log.i("peter", "auto close");
        }

        public void doSomething() {
            Log.i("peter", "doSomething");
        }
    }

    class Resource2 implements AutoCloseable {

        @Override
        public void close() throws Exception {
            Log.i("peter", "auto close");
        }

        public void doSomething() {
            Log.i("peter", "doSomething");
            int i = 1;
            int j = 0;
            int k = i / j;
        }

    }

}
