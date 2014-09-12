package com.codebag.code.mycode.view.inflate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	LayoutInflater factory;
	FrameLayout root;
	
	public Invoker(Context context) {
		super(context);
		factory = LayoutInflater.from(context);
		root = new FrameLayout(getContext());
	}
	
	@Entry
	public void a_inflate_1_res_ok__2_root_null() {
		
		//返回temp
		View v = factory.inflate(R.layout.time_appwidget, null);
		showView(v);
	}
	
	/**
	 * 返回
	 */
	@Entry
	public void b_inflate_1_res_ok__2_root_ok() {
		
		//返回root
		View v = factory.inflate(R.layout.time_appwidget, root);
		showView(v);
	}
	
	@Entry
	public void c_inflate_1_res_ok__2_root_null_attach_false() {
		//返回temp
		View v = factory.inflate(R.layout.time_appwidget, null, false);
		showView(v);
	}
	
	@Entry
	public void d_inflate_1_res_ok__2_root_ok_attach_false() {
		//返回temp （temp被设置上root生成的layoutparam）
		View v = factory.inflate(R.layout.time_appwidget, root, false);
		showView(v);
	}
	
	@Entry
	public void e_inflate_1_res_ok__2_root_ok_attach_true() {
		//返回root
		View v = factory.inflate(R.layout.time_appwidget, root, true);
		showView(v);
	}
}
