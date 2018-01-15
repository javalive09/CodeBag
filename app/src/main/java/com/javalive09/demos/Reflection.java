package com.javalive09.demos;

import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Player(name = "反射调用")
public class Reflection {

    @Play(name = "反射构造器无参的对象")
    public void newInstance_noParams() throws Exception {
        ReflectionClass reflection = ReflectionClass.class.newInstance();
        CaseActivity.showText("reflection object =" + reflection);
    }

    @Play(name = "反射有参构造器的对象")
    public void newInstance_haveParams() throws Exception {
        ReflectionClass reflection = ReflectionClass.class.getConstructor(String.class).newInstance("123");
        CaseActivity.showText("reflection object =" + reflection);
    }

    @Play(name = "获取私有属性值")
    public void getPrivateField() {
        try {
            Field field = ReflectionClass.class.getDeclaredField("name");
            field.setAccessible(true);
            Object obj = field.get(new ReflectionClass());
            CaseActivity.showText("obj=" + obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Play(name = "设置私有属性值")
    public void setPrivateFiele() {
        ReflectionClass reflection = new ReflectionClass();
        try {
            Field field = ReflectionClass.class.getDeclaredField("age");
            field.setAccessible(true);
            field.set(reflection, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CaseActivity.showText("setFiled = " + reflection.getAge());
    }

    @Play(name = "调用无参数的私有方法")
    public void invokePrivateMethod_no_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", int.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Play(name = "调用含有基本类型参数的私有方法")
    public void invokePrivateMethod_rawType_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", int.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Play(name = "调用含有对象类型参数的私有方法")
    public void invokePrivateMethod_objectType_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", Test.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), new Test());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Play(name = "调用含有基本类型参, 对象类型参数的私有方法")
    public void invokePrivateMethod_multiType_param() {
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show", int.class, String.class);
            method.setAccessible(true);
            method.invoke(new ReflectionClass(), 1, "string");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Play(name = "获取私有方法的返回值")
    public void invokePrivateMethod_returnValue() {
        Object returnObj = "null";
        try {
            Method method = ReflectionClass.class.getDeclaredMethod("show");
            method.setAccessible(true);
            returnObj = method.invoke(new ReflectionClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        CaseActivity.showText("returnObj=" + returnObj);
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
            CaseActivity.showText("show(" + a + ")");
        }

        private void show(Test test) {
            CaseActivity.showText("show(" + test.toString() + ")");
        }

        private void show(int a, String str) {
            CaseActivity.showText("show(" + a + "," + str + ")");
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }

    class Test {
        @Override
        public String toString() {
            return "custom class Play";
        }

    }
}
