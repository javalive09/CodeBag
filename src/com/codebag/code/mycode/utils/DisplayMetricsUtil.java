package com.codebag.code.mycode.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.MultiViews;
import com.codebag.bag.MultiViews.MyAdapter;

public class DisplayMetricsUtil extends CaseListView{

	public DisplayMetricsUtil(Context context) {
		super(context);
	}

	@Entry
	public void getDisplayMetrics() {
		MultiViews views = new MultiViews(getContext(), 1);
		views.setAdapter(new MyAdapter() {
			TextView view = null;
			DisplayMetrics dm = getResources().getDisplayMetrics();
			
			@Override
			public View getView(int position) {
				switch(position) {
				case 0:
					view = new TextView(getContext());
					view.setText("手机型号: " + android.os.Build.MODEL);
					break;
				case 1:
					view = new TextView(getContext());
					view.setText("SDK版本号: " + android.os.Build.VERSION.SDK_INT);
					break;
				case 2:
					view = new TextView(getContext());
					view.setText("固件版本: " + android.os.Build.VERSION.RELEASE);
					break;
				case 3:
					view = new TextView(getContext());
					String resolution = dm.widthPixels +" x " + dm.heightPixels;
					view.setText("分辨率: " + resolution);
					break;
				case 4:
					view = new TextView(getContext());
					view.setText("屏幕密度: " + dm.densityDpi);
					break;
				}
				return view;
			}
			
			@Override
			public int getCount() {
				return 5;
			}
		});
		views.setBackgroundColor(Color.WHITE);
		
		showView(views, fillParentParams(Gravity.CENTER));
	}
	
	
	
}
