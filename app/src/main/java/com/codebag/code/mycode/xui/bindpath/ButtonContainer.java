package com.codebag.code.mycode.xui.bindpath;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

public class ButtonContainer extends FrameLayout {

	ColorButton flow;
	ColorButton end;
	
	public ButtonContainer(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {
		end = new ColorButton(context); 
		end.setRippleColor(Color.parseColor("#83c602"), 1.0f);
		end.setVisibility(View.INVISIBLE);
		addView(end);
		
		flow = new ColorButton(context);
		flow.setRippleColor(Color.parseColor("#6096e9"), 1.0f);
		flow.getTV().setText("绑定");
		addView(flow);
	}
	
	public void anim() {
		end.setVisibility(View.VISIBLE);
		ObjectAnimator mRadiusAnimator = ObjectAnimator.ofFloat(flow, "radius", flow.getDiameter(), 0).setDuration(2000);
		mRadiusAnimator.addListener(new AnimatorListenerAdapter() {
			
			@Override
			public void onAnimationEnd(Animator animation) {
				removeView(flow);
				end.getTV().setText("开始使用");
				
				ObjectAnimator mRadiusAnimator = ObjectAnimator.ofFloat(end.getTV(), "alpha", 0, 1).setDuration(1000);
				mRadiusAnimator.start();
			}
		});
		mRadiusAnimator.start();
	}

}
