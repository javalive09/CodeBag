package com.codebag.code.mycode.cleanmastertest.animination;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.codebag.bag.CaseListView;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	public void scaleAnimation(View view) {
		AnimationSet animinationSet = new AnimationSet(true);
		
		ScaleAnimation animation = new ScaleAnimation(1, 0.1f, 1, 0.1f, 
				Animation.RELATIVE_TO_SELF , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		
		animation.setDuration(2000);
		animinationSet.addAnimation(animation);
		animinationSet.setFillAfter(true);
		view.startAnimation(animinationSet);
	}
	
	public void roateAnimation(View view) {
		AnimationSet animinationSet = new AnimationSet(true);
		//地平面开始（0度） 结束角度为顺时针结束位置, 相对轴，百分比或绝对数值
		RotateAnimation animation = new RotateAnimation(0, 360, 
				Animation.RELATIVE_TO_SELF , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(2000);
		animinationSet.addAnimation(animation);
		animinationSet.setFillAfter(true);
		view.startAnimation(animinationSet);
	}
	
	public void alphaAnimation(View view) {
		AnimationSet animinationSet = new AnimationSet(true);
		AlphaAnimation animation = new AlphaAnimation(1, 0); //透明度，从不透明到透明
		animation.setDuration(2000);
		animinationSet.addAnimation(animation);
		animinationSet.setFillAfter(true);
		view.startAnimation(animinationSet);
	}

}
