package com.codebag.code.mycode.function.reflection;

import java.lang.reflect.Field;

import android.content.Context;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;

public class InvokePrivateField  extends MyCode {

	Reflection reflection;
	
	public InvokePrivateField(MainActivity context) {
		super(context);
		reflection = new Reflection();
	}
	
	@Entry
	public void getFiled() {
		try {
			Field field = Reflection.class.getDeclaredField("name");
			field.setAccessible(true);
			Object obj = field.get(reflection);
			Log.addLog(this, "obj=" + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Entry
	public void setFiled() {
		try {
			Field field = Reflection.class.getDeclaredField("age");
			field.setAccessible(true);
			field.set(reflection, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Log.addLog(this, "setFiled = " + reflection.getAge());
	}

}
