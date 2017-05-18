package com.javalive09.sample.study.pattern.behavioral.interpreter;

import android.util.Log;

public class EndExpression implements Expression {

	@Override
	public void express() {
		Log.i("peter", "end express");
	}

}
