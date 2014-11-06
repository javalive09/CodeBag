package com.codebag.code.mycode.function.reflection;

import com.codebag.code.mycode.utils.Log;

public class Reflection {

	private int age = 1;
	private String name = "peter";
	
	private String show() {
		Log.addLog("peter", this, "show()");
		return "Reflection()";
	}
	
	private void show(int a) {
		Log.addLog("peter", this, "show(" + a + ")");
	}
	
	private void show(int a, String str) {
		Log.addLog("peter", this, "show(" + a + ","+str + ")");
	}
	
	private void show(Test test) {
		Log.addLog("peter", this, "show(" + test.toString() + ")");
	}
	
	static class Test{

		@Override
		public String toString() {
			return "custom class Test";
		}
		
	}
	
	public int getAge() {
		return age;
	}
	
	public String getName() {
		return name;
	}
}
