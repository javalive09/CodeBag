package com.codebag.code.mycode.xui.search;

import android.view.View;
import android.view.animation.LinearInterpolator;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
		super(act);
	}

	@Entry
	public void showAnim() {
		
		showView(R.layout.searchlayout);
		
		getActivity().getWindow().getDecorView().post(new Runnable() {
			
			@Override
			public void run() {
				AnimatorSet set = new AnimatorSet();
				final View root = getActivity().findViewById(R.id.root);
				ObjectAnimator rootAnim = ObjectAnimator.ofFloat(root, "rotation", 0, 360);
				rootAnim.setDuration(2000);
				rootAnim.setRepeatCount(ObjectAnimator.INFINITE);
				rootAnim.setInterpolator(new LinearInterpolator());
				
				View search = getActivity().findViewById(R.id.search);
				ObjectAnimator searchAnim = ObjectAnimator.ofFloat(search, "rotation", 360, 0);
				searchAnim.setRepeatCount(ObjectAnimator.INFINITE);
				searchAnim.setInterpolator(new LinearInterpolator());
				searchAnim.setDuration(2000);
				set.playTogether(rootAnim, searchAnim);
				set.start();
			}
		});
	}
	
}
