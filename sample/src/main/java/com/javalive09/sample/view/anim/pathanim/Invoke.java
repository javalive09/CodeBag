package com.javalive09.sample.view.anim.pathanim;

import android.animation.ValueAnimator;
import android.graphics.Color;
import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

	public void showChain() {
		Chain chain = new Chain(getActivity());
		chain.setBackgroundColor(Color.BLACK);
		final ValueAnimator anim = ValueAnimator.ofInt(0, 900);
		anim.addUpdateListener(chain);
		anim.setDuration(3000);
		chain.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				anim.start();
			}
		}, 1000);
		showView(chain);
	}

	public void customView() {
		showView(new CustomView(getActivity()));
	}

}
