package com.javalive09.sample.function.reflection;

import java.lang.reflect.Field;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

public class InvokePrivateField  extends Entry {

	Reflection reflection;
	
	public InvokePrivateField() {
		reflection = new Reflection();
	}
	
	public void getFiled() {
		try {
			Field field = Reflection.class.getDeclaredField("name");
			field.setAccessible(true);
			Object obj = field.get(reflection);
			Log.i("obj=" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setFiled() {
		try {
			Field field = Reflection.class.getDeclaredField("age");
			field.setAccessible(true);
			field.set(reflection, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Log.i("setFiled = " + reflection.getAge());
	}

}
