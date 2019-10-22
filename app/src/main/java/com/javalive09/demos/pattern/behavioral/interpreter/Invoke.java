package com.javalive09.demos.pattern.behavioral.interpreter;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 *有待完善
 *
 */
public class Invoke {

	@Run
	public void invoke(CodeActivity codeActivity) {

		// (1 + 3) + (5 - 2);
		Expression expression = new PlusExpression(new PlusExpression(new EndExpression("1"), new EndExpression("3")),
				new MinusExpression(new EndExpression("5"), new EndExpression("2")));
		codeActivity.showText(expression.interpret() + "");
	}

}
