package com.javalive09.sample.function.reflection;


import com.javalive09.codebag.LogUtil;

public class Reflection {

	private int age = 1;
	private String name = "peter";
	
	private String show() {
		LogUtil.i( "show()");
		return "Reflection()";
	}
	
	private void show(int a) {
		LogUtil.i("show(" + a + ")");
	}
	
	private void show(int a, String str) {
		LogUtil.i( "show(" + a + ","+str + ")");
	}
	
	private void show(Test test) {
		LogUtil.i("show(" + test.toString() + ")");
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
