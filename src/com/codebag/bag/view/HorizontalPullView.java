package com.codebag.bag.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalPullView extends ViewGroup {

	private Scroller mScroller;
	private int mTouchSlop;
	private VelocityTracker mVelocityTracker;
	private static final int STATE_IDLE = 0;
	private static final int STATE_DRAGGING = 1;
	private static final int STATE_SETTLING = 2;
	private static final int VELOCITY_BOUNDRY = 1000;
	private static final int mAnimTime = 600;
	private int mTouchState = STATE_IDLE;
	private boolean mFinish;
	private int mStartX;
	private int mDeltaX;
	private boolean mCanPull;
	private boolean canFinish;
	
	public HorizontalPullView(Context context) {
		super(context);
		init();
	}
	
    public HorizontalPullView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    private void init() {
		mScroller = new Scroller(getContext());
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int mScrollerX = mScroller.getCurrX();
			int mScrollerY = mScroller.getCurrY();
			scrollTo(mScrollerX, mScrollerY);
			invalidate();
		}else if(mFinish) {
			Activity act = (Activity) getContext();
			act.finish();
		}
	}
	
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	final int action = ev.getAction();
    	final int currentX = (int) ev.getX();
    	
    	switch(action) {
    	case MotionEvent.ACTION_DOWN:
    		mStartX = currentX;
    		if(mScroller.isFinished()) {
    			mTouchState = STATE_IDLE;
    		}else {
    			mTouchState = STATE_SETTLING;
    		}
    		break;
    	case MotionEvent.ACTION_MOVE:
    		final int deltaX = currentX - mStartX;
    		if(Math.abs(deltaX) > mTouchSlop) {
    			mTouchState = STATE_DRAGGING;
    		}
    		break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = STATE_IDLE;
			break;
    	}
    	return mTouchState == STATE_DRAGGING;
    }
    
    @SuppressLint("ClickableViewAccessibility") 
    public boolean onTouchEvent(MotionEvent event) {
    	final int action = event.getAction();
    	final int currentX = (int) event.getX();
    	
    	if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
		
    	switch(action) {
    	case MotionEvent.ACTION_DOWN:
    		if(mCanPull) {
    			return true;
    		}else {
    			return false;
    		}
    	case MotionEvent.ACTION_MOVE:
    		mDeltaX = currentX - mStartX;
    		scrollTo(-mDeltaX, 0);
    		if(Math.abs(mDeltaX) > getWidth() / 2) {
    			canFinish = true;
    		}else {
    			canFinish = false;
    		}
    		break;
    	case MotionEvent.ACTION_UP:
			final VelocityTracker velocityTracker = mVelocityTracker;
			velocityTracker.computeCurrentVelocity(1000);
			int velocityX = (int) velocityTracker.getXVelocity();
    		Log.i("peter", "velocityX=" + velocityX);
			int deltaX;
    		if(canFinish || 
    				((velocityX* mDeltaX > 0) && Math.abs(velocityX) > VELOCITY_BOUNDRY)) {
    			mFinish = true;
    			if(mDeltaX < 0) {//向右
    				deltaX = getWidth() - getScrollX();
    			}else {//向左
    				deltaX = - (getWidth() + getScrollX());
    			}
    		}else {
    			deltaX = -getScrollX();
    		}
    		mScroller.startScroll(getScrollX(), getScrollY(), deltaX, 0, mAnimTime);
    		invalidate();
    		
    		if (mVelocityTracker != null) {
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
    		break;
    	}
		return true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }
    
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		getChildAt(0).layout(l, t, r, b);
	}
	
}