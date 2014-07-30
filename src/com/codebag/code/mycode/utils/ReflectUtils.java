package com.codebag.code.mycode.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtils {

	/**
	 * 通过构造函数实例化对象
	 * 
	 * @param className
	 *            类的全路径名称
	 * @param parameterTypes
	 *            参数类型
	 * @param initargs
	 *            参数值
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object constructorNewInstance(String className,
			Class[] parameterTypes, Object[] initargs) {
		try {
			Constructor<?> constructor = (Constructor<?>) Class.forName(
					className).getDeclaredConstructor(parameterTypes); // 暴力反射
			constructor.setAccessible(true);
			return constructor.newInstance(initargs);
		} catch (Exception ex) {
			throw new RuntimeException();
		}

	}

	/**
	 * 暴力反射获取字段值
	 * 
	 * @param propertyName
	 *            属性名
	 * @param obj
	 *            实例对象
	 * @return 属性值
	 */
	public static Object getFieldValue(String propertyName, Object obj) {
		try {
			Field field = obj.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	/**
	 * 设置字段值
	 * 
	 * @param propertyName
	 *            字段名
	 * @param obj
	 *            实例对象
	 * @param value
	 *            新的字段值
	 * @return
	 */
	public static void setFieldValue(Object obj, String propertyName,
			Object value) {
		try {
			Field field = obj.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}

	/**
	 * 利用递归找一个类的指定方法，如果找不到，去父亲里面找直到最上层Object对象为止。
	 * 
	 * @param clazz
	 *            目标类
	 * @param methodName
	 *            方法名
	 * @param classes
	 *            方法参数类型数组
	 * @return 方法对象
	 * @throws Exception
	 */
	public static Method getMethod(Class clazz, String methodName,
			final Class[] classes) throws Exception {
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, classes);
		} catch (NoSuchMethodException e) {
			try {
				method = clazz.getMethod(methodName, classes);
			} catch (NoSuchMethodException ex) {
				if (clazz.getSuperclass() == null) {
					return method;
				} else {
					method = getMethod(clazz.getSuperclass(), methodName,
							classes);
				}
			}
		}
		return method;
	}

	/**
	 * 
	 * @param obj
	 *            调整方法的对象
	 * @param methodName
	 *            方法名
	 * @param classes
	 *            参数类型数组
	 * @param objects
	 *            参数数组
	 * @return 方法的返回值
	 */
	public static Object invoke(final Object obj, final String methodName,
			final Class[] classes, final Object[] objects) {
		try {
			Method method = getMethod(obj.getClass(), methodName, classes);
			method.setAccessible(true);// 调用private方法的关键一句话
			return method.invoke(obj, objects);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object invoke(final Object obj, final String methodName,
			final Class[] classes) {
		return invoke(obj, methodName, classes, new Object[] {});
	}

	public static Object invoke(final Object obj, final String methodName) {
		return invoke(obj, methodName, new Class[] {}, new Object[] {});
	}

}
