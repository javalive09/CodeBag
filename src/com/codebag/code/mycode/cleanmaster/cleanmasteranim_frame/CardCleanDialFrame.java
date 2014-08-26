package com.codebag.code.mycode.cleanmaster.cleanmasteranim_frame;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class CardCleanDialFrame extends FrameLayout {

	private FrameLayout mShowLayer;
	
	private FrameLayout mPrepareLayer;
	
	private CardAniminationController mController;
	
	private LayoutParams mParams;
	
	public CardCleanDialFrame(Context context) {
		super(context);
		mController = new CardAniminationController(context, this);
		mPrepareLayer = new FrameLayout(getContext());
		mShowLayer = new FrameLayout(getContext());
		mParams.gravity = Gravity.CENTER;
		addView(mPrepareLayer, mParams);
		addView(mShowLayer, mParams);
	}
	
	public void installShowLayer(View view) {
		mShowLayer.removeAllViews();
		mShowLayer.addView(view);
	}
	
	public void installPrepareLayer(View view) {
		mPrepareLayer.removeAllViews();
		mPrepareLayer.addView(view);
	}
	
	public void action(int scene, int markRes, int data) {
		if(mController != null) {
			mController.action(scene, markRes, data);
		}
	}
	
	
}
