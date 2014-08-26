package com.codebag.code.mycode.cleanmaster.cleanmasteranim_ashcanbackup;


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

	private ImageView mDialMark;

	private FrameLayout mSmallMark;

	private View mRoating;

	private ImageView mSmallMarkBg;

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
		mDialMark.setBackgroundResource(resid);
	}
	
	public void setDialMarkResourceFromRoating() {
		mRoating.clearAnimation();
		resetDialMarkImageState();
		mSmallMark.removeAllViews();
		switch(currentRoatingStatus) {
		case STATUS_ZERO:
			mDialMark.setBackgroundResource(R.drawable.card_danager_scan_zero);
			break;
		case STATUS_SMALL:
			mDialMark.setBackgroundResource(R.drawable.card_danager_scan_small);
			break;
		case STATUS_MIDDLE:
			mDialMark.setBackgroundResource(R.drawable.sysclear_card_danger_scan_middle);
			break;
		case STATUS_BIG:
			mDialMark.setBackgroundResource(R.drawable.sysclear_card_danger_scan_big);
			break;
		}
		currentRoatingStatus = STATUS_DEFAULT;
	}

	private boolean cancelRoatingAnim() {
		Animation animation = mRoating.getAnimation();
		if (animation != null && !animation.hasEnded()) {
			mRoating.clearAnimation();
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
		mDialMark.setVisibility(VISIBLE);
		mWaveBar.setVisibility(INVISIBLE);
		mWaveBar.getDialView().setVisibility(INVISIBLE);
		mCakeBar.setVisibility(INVISIBLE);
		mCakeBar.getDialView().setVisibility(INVISIBLE);
		mSmallMark.setVisibility(INVISIBLE);
		mSmallMarkBg.setVisibility(INVISIBLE);
		mRoating.setBackgroundColor(INVISIBLE);
		mBackGround.setVisibility(INVISIBLE);
	}

	private void resetRoatingState() {
		mDialMark.setVisibility(INVISIBLE);
		mWaveBar.setVisibility(INVISIBLE);
		mWaveBar.getDialView().setVisibility(INVISIBLE);
		mCakeBar.setVisibility(INVISIBLE);
		mCakeBar.getDialView().setVisibility(INVISIBLE);
		mWaveBar.cancelAnim();
		mCakeBar.cancelAnim();
		mSmallMark.setVisibility(VISIBLE);
		mSmallMarkBg.setVisibility(VISIBLE);
		mRoating.setVisibility(VISIBLE);
		mBackGround.setVisibility(VISIBLE);
	}

	private void resetProgressState(CardBar positiveBar, CardBar negativeBar) {
		mDialMark.setVisibility(INVISIBLE);
		positiveBar.setVisibility(VISIBLE);
		positiveBar.getDialView().setVisibility(VISIBLE);
		negativeBar.setVisibility(INVISIBLE);
		negativeBar.getDialView().setVisibility(INVISIBLE);
		
		mSmallMark.setVisibility(INVISIBLE);
		mSmallMarkBg.setVisibility(INVISIBLE);
		mRoating.setVisibility(INVISIBLE);
		mRoating.setBackgroundColor(Color.TRANSPARENT);
		mBackGround.setVisibility(INVISIBLE);
	}

	public void showRoket() {
		cancelRoatingAnim();
		mRoating.setBackgroundResource(R.drawable.sysclear_card_anim_fan);
		mSmallMark.setBackgroundResource(R.drawable.sysclear_card_anim_roket);
		mBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_bluebg);
		mSmallMarkBg.setImageResource(R.drawable.sysclear_card_anim_circlewhite);
		if (mDialMark.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}

	public void showMagnifier() {
		cancelRoatingAnim();
		mRoating.setBackgroundResource(R.drawable.sysclear_card_anim_scanner);
		mSmallMark.setBackgroundResource(R.drawable.sysclear_card_anim_magnifier);
		mBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_bluebg);
		mSmallMarkBg.setImageResource(R.drawable.sysclear_card_anim_circlewhite);
		if (mDialMark.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}
	
	public void showRoatingBluePoint() {
		cancelRoatingAnim();
		mRoating.setBackgroundResource(R.drawable.sysclear_card_anim_roating_point);
		mSmallMark.setBackgroundResource(R.drawable.card_danager_scan_zero);
		startRoatingBgAnim();
	}
	
	public void showAshcanRoating() {
		cancelRoatingAnim();
		mBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_circlered);
		mRoating.setBackgroundResource(R.drawable.sysclear_card_anim_roating_ashcan);
		mSmallMark.setBackgroundResource(R.drawable.card_danager_scan_only_zero);
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
		mSmallMark.setBackgroundResource(R.drawable.card_danager_scan_only_small);
		View splitBall = new View(getContext());
		splitBall.setBackgroundResource(res);
		mSmallMark.addView(splitBall);
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
				mSplitBall = mSmallMark.getChildAt(0);
				if(mSplitBall == null) {//没做过动画
					mSplitBall = setSplitBall(R.drawable.card_danager_scan_small_splitball);
					mSplitBall.clearAnimation();
					showSplitBalAnim(mSplitBall, endRoatingStatusListener);
				}else {//做过动画
					setDialMarkResourceFromRoating();
				}
				break;
			case STATUS_BIG:
				int count = mSmallMark.getChildCount();
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
				mSmallMark.setBackgroundResource(R.drawable.card_danager_scan_only_zero);
				break;
			case STATUS_SMALL:
				mSmallMark.setBackgroundResource(R.drawable.card_danager_scan_only_small);
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
		mRoating.setBackgroundResource(R.drawable.sysclear_card_anim_fan);
		mSmallMark.setBackgroundResource(R.drawable.sysclear_card_anim_brush);
		mBackGround.setBackgroundResource(R.drawable.sysclear_card_anim_bluebg);
		mSmallMarkBg.setImageResource(R.drawable.sysclear_card_anim_circlewhite);
		if (mDialMark.getVisibility() == VISIBLE) {// 可见
			startDialMarkAnim();
		} else {
			startRoatingBgAnim();
		}
	}

	private void init(Context context) {
		mDialMark = new ImageView(context);
		mRoating = new ImageView(context);
		mSmallMark = new FrameLayout(context);
		splitBallAnimEnd = false;
		
		int waveProgressColor = getResources().getColor(R.color.card_bar_wave_progress);
		int cakeProgressColor = getResources().getColor(R.color.card_bar_cake_progress);
		int bgColor = getResources().getColor(R.color.card_cleandial_bg);
		
		mWaveBar = new CardWaveBar(context);
		mWaveBar.setColor(waveProgressColor, bgColor);
		
		mCakeBar = new CardCakeBar(context);
		mCakeBar.setColor(cakeProgressColor, bgColor);
		
		mBackGround = new ImageView(context);
		mSmallMarkBg = new ImageView(context);
		
		int widthAndHeight = getResources().getDimensionPixelSize(R.dimen.sysclear_card_anim_pic);
		LayoutParams paramsWrap = new LayoutParams(widthAndHeight, widthAndHeight);
		paramsWrap.gravity = Gravity.CENTER;
		resetDialMarkImageState();

		addView(mBackGround, paramsWrap);
		addView(mRoating, paramsWrap);

		addView(mSmallMarkBg, paramsWrap);
		addView(mSmallMark, paramsWrap);

		addView(mWaveBar, paramsWrap);
		addView(mCakeBar, paramsWrap);
		
		addView(mDialMark, paramsWrap);
		
		int ply = getResources().getDimensionPixelSize(R.dimen.sysclear_card_anim_fan_ply);
		int diameter = getResources().getDimensionPixelSize(R.dimen.sysclear_card_anim_bluecircle);
		
		mWaveBar.setData(0, diameter, 9);
		mCakeBar.setData(ply, diameter + ply, 2);
		
	}
	
	private void resetShrinkDialMark() {
		mSmallMarkBg.setVisibility(VISIBLE);
		mRoating.setVisibility(VISIBLE);
		mBackGround.setVisibility(VISIBLE);
		mSmallMark.setVisibility(VISIBLE);
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
		mDialMark.clearAnimation();
		mDialMark.startAnimation(animinationSet);
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
		mRoating.clearAnimation();
		mRoating.startAnimation(mRoatingAnim);
	}

	private void startScaleWhiteBgAnim(final int endProgress, final CardBar positiveBar, final CardBar negativeBar) {
		positiveBar.setProgressText(endProgress);
		ScaleAnimation animationScale = new ScaleAnimation(1, 1.8f, 1, 1.8f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlphaHide = new AlphaAnimation(1.0f, 0.0f); // 透明度，从不透明到透明
		mSmallMarkBg.clearAnimation();
		mSmallMarkBg.startAnimation(animationScale);
		animationScale.setDuration(mAnimDuration);
		mSmallMark.clearAnimation();
		mSmallMark.startAnimation(animationAlphaHide);
		mRoating.clearAnimation();
		mRoating.startAnimation(animationAlphaHide);
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