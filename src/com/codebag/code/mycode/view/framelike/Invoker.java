package com.codebag.code.mycode.view.framelike;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codebag.R;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

/**
 * @author peter
 * 
 * 测试结果： framelayout 效率较高
 *framelayout   linearlayout  relativelayout
      5             6              10
      4             6               9
      3	            5               6
      4             4               6
      5             8               10
 */
public class Invoker extends MyCode {

	public Invoker(MainActivity context) {
		super(context);
	}
	
	@Entry
	public void framelayoutView() {
		FrameLayoutView f1 = new FrameLayoutView(getActivity());
		FrameLayout f2 = getMultiView();
		
		View v = new View(getActivity());
		v.setBackgroundResource(R.drawable.pic);
		
		FrameLayout.LayoutParams params_2 = new FrameLayout.LayoutParams(300, 300);
		params_2.gravity = Gravity.CENTER;
		
		FrameLayout.LayoutParams params_v = new FrameLayout.LayoutParams(500, 500);
		params_v.gravity = Gravity.CENTER;
		
		f1.addView(v, params_v);
		f1.addView(f2, params_2);
		
		showView(f1);
				
	}
	
	@Entry
	public void relativeLayoutView() {
		RelativeLayoutView f1 = new RelativeLayoutView(getActivity());
		
		FrameLayout f2 = getMultiView();
		
		View v = new View(getActivity());
		v.setBackgroundResource(R.drawable.pic);
		
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
		LinearLayoutView f1 = new LinearLayoutView(getActivity());
		
		FrameLayout f2 = getMultiView();
		
		View v = new View(getActivity());
		v.setBackgroundResource(R.drawable.pic);
		
		LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(300,300);
		params_2.gravity = Gravity.CENTER;
		
		LinearLayout.LayoutParams params_v = new LinearLayout.LayoutParams(500,500);
		params_v.gravity = Gravity.CENTER;
		
		Point outSize = new Point();
		WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getSize(outSize);
		int screenW = outSize.x;
		int marginLeft_v = (screenW - 500)/2;
		
		params_v.leftMargin = marginLeft_v;
		
		int marginLeft_f2 = -100 - 300;
		params_2.leftMargin = marginLeft_f2;
		
		f1.addView(v, params_v);
		f1.addView(f2, params_2);
		
		showView(f1);
		
	}
	
	@Entry
	public void showTextView() {
		FrameLayout fl = new FrameLayout(getActivity());
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER;
		fl.setBackgroundColor(Color.CYAN);
		
		TextView v = new TextView(getActivity());
		v.setText("123456789");
		v.setTextSize(50);
		v.setTextColor(Color.WHITE);
		v.setBackgroundColor(Color.BLUE);
		
		fl.addView(v, params);
		
		showView(fl);
	}
	
	
	private FrameLayout getMultiView() {
		FrameLayout f1 = new FrameLayout(getActivity());
		f1.setBackgroundResource(R.drawable.pic);
		return f1;
		
	}
	

}
