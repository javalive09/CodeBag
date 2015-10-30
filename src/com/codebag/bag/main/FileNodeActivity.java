package com.codebag.bag.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FileNodeActivity extends BaseActivity implements OnClickListener ,OnLongClickListener{

	ListAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		ListView listview = (ListView) findViewById(R.id.lv);
		adapter = new ListAdapter(FileNodeActivity.this, mNode.mSubNodeList);
		listview.setAdapter(adapter);
	}

	public class ListAdapter extends BaseAdapter {

		static final int NO_ENTRY = 0; // 不含有入口方法
		static final int ENTRY = 1; // 含有入口方法

		FileNodeActivity mContext;
		LayoutInflater factory;
		ArrayList<Node> mList;

		public ListAdapter(FileNodeActivity context, ArrayList<Node> list) {
			mContext = context;
			mList = list;
			factory = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Node getItem(int position) {
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
				holder.tv = (TextView) convertView.findViewById(R.id.listitem_tv);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			Node node = getItem(position);
			Drawable icon = null;
			CharSequence name = node.name;
			switch(node.type) {
			case Node.CLASS:
				icon = getResources().getDrawable(R.drawable.file);
				name = name + ".java";
				break;
			case Node.DIR:
				icon = getResources().getDrawable(R.drawable.folder);
				break;
			case Node.APP:
				icon = getResources().getDrawable(R.drawable.folder);
				break;
			}
			icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
			holder.tv.setCompoundDrawables(icon, null, null, null);
			holder.tv.setText(name);
			if (getItemViewType(position) == NO_ENTRY) {
				convertView.setBackgroundResource(android.R.color.darker_gray);
				convertView.setOnClickListener(null);
			}else {
				convertView.setBackgroundResource(R.drawable.ripple_item_bg);
				convertView.setOnClickListener(mContext);
			}
			
			convertView.setOnLongClickListener(mContext);
			convertView.setTag(R.id.main_item_pos, position);
			return convertView;
		}
		
		@Override
		public int getItemViewType(int position) {
			Node node = (Node) getItem(position);
			if (node.type == Node.CLASS) {
				String className = node.className;
				try {
					Class<?> cls = Class.forName(className);
					Method[] methods = cls.getDeclaredMethods();
					for (Method m : methods) {
						if (m.isAnnotationPresent(Entry.class)) {
							return ENTRY;
						}
					}
					return NO_ENTRY;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			return ENTRY;
		}

		class Holder {
			TextView tv;
		}
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag(R.id.main_item_pos);
		Node node = adapter.getItem(position);
		Intent intent = null;
		switch (node.type) {
		case Node.DIR:
			intent = new Intent(FileNodeActivity.this, FileNodeActivity.class);
			break;
		case Node.CLASS:
			intent = new Intent(FileNodeActivity.this, MethodNodeActivity.class);
			break;
		case Node.APP:
			intent = new Intent(FileNodeActivity.this, AppNodeActivity.class);
			break;
		}
		intent.putExtra("node", adapter.getItem(position));
		startActivity(intent);
	}

	@Override
	public boolean onLongClick(View v) {
		int position = (Integer) v.getTag(R.id.main_item_pos);
		Node node = (Node) adapter.getItem(position);
		if(node.type == Node.CLASS) {
			String dir = node.className.replace(".", "/") + ".java";
			InputStream is = null;
			try {
				is = getAssets().open(dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (is != null) {
				String content = readTextFile(is);
				showAlertDialog(node.name + ".java", content);
			}
		}else if(node.type == Node.DIR) {
			int count = getSubFileCount(node);
			String title = getString(R.string.java_count_title);
			showAlertDialog(title, count + "");
		}else if(node.type == Node.APP) {
			int count = getSubFileCount(node);
			String title = getString(R.string.app_count_title);
			showAlertDialog(title, count + "");
		}
		return true;
	}

	private String readTextFile(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream.toString();
	}
	
	private int getSubFileCount(Node node) {
		int count = 0;
		ArrayList<Node> nodes = node.mSubNodeList;
		if (nodes != null) {
			for (int i = 0, len = nodes.size(); i < len; i++) {
				Node n = nodes.get(i);
				if (n.type == Node.CLASS || n.type == Node.APP) {
					count++;
				} else if (n.type == Node.DIR) {
					count += getSubFileCount(n);
				}
			}
		}
		return count;
	}
}
