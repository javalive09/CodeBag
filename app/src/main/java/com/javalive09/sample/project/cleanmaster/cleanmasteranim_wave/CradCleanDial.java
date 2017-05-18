package com.javalive09.sample.project.cleanmaster.cleanmasteranim_wave;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.javalive09.sample.utils.DisplayUtil;
import com.javalive09.sample.R;

/**
 * 旋转动画包装类
 * 
 * @author zhangrui-ms
 *
 */
public class CradCleanDial extends FrameLayout {

	private static final int mAnimDuration = 1000;

	private ImageView mDialMarkImage;

	private ImageView mSmallMarkImage;

	private View mRoatingBackGround;

	private ImageView mWhiteBackGround;

	private CardWaveBar mWaveBar;
	
	private CardCakeBar mCakeBar;

	private ImageView mBackGround;

	private RotateAnimation mRoatingAnim;

	public CradCleanDial(Context context) {
		super(context);
		init(context);
	}

	public void setDialMarkResource(int resid) {
		cancelRoatingAnim();
		resetDialMarkImageState();
		mDialMarkImage.setBackgroundResource(resid);
	}

	private boolean cancelRoatingAnim() {
		if (mRoatingAnim != null && !mRoatingAnim.hasEnded()) {
			mRoatingAnim.cancel();
			return true;
		}
		return false;
	}

	public void setWaveProgress(int progress) {
		if (cancelRoatingAnim()) {
			startScaleWhiteBgAnim(progress, mWaveBar, mCakeBar);
		} else {
			resetProgressState(mWaveBar, mCakeBar);
			mWaveBar.setProgress(progress);
		}
	}
	
	public void setCakeProgress(int progress) {
		if (cancelRoatingAnim()) {
			startScaleWhiteBgAnim(progress, mCakeBar, mWaveBar);
		} else {
			resetProgressState(mCakeBar, mWaveBar);
			mCakeBar.setProgress(progress);
		}
	}

	private void resetDialMarkImageState() {
		mDialMarkImage.setVisibility(VISIBLE);
		mWaveBar.setVisibility(INVISIBLE);
		mWaveBar.getDialView().setVisibility(INVISIBLE);
		mCakeBar.setVisibility(INVISIBLE);
		mCakeBar.getDialView().setVisibility(INVISIBLE);
		mSmallMarkImage.setVisibility(VISIBLE);
		mWhiteBackGround.setVisibility(VISIBLE);
		mRoatingBackGround.setVisibility(VISIBLE);
		mBackGround.setVisibility(VISIBLE);
	}

	private void resetRoatingState() {
		mDialMarkImage.setVisibility(INVISIBLE);
		mWaveBar.setVisibility(INVISIBLE);
		mWaveBar.getDialView().setVisibility(INVISIBLE);
		mCakeBar.setVisibility(INVISIBLE);
		mCakeBar.getDialView().setVisibility(INVISIBLE);
		mWaveBar.cancelAnim();
		mCakeBar.cancelAnim();
		mSmallMarkImage.setVisibility(VISIBLE);
		mWhiteBackGround.setVisibility(VISIBLE);
		mRoatingBackGround.setVisibility(VISIBLE);
		mBackGround.setVisibility(VISIBLE);
	}

	private void resetProgressState(CardBar positiveBar, CardBar negativeBar) {
		mDialMarkImage.setVisibility(INVISIBLE);
		positiveBar.setVisibility(VISIBLE);
		positiveBar.getDialView().setVisibility(VISIBLE);
		negativeBar.setVisibility(INVISIBLE);
		negativeBar.getDialView().setVisibility(INVISIBLE);
		
		mSmallMarkImage.setVisibility(INVISIBLE);
		mWhiteBackGround.setVisibility(INVISIBLE);
		mRoatingBackGround.setVisibility(INVISIBLE);
		mRoatingBackGround.setBackgroundColor(Color.TRANSPARENT);
		mBackGround.setVisibility(INVISIBLE);
	}

	public void showRoket() {
		cancelRoatingAnim();
		mRoatingBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_fan);
		mSmallMarkImage.setBackgroundResource(R.drawable.sysclear_card_anim_roket);
		if (mDialMarkImage.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}

	public void showMagnifier() {
		cancelRoatingAnim();
		mRoatingBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_scanner);
		mSmallMarkImage.setBackgroundResource(R.drawable.sysclear_card_anim_magnifier);
		if (mDialMarkImage.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}

