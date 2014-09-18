package com.codebag.code.mycode.cleanmaster.cleanmasteranim_circle;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.DisplayUtil;
import com.codebag.code.mycode.utils.MultiViews;
import com.codebag.code.mycode.utils.MultiViews.MyAdapter;

public class Invoker extends MyCode {

	public CradCleanDial c;
	
	public Invoker(MainActivity act) {
		super(act);
		c = new CradCleanDial(act);
		c.setBackgroundColor(Color.DKGRAY);
	}
	
	@Entry
	public void showButtons() {
		MultiViews views = new MultiViews(getActivity(), 3);
		views.setAdapter(new MyAdapter(){

			@Override
			public int getCount() {
				return 7;
			}

			@Override
			public View getView(int position) {
				Button b = new Button(getActivity());
				b.setOnClickListener(listener);
				b.setText(position + "");
				return b;
			}
			
		});
		
		FrameLayout fl = new FrameLayout(getActivity());
		int d = DisplayUtil.dip2px(getActivity(), 110);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(d, d);
		c.setBackgroundColor(Color.BLACK);
		params.gravity = Gravity.CENTER;
		fl.addView(c, params);
		fl.addView(views);
		showView(fl);
	}
	
	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case 0:
				c.showMagnifier();
				break;
			case 1:
				c.setProgress(80);
				break;
			case 2:
				c.showBrush();
				break;
			case 3:
				c.showRoket();
				break;
			case 4:
				c.setDialMarkResource(R.drawable.card_danager_memory);
				break;
			case 5:
				c.setDialMarkResource(R.drawable.card_danager_storage);
				break;
			case 6:
				c.setDialMarkResource(R.drawable.card_danager_system);
				break;
			}
		}
		
	};
	
}
