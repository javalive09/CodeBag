package com.javalive09.demos.pattern.behavioral.interpreter;

import android.util.Log;

import java.util.ArrayList;

public class NormalExpression implements Expression {

	private ArrayList<Expression> mList = new ArrayList<Expression>();
	
	@Override
	public void express() {
		Log.i("peter", "normal express");
		for(Expression e: mList) {
			e.express();
		}
	}
	
	public void add(Expression e) {
		mList.add(e);
	}

}
