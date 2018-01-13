package com.javalive09.demos.pattern.behavioral.mediator;

/**
 * 一个功能的内部的各个模块有千丝万缕的联系。中介者用来解耦。
 */
public class Invoker{

	public void invoke() {
		Mediator m = new Mediator();
		ClassA a = new ClassA();
		ClassB b = new ClassB();
		ClassC c = new ClassC();
		
		m.setA(a);
		m.setB(b);
		m.setC(c);
		
		a.optA(m);
		b.optB(m);
		c.optC(m);
	}

}
