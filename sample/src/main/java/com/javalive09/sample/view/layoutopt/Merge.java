package com.javalive09.sample.view.layoutopt;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Merge extends Entry {


	/**
	 * merge标签的主要使用场景是：减少根元素的冗余。
	 * 代码加载merge文件时需要 inflate (int resource, ViewGroup Guide, boolean attachToRoot) attachToRoot为true。
	 */
	public void showMerge() {
		showView(R.layout.merge);
	}

}
