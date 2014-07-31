package com.codebag.code.mycode.utils;

import android.content.Context;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class InvokeReflectUtils extends CaseListView {

	public InvokeReflectUtils(Context context) {
		super(context);
	}
	
	@Entry
	public void invoke_private_method() {
		TestMethod method = new TestMethod();
		method.setName("456");;
		String name = (String) ReflectUtils.invoke(method, "getName");
		Log.addLog(this, "name = " + name);
	}

	class TestMethod {
		private String name;
		
		private String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
}
