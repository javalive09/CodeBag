package com.codebag.code.mycode.cleanmasteranim_text;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class MyTextview extends CaseListView {

	public MyTextview(Context context) {
		super(context);
	}
	
	@Entry
	public void invoke_linearlayout() {
		LinearLayout mText = new LinearLayout(getContext());
		mText.setOrientation(LinearLayout.VERTICAL);
		LinearLayout l = new LinearLayout(getContext());
		l.setGravity(Gravity.CENTER);
		
		//百分比数字
		TextView mNum = new TextView(getContext());
		mNum.setTextSize(36);
		mNum.setTextColor(Color.WHITE);
		mNum.setLineSpacing(0.0f, 0.8f);
		int color = Color.parseColor("#33ffffff");
		mNum.setShadowLayer(1, 0, 4, color);
		mNum.setGravity(Gravity.RIGHT);
		mNum.setText("88");
		
		//已使用文字
		TextView used = new TextView(getContext());
		used.setTextSize(14);
		used.setTextColor(Color.WHITE);
		used.setGravity(Gravity.CENTER | Gravity.TOP);
		used.setText("已用");
		used.setGravity(Gravity.CENTER);
		//百分比符号
		TextView mPercentSign = new TextView(getContext());
		mPercentSign.setText("%");
		mPercentSign.setTextSize(12);
		mPercentSign.setPadding(0, 13, 0, 0);
		mPercentSign.setTextColor(Color.WHITE);
		mPercentSign.setShadowLayer(1, 0, 2, color);
		
		//组合view
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.RIGHT;
		
		LinearLayout.LayoutParams paramsP = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT);
		paramsP.gravity = Gravity.LEFT | Gravity.TOP;
		
		l.addView(mNum,params);
		l.addView(mPercentSign, paramsP);
		
		mText.addView(l);
		mText.addView(used);
		
		mNum.setBackgroundColor(Color.RED);
		used.setBackgroundColor(Color.GREEN);
		l.setBackgroundColor(Color.BLACK);
		mText.setBackgroundColor(Color.GRAY);

		LinearLayout.LayoutParams paramsWrap = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams paramsFill = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;
		
		showView(mText, wrapContentParams(Gravity.CENTER));
		
	}
	
	@Entry
	public void invoke_relativelayout() {
		RelativeLayout mText = new RelativeLayout(getContext());
		
		//百分比数字
		TextView mNum = new TextView(getContext()){

			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				// TODO Auto-generated method stub
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			}
			
		};
		mNum.setTextSize(36);
		mNum.setTextColor(Color.WHITE);
//		mNum.setLineSpacing(0.0f, 0.8f);
		int color = Color.parseColor("#33ffffff");
		mNum.setShadowLayer(1, 0, 4, color);
		mNum.setGravity(Gravity.TOP);
		mNum.setText("88");
		
		
		//已使用文字
		TextView used = new TextView(getContext());
		used.setTextSize(14);
		used.setTextColor(Color.WHITE);
		used.setGravity(Gravity.CENTER | Gravity.TOP);
		used.setText("已用");
		used.setGravity(Gravity.CENTER);
		
		//百分比符号
		TextView mPercentSign = new TextView(getContext());
		mPercentSign.setText("%");
		mPercentSign.setTextSize(12);
		mPercentSign.setPadding(0, 13, 0, 0);
		mPercentSign.setTextColor(Color.WHITE);
		mPercentSign.setShadowLayer(1, 0, 2, color);
		
		//组合view
		RelativeLayout.LayoutParams params_num = new RelativeLayout.LayoutParams(-2, -2);
		params_num.addRule(RelativeLayout.CENTER_IN_PARENT);
		mText.addView(mNum, params_num);
		
		
		mNum.setBackgroundColor(Color.RED);
		used.setBackgroundColor(Color.GREEN);
		mText.setBackgroundColor(Color.GRAY);
		
		showView(mText, wrapContentParams(Gravity.CENTER));
		
	}

}
