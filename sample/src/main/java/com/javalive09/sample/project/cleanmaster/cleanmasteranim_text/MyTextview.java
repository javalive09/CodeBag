package com.javalive09.sample.project.cleanmaster.cleanmasteranim_text;

import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class MyTextview extends Entry {


	public void invoke_linearlayout() {
		LinearLayout mText = new LinearLayout(getActivity());
		mText.setOrientation(LinearLayout.VERTICAL);
		LinearLayout l = new LinearLayout(getActivity());
		l.setGravity(Gravity.CENTER);
		
		//百分比数字
		TextView mNum = new TextView(getActivity());
		mNum.setTextSize(36);
		mNum.setTextColor(Color.WHITE);
		mNum.setLineSpacing(0.0f, 0.8f);
		int color = Color.parseColor("#33ffffff");
		mNum.setShadowLayer(1, 0, 4, color);
		mNum.setGravity(Gravity.RIGHT);
		mNum.setText("88");
		
		//已使用文字
		TextView used = new TextView(getActivity());
		used.setTextSize(14);
		used.setTextColor(Color.WHITE);
		used.setGravity(Gravity.CENTER | Gravity.TOP);
		used.setText("已用");
		used.setGravity(Gravity.CENTER);
		//百分比符号
		TextView mPercentSign = new TextView(getActivity());
		mPercentSign.setText("%");
		mPercentSign.setTextSize(12);
		mPercentSign.setPadding(0, 13, 0, 0);
		mPercentSign.setTextColor(Color.WHITE);
		mPercentSign.setShadowLayer(1, 0, 2, color);
		
		//组合view
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.RIGHT;
		
		LayoutParams paramsP = new LayoutParams(LayoutParams.WRAP_CONTENT,
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

		LayoutParams paramsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		LayoutParams paramsFill = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;
		
		showView(mText);
		
	}
	
	public void invoke_relativelayout() {
		RelativeLayout mText = new RelativeLayout(getActivity());
		
		//百分比数字
		TextView mNum = new TextView(getActivity()){

			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
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
		mNum.setLines(1);
		mNum.setLineSpacing(0.0f, 0.81f);
		
		//已使用文字
		TextView used = new TextView(getActivity());
		used.setTextSize(14);
		used.setTextColor(Color.WHITE);
		used.setGravity(Gravity.CENTER | Gravity.TOP);
		used.setText("已用");
		used.setGravity(Gravity.CENTER);
		
		//百分比符号
		TextView mPercentSign = new TextView(getActivity());
		mPercentSign.setText("%");
		mPercentSign.setTextSize(12);
		mPercentSign.setPadding(0, 13, 0, 0);
		mPercentSign.setTextColor(Color.WHITE);
		mPercentSign.setShadowLayer(1, 0, 2, color);
		
		
		//组合view
		//num
		RelativeLayout.LayoutParams params_num = new RelativeLayout.LayoutParams(-2, -2);
		params_num.addRule(RelativeLayout.CENTER_IN_PARENT);
		mText.addView(mNum, params_num);
		mNum.setId(R.id.one);
		//percent
	    RelativeLayout.LayoutParams params_percent = new RelativeLayout.LayoutParams
	    		(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    params_percent.addRule(RelativeLayout.RIGHT_OF, R.id.one);
	    params_percent.addRule(RelativeLayout.ALIGN_TOP, R.id.one);
	    mText.addView(mPercentSign, params_percent);
	    //used
	    RelativeLayout.LayoutParams params_used = new RelativeLayout.LayoutParams
	    		(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    params_used.addRule(RelativeLayout.BELOW, R.id.one);
	    params_used.addRule(RelativeLayout.CENTER_HORIZONTAL, R.id.one);
	    
	    mText.addView(used, params_used);
	    
		mNum.setBackgroundColor(Color.RED);
		used.setBackgroundColor(Color.GREEN);
		mText.setBackgroundColor(Color.GRAY);
		
		showView(mText);
		
	}

}
