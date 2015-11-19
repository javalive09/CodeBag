package com.codebag.code.mycode.breakpoint;

import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 
 * 参考：
 * http://www.oschina.net/translate/again-10-tips-on-java-debugging-with-eclipse
 *
 */
public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}

	/**
	 * 一，一般断点
	 */
	@Entry
	public void breakpoint_normal() {
		int a = 1;
		int b = 2;
		int c = a + b;
		int d = a + b + c;
		Log.i("peter", "d = " + d);
	}

	/**
	 * 二， 条件断点
	 *  1.命中次数
	 *  2.条件
	 */
	public void breakpoint_conditaion() {
		for (int i = 0; i < 1000; i++) {
			Log.i("peter", "i = " + i);
		}
	}
	
	/**
	 * 三， 异常断点
	 * 
	 * 当程序有异常，而不知道具体位置的时候可以使用这个断点
	 */
	public void breakpoint_exception() {
		int a = 0;
		int b = 1;
		
		int c = b / a;
		
		Log.i("peter", "c =" + c);
	}
	
	/**
	 * 四， 显示逻辑结构 如: hashMap
	 * Show Logical Structure
	 */
	public void breakpoint_Show_Logical_Structure() {
		//在variables tab 栏上
	}
	
	/**
	 * 五， 修改变量值 
	 */
	public void breakpoint_change_value() {
		//在variables tab 栏右键菜单 change value... 项
	}

}
