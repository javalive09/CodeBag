package com.codebag.code.mycode.cleanmasteranim_frame;

import android.R.color;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.codebag.R;
import com.codebag.code.mycode.cleanmasteranim_wave.CardCakeBar;
import com.codebag.code.mycode.cleanmasteranim_wave.CardWaveBar;

public class CardAniminationController {
	
	private static final int mAnimDuration = 1000;
	private static final int TRANSPARENT = -1;
	
	private Context mContext;
	
	private CardCleanDialFrame mFrame;//动画框架
	
	//Roating Anim
	
	public static final int ROKET_ROATING = 1;//小火箭
	public static final int MAGNIFIER_ROATING = 2;//放大镜
	public static final int BRUSH_ROATING = 3;//小刷子
	public static final int ASHCAN_ROATING = 4;//垃圾桶
	public static final int ASHCAN_ROATING_UPDATE = 5;//垃圾桶更新
	public static final int ASHCAN_ROATING_END = 6;//垃圾桶更新结束
	
	private FrameLayout mScanMark;//扫描标记
	private View mScanMarkBg;//扫描标记BG
	private View mScannerRoating;//旋转View
	private FrameLayout mScanner; //扫描
	
	//Mark
	public static final int DIAL_MARK = 7;//表盘标记
	
	private View mMark; //大图标
	
	//progress
	public static final int PROGRESS_CAKE = 8;
	public static final int PROGRESS_WAVE = 9;
	
	private CardWaveBar mWaveBar;//进度条
	private CardCakeBar mCakeBar;//进度条

	private int mCurrentScene;
	
	public CardAniminationController(Context context, CardCleanDialFrame frame) {
		mContext = context;
		mFrame = frame;
	}
	
	public void action(int scene, int markRes, long data) {
		
		switch(scene){
		case ROKET_ROATING:
			//装载
			initRoating(R.drawable.sysclear_card_anim_roket, R.drawable.sysclear_card_anim_circlewhite,
					R.drawable.sysclear_card_anim_fan, R.drawable.sysclear_card_anim_bluebg);
			mFrame.installPrepareLayer(mScanner);
			
			if(mCurrentScene == DIAL_MARK) {
				shrinkMarkAnim();
			}else {
				startScannerRoatingAnim();
			}
			break;
		case MAGNIFIER_ROATING:
			//装载
			initRoating(R.drawable.sysclear_card_anim_magnifier, R.drawable.sysclear_card_anim_circlewhite,
					R.drawable.sysclear_card_anim_fan, R.drawable.sysclear_card_anim_bluebg);
			mFrame.installPrepareLayer(mScanner);
			if(mCurrentScene == DIAL_MARK) {
				shrinkMarkAnim();
			}else {
				startScannerRoatingAnim();
			}
			break;
		case BRUSH_ROATING:
			//装载
			initRoating(R.drawable.sysclear_card_anim_brush, R.drawable.sysclear_card_anim_circlewhite,
					R.drawable.sysclear_card_anim_fan, R.drawable.sysclear_card_anim_bluebg);
			mFrame.installPrepareLayer(mScanner);
			if(mCurrentScene == DIAL_MARK) {
				shrinkMarkAnim();
			}else {
				startScannerRoatingAnim();
			}
			break;
		case ASHCAN_ROATING:
			//装载
			initRoating(TRANSPARENT, R.drawable.card_danager_scan_zero,
					R.drawable.sysclear_card_anim_roating_point, TRANSPARENT);
			mFrame.installPrepareLayer(mScanner);
			startScannerRoatingAnim();
			break;
		case ASHCAN_ROATING_UPDATE:
			ashcanRoatingUpdate(data);
			break;
			
		case ASHCAN_ROATING_END:
			ashcanRoatingEnd(data);
			break;
		case DIAL_MARK:
			initDialMark(markRes);
			mFrame.installShowLayer(mMark);
			break;
		case PROGRESS_CAKE:
			if(mWaveBar == null) {
				mWaveBar = new CardWaveBar(mContext);
				mFrame.installShowLayer(mWaveBar);
			}
			mWaveBar.setProgress(data);
			break;
		case PROGRESS_WAVE:
			if(mCakeBar == null) {
				mCakeBar = new CardCakeBar(mContext);
				mFrame.installShowLayer(mWaveBar);
			}
			mFrame.installShowLayer(mCakeBar);
			mCakeBar.setProgress(data);
			break;
		default:
			break;
		}
		mCurrentScene = scene;
	}
	
	public void initDialMark(int markRes) {
		if(mMark == null) {
			mMark = new View(mContext);
		}
		mMark.setBackgroundResource(markRes);
	}
	
	private static final int STATUS_DEFAULT = -1;
	private static final int STATUS_ZERO = 0;
	private static final int STATUS_SMALL = 30;
	private static final int STATUS_MIDDLE = 50;
	private static final int STATUS_BIG = 80;
	private int currentRoatingStatus = STATUS_DEFAULT;
	private boolean splitBallAnimEnd;
	
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
	
	public View installSplitBall(int res) {
		mScanMark.setBackgroundResource(R.drawable.card_danager_scan_small);
		View splitBall = new View(mContext);
		splitBall.setBackgroundResource(res);
		mScanMark.addView(splitBall);
		return splitBall;
	}
	
	private AnimationListener mashcanRoatingEndListener = new AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {}

        @Override
        public void onAnimationEnd(Animation animation) {
            setDialMarkResourceFromRoating();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {}

    };
    
