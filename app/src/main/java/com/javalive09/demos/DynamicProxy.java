package com.javalive09.demos;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.util.Log;

/**
 * Created by peter on 2018/12/11
 */
public class DynamicProxy {

    public interface IProxy {
        void add();
    }

    public static class ProxyImp implements IProxy {

        @Override
        public void add() {
            Log.i("proxy", "----- add -----");
        }
    }

    public static class MyInvocationHandler implements InvocationHandler {

        private Object target;

        public MyInvocationHandler(Object target) {
            super();
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.i("proxy", "----- before -----");
            Object result = method.invoke(target, args);
            Log.i("proxy", "----- after -----");
            return result;
        }
    }

    @Run
    public void proxyAdd(CodeActivity activity) {
        IProxy proxy = new ProxyImp();
        InvocationHandler invocationHandler = new MyInvocationHandler(proxy);
        IProxy object = (IProxy) Proxy.newProxyInstance(activity.getClassLoader(), proxy.getClass().getInterfaces(),
                invocationHandler);
        object.add();
    }

}
