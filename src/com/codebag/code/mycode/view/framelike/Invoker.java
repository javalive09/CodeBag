package com.codebag.code.mycode.view.framelike;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void framelayoutView() {
		FrameLayoutView f1 = new FrameLayoutView(getContext());
		f1.setBackgroundColor(Color.BLUE);
		
		FrameLayout f2 = getMultiView();
		
		View v = new View(getContext());
		v.setBackgroundColor(Color.BLACK);
		
		FrameLayout.LayoutParams params_2 = new FrameLayout.LayoutParams(300, 300);
		params_2.gravity = Gravity.CENTER;
		
		FrameLayout.LayoutParams params_v = new FrameLayout.LayoutParams(500, 500);
		params_v.gravity = Gravity.CENTER;
		
		f1.addView(v, params_v);
		f1.addView(f2, params_2);
		
		showView(f1);
				
	}
	
	@Entry
	public void frameLayout() {
		FrameLayoutView f1 = new FrameLayoutView(getContext());
		
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		f1.addView(getMultiView());
		showView(f1);
	}
	
	@Entry
	public void relativeLayoutView() {
		RelativeLayoutView f1 = new RelativeLayoutView(getContext());
		f1.setBackgroundColor(Color.BLUE);

		FrameLayout f2 = getMultiView();
		
		View v = new View(getContext());
		v.setBackgroundColor(Color.BLACK);
		
		RelativeLayout.LayoutParams params_2 = new RelativeLayout.LayoutParams(300, 300);
		params_2.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		RelativeLayout.LayoutParams params_v = new RelativeLayout.LayoutParams(500, 500);
		params_v.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		f1.addView(v, params_v);
		f1.addView(f2, params_2);
		
		showView(f1);
	}
	
	@Entry
	public void linearLayoutView() {
		LinearLayoutView f1 = new LinearLayoutView(getContext());
		f1.setBackgroundColor(Color.BLUE);
		
		FrameLayout f2 = getMultiView();
		
		View v = new View(getContext());
		v.setBackgroundColor(Color.BLACK);
		
		LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(300,300);
		params_2.gravity = Gravity.CENTER;
		params_2.leftMargin = -400;
		
		LinearLayout.LayoutParams params_v = new LinearLayout.LayoutParams(500,500);
		params_v.gravity = Gravity.CENTER;
		params_v.leftMargin = 110;
		
		f1.addView(v, params_v);
		f1.addView(f2, params_2);
		
		showView(f1);
		
	}
	
	
	private FrameLayout getMultiView() {
		FrameLayout f1 = new FrameLayout(getContext());
		FrameLayout f2 = new FrameLayout(getContext());
		FrameLayout f3 = new FrameLayout(getContext());
		FrameLayout f4 = new FrameLayout(getContext());
		FrameLayout f5 = new FrameLayout(getContext());
		FrameLayout f6 = new FrameLayout(getContext());
		FrameLayout f7 = new FrameLayout(getContext());
		FrameLayout f8 = new FrameLayout(getContext());
		FrameLayout f9 = new FrameLayout(getContext());
		
		f1.setBackgroundColor(Color.BLACK);
		f2.setBackgroundColor(Color.BLACK);
		f3.setBackgroundColor(Color.BLACK);
		f4.setBackgroundColor(Color.BLACK);
		f5.setBackgroundColor(Color.BLACK);
		f6.setBackgroundColor(Color.BLACK);
		f7.setBackgroundColor(Color.BLACK);
		f8.setBackgroundColor(Color.BLACK);
		f9.setBackgroundColor(Color.BLACK);
		
		f1.addView(f2);
		f2.addView(f3);
		f3.addView(f4);
		f4.addView(f5);
		f5.addView(f6);
		f6.addView(f7);
		f7.addView(f8);
		f8.addView(f9);
		
		f9.setBackgroundColor(Color.WHITE);
		return f1;
		
	}

}
