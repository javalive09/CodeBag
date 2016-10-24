package com.javalive09.sample.view.expandablelisview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.sample.R;

/**
 * 可分组显示的gridview cell填满
 */
public class Invoker1 extends Entry {

	private ExpandableListView listView;
	private List<String> group;
	private List<List<String>> child;
	private MyAdapter2 adapter;

	public void showExpandList() {
		LayoutInflater factory = LayoutInflater.from(getViewActivity());
		View root = factory.inflate(R.layout.expand_list_view, null, false);
		listView = (ExpandableListView) root
				.findViewById(R.id.expandableListView);
		initData();
		adapter = new MyAdapter2(getViewActivity(), group, child);
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

	public static class MyAdapter2 extends BaseExpandableListAdapter {
		private Context context;
		private List<String> group;
		private List<List<String>> child;
		private int mColumn;

		public MyAdapter2(Context context, List<String> group,
						  List<List<String>> child) {
			this.context = context;
			this.group = group;
			this.child = child;

			int width = context.getResources().getDisplayMetrics().widthPixels;
			if (width >= 480) {
				mColumn = 4;
			} else {
				mColumn = 2;
			}
		}

		@Override
		public int getGroupCount() {
			return group.size();
		}

		/*
         * rowView 的行数
         */
		@Override
		public int getChildrenCount(int groupPosition) {
			int totalCount = child.get(groupPosition).size();
			if (totalCount % mColumn == 0) {
				return totalCount / mColumn;
			}
			return totalCount / mColumn + 1;
		}

		@Override
		public String getGroup(int groupPosition) {
			return group.get(groupPosition);
		}

		@Override
		public String getChild(int groupPosition, int childPosition) {
			return child.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		/**
		 * 显示：group
		 */
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
								 View convertView, ViewGroup parent) {

			LinearLayout g = new LinearLayout(context);
			g.setOrientation(LinearLayout.HORIZONTAL);
			TextView tv = new TextView(context);
			tv.setText(group.get(groupPosition));
			Button b = new Button(context);
			b.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					LogUtil.i("" + v);
				}
			});
			g.addView(tv);
			g.addView(b);
			g.setBackgroundColor(Color.CYAN);
			return g;
		}

		/**
		 * 显示：child
		 */
		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

			LinearLayout row = new LinearLayout(context);
			row.setBackgroundColor(Color.BLACK);
			LayoutInflater factory = LayoutInflater.from(context);
			int totalSize = child.get(groupPosition).size();
			for (int i = 0; i < mColumn; i++) {
				//获取view
				View cell = factory.inflate(R.layout.cell, row, false);//不可以用attach = true， 因为这样会使用生成的parentView的LayoutParams。效果不对
				row.addView(cell);

				//view赋值
				final int childIndex = childPosition * mColumn + i;

				if(childIndex < totalSize) {
					String name = getChild(groupPosition, childIndex);
					TextView tv = (TextView) cell.findViewById(R.id.tv);
					tv.setText(name);
					ImageView iv = (ImageView) cell.findViewById(R.id.iv);
					iv.setImageResource(R.drawable.card_danager_memory);
					iv.setBackgroundColor(Color.WHITE);
				}
			}

			return row;

		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}
	}


}
