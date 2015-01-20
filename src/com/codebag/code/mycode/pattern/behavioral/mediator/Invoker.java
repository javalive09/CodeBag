package com.codebag.code.mycode.pattern.behavioral.mediator;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 一个功能的内部的各个模块有千丝万缕的联系。中介者用来解耦。
 */
public class Invoker extends MyCode {

	public Invoker(MainActivity act) {
		super(act);
	}
	
	@Entry
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
