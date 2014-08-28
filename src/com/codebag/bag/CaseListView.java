package com.codebag.bag;

import java.lang.reflect.Field;

import com.codebag.R;
import com.codebag.code.mycode.utils.Log;

import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

public class CaseListView extends ListView {

	PopupWindow mPopupWindow;
	FrameLayout mContainer;

	public CaseListView(Context context) {
		super(context);
	}

	public FrameLayout.LayoutParams fillParentParams(int gravity) {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -1);
		params.gravity = gravity;
		return params;
	}

	public FrameLayout.LayoutParams wrapContentParams(int gravity) {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
		params.gravity = gravity;
		return params;
	}

	public FrameLayout.LayoutParams wrapH_fillW_Params(int gravity) {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1, -2);
		params.gravity = gravity;
		return params;
	}

	public void showView(View view) {
		showView(view, fillParentParams(Gravity.CENTER));
	}

	public void showView(View view, FrameLayout.LayoutParams params) {
		initContainer();
		mContainer.addView(view, params);
		mPopupWindow.setContentView(mContainer);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setAnimationStyle(R.style.popUpWindowAnimation);
		mPopupWindow.showAtLocation(this, Gravity.NO_GRAVITY, 0, getStatusBarHeight());
	}
	
	public void showView(int resId) {
		initContainer();
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(resId, mContainer, true);
		mPopupWindow.setContentView(mContainer);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setAnimationStyle(R.style.popUpWindowAnimation);
		mPopupWindow.showAtLocation(this, Gravity.NO_GRAVITY, 0, getStatusBarHeight());
	}
	
	protected FrameLayout getContainer() {
		initContainer();
		return mContainer;
	}

	private void initContainer() {
		if (mContainer == null) {
			if (mPopupWindow == null) {
				WindowManager mWindowManager = (WindowManager) getContext()
						.getSystemService(Context.WINDOW_SERVICE);
				Display display = mWindowManager.getDefaultDisplay();
				int width = display.getWidth();
				int height = display.getHeight();
				mPopupWindow = new PopupWindow(width, height - getStatusBarHeight());
			}
			mContainer = new FrameLayout(getContext()) {
				@Override
				public boolean dispatchKeyEvent(KeyEvent event) {
					if (event.getAction() == KeyEvent.ACTION_UP) {
						if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
							Log.addLog(
									this,
									"window height ="
											+ mPopupWindow.getHeight());
							removeAllViews();
							mPopupWindow.dismiss();
							return true;
						}
					}
					return super.dispatchKeyEvent(event);
				}

			};
			mContainer.setBackgroundColor(Color.WHITE);
		}
	}

	/**
	 * 用于获取状态栏的高度。
	 * 
	 * @return 返回状态栏高度的像素值。
	 */
	private int getStatusBarHeight() {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object o = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = (Integer) field.get(o);
			return getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
