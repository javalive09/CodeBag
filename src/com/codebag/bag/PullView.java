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
	private int mTouchSlop;
	private VelocityTracker mVelocityTracker = null;
	private static final int STATE_IDLE = 0;//空闲状态
	private static final int STATE_DRAGGING = 1;//拖拽状态
	private static final int STATE_SETTLING = 2;//还原状态	
	private static final int VELOCITY_BOUNDRY = 2000;
	private int mTouchState = STATE_IDLE;
	private static final int mAnimTime = 600;
	private boolean mFinish;
	private int mStartX;
	private int mStartY;
	private int mDeltaX;
	private int mDeltaY;
	
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

	public void startBounceAnim(int startX, int startY, int dx, int dy, int duration) {
		mScroller.startScroll(startX, startY, dx, dy, duration);
		invalidate();
	}
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int mScrollerX = mScroller.getCurrX();
			int mScrollerY = mScroller.getCurrY();
			scrollTo(mScrollerX, mScrollerY);
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
    	
    	final int action = ev.getAction();
    	final int currentX = (int) ev.getX();
    	final int currentY = (int) ev.getY();
    	
    	switch(action) {
    	case MotionEvent.ACTION_DOWN:
    		mStartX = currentX;
    		mStartY = currentY;
    		mTouchState = mScroller.isFinished() ? STATE_IDLE : STATE_SETTLING;
    		break;
    	case MotionEvent.ACTION_MOVE:
    		final int deltaX = currentX - mStartX;
    		if(deltaX > mTouchSlop) {//向右滑动
    			mTouchState = STATE_DRAGGING;
    		}
    		break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = STATE_IDLE;
			break;
    	}
        return mTouchState != STATE_IDLE;
    }
   
    public boolean onTouchEvent(MotionEvent event) {

    	final int action = event.getAction();
    	final int currentX = (int) event.getX();
    	final int currentY = (int) event.getY();
    	
    	if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
		
    	switch(action) {
    	case MotionEvent.ACTION_MOVE:
    		mDeltaX = mStartX - currentX;
    		mDeltaY = mStartY - currentY;
    		scrollTo(mDeltaX, mDeltaY);
    		break;
    	case MotionEvent.ACTION_UP:
    		mTouchState = STATE_IDLE;
			final VelocityTracker velocityTracker = mVelocityTracker;
			velocityTracker.computeCurrentVelocity(1000);
			int velocityX = (int) velocityTracker.getXVelocity();
    		
			Log.i("peter", "velocityX=" + velocityX);
			Log.i("peter", "getScrollX()=" + getScrollX());
			
    		if(velocityX > VELOCITY_BOUNDRY || -mDeltaX > getWidth()/2) {
    			mFinish = true;
    			startBounceAnim(getScrollX(), getScrollY(), -(getWidth() + getScrollX()), -(getHeight() + getScrollY()), mAnimTime);
    		}else {
    			startBounceAnim(getScrollX(), getScrollY(), -getScrollX(), -getScrollY(), mAnimTime);
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
