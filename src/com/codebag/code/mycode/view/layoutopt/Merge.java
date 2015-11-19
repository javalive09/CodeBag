package com.codebag.code.mycode.view.layoutopt;

import android.content.Context;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class Merge extends MyCode {

	public Merge(InovkedViewActivity context) {
		super(context);
	}
	
	/**
	 * merge标签的主要使用场景是：减少根元素的冗余。
	 * 代码加载merge文件时需要 inflate (int resource, ViewGroup root, boolean attachToRoot) attachToRoot为true。
	 */
	@Entry
	public void showMerge() {
		showView(R.layout.merge);
	}

}
