package com.javalive09.sample.view.framelike;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

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
public class Invoker extends Entry {

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
	
	public void linearLayoutView() {
		LinearLayoutView f1 = new LinearLayoutView(getActivity());

		FrameLayout f2 = getMultiView();

		View v = new View(getActivity());
		v.setBackgroundResource(R.drawable.pic);

		LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(300, 300);
		params_2.gravity = Gravity.CENTER;

		LinearLayout.LayoutParams params_v = new LinearLayout.LayoutParams(500, 500);
		params_v.gravity = Gravity.CENTER;

		WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
		int screenW = wm.getDefaultDisplay().getWidth();
		int marginLeft_v = (screenW - 500) / 2;

		params_v.leftMargin = marginLeft_v;

		int marginLeft_f2 = -100 - 300;
		params_2.leftMargin = marginLeft_f2;

		f1.addView(v, params_v);
		f1.addView(f2, params_2);

		showView(f1);

	}
	
	
	private FrameLayout getMultiView() {
		FrameLayout f1 = new FrameLayout(getActivity());
		f1.setBackgroundResource(R.drawable.pic);
		return f1;
		
	}
	

}
