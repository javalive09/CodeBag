package com.javalive09.sample.view.toast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * window 实现的toast
 */
public class SuperToast {

	public static SuperToast makeText(Context context, CharSequence text,
			int duration) {
		return new SuperToast(context, text, duration);
	}

	public static final int LENGTH_SHORT = 2000;
	public static final int LENGTH_LONG = 3500;
	private final Handler mHandler = new Handler(Looper.getMainLooper());
	private int mDuration = LENGTH_SHORT;
	private int mGravity = Gravity.BOTTOM;
	private int mX, mY;
	private float mHorizontalMargin;
	private float mVerticalMargin;
	private View mView;
	private View mNextView;
	private WindowManager mWM;
	private WindowManager.LayoutParams mParams;

	public SuperToast(Context context, CharSequence text, int duration) {
		mParams = new WindowManager.LayoutParams();
		mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		mParams.format = PixelFormat.TRANSLUCENT;
		mParams.windowAnimations = android.R.style.Animation_Toast;
		mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
		mParams.setTitle("Toast");
		mWM = (WindowManager) context.getApplicationContext().getSystemService(
				Context.WINDOW_SERVICE);
		LinearLayout mLayout = new LinearLayout(context);
		TextView tv = new TextView(context);
		tv.setText(text);
		tv.setTextColor(Color.WHITE);
		tv.setGravity(Gravity.CENTER);
		mLayout.setBackgroundResource(android.R.drawable.toast_frame);
		int w = context.getResources().getDisplayMetrics().widthPixels;
		tv.setMaxWidth(w);
		mLayout.addView(tv);
		mNextView = mLayout;
	}

	public void setView(View view) {
		mNextView = view;
	}

	public View getView() {
		return mNextView;
	}

	public void setDuration(int duration) {
		mDuration = duration;
	}

	public int getDuration() {
		return mDuration;
	}

	public void setMargin(float horizontalMargin, float verticalMargin) {
		mHorizontalMargin = horizontalMargin;
		mVerticalMargin = verticalMargin;
	}

	public float getHorizontalMargin() {
		return mHorizontalMargin;
	}

	public float getVerticalMargin() {
		return mVerticalMargin;
	}

	public void setGravity(int gravity, int xOffset, int yOffset) {
		mGravity = gravity;
		mX = xOffset;
		mY = yOffset;
	}

	public int getGravity() {
		return mGravity;
	}

	public int getXOffset() {
		return mX;
	}

	public int getYOffset() {
		return mY;
	}

	public void show() {
		mHandler.post(mShow);
		if (mDuration > 0) {
			mHandler.postDelayed(mHide, mDuration);
		}
	}

	public void hide() {
		mHandler.post(mHide);
	}

	private final Runnable mShow = new Runnable() {
		public void run() {
			handleShow();
		}
	};

	private final Runnable mHide = new Runnable() {
		public void run() {
			handleHide();
		}
	};

	private void handleShow() {
		if (mView != mNextView) {
			handleHide();
			mView = mNextView;
			final int gravity = mGravity;
			mParams.gravity = gravity;
			mParams.x = mX;
			mParams.y = mY;
			mParams.verticalMargin = mVerticalMargin;
			mParams.horizontalMargin = mHorizontalMargin;
			mWM.addView(mView, mParams);
		}
	}

	private void handleHide() {
		if (mView != null && mView.getParent() != null) {
			mWM.removeView(mView);
			mView = null;
		}
	}
}
