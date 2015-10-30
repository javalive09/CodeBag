package com.codebag.bag.main;

import java.util.ArrayList;
import java.util.List;

import com.codebag.R;
import com.codebag.bag.Node;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AppNodeActivity extends BaseActivity implements OnClickListener{

	ListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		ListView listview = (ListView) findViewById(R.id.lv);
		adapter = new ListAdapter(AppNodeActivity.this, mNode.mSubNodeList);
		listview.setAdapter(adapter);
	}
	
	public static class ListAdapter extends BaseAdapter {

		LayoutInflater factory;
		ArrayList<Node> mList;
		AppNodeActivity mContext;

		public ListAdapter(AppNodeActivity context, ArrayList<Node> list) {
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
				convertView = View.inflate(mContext, R.layout.main_list_item, null);
				holder = new Holder();
				holder.tv = (TextView) convertView.findViewById(R.id.listitem_tv);
				convertView.setTag(holder);
			}else {
				holder = (Holder) convertView.getTag();
			}
			Node node = getItem(position);
			try {
				PackageManager pm = mContext.getPackageManager();
				ApplicationInfo info = pm.getApplicationInfo(node.name, 0);
				Drawable drawable = pm.getApplicationIcon(info);
				BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
				Drawable icon = getRightSizeIcon(bitmapDrawable);
				icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
				holder.tv.setCompoundDrawables(icon, null, null, null);
				holder.tv.setText(pm.getApplicationLabel(info));
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
			convertView.setOnClickListener(mContext);
			convertView.setTag(R.id.main_item_pos, position);
			
			return convertView;
		}
		
		private Drawable getRightSizeIcon(BitmapDrawable drawable) {
			Drawable rightDrawable = mContext.getResources().getDrawable(R.drawable.ic_launcher);
			int rightSize = rightDrawable.getIntrinsicWidth();
			Bitmap bitmap = drawable.getBitmap();
			int width = bitmap.getWidth();
			float widths = width;
			float scale = rightSize / widths;
			Matrix matrix = new Matrix();
			matrix.setScale(scale, scale);
			Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			return new BitmapDrawable(mContext.getResources(), bm);
		}
		
		class Holder{
			TextView tv;
		}
		
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag(R.id.main_item_pos);
		Node node = adapter.getItem(position);
		if (node != null) {
			Intent intent = new Intent();
			intent.setPackage(node.name);
			intent.setAction(Intent.ACTION_MAIN);
			List<ResolveInfo> infos = getPackageManager().queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
			ResolveInfo inf = infos.get(0);
			String className = inf.activityInfo.name;
			intent.setClassName(node.name, className);
			startActivity(intent);
		}
	}
	
}
