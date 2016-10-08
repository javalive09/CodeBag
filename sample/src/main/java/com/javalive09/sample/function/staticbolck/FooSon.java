package com.javalive09.sample.function.staticbolck;

public class FooSon extends Foo{
	static {
		System.out.println("fooson static block"); 
	}
	
	{
		System.out.println("fooson construction block no name"); 
	}
	
	public FooSon() {
		System.out.println("fooson 构造方法");
	}
}
