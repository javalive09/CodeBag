package com.javalive09.sample.project.xui.transfer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Transfer extends Entry {

	View music;
	
	public void trans() {
		music = showView(View.inflate(getActivity(), R.layout.curve_path, null))
				.findViewById(R.id.music);
		music.setVisibility(View.INVISIBLE);
		music.post(new Runnable() {
			
			@Override
			public void run() {
				music.setVisibility(View.VISIBLE);
				anim();

		}});
	}

	private void anim() {
		AnimatorPath path = new AnimatorPath();
		View root = getActivity().findViewById(R.id.rootview);
		int x = root.getWidth();
		int y = root.getHeight();
		
		path.moveTo(0, y - music.getHeight());
		path.curveTo(0, y - music.getHeight(),   x / 2,  0,   x - music.getWidth(), y - music.getHeight());

		AnimatorSet set = new AnimatorSet();
		set.setDuration(2000);

		ObjectAnimator anim = ObjectAnimator.ofObject(this, "MusicLoc",
				new PathEvaluator(), path.getPoints().toArray());
		anim.setRepeatCount(ObjectAnimator.INFINITE);

		music.setPivotX(music.getWidth() / 2f);
		music.setPivotY(music.getHeight() / 2f);

		ObjectAnimator xa = ObjectAnimator.ofFloat(music, "scaleX", 0.f, 1.0f, 0.f);
		ObjectAnimator ya = ObjectAnimator.ofFloat(music, "scaleY", 0.f, 1.0f, 0.f);

		xa.setInterpolator(new LinearInterpolator());
		ya.setInterpolator(new LinearInterpolator());
		xa.setRepeatCount(ObjectAnimator.INFINITE);
		ya.setRepeatCount(ObjectAnimator.INFINITE);

		set.playTogether(anim, xa, ya);
		set.start();
	}

	public void setMusicLoc(PathPoint newLoc) {
		music.setTranslationX(newLoc.mX);
		music.setTranslationY(newLoc.mY);
	}

}
