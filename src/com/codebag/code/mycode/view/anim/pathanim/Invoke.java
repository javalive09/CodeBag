package com.codebag.code.mycode.view.anim.pathanim;

import android.graphics.Color;
import android.widget.ImageView;
import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.nineoldandroids.animation.ValueAnimator;

public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
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
		showView(new CustomView(getActivity()));
	}
	
	@Entry
	public void myView() {
		showView(new MyView(getActivity()));
	}
	
	@Entry
	public void pathDrawable() {
		ImageView iv = new ImageView(getActivity());
		PathDrawable d = new PathDrawable();
		d.setBounds(0,0, 1000, 1000);
		iv.setBackgroundDrawable(d);
		showView(iv, match_parent);
	}
}
