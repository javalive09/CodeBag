package com.codebag.bag;



import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

public class CaseListView extends ListView {

	public CaseListView(Context context) {
		super(context);
	}
	
	public void showView(View view) {
		FrameLayout f = new FrameLayout(getContext());
		f.setBackgroundColor(Color.BLUE);
		f.addView(view);
		final PopupWindow pw = new PopupWindow(f, LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT,true);
		pw.showAtLocation(this, Gravity.CENTER, 0, 0);
		pw.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
		view.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				pw.dismiss();
				return false;
			}
		});
	}
	

}
