package com.codebag.code.mycode.view.layoutopt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.view.View.*;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.MultiViews;
import com.codebag.code.mycode.utils.MultiViews.MyAdapter;

public class ViewStub_ extends MyCode implements OnClickListener{

	View mView;
	
	public ViewStub_(MainActivity context) {
		super(context);
	}

	/**
	 * ViewStub的应用场景：viewStub是用来操控layout文件的，主要用来延迟view加载时机。
	 * 注：Inflate() 返回的是布局文件root View的弱引用
	 */
	@Entry
	public void showViewStub() {
		MultiViews views = new MultiViews(getActivity(), 1);
		views.setAdapter(new MyAdapter() {
			
			@Override
			public View getView(int position) {
				switch(position) {
				case 0:
					Button bt = new Button(getActivity());
					bt.setText("stub1");
					bt.setOnClickListener(ViewStub_.this);
					return bt;
				case 1:
					bt = new Button(getActivity());
					bt.setText("stub2");
					bt.setOnClickListener(ViewStub_.this);
					return bt;
				case 2:
					 LayoutInflater factory = LayoutInflater.from(getActivity());
					return mView = factory.inflate(R.layout.viewstub, null, false);
				}
				return null;
			}
			
			@Override
			public int getCount() {
				return 3;
			}
		});
		showView(views);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case 0:
			ViewStub stub1 = (ViewStub) mView.findViewById(R.id.stub1);
			stub1.inflate();
			v.setEnabled(false);
			break;
			
		case 1:
			ViewStub stub2 = (ViewStub) mView.findViewById(R.id.stub2);
			stub2.inflate();
			v.setEnabled(false);
			break;
		}
	}
}
