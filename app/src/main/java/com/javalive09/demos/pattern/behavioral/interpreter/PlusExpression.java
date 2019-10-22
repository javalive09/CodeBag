package com.javalive09.demos.pattern.behavioral.interpreter;

import android.util.Log;

import java.util.ArrayList;

public class PlusExpression implements Expression {

	private Expression l;
	private Expression r;

	public PlusExpression(Expression l, Expression r) {
		this.l = l;
		this.r = r;
	}
	
	@Override
	public int interpret() {
		return l.interpret() + r.interpret();
	}

}
