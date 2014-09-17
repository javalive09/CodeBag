package com.codebag.code.mycode.view.scroll;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoke extends CaseListView {

	private int currentX;
	private int currentY;

	public Invoke(Context context) {
		super(context);
	}

	@Entry
	public void show() {

		final View container = inflate(getContext(), R.layout.main, null);
		final View icon = container.findViewById(R.id.icon);
		container.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					currentX = (int) event.getRawX();
					currentY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:
					int x2 = (int) event.getX();
					int y2 = (int) event.getY();
//					icon.scrollTo(currentX - x2, currentY - y2);
					icon.scrollTo(20, 20);
//					container.scrollTo(x2, y2);
					break;
				case MotionEvent.ACTION_UP:
					break;
				}
				return true;
			}

		});
		showView(container);

	}

}
