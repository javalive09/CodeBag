package com.javalive09.sample.function.reflection;

import java.lang.reflect.Method;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

/**
 * class中的getDeclaredMethod() 和 getMethod() 的区别？ getDeclaredMethod()
 * 获取的是类自身声明的所有方法，包含public、protected和private方法。
 * getMethod()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
 * 所以要调用隐藏方法，或私有方法必须用getDeclaredMethod();
 * 
 * @author zhangrui-ms
 *
 */
public class InvokePrivateMethod extends Entry {

	Reflection reflection;

	public InvokePrivateMethod() {
		reflection = new Reflection();
	}

	/**
	 * 基本类型都有自己的Class(不是他们的包装类), byte.class, short.class, long.class
	 */
	public void params_baseType() {
		try {
			Method method = Reflection.class.getDeclaredMethod("show",
					new Class[] { int.class });
			method.setAccessible(true);
			method.invoke(reflection, new Object[] { 1 });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void params_objectType() {
		try {
			Method method = Reflection.class.getDeclaredMethod("show",
					new Class[] { Reflection.Test.class });
			method.setAccessible(true);
			method.invoke(reflection, new Object[] { new Reflection.Test() });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void params_multi() {
		try {
			Method method = Reflection.class.getDeclaredMethod("show",
					new Class[] { int.class, String.class });
			method.setAccessible(true);
			method.invoke(reflection, new Object[] { 1, "string" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void returnValue() {
		Object returnObj = "null";
		try {
			Method method = Reflection.class.getDeclaredMethod("show");
			method.setAccessible(true);
			returnObj = method.invoke(reflection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogUtil.i( "returnValue=" + returnObj.toString());
	}

	public void params_no() {

		try {
			Method method = Reflection.class.getDeclaredMethod("show");
			method.setAccessible(true);
			method.invoke(reflection);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
