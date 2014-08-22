package com.codebag.code.mycode.cleanmasteranim_frame;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class CardCleanDialFrame extends FrameLayout {

	private FrameLayout mShowLayer;
	
	private FrameLayout mPrepareLayer;
	
	private CardAniminationController mController;
	
	public CardCleanDialFrame(Context context) {
		super(context);
		mController = new CardAniminationController(context, this);
	}
	
	private void reSetShowLayer(){
		if(mShowLayer == null) {
			mShowLayer = new FrameLayout(getContext());
		}else {
			mShowLayer.removeAllViews();
		}
	}
	
	private void reSetPrepareLayer() {
		if(mPrepareLayer == null) {
			mPrepareLayer = new FrameLayout(getContext());
		}else {
			mPrepareLayer.removeAllViews();
		}
	}
	
	public void installShowLayer(View view) {
		reSetShowLayer();
		mShowLayer.addView(view);
	}
	
	public void installPrepareLayer(View view) {
		reSetPrepareLayer();
		mPrepareLayer.addView(view);
	}
	
	public void action(int scene, int markRes, int data) {
		if(mController != null) {
			mController.action(scene, markRes, data);
		}
	}

}
