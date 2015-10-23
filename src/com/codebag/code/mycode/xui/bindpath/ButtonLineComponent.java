package com.codebag.code.mycode.xui.bindpath;

import com.codebag.code.mycode.xui.bindpath.RectPathView.MyAnimListener;

import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

public class ButtonLineComponent extends FrameLayout {

	RectPathView rv;
	ButtonContainer bc;
	float mDensity;
	Point start = new Point();
	Point one = new Point();
	Point two = new Point();
	Point two_ = new Point();
	Point three = new Point();
	Point three_ = new Point();
	Point four = new Point();
	Point four_ = new Point();
	Point five = new Point();
	
	public ButtonLineComponent(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mDensity = getContext().getResources().getDisplayMetrics().density;
		bc = new ButtonContainer(context);
		LayoutParams p = new LayoutParams(645, 159, Gravity.CENTER);
		addView(bc, p);
		bc.flow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bc.flow.setClickable(false);
				bc.flow.getTV().setText("绑定中");
				bc.flow.anim();
				postDelayed(new Runnable() {
					
					@Override
					public void run() {
						bc.flow.getTV().setVisibility(View.INVISIBLE);
						bc.flow.getPoint().setVisibility(View.INVISIBLE);
						bc.flow.endAnim();
						rv.anim();
					}
				}, 5000);
				
			}
		});
		
		post(new Runnable() {
			
			@Override
			public void run() {
				if(rv == null) {
					rv = new RectPathView(getContext());
					rv.setAnimListener(new MyAnimListener() {
						
						@Override
						public void end() {
							bc.end.setClickable(true);
							bc.anim();
						}
					});
					addView(rv);
				}
				
				start.set(getMeasuredWidth() / 2, 0);
				one.set(getMeasuredWidth() / 2, bc.getTop() - dp(3));
				
				two.set(bc.getRight() + dp(3), bc.getTop() - dp(3));
				two_.set(bc.getLeft() - dp(3), bc.getTop() - dp(3));
				
				three.set(bc.getRight() + dp(3), bc.getBottom() + dp(3));
				three_.set(bc.getLeft() - dp(3), bc.getBottom() + dp(3));
				
				four.set(getMeasuredWidth() / 2, bc.getBottom() + dp(3));
				
				five.set(getMeasuredWidth() / 2, getMeasuredHeight());
				
				rv.setPoint(start, one, two, two_, three, three_, four, five);
				rv.setClickable(false);
			}
		});

	}

	private int dp(int dp) {
		return (int) (dp * mDensity + 0.5f);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		
	}
	
	
	
}
