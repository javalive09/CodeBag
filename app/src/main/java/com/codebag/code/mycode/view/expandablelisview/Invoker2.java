package com.codebag.code.mycode.view.expandablelisview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoker2 extends MyCode {

	private ExpandableListView listView;
	private List<String> group;
	private List<List<String>> child;
	private MyAdapter2 adapter;

	public Invoker2(InovkedViewActivity context) {
		super(context);
	}

	@Entry
	public void showExpandList() {
		LayoutInflater factory = LayoutInflater.from(getActivity());
		View root = factory.inflate(R.layout.expand_list_view, null, false);
		listView = (ExpandableListView) root
				.findViewById(R.id.expandableListView);
		initData();
		adapter = new MyAdapter2(getActivity(), group, child);
		listView.setAdapter(adapter);
		for (int i = 0; i < adapter.getGroupCount(); i++) {
			listView.expandGroup(i);
		}
		
		listView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
			
		});
		
		showView(root);
		
		
	}

	private void initData() {
		group = new ArrayList<String>();
		child = new ArrayList<List<String>>();
		addInfo("北京", new String[] { "朝阳", "海淀", "东城区", "西城区",  "朝阳1", "海淀2", "东城区3", "西城区4",  "朝阳", "海淀", "东城区", "西城区",  "朝阳1", "海淀2", "东城区3", "西城区4"});
		addInfo("河北", new String[] { "邯郸", "石家庄", "邢台", "朝阳1", "海淀2", "东城区3", "西城区4"});
		addInfo("广东", new String[] { "广州", "深圳", "珠海" , "朝阳1", "海淀2", "东城区3", "西城区4"});
		addInfo("辽宁", new String[] { "广州", "深圳", "珠海" , "朝阳1", "海淀2", "东城区3", "西城区4"});
		addInfo("山东", new String[] { "广州", "深圳", "珠海" , "朝阳1", "海淀2", "东城区3", "西城区4"});
	}

	/**
	 * 添加数据信息
	 * 
	 * @param g
	 * @param c
	 */
	private void addInfo(String g, String[] c) {
		group.add(g);
		List<String> list = new ArrayList<String>();
		for (int i = 0, len = c.length; i < len; i++) {
			list.add(c[i]);
		}
		child.add(list);
	}

}
