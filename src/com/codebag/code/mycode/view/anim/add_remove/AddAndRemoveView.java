package com.codebag.code.mycode.view.anim.add_remove;

import android.graphics.Canvas;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;

public class AddAndRemoveView extends MyCode {

	private FrameLayout container;
	
	public AddAndRemoveView(InovkedViewActivity context) {
		super(context);
		container = new FrameLayout(context){
		    public void draw(Canvas canvas) {
                super.draw(canvas);
            }
		};
	}
	
	@Entry
	public void addView() {
		final ImageView iv = new ImageView(getActivity()){
		    public void draw(Canvas canvas) {
		        super.draw(canvas);
		    }
		};
		iv.setBackgroundResource(R.drawable.ic_launcher);
		
		Button addView = new Button(getActivity());
		addView.setText("addView");
		addView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
				params.gravity = Gravity.CENTER;
				container.addView(iv, params);
				attachAnim(iv);
			}
		});
		
		Button removeView = new Button(getActivity());
		removeView.setText("removeView");
		removeView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				detachAnim(iv);
				container.removeView(iv);
			}
		});
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER_VERTICAL| Gravity.LEFT;
		container.addView(addView, params);
		
		params = new FrameLayout.LayoutParams(-2, -2);
		params.gravity = Gravity.CENTER_VERTICAL| Gravity.RIGHT;
		container.addView(removeView, params);

		showView(container);
		
		
	}
	
	private void attachAnim(View iv) {
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(1, 2.0f, 1, 2.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlpha = new AlphaAnimation(.3f, 1.0f); // 透明度，从不透明到透明
		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(true);
		animinationSet.setDuration(2000);
		iv.clearAnimation();
		iv.startAnimation(animinationSet);
		animinationSet.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
		});
		
		iv.startAnimation(animinationSet);
	}
	
	private void detachAnim(View iv) {
		AnimationSet animinationSet = new AnimationSet(true);
		ScaleAnimation animationScale = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		AlphaAnimation animationAlpha = new AlphaAnimation(1.0f, .3f); // 透明度，从不透明到透明
		animinationSet.addAnimation(animationScale);
		animinationSet.addAnimation(animationAlpha);
		animinationSet.setFillAfter(true);
		animinationSet.setDuration(2000);
		iv.clearAnimation();
		iv.startAnimation(animinationSet);
		animinationSet.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				
			}
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
		});
		
		iv.startAnimation(animinationSet);
	}
	
	
	
	

}
