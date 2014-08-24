package com.codebag.code.mycode.cleanmasteranim_wave;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 卡片圆形进度
 * 
 * @author zhangrui-ms
 *
 */
public class CardCakeBar extends CardBar {
	
	private LinearLayout mText;

	private CakeProgressBar mProgressBar;

	private TextView mNum;
	
	private TextView mPercentSign;

	public CardCakeBar(Context context) {
		super(context);
	}

	protected void init(Context context) {
		mText = new LinearLayout(getContext());
		LinearLayout l = new LinearLayout(getContext());
		l.setOrientation(LinearLayout.VERTICAL);

		mNum = new TextView(context);
		mNum.setTextSize(36);
		mNum.setTextColor(Color.WHITE);
		mNum.setLineSpacing(0.0f, 0.8f);

		TextView used = new TextView(context);
		used.setTextSize(14);
		used.setTextColor(Color.WHITE);
		used.setGravity(Gravity.CENTER | Gravity.TOP);
		used.setText("已用");

		mPercentSign = new TextView(getContext());
		mPercentSign.setText("%");
		mPercentSign.setTextSize(12);
		mPercentSign.setPadding(0, 13, 0, 0);
		mPercentSign.setTextColor(Color.WHITE);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER;

		l.addView(mNum);
		l.addView(used);

		mText.addView(l, params);

		LinearLayout.LayoutParams paramsP = new LinearLayout.LayoutParams(-2,
				-2);
		paramsP.gravity = Gravity.LEFT | Gravity.TOP;

		mText.addView(mPercentSign, paramsP);

		mProgressBar = new CakeProgressBar(context);
		LayoutParams paramsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		LayoutParams paramsFill = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;

		addView(mProgressBar, paramsFill);
		addView(mText, paramsWrap);
	}
	
	public void setData(int ply, int diameter, int speed) {
		mProgressBar.setData(ply, diameter, speed);
	}
	
	public void setColor(int progressColor, int backGroundColor) {
		mProgressBar.setColor(progressColor, backGroundColor);
	}
	
	public void setProgressText(long progress) {
		mNum.setText(progress+"");
	}
	
	public void setProgress(long progress) {
		setProgressText(progress);
		mProgressBar.setProgress(progress);
	}
	
	public View getDialView() {
		return mText;
	}
	
	public void startAnimination(int endProgress) {
		setProgressText(endProgress);
		mProgressBar.startAnimination(endProgress);
	}
	
	public void cancelAnim() {
		mProgressBar.cancelAnim();
	}


}
