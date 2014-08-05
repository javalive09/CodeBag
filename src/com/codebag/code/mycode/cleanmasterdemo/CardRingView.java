package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardRingView extends FrameLayout {

	private LinearLayout mText;

	private RingView mProgressBar;

	private TextView mNum;
	
	private TextView mPercentSign;

	private int mPly;

	public CardRingView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mText = new LinearLayout(getContext());
		mNum = new TextView(context);
		mNum.setTextSize(40);

		mPercentSign = new TextView(getContext());
		mPercentSign.setText("%");
		mPercentSign.setTextSize(20);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER;

		mText.addView(mNum, params);

		LinearLayout.LayoutParams paramsP = new LinearLayout.LayoutParams(-2, -2);
		paramsP.gravity = Gravity.LEFT | Gravity.TOP;

		mText.addView(mPercentSign, paramsP);
		
		mProgressBar = new RingView(context);
		LayoutParams paramsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LayoutParams paramsFill = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;

		addView(mProgressBar, paramsFill);
		addView(mText, paramsWrap);
	}
	
	public void setData(int ply, int diameter) {
		mPly = ply;
		mProgressBar.setData(ply, diameter);
	}
	
	public void setColor(int progressColor, int backGroundColor) {
		mProgressBar.setColor(progressColor, backGroundColor);
		mPercentSign.setTextColor(progressColor);
		mNum.setTextColor(progressColor);
	}
	
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		if(mPly != 0) {
//			int width = getMeasuredWidth();
//			Log.i("peter", "width = " + width);
//			mProgressBar.setData(mPly, 1280);
//		}
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//	}
	
	public void setProgress(int progress) {
//		mProgressBar.setProgress(progress);
		mNum.setText(progress+"");
	}
	
	public View getDialView() {
		return mText;
	}
	
	public void startAnimination(int endProgress) {
		mProgressBar.startAnimination(endProgress);
	}

}
