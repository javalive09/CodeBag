package com.codebag.bag.main;

import java.lang.reflect.Method;
import java.util.ArrayList;
import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.Node;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MethodNodeActivity extends BaseActivity implements OnClickListener{

	ListAdapter adapter;
	String className;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		ListView listview = (ListView) findViewById(R.id.lv);
		ArrayList<String> list = new ArrayList<String>();
		try {
			className = mNode.className;
			Class<?> cls = Class.forName(className);
			Method[] methods = cls.getDeclaredMethods();
			for (Method m : methods) {
				if (m.isAnnotationPresent(Entry.class)) {
					list.add(m.getName());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		adapter = new ListAdapter(MethodNodeActivity.this, list);
		listview.setAdapter(adapter);
	}

	public class ListAdapter extends BaseAdapter {

		LayoutInflater factory;
		ArrayList<String> mList;
		MethodNodeActivity mContext;

		public ListAdapter(MethodNodeActivity context, ArrayList<String> list) {
			mContext = context;
			mList = list;
			factory = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public String getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				convertView = factory.inflate(R.layout.main_list_item, parent, false);
				holder = new Holder();
				holder.tv = (TextView) convertView
						.findViewById(R.id.listitem_tv);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			Drawable icon = getResources().getDrawable(R.drawable.method);
			icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
			holder.tv.setCompoundDrawables(icon, null, null, null);
			holder.tv.setText(getItem(position) + " ( )");
			convertView.setOnClickListener(mContext);
			convertView.setTag(R.id.main_item_pos, position);
			return convertView;
		}

		class Holder {
			TextView tv;
		}
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag(R.id.main_item_pos);
		String methodName = (String) adapter.getItem(position);
		Node node = new Node(methodName, Node.METHOD, className);
		Intent intent = new Intent(MethodNodeActivity.this, InovkedViewActivity.class);
		intent.putExtra("node", node);
		startActivity(intent);
		Toast.makeText(MethodNodeActivity.this, node.name, Toast.LENGTH_SHORT).show();
	}

}
