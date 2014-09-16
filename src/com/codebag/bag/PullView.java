package com.codebag.bag;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Scroller;

public class PullView extends ViewGroup {

	private Scroller mScroller;
	private int mTouchSlop = 0;
	private VelocityTracker mVelocityTracker = null;
	private static final int RESET = 0;
	private static final int SCROLLING = 1;
	private static final int VELOCITY_BOUNDRY = -5000;
	private int mTouchState = RESET;
	private static final int mAnimTime = 600;
	private boolean mFinish = false;
	private int mStartY = 0;
	private int mDeltaY = 0;

	
	public PullView(Context context) {
		super(context);
		init();
	}
	
    public PullView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    private void init() {
		mScroller = new Scroller(getContext(), new BounceInterpolator());
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

	public void startBounceAnim(int startY, int dy, int duration) {
		mScroller.startScroll(0, startY, 0, dy, duration);
		invalidate();
	}
	
	@Override
	public void computeScroll() {

		if (mScroller.computeScrollOffset()) {
			int mScrollerY = mScroller.getCurrY();
			scrollTo(0, mScrollerY);
//			Log.i("~peter", "mScrollerY=" + mScrollerY);
			invalidate();
		}else if(mFinish) {
			MainActivity act = (MainActivity) getContext();
			act.finish();
		}
	}
	
	public boolean dispatchTouchEvent(MotionEvent event) {
		return super.dispatchTouchEvent(event);
	}
	
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	Log.i("peter", "onInterceptTouchEvent" + ev);
    	final int action = ev.getAction();
    	final int currentY = (int) ev.getY();
    	
    	switch(action) {
    	case MotionEvent.ACTION_DOWN:
    		mStartY = currentY;
    		mTouchState = mScroller.isFinished() ? RESET : SCROLLING;
    		break;
    	case MotionEvent.ACTION_MOVE:
    		final int deltaY = mStartY - currentY;
    		if(deltaY > mTouchSlop) {//向上滑动
    			mTouchState = SCROLLING;
    		}
    		break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = RESET;
			break;
    	}
        return mTouchState != RESET;
    }
   
    public boolean onTouchEvent(MotionEvent event) {
//    	Log.i("peter", "onTouchEvent" + event);
    	final int action = event.getAction();
    	final int currentY = (int) event.getY();
    	
    	if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
		
    	switch(action) {
    	case MotionEvent.ACTION_DOWN:
    		mStartY = currentY;
    		if (!mScroller.isFinished()){
				mScroller.abortAnimation();
			}
    		break;
    	case MotionEvent.ACTION_MOVE:
    		mDeltaY = mStartY - currentY;
    		scrollTo(0, mDeltaY);
    		break;
    	case MotionEvent.ACTION_UP:
    		mTouchState = RESET;
			final VelocityTracker velocityTracker = mVelocityTracker;
			velocityTracker.computeCurrentVelocity(1000);
			int velocityY = (int) velocityTracker.getYVelocity();
    		
    		if(velocityY < VELOCITY_BOUNDRY|| mDeltaY > getHeight()/2) {
    			mFinish = true;
    			startBounceAnim(getScrollY(), getHeight() - getScrollY(), mAnimTime);
    		}else {
    			startBounceAnim(getScrollY(), -getScrollY(), mAnimTime);
    		}
    		
    		if (mVelocityTracker != null) {
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
    		break;
    	}
		return false;
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
