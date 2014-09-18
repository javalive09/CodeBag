package com.codebag.code.mycode.cleanmaster.cleanmasteranim_frame;


/*
import com.qihoo.cleandroid_cn.R;

import android.content.Context;
import android.graphics.Color;
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

/*
public class CradCleanDial2 extends FrameLayout {

	private static final int mAnimDuration = 1000;

	private ImageView mDialMarkImage;

	private FrameLayout mSmallMarkImage;

	private View mRoatingBackGround;

	private ImageView mWhiteBackGround;

	private CardWaveBar mWaveBar;
	
	private CardCakeBar mCakeBar;

	private ImageView mBackGround;

	private boolean splitBallAnimEnd;
	
	private View mSplitBall;

	public CradCleanDial2(Context context) {
		super(context);
		init(context);
	}

	public void setDialMarkResource(int resid) {
		cancelRoatingAnim();
		resetDialMarkImageState();
		mDialMarkImage.setBackgroundResource(resid);
	}
	
	public void setDialMarkResourceFromRoating() {
		mRoatingBackGround.clearAnimation();
		resetDialMarkImageState();
		mSmallMarkImage.removeAllViews();
		switch(currentRoatingStatus) {
		case STATUS_ZERO:
			mDialMarkImage.setBackgroundResource(R.drawable.card_danager_scan_zero);
			break;
		case STATUS_SMALL:
			mDialMarkImage.setBackgroundResource(R.drawable.card_danager_scan_small);
			break;
		case STATUS_MIDDLE:
			mDialMarkImage.setBackgroundResource(R.drawable.sysclear_card_danger_scan_middle);
			break;
		case STATUS_BIG:
			mDialMarkImage.setBackgroundResource(R.drawable.sysclear_card_danger_scan_big);
			break;
		}
		currentRoatingStatus = STATUS_DEFAULT;
	}

	private boolean cancelRoatingAnim() {
		
		Animation animation = mRoatingBackGround.getAnimation();
		if (animation != null && !animation.hasEnded()) {
			mRoatingBackGround.clearAnimation();
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
		mSmallMarkImage.setVisibility(INVISIBLE);
		mWhiteBackGround.setVisibility(INVISIBLE);
		mRoatingBackGround.setBackgroundColor(INVISIBLE);
		mBackGround.setVisibility(INVISIBLE);
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
	
	public void showRoatingBluePoint() {
		cancelRoatingAnim();
		mRoatingBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_roating_point);
		mSmallMarkImage.setBackgroundResource(R.drawable.card_danager_scan_zero);
		startRoatingBgAnim();
	}
	
	private static final int STATUS_DEFAULT = -1;
	private static final int STATUS_ZERO = 0;
	private static final int STATUS_SMALL = 30;
	private static final int STATUS_MIDDLE = 50;
	private static final int STATUS_BIG = 80;
	private int currentRoatingStatus = STATUS_DEFAULT;
	
	private int checkStatus(long progress) {
		if(progress > STATUS_ZERO && progress < STATUS_SMALL * 1000 * 1024) {
			return STATUS_ZERO;
		}else if(progress < STATUS_MIDDLE * 1000 * 1024) {
			return STATUS_SMALL;
		}else if(progress < STATUS_BIG * 1000 * 1024) {
			return STATUS_MIDDLE;
		}else {
			return STATUS_BIG;
		}
	}
	
	public View setSplitBall(int res) {
		mSmallMarkImage.setBackgroundResource(R.drawable.card_danager_scan_small);
		View splitBall = new View(getActivity());
		splitBall.setBackgroundResource(res);
		mSmallMarkImage.addView(splitBall);
		return splitBall;
	}
	
	public void endRoatingStatus(long progress) {
		int status = checkStatus(progress);
		if(!splitBallAnimEnd) {
			splitBallAnimEnd = true;
			currentRoatingStatus = status;
			switch(status) {
			case STATUS_ZERO:
			case STATUS_SMALL:
				setDialMarkResourceFromRoating();
				break;
			case STATUS_MIDDLE:
				mSplitBall = mSmallMarkImage.getChildAt(0);
				if(mSplitBall == null) {//没做过动画
					mSplitBall = setSplitBall(R.drawable.card_danager_scan_small_splitball);
					mSplitBall.clearAnimation();
					showSplitBalAnim(mSplitBall, endRoatingStatusListener);
				}else {//做过动画
					setDialMarkResourceFromRoating();
				}
				break;
			case STATUS_BIG:
				int count = mSmallMarkImage.getChildCount();
				if(count == 2) {//都做过动画
					setDialMarkResourceFromRoating();
				}else if(count == 1) {//小纸团做过动画
					mSplitBall = setSplitBall(R.drawable.card_danager_scan_big_splitball);
					mSplitBall.clearAnimation();
					showSplitBalAnim(mSplitBall, endRoatingStatusListener);
				}else {//都未做过动画
					mSplitBall = setSplitBall(R.drawable.card_danager_scan_double_splitball);
					mSplitBall.clearAnimation();
					showSplitBalAnim(mSplitBall, endRoatingStatusListener);
				}
				break;
			}
		}else {
			if(mSplitBall != null) {
				mSplitBall.clearAnimation();
			}
			setDialMarkResourceFromRoating();
		}
	}
	
	private MyAnimListener endRoatingStatusListener = new MyAnimListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            setDialMarkResourceFromRoating();
        }
    };
	
	public void updateRoatingStatus(long progress) {
		int status = checkStatus(progress);
		if(status != currentRoatingStatus) {
			currentRoatingStatus = status;
			switch(currentRoatingStatus) {
			case STATUS_ZERO:
				mSmallMarkImage.setBackgroundResource(R.drawable.card_danager_scan_zero);
				break;
			case STATUS_SMALL:
				mSmallMarkImage.setBackgroundResource(R.drawable.card_danager_scan_small);
				break;
			case STATUS_MIDDLE:
				mSplitBall = setSplitBall(R.drawable.card_danager_scan_small_splitball);
				mSplitBall.clearAnimation();
				showSplitBalAnim(mSplitBall, null);
				break;
			case STATUS_BIG:
				mSplitBall = setSplitBall(R.drawable.card_danager_scan_big_splitball);
				mSplitBall.clearAnimation();
				showSplitBalAnim(mSplitBall, null);
				break;
			}
		}
	}
	
	private void showSplitBalAnim(View view, AnimationListener listener) {
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlpha = new AlphaAnimation(0.1f, 1.0f); // 透明度，从不透明到透明
		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(true);
		animinationSet.setDuration(mAnimDuration);
		view.clearAnimation();
		view.startAnimation(animinationSet);
		animinationSet.setAnimationListener(listener);
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
		mSmallMarkImage = new FrameLayout(context);
		splitBallAnimEnd = false;
		
		int waveProgressColor = getResources().getColor(R.color.card_bar_wave_progress);
		int cakeProgressColor = getResources().getColor(R.color.card_bar_cake_progress);
		int bgColor = getResources().getColor(R.color.card_cleandial_bg);
		
		mWaveBar = new CardWaveBar(context);
		mWaveBar.setColor(waveProgressColor, bgColor);
		
		mCakeBar = new CardCakeBar(context);
		mCakeBar.setColor(cakeProgressColor, bgColor);
		
		mBackGround = new ImageView(context);
		mBackGround.setImageResource(R.drawable.sysclear_card_anim_bluebg);
		mWhiteBackGround = new ImageView(context);
		mWhiteBackGround.setImageResource(R.drawable.sysclear_card_anim_circlewhite);
		
		int widthAndHeight = getResources().getDimensionPixelSize(R.dimen.sysclear_card_anim_pic);
		LayoutParams paramsWrap = new LayoutParams(widthAndHeight, widthAndHeight);
		paramsWrap.gravity = Gravity.CENTER;
		resetDialMarkImageState();

		addView(mBackGround, paramsWrap);
		addView(mRoatingBackGround, paramsWrap);

		addView(mWhiteBackGround, paramsWrap);
		addView(mSmallMarkImage, paramsWrap);

		addView(mWaveBar, paramsWrap);
		addView(mCakeBar, paramsWrap);
		
		addView(mDialMarkImage, paramsWrap);
		
		int ply = getResources().getDimensionPixelSize(R.dimen.sysclear_card_anim_fan_ply);
		int diameter = getResources().getDimensionPixelSize(R.dimen.sysclear_card_anim_bluecircle);
		
		mWaveBar.setData(0, diameter, 9);
		mCakeBar.setData(ply, diameter + ply, 2);
		
	}
	
	private void resetShrinkDialMark() {
		mWhiteBackGround.setVisibility(VISIBLE);
		mRoatingBackGround.setVisibility(VISIBLE);
		mBackGround.setVisibility(VISIBLE);
		mSmallMarkImage.setVisibility(VISIBLE);
	}

	private void startDialMarkAnim() {
		resetShrinkDialMark();
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(1, 0.6f, 1, 0.6f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, 0.3f); // 透明度，从不透明到透明
		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(false);
		animinationSet.setDuration(mAnimDuration/2);
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
		RotateAnimation mRoatingAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
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
*/