package com.javalive09.sample.project.cleanmaster.cleanmaster_test.animination;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import com.javalive09.codebag.Entry;

public class Guide {

	public void scaleAnimation(View view) {
		AnimationSet animinationSet = new AnimationSet(true);
		//参数意义： 
		//1. x轴开始位置
		//2. x轴结束位置
		//3. y轴开始位置
		//4. y轴结束位置
		//5. x中心轴移动类型（相对于谁）
		//6. x中心轴位置
		//7. y中心轴移动类型（相对于谁）
		//8. y中心轴位置
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
