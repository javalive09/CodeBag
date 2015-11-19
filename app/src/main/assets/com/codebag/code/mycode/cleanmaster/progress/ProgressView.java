package com.codebag.code.mycode.cleanmaster.progress;

import com.codebag.R;
import com.codebag.code.mycode.cleanmaster.progress.CircleView.AniminationCallBack;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ProgressView extends FrameLayout implements AniminationCallBack{

	private ImageView mIcon;
	private CircleView mProgress;
	private ImageView mRotate;//要隐藏旋转动画，先clearAnimation(),再隐藏
	
	public ProgressView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {
		mProgress = new CircleView(context);
//		int color = Color.parseColor("#1e83d1");
//		mProgress.setBackgroundColor(color);
		mProgress.setDuration(0);
		FrameLayout.LayoutParams paramsP = new FrameLayout.LayoutParams(-2, -2);
		paramsP.gravity = Gravity.CENTER;
		addView(mProgress, paramsP);

		mIcon = new ImageView(context);
		mIcon.setBackgroundResource(R.drawable.ic_launcher);
		FrameLayout.LayoutParams paramsI = new FrameLayout.LayoutParams(-2, -2);
		paramsI.gravity = Gravity.CENTER;
		addView(mIcon, paramsI);
		
		mRotate = new ImageView(context);
		FrameLayout.LayoutParams paramsR = new FrameLayout.LayoutParams(-2, -2);
		paramsR.gravity = Gravity.CENTER;
		mRotate.setBackgroundResource(R.drawable.roate_arc);
		addView(mRotate, paramsR);
	}
	
	public void setIcon(Bitmap bm) {
		mIcon.setImageBitmap(bm);
	}
	
	public void startAnim() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.data_loading_rotate);
        mRotate.setAnimation(animation);
        animation.start();
        mProgress.startAnim();
	}
	
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

	@Override
	public void start() {}

	@Override
	public void end() {
		
	}
}
