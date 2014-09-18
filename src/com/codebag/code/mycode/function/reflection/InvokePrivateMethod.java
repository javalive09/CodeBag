package com.codebag.code.mycode.function.reflection;

import java.lang.reflect.Method;

import android.content.Context;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.function.reflection.Reflection.Test;
import com.codebag.code.mycode.utils.Log;

/**
 * class中的getDeclaredMethod() 和 getMethod() 的区别？ getDeclaredMethod()
 * 获取的是类自身声明的所有方法，包含public、protected和private方法。
 * getMethod()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
 * 所以要调用隐藏方法，或私有方法必须用getDeclaredMethod();
 * 
 * @author zhangrui-ms
 *
 */
public class InvokePrivateMethod extends MyCode {

	Reflection reflection;

	public InvokePrivateMethod(MainActivity context) {
		super(context);
		reflection = new Reflection();
	}

	/**
	 * 基本类型都有自己的Class(不是他们的包装类), byte.class, short.class, long.class
	 */
	@Entry
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

	@Entry
	public void params_objectType() {
		try {
			Method method = Reflection.class.getDeclaredMethod("show",
					new Class[] { Test.class });
			method.setAccessible(true);
			method.invoke(reflection, new Object[] { new Test() });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Entry
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

	@Entry
	public void returnValue() {
		Object returnObj = "null";
		try {
			Method method = Reflection.class.getDeclaredMethod("show");
			method.setAccessible(true);
			returnObj = method.invoke(reflection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.addLog(this, "returnValue=" + returnObj.toString());
	}

	@Entry
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
