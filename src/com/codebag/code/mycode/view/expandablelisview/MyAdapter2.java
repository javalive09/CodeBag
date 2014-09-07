package com.codebag.code.mycode.view.expandablelisview;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.codebag.R;
import com.codebag.code.mycode.utils.Log;

public class MyAdapter2 extends BaseExpandableListAdapter {
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
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.addLog(this, "" + v);
			}
		});
		g.addView(tv);
		g.addView(b);
		g.setBackgroundColor(Color.CYAN);
		return g;
	}

	int [] colors = new int[]{Color.BLACK, Color.BLUE, Color.RED, Color.GRAY};
	
	/**
	 * 显示：child
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		LinearLayout row = new LinearLayout(context);
		LayoutInflater factory = LayoutInflater.from(context);
		final int totalSize = child.get(groupPosition).size();
		for (int i = 0; i < mColumn; i++) {
			//获取view
			View cell = factory.inflate(R.layout.cell, row, false);
			row.addView(cell, getDefaultLayoutParams());
			
			//view赋值
			final int childIndex = childPosition * mColumn + i;
			
			if(childIndex < totalSize) {
				String name = getChild(groupPosition, childIndex);
				TextView tv = (TextView) cell.findViewById(R.id.tv);
				tv.setText(name);
				ImageView iv = (ImageView) cell.findViewById(R.id.iv);
				iv.setImageResource(R.drawable.card_danager_memory);
			}
		}
		
		
		return row;
		
	}
	
	protected LayoutParams getDefaultLayoutParams() {
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		params.weight = 1;
		return params;
	}

	class ViewHolder {
		TextView textView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}
