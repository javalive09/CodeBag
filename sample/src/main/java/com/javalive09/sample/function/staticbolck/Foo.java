package com.javalive09.sample.function.staticbolck;

public class Foo {
	
	static {//静态代码块
		System.out.println("foo static block"); 
	}
	
	{//构造代码块
		System.out.println("foo construction block no name"); 
	}
	
	public Foo() {
		{//普通代码块
			System.out.println("normal block");
		}
		System.out.println("foo 构造方法");
	}
}
