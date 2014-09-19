package com.codebag.code.mycode.resource.xliff;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * noxliff:XML Localization Interchange File Format
 * XML本地化数据交换格式。
 * 用于把XML中的一些变值作为变量，从而提高效率
 * 
 * android中主要用于动态的在本地资源中加入一些可以作为变量的字符。
 * 
 * %n$ms：代表输出的是字符串，n代表是第几个参数，设置m的值可以在输出之前放置空格 
 * %n$md：代表输出的是整数，n代表是第几个参数，设置m的值可以在输出之前放置空格，也可以设为0m,在输出之前放置m个0 
 * %n$mf：代表输出的是浮点数，n代表是第几个参数，设置m的值可以控制小数位数，如m=2.2时，输出格式为00.00 
 * 也可简单写成： 
 * %d   （表示整数） 
 * %f    （表示浮点数） 
 * %s   （表示字符串） 
 * 
 */
public class XLIFF extends MyCode{

	public XLIFF(MainActivity context) {
		super(context);
	}
	
	
	
	

}
