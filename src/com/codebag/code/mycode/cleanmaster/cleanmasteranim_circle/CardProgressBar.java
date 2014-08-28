package com.codebag.code.mycode.cleanmaster.cleanmasteranim_circle;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 卡片圆形进度
 * 
 * @author zhangrui-ms
 *
 */
public class CardProgressBar extends FrameLayout {
	
	private LinearLayout mText;

	private CircleProgressBar mProgressBar;

	private TextView mNum;
	
	private TextView mPercentSign;

	public CardProgressBar(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mText = new LinearLayout(getContext());
		mNum = new TextView(context);
		mNum.setTextSize(36);
		mNum.setTextColor(0xff24a0ff);

		mPercentSign = new TextView(getContext());
		mPercentSign.setText("%");
		mPercentSign.setTextSize(12);
		mPercentSign.setPadding(0, 13, 0, 0);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER;

		mText.addView(mNum, params);

		LinearLayout.LayoutParams paramsP = new LinearLayout.LayoutParams(-2, -2);
		paramsP.gravity = Gravity.LEFT | Gravity.TOP;

		mText.addView(mPercentSign, paramsP);
		
		mProgressBar = new CircleProgressBar(context);
		LayoutParams paramsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LayoutParams paramsFill = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;

		addView(mProgressBar, paramsFill);
		addView(mText, paramsWrap);
	}
	
	public void setData(int innerCirclePly, int outerCirclePly, int innerCircleDiameter, int outerCircleDiameter) {
		mProgressBar.setData(innerCirclePly, outerCirclePly, innerCircleDiameter, outerCircleDiameter, 2);
	}
	
	public void setColor(int innerCircleColor, int outerCircleColor) {
		mProgressBar.setColor(innerCircleColor, outerCircleColor);
		mPercentSign.setTextColor(outerCircleColor);
		mNum.setTextColor(outerCircleColor);
	}
	
	public void setProgressText(int progress) {
		mNum.setText(progress+"");
	}
	
	public void setProgress(int progress) {
		mProgressBar.setProgress(progress);
		mNum.setText(progress+"");
	}
	
	public View getDialView() {
		return mText;
	}
	
	public void startAnimination(int endProgress) {
		mProgressBar.startAnimination(endProgress);
	}


}