	public void setDialMarkResourceFromRoating() {
		int res = 0;
		switch(currentRoatingStatus) {
		case STATUS_ZERO:
			res = R.drawable.card_danager_scan_zero;
			break;
		case STATUS_SMALL:
			res = R.drawable.card_danager_scan_small;
			break;
		case STATUS_MIDDLE:
//			res = R.drawable.sysclear_card_danger_scan_middle;
			res = R.drawable.card_danager_scan_small;
			break;
		case STATUS_BIG:
//			res = R.drawable.sysclear_card_danger_scan_big;
			res = R.drawable.card_danager_scan_small;
			break;
		}
		action(DIAL_MARK, res, -1);
		currentRoatingStatus = STATUS_DEFAULT;
	}
	
	public void ashcanRoatingEnd(long progress) {
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
				View mSplitBall = mScanMark.getChildAt(0);
				if(mSplitBall == null) {//没做过动画
					mSplitBall = installSplitBall(R.drawable.card_danager_scan_small_splitball);
					mSplitBall.clearAnimation();
					showSplitBalAnim(mSplitBall, mashcanRoatingEndListener);
				}else {//做过动画
					setDialMarkResourceFromRoating();
				}
				break;
			case STATUS_BIG:
				int count = mScanMark.getChildCount();
				if(count == 2) {//都做过动画
					setDialMarkResourceFromRoating();
				}else if(count == 1) {//小纸团做过动画
					mSplitBall = installSplitBall(R.drawable.card_danager_scan_big_splitball);
					mSplitBall.clearAnimation();
					showSplitBalAnim(mSplitBall, mashcanRoatingEndListener);
				}else {//都未做过动画
					mSplitBall = installSplitBall(R.drawable.card_danager_scan_double_splitball);
					mSplitBall.clearAnimation();
					showSplitBalAnim(mSplitBall, mashcanRoatingEndListener);
				}
				break;
			}
		}else {
			setDialMarkResourceFromRoating();
		}
	}
	
	public void ashcanRoatingUpdate(long progress) {
		int status = checkStatus(progress);
		if(status != currentRoatingStatus) {
			currentRoatingStatus = status;
			switch(currentRoatingStatus) {
			case STATUS_ZERO:
				mScanMarkBg.setBackgroundResource(R.drawable.card_danager_scan_zero);
				break;
			case STATUS_SMALL:
				mScanMarkBg.setBackgroundResource(R.drawable.card_danager_scan_small);
				break;
			case STATUS_MIDDLE:
				View smallSplitBall = installSplitBall(R.drawable.card_danager_scan_small_splitball);
				showSplitBalAnim(smallSplitBall, null);
				break;
			case STATUS_BIG:
				View bigSplitBall = installSplitBall(R.drawable.card_danager_scan_big_splitball);
				showSplitBalAnim(bigSplitBall, null);
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
	
	public void initRoating(int scanMarkRes, int scanMarkBgRes, int scannerRoatingRes, int bgRes) {
		if(mScannerRoating == null) {
			mScannerRoating = new View(mContext);
		}
		
		if(mScanMark == null) {
			mScanMark = new FrameLayout(mContext);
		}else {
			mScanMark.removeAllViews();
		}
		
		if(mScanMarkBg == null) {
			mScanMarkBg = new View(mContext);
		}
		
		if(mScanner == null) {
			mScanner = new FrameLayout(mContext);
			int widthAndHeight = mContext.getResources().getDimensionPixelSize(R.dimen.sysclear_card_anim_pic);
			LayoutParams prams = new LayoutParams(widthAndHeight, widthAndHeight);
			prams.gravity = Gravity.CENTER;
			mScanner.addView(mScannerRoating, prams);
			mScanner.addView(mScanMark, prams);
		}
		
		//设置scanMark
		if(scanMarkRes == TRANSPARENT){
			mScanMark.setBackgroundColor(color.transparent);
		}else {
			mScanMark.setBackgroundResource(scanMarkRes);
		}
		
		//设置scanMark bg
		mScanMarkBg.setBackgroundResource(scanMarkBgRes);
		
		//设置mScannerRoating view
		mScannerRoating.setBackgroundResource(scannerRoatingRes);
		
		//设置背景
		if(bgRes == TRANSPARENT) {
			mScanner.setBackgroundColor(color.transparent);
		}else {
			mScanner.setBackgroundResource(bgRes);
		}
	}
	
	private void shrinkMarkAnim() {
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(1, 0.6f, 1, 0.6f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, 0.3f); // 透明度，从不透明到透明
		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(false);
		animinationSet.setDuration(mAnimDuration/2);
		mMark.clearAnimation();
		mMark.startAnimation(animinationSet);
		animinationSet.setAnimationListener(mShrinkMarkEndListener);
	}
	
	private AnimationListener mShrinkMarkEndListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {}

		@Override
		public void onAnimationEnd(Animation animation) {
			startScannerRoatingAnim();
		}

		@Override
		public void onAnimationRepeat(Animation animation) {}
		
	};
	
	private void startScannerRoatingAnim() {
		RotateAnimation roatingAnim = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		roatingAnim.setDuration(mAnimDuration);
		roatingAnim.setRepeatCount(-1);
		roatingAnim.setRepeatMode(Animation.RESTART);
		roatingAnim.setInterpolator(new LinearInterpolator());
		mScannerRoating.clearAnimation();
		mScannerRoating.startAnimation(roatingAnim);
	}
	
}
