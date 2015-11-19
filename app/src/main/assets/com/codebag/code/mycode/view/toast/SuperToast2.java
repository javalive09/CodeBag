package com.codebag.code.mycode.view.toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.widget.Toast;

/**
 * 无效果，无用
 *
 */
@Deprecated
public class SuperToast2 {

	private Toast toast;
	private Field field;
	private Object obj;
	private Method showMethod,hideMethod;
	
	public SuperToast2(Toast toast) {
		this.toast = toast;
		reflectionTN();
	}
	
	private void reflectionTN() {
		try {
			field = toast.getClass().getDeclaredField("mTN");
			field.setAccessible(true);
			obj = field.get(toast);
			showMethod = obj.getClass().getDeclaredMethod("show", null);
			hideMethod = obj.getClass().getDeclaredMethod("hide", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void show() {
		try {
			showMethod.invoke(obj, null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void hide() {
		try {
			hideMethod.invoke(obj, null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}//调用TN对象的hide()方法，关闭toast
	}
	
}
