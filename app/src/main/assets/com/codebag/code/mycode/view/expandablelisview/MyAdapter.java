package com.codebag.code.mycode.view.expandablelisview;

import java.util.List;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.codebag.R;
import com.codebag.code.mycode.utils.Log;

public class MyAdapter extends BaseExpandableListAdapter {
	private Context context;
    private List<String> group;
    private List<List<String>> child;
 
    public MyAdapter(Context context, List<String> group,
            List<List<String>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }
 
    @Override
    public int getGroupCount() {
        return group.size();
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return child.size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(childPosition).get(childPosition);
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
     * 注意： 如果groupview中有button ExpandableListView无法展开
     * 
     * 如果含有，把它改成TextView或者ImageView试试，可能问题就解决。
	 * 原因：布局文件的监听事件顺序是组件视图，然后才是布局文件本事，
	 * 而像OnItemClickListener这样的监听事件，都是监听的布局，
	 * 如果上面有了Button这样的，就会被拦截而监听不到了
     * 
     * 显示：group
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
    	
    	LinearLayout g = new LinearLayout(context);
    	g.setOrientation(LinearLayout.HORIZONTAL);
    	TextView tv = new TextView(context);
    	tv.setText(group.get(groupPosition));
//    	Button b = new Button(context);
//    	b.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Log.addLog("peter", this, "" + v);
//			}
//		});
    	g.addView(tv);
//    	g.addView(b);
//    	g.setBackgroundColor(Color.CYAN);
    	return g;
    }
     
    /**
     * 显示：child
     */
    @Override
    public View getChildView(int groupPosition, int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.expand_list_item, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView
                    .findViewById(R.id.textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(child.get(groupPosition).get(childPosition));
        holder.textView.setTextSize(20);
        return convertView;
    }
 
    class ViewHolder {
        TextView textView;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
