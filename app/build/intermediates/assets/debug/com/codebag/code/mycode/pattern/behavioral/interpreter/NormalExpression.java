package com.codebag.code.mycode.pattern.behavioral.interpreter;

import java.util.ArrayList;

import android.util.Log;

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
