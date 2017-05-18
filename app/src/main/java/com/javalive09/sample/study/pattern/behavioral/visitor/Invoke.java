package com.javalive09.sample.study.pattern.behavioral.visitor;

import com.javalive09.codebag.Entry;

/**
 *封装（用容器）一些施加于某种数据结构元素之上的操作，在不改变数据结构的前提下，修改元素操作。
 */
public class Invoke extends Entry {

	public void invoke() {
		new Structure().doing(new Visitor() {

			@Override
			void visit(Element e) {
				e.operate2();
			}
			
		});
	}

}
