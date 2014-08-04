package com.codebag.code.mycode.cleanmasterdemo;

import com.codebag.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CleanDial extends FrameLayout {

	private ImageView mDialMarkImage;

	private ImageView mSmallMarkImage;

	private View mRoatingBackGround;

	private ImageView mWhiteBackGround;

	private TextView mNum;

	private RingView mProgressBar;

	private int mProgress;

	public CleanDial(Context context) {
		super(context);
		init(context);
	}

	public CleanDial setDialMarkResource(int resid) {
		mDialMarkImage.setBackgroundResource(resid);
		return this;
	}

	public CleanDial setSmallMarkResource(int resid) {
		mSmallMarkImage.setBackgroundResource(resid);
		return this;
	}

	public CleanDial setRoatingBg(int resid) {
		mRoatingBackGround.setBackgroundResource(resid);
		return this;
	}

	public CleanDial setProgress(int progress) {
		mProgress = progress;
		return this;
	}

	private void init(Context context) {
		mDialMarkImage = new ImageView(context);
		
		mRoatingBackGround = new ImageView(context);
		mRoatingBackGround.setVisibility(View.INVISIBLE);
		
		mSmallMarkImage = new ImageView(context);
		mSmallMarkImage.setVisibility(View.INVISIBLE);
		
		mNum = new TextView(context);
		mNum.setVisibility(View.INVISIBLE);
		
		mProgressBar = new RingView(context);
		
		mWhiteBackGround = new ImageView(context);
		mWhiteBackGround.setImageResource(R.drawable.circlewhite);
		mWhiteBackGround.setVisibility(View.INVISIBLE);
		LayoutParams paramsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		LayoutParams paramsFill = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;

		addView(mRoatingBackGround, paramsWrap);
		addView(mProgressBar, paramsFill);
		addView(mSmallMarkImage, paramsWrap);
		addView(mNum, paramsWrap);
		addView(mWhiteBackGround, paramsWrap);
		addView(mDialMarkImage, paramsWrap);
		setBackgroundResource(R.drawable.bluebg);
	}

	public void start() {
		startDialMarkAnim();
	}

	private void startDialMarkAnim() {
		AnimationSet animinationSet = new AnimationSet(true);

		ScaleAnimation animationScale = new ScaleAnimation(1, 0.5f, 1, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, 0.5f); // 透明度，从不透明到透明

		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(false);
		animinationSet.setDuration(2000);

		mDialMarkImage.startAnimation(animinationSet);
		animinationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				startRoatingBgAnim();
			}
		});
	}
	
	private void startRoatingBgAnim() {
		mDialMarkImage.setVisibility(View.GONE);
		mSmallMarkImage.setVisibility(View.VISIBLE);
		mWhiteBackGround.setVisibility(View.VISIBLE);
		mRoatingBackGround.setVisibility(View.VISIBLE);
		RotateAnimation animation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(1000);
		animation.setFillAfter(false);
//		animation.setInterpolator(new DecelerateInterpolator(1.0f));
		mRoatingBackGround.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				
			}
		});
	}

}
