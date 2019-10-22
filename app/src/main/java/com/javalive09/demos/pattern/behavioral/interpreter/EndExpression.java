package com.javalive09.demos.pattern.behavioral.interpreter;

public class EndExpression implements Expression {

	private int number;

	public EndExpression(String s) {
		number = Integer.parseInt(s);
	}

	@Override
	public int interpret() {
		return number;
	}

}
