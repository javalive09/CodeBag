package com.javalive09.demos.pattern.behavioral.interpreter;

public class MinusExpression implements Expression {

	private Expression l;
	private Expression r;

	public MinusExpression(Expression l, Expression r) {
		this.l = l;
		this.r = r;
	}


	@Override
	public int interpret() {
		return l.interpret() - r.interpret();
	}

}
