package com.codebag.code.mycode.view.drawable.drawablecontainer;

import android.content.Context;
import android.view.View;
import android.view.View.*;
import android.widget.Button;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.MultiViews;
import com.codebag.bag.MultiViews.MyAdapter;

public class StateListDrawable_ extends CaseListView implements OnClickListener{

	Button show = null;
	
	public StateListDrawable_(Context context) {
		super(context);
	}
	
	@Entry
	public void show() {
		MultiViews views = new MultiViews(getContext());
		views.setAdapter(new MyAdapter() {
			Button b = null;
			@Override
			public View getView(int position) {
				switch(position) {
				case 0:
					show = new Button(getContext());
					show.setText("___show___");
					b = show;
					break;
				case 1:
					b = new Button(getContext());
					b.setText("enable");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 2:
					b = new Button(getContext());
					b.setText("disable");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 3:
					b = new Button(getContext());
					b.setText("focused");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 4:
					b = new Button(getContext());
					b.setText("selected");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 5:
					b = new Button(getContext());
					b.setText("disSelected");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				}
				return b;
			}
			
			@Override
			public int getCount() {
				return 6;
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case 1:
			show.setEnabled(true);
			break;
		case 2:
			show.setEnabled(false);
			break;
		case 3:
			show.requestFocus();
			break;
		case 4:
			show.setSelected(true);
			break;
		case 5:
			show.setSelected(false);
			break;
		}
	}

}
