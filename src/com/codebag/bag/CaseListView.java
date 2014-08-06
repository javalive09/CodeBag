package com.codebag.bag;

import java.lang.reflect.Field;

import com.codebag.R;
import com.codebag.code.mycode.utils.Log;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

public class CaseListView extends ListView {

	public CaseListView(Context context) {
		super(context);
	}
	
	public FrameLayout.LayoutParams getCenterParams() {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1 , -1);
		params.gravity = Gravity.CENTER;
		return params;
	}
	
	public FrameLayout.LayoutParams getBottomParams() {
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1 , -1);
		params.gravity = Gravity.BOTTOM;
		return params;
	}

	public void showView(View view) {
		showView(view, getCenterParams());
	}
	
	public void showView(View view, FrameLayout.LayoutParams params) {
		final FrameLayout container = new FrameLayout(getContext());
		container.setBackgroundColor(Color.BLACK);
		container.addView(view);
		
		Point outSize = new Point();
		WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		mWindowManager.getDefaultDisplay().getSize(outSize);
		int statusBarHeight = getStatusBarHeight();
		final PopupWindow pw = new PopupWindow(container, outSize.x, outSize.y - statusBarHeight, true);
		pw.setAnimationStyle(R.style.popUpWindowAnimation);
		pw.showAtLocation(this, Gravity.NO_GRAVITY, 0, statusBarHeight);

		//以下两句是按键能响应的关键代码
		container.setFocusableInTouchMode(true);
		container.requestFocus();
		
		container.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_UP) {
					if(keyCode == KeyEvent.KEYCODE_BACK) {
						Log.addLog(this, "window height =" + pw.getHeight());
						container.removeAllViews();
						pw.dismiss();
						return true;
					}
				}
				return false;
			}
		});
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