	public void showBrush() {
		cancelRoatingAnim();
		mRoatingBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_fan);
		mSmallMarkImage.setBackgroundResource(R.drawable.sysclear_card_anim_brush);
		if (mDialMarkImage.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}

	private void init(Context context) {
		mDialMarkImage = new ImageView(context);
		mRoatingBackGround = new ImageView(context);
		mSmallMarkImage = new ImageView(context);
		
		int progressColor = Color.parseColor("#33ffffff");
		int bgColor = Color.parseColor("#24a0ff");
		
		mWaveBar = new CardWaveBar(context);
		mWaveBar.setColor(progressColor, bgColor);
		
		mCakeBar = new CardCakeBar(context);
		mCakeBar.setColor(progressColor, bgColor);
		
		mBackGround = new ImageView(context);
		mBackGround.setImageResource(R.drawable.sysclear_card_anim_bluebg);
		mWhiteBackGround = new ImageView(context);
		mWhiteBackGround.setImageResource(R.drawable.sysclear_card_anim_circlewhite);
		LayoutParams paramsWrap = new LayoutParams(-1, -1);
		paramsWrap.gravity = Gravity.CENTER;
		resetDialMarkImageState();

		addView(mBackGround, paramsWrap);
		addView(mRoatingBackGround, paramsWrap);

		addView(mWhiteBackGround, paramsWrap);
		addView(mSmallMarkImage, paramsWrap);

		addView(mWaveBar, paramsWrap);
		addView(mCakeBar, paramsWrap);
		
		addView(mDialMarkImage, paramsWrap);
		
		int height = getMeasuredHeight();
		int ply = DisplayUtil.dip2px(getContext(), 6);
		int diameter = DisplayUtil.dip2px(getContext(), 94);
		Log.i("peter", "height=" + height);
		mWaveBar.setData(0, diameter, 9);
		mCakeBar.setData(ply, diameter, 2);
	}
	
	PaintFlagsDrawFilter filter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
            | Paint.FILTER_BITMAP_FLAG);

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.setDrawFilter(filter);
		super.onDraw(canvas);
	}

	private void startDialMarkAnim() {
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(1, 0.6f, 1, 0.6f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, 0.3f); // 透明度，从不透明到透明
		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(false);
		animinationSet.setDuration(mAnimDuration);
		mDialMarkImage.clearAnimation();
		mDialMarkImage.startAnimation(animinationSet);
		animinationSet.setAnimationListener(new MyAnimListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				startRoatingBgAnim();
			}
		});
	}

	private void startRoatingBgAnim() {
		resetRoatingState();
		mRoatingAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mRoatingAnim.setDuration(mAnimDuration);
		mRoatingAnim.setRepeatCount(-1);
		mRoatingAnim.setRepeatMode(Animation.RESTART);
		mRoatingAnim.setInterpolator(new LinearInterpolator());
		mRoatingBackGround.clearAnimation();
		mRoatingBackGround.startAnimation(mRoatingAnim);
	}

	private void startScaleWhiteBgAnim(final int endProgress, final CardBar positiveBar, final CardBar negativeBar) {
		positiveBar.setProgressText(endProgress);
		ScaleAnimation animationScale = new ScaleAnimation(1, 1.8f, 1, 1.8f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlphaHide = new AlphaAnimation(1.0f, 0.0f); // 透明度，从不透明到透明
		mWhiteBackGround.clearAnimation();
		mWhiteBackGround.startAnimation(animationScale);
		animationScale.setDuration(mAnimDuration);
		mSmallMarkImage.clearAnimation();
		mSmallMarkImage.startAnimation(animationAlphaHide);
		mRoatingBackGround.clearAnimation();
		mRoatingBackGround.startAnimation(animationAlphaHide);
		animationAlphaHide.setDuration(mAnimDuration);
		animationScale.setAnimationListener(new MyAnimListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				startCircleBarAnim(endProgress, positiveBar, negativeBar);
			}
		});
	}

	private void startCircleBarAnim(int endProgress, CardBar positiveBar, CardBar negativeBar) {
		resetProgressState(positiveBar, negativeBar);
		View mText = positiveBar.getDialView();
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(0.5f, 1.0f, 0.5f,
				1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlpha = new AlphaAnimation(0.3f, 1.0f);
		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setDuration(mAnimDuration);
		mText.clearAnimation();
		mText.startAnimation(animinationSet);
		positiveBar.startAnimination(endProgress);
	}

	public class MyAnimListener implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

	}

}
