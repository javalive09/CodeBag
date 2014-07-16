package com.codebag.code.mycode.function.recursion;

import android.content.Context;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;


/**
 *  阶乘
 * @author peter
 *
 */
public class Factorial extends CaseListView {

	public Factorial(Context context) {
		super(context);
	}
	
	@Entry()
	public void function() {
		int result = factorial(5);
		Log.addLog(this, "result = " + result);
	}
	
	private int factorial(int n) {
		if(n < 0) {
			return -1;
		}else if(n == 0) {
			return 1;
		}else if(n == 1) {
			return 1;
		}else {
			return n* factorial(n - 1);
		}
	}
	

}
