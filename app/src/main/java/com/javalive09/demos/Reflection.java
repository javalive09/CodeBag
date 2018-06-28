package com.javalive09.demos;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;

@Code(name = "反射调用")
public class Reflection {

    @Run(name = "反射构造器无参的对象")
    public void newInstance_noParams(CodeActivity activity) throws Exception {
        ReflectionClass reflection = ReflectionClass.class.newInstance();
        activity.showText("reflection object =" + reflection);
    }

    @Run(name = "反射有参构造器的对象")
    public void newInstance_haveParams(CodeActivity activity) throws Exception {
        ReflectionClass reflection = ReflectionClass.class.getConstructor(String.class).newInstance("123");
        activity.showText("reflection object =" + reflection);
    }

    @Run(name = "获取私有属性值")
    public void getPrivateField(CodeActivity activity) {
        try {
            Field field = ReflectionClass.class.getDeclaredField("name");
            field.setAccessible(true);
            Object obj = field.get(new ReflectionClass());
            activity.showText("obj=" + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run(name = "设置私有属性值")
    public void setPrivateFiele(CodeActivity activity) {
        ReflectionClass reflection = new ReflectionClass();
        try {
            Field field = ReflectionClass.class.getDeclaredField("age");
            field.setAccessible(true);
            field.set(reflection, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        activity.showText("setFiled = " + reflection.getAge());
    }

    @Run(name = "调用无参数的私有方法")
    public void invokePrivateMethod_no_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", int.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run(name = "调用含有基本类型参数的私有方法")
    public void invokePrivateMethod_rawType_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", int.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run(name = "调用含有对象类型参数的私有方法")
    public void invokePrivateMethod_objectType_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", TestIt.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), new TestIt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run(name = "调用含有基本类型参, 对象类型参数的私有方法")
    public void invokePrivateMethod_multiType_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", int.class, String.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), 1, "string");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run(name = "获取私有方法的返回值")
    public void invokePrivateMethod_returnValue(CodeActivity activity) {
        Object returnObj = "null";
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show");
            method.setAccessible(true);
            returnObj = method.invoke(new ReflectionClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        activity.showText("returnObj=" + returnObj);
    }


    class ReflectionClass {

        private int age = 1;
        private String name = "peter";
        private String args = "";

        public ReflectionClass() {
        }

        public ReflectionClass(String args) {
            this.args = args;
        }

        private String show() {
            return "return value";
        }

        private void show(int a) {
            Log.i("ReflectionClass","show(" + a + ")");
        }

        private void show(TestIt test) {
            Log.i("ReflectionClass","show(" + test.toString() + ")");
        }

        private void show(int a, String str) {
            Log.i("ReflectionClass","show(" + a + "," + str + ")");
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }

    class TestIt {
        @Override
        public String toString() {
            return "custom class Test";
        }

    }
}
