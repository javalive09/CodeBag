package com.javalive09.sample.function.string;

import android.graphics.Color;
import android.widget.TextView;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * 使用 String.format 格式化输出字符串来动态的在本地资源中加入一些可以作为变量的字符。
 * 
 * %n$ms：代表输出的是字符串，n代表是第几个参数，设置m的值可以在输出之前放置空格 
 * %n$md：代表输出的是整数，n代表是第几个参数，设置m的值可以在输出之前放置空格，也可以设为0m,在输出之前放置m个0 
 * %n$mf：代表输出的是浮点数，n代表是第几个参数，设置m的值可以控制小数位数，如m=2.2时，输出格式为00.00 
 * 也可简单写成： 
 * %d   （表示整数） 
 * %f    （表示浮点数） 
 * %s   （表示字符串） 
 * 参考：http://blueram.iteye.com/blog/441683
 */
public class StringFormat extends Entry{

	public void format_name() {
		String nameFormat = getViewActivity().getResources().getString(R.string.format_test_name);
		String name = String.format(nameFormat, "张瑞");
		TextView tv = new TextView(getViewActivity());
		tv.setBackgroundColor(Color.WHITE);
		tv.setText(name);
		showView(tv);
	}
	
	public void format_old() {
		String nameFormat = getViewActivity().getResources().getString(R.string.format_test_old);
		String name = String.format(nameFormat, 20);
		TextView tv = new TextView(getViewActivity());
		tv.setBackgroundColor(Color.WHITE);
		tv.setText(name);
		showView(tv);
	}
	
	public void format_name_place() {
		String nameFormat = getViewActivity().getResources().getString(R.string.format_test_name_place);
		String name = String.format(nameFormat, "张瑞", "辽宁");
		TextView tv = new TextView(getViewActivity());
		tv.setBackgroundColor(Color.WHITE);
		tv.setText(name);
		showView(tv);
	}
	
	public void format_name_place_old() {
		String nameFormat = getViewActivity().getResources().getString(R.string.format_test_name_place_old);
		String name = String.format(nameFormat, "张瑞", "辽宁", 20);
		TextView tv = new TextView(getViewActivity());
		tv.setBackgroundColor(Color.WHITE);
		tv.setText(name);
		showView(tv);
	}
	
	
	
	

}
