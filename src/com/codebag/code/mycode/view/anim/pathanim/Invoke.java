package com.codebag.code.mycode.view.anim.pathanim;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.Gravity;
import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
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
	
	@Entry
	public void showChain2() {
		Chain2 chain = new Chain2(getActivity());
		chain.setImageResource(R.drawable.chain);
		chain.setBackgroundColor(Color.BLACK);
		showView(chain);
	}

	@Entry
	public void customView() {
		match_parent.gravity = Gravity.CENTER;
		showView(new CustomView(getActivity()), match_parent);
	}
	
}
