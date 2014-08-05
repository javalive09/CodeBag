package com.codebag.code.mycode.cleanmasterdemo;

import com.codebag.R;
import com.codebag.code.mycode.utils.DisplayUtil;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
	
	private ImageView mBackGround;
	
	private int mPly;
	
	private static final int mAnimDuration = 1000;

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
		mNum.setText(progress + "%");
		return this;
	}

	private void init(Context context) {
		mDialMarkImage = new ImageView(context);
		
		mRoatingBackGround = new ImageView(context);
		
		mSmallMarkImage = new ImageView(context);
		mSmallMarkImage.setVisibility(View.INVISIBLE);
		
		mNum = new TextView(context);
		mNum.setVisibility(View.INVISIBLE);
		mNum.setTextSize(40);
		mNum.setTextColor(0xE624a0ff);
		
		mProgressBar = new RingView(context);
		mProgressBar.setColor(0xE624a0ff, 0x19000000);
		mPly = DisplayUtil.dip2px(context, 12);
		mProgressBar.setVisibility(View.INVISIBLE);
		
		mBackGround = new ImageView(context);
		mBackGround.setImageResource(R.drawable.bluebg);
		
		mWhiteBackGround = new ImageView(context);
		mWhiteBackGround.setImageResource(R.drawable.circlewhite);
		mWhiteBackGround.setVisibility(View.INVISIBLE);
		
		LayoutParams paramsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		LayoutParams paramsFill = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;

		addView(mBackGround, paramsWrap);
		addView(mRoatingBackGround, paramsWrap);
		
		addView(mWhiteBackGround, paramsWrap);
		addView(mSmallMarkImage, paramsWrap);
		
		addView(mProgressBar, paramsFill);
		addView(mNum, paramsWrap);
		addView(mDialMarkImage, paramsWrap);
	}

	public void start() {
		startDialMarkAnim();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mProgressBar.setData(mPly, mBackGround.getMeasuredHeight()- 10);
	}

	private void startDialMarkAnim() {
		AnimationSet animinationSet = new AnimationSet(true);

		ScaleAnimation animationScale = new ScaleAnimation(1, 0.5f, 1, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, 0.3f); // 透明度，从不透明到透明

		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(false);
		animinationSet.setDuration(mAnimDuration);

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
		RotateAnimation animation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(mAnimDuration);
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
				startScaleWhiteBgAnim();
			}
		});
	}
	
	private void startScaleWhiteBgAnim() {
		ScaleAnimation animationScale = new ScaleAnimation(1, 1.60f, 1, 1.60f, 
				Animation.RELATIVE_TO_SELF , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlphaHide = new AlphaAnimation(1.0f, 0.0f); // 透明度，从不透明到透明
		mWhiteBackGround.startAnimation(animationScale);
		animationScale.setDuration(mAnimDuration);
		mSmallMarkImage.startAnimation(animationAlphaHide);
		animationAlphaHide.setDuration(mAnimDuration);
		
		animationScale.setFillAfter(true);
		animationAlphaHide.setFillAfter(true);
		animationScale.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				startCircleBarAnim();
			}
		});
		
	}
	
	private void startCircleBarAnim() {
		
		mProgressBar.setVisibility(View.VISIBLE);
		mRoatingBackGround.setVisibility(View.INVISIBLE);
		mBackGround.setVisibility(View.INVISIBLE);
		mNum.setVisibility(View.VISIBLE);
		AnimationSet animinationSet = new AnimationSet(true);

		ScaleAnimation animationScale = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		AlphaAnimation animationAlpha = new AlphaAnimation(0.3f, 1.0f);

		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(true);
		animinationSet.setDuration(mAnimDuration);
		mNum.startAnimation(animinationSet);
		mProgressBar.startAnimination(mProgress);
	}

}
