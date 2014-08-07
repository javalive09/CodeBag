package com.codebag.code.mycode.cleanmasteranim;

import com.codebag.R;
import com.codebag.code.mycode.utils.DisplayUtil;

import android.content.Context;
import android.graphics.Color;
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

	private CardProgressBar mProgressBar;

	private ImageView mBackGround;

	private RotateAnimation mRoatingAnim;

	private int mPly;

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

	public void setProgress(int progress) {
		if (cancelRoatingAnim()) {
			startScaleWhiteBgAnim(progress);
		} else {
			resetProgressState();
			mProgressBar.setProgress(progress);
		}
	}

	private void resetDialMarkImageState() {
		mDialMarkImage.setVisibility(VISIBLE);
		mProgressBar.setVisibility(INVISIBLE);
		mProgressBar.getDialView().setVisibility(INVISIBLE);
		mSmallMarkImage.setVisibility(VISIBLE);
		mWhiteBackGround.setVisibility(VISIBLE);
		mRoatingBackGround.setVisibility(VISIBLE);
		mBackGround.setVisibility(VISIBLE);
	}

	private void resetRoatingState() {
		mDialMarkImage.setVisibility(INVISIBLE);
		mProgressBar.setVisibility(INVISIBLE);
		mProgressBar.getDialView().setVisibility(INVISIBLE);
		mSmallMarkImage.setVisibility(VISIBLE);
		mWhiteBackGround.setVisibility(VISIBLE);
		mRoatingBackGround.setVisibility(VISIBLE);
		mBackGround.setVisibility(VISIBLE);
	}

	private void resetProgressState() {
		mDialMarkImage.setVisibility(INVISIBLE);
		mProgressBar.setVisibility(VISIBLE);
		mProgressBar.getDialView().setVisibility(VISIBLE);
		mSmallMarkImage.setVisibility(INVISIBLE);
		mWhiteBackGround.setVisibility(INVISIBLE);
		mRoatingBackGround.setVisibility(INVISIBLE);
		mRoatingBackGround.setBackgroundColor(Color.TRANSPARENT);
		mBackGround.setVisibility(INVISIBLE);
	}

	public void showRoket() {
		cancelRoatingAnim();
		mRoatingBackGround
				.setBackgroundResource(R.drawable.sysclear_card_anim_fan);
		mSmallMarkImage
				.setBackgroundResource(R.drawable.sysclear_card_anim_roket);
		if (mDialMarkImage.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}

	public void showMagnifier() {
		cancelRoatingAnim();
		mRoatingBackGround
				.setBackgroundResource(R.drawable.sysclear_card_anim_scanner);
		mSmallMarkImage
				.setBackgroundResource(R.drawable.sysclear_card_anim_magnifier);
		if (mDialMarkImage.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}

	public void showBrush() {
		cancelRoatingAnim();
		mRoatingBackGround
				.setBackgroundResource(R.drawable.sysclear_card_anim_fan);
		mSmallMarkImage
				.setBackgroundResource(R.drawable.sysclear_card_anim_brush);
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
		mProgressBar = new CardProgressBar(context);
		mProgressBar.setColor(0xE624a0ff, 0x19000000);
		mPly = DisplayUtil.dip2px(context, 6);
		mBackGround = new ImageView(context);
		mBackGround.setImageResource(R.drawable.sysclear_card_anim_bluebg);
		mWhiteBackGround = new ImageView(context);
		mWhiteBackGround
				.setImageResource(R.drawable.sysclear_card_anim_circlewhite);
		LayoutParams paramsWrap = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		LayoutParams paramsFill = new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		paramsWrap.gravity = Gravity.CENTER;
		paramsFill.gravity = Gravity.CENTER;

		resetDialMarkImageState();

		addView(mBackGround, paramsWrap);
		addView(mRoatingBackGround, paramsWrap);

		addView(mWhiteBackGround, paramsWrap);
		addView(mSmallMarkImage, paramsWrap);

		addView(mProgressBar, paramsFill);
		addView(mDialMarkImage, paramsWrap);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int pad = DisplayUtil.dip2px(getContext(), 16);
		int height = mBackGround.getMeasuredHeight();
		Log.i("peter", "height=" + height);
		mProgressBar.setData(mPly, height - pad);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private void startDialMarkAnim() {
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(1, 0.6f, 1, 0.6f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
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

	private void startScaleWhiteBgAnim(final int endProgress) {
		mProgressBar.setProgressText(endProgress);
		ScaleAnimation animationScale = new ScaleAnimation(1, 1.40f, 1, 1.40f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
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
				startCircleBarAnim(endProgress);
			}
		});
	}

	private void startCircleBarAnim(int endProgress) {
		resetProgressState();
		View mText = mProgressBar.getDialView();
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
		mProgressBar.startAnimination(endProgress);
		// mProgressBar.setProgress(endProgress);
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
