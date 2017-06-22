package com.javalive09.sample.function.staticbolck;

import com.javalive09.codebag.logger.Log;

public class FooSon extends Foo{
	static {
//		Log.i("fooson static block");
	}
	
	{
		Log.i("fooson construction block no name");
	}
	
	public FooSon() {
		Log.i("fooson 构造方法");
	}
}
