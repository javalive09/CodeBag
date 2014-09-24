package com.codebag.code.mycode.test.staticbolck;

public class Foo {
	
	static {
		System.out.println("static block"); 
	}
	
	{
		System.out.println("static block no name"); 
	}
	
	public Foo() {
		System.out.println("构造方法");
	}
}
