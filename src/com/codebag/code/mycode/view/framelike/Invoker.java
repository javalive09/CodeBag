package com.codebag.code.mycode.view.framelike;

import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.codebag.R;
import com.codebag.bag.CaseListView;
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
public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void framelayoutView() {
		FrameLayoutView f1 = new FrameLayoutView(getContext());
		f1.setBackgroundResource(R.drawable.pic);
		
		FrameLayout f2 = getMultiView();
		
		View v = new View(getContext());
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
		RelativeLayoutView f1 = new RelativeLayoutView(getContext());
		f1.setBackgroundResource(R.drawable.pic);

		FrameLayout f2 = getMultiView();
		
		View v = new View(getContext());
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
		LinearLayoutView f1 = new LinearLayoutView(getContext());
		f1.setBackgroundResource(R.drawable.pic);
		
		FrameLayout f2 = getMultiView();
		
		View v = new View(getContext());
		v.setBackgroundResource(R.drawable.pic);
		
		LinearLayout.LayoutParams params_2 = new LinearLayout.LayoutParams(300,300);
		params_2.gravity = Gravity.CENTER;
		
		LinearLayout.LayoutParams params_v = new LinearLayout.LayoutParams(500,500);
		params_v.gravity = Gravity.CENTER;
		
		Point outSize = new Point();
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
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
		
		f1.setBackgroundResource(R.drawable.pic);
		f2.setBackgroundResource(R.drawable.pic);
		f3.setBackgroundResource(R.drawable.pic);
		f4.setBackgroundResource(R.drawable.pic);
		f5.setBackgroundResource(R.drawable.pic);
		f6.setBackgroundResource(R.drawable.pic);
		f7.setBackgroundResource(R.drawable.pic);
		f8.setBackgroundResource(R.drawable.pic);
		f9.setBackgroundResource(R.drawable.pic);
		
		f1.addView(f2);
		f2.addView(f3);
		f3.addView(f4);
		f4.addView(f5);
		f5.addView(f6);
		f6.addView(f7);
		f7.addView(f8);
		f8.addView(f9);
		
		return f1;
		
	}

}
