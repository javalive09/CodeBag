package com.codebag.bag;

import com.codebag.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
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
	private VelocityTracker mVelocityTracker;
	private static final int STATE_IDLE = 0;
	private static final int STATE_DRAGGING = 1;
	private static final int STATE_SETTLING = 2;
	private static final int VELOCITY_BOUNDRY = 2000;
	private static final int mAnimTime = 600;
	private int mTouchState = STATE_IDLE;
	private boolean mFinish;
	private int mStartX;
	private int mStartY;
	private int mDeltaX;
	private int mDeltaY;
	private int mStatusBarH;
	private boolean mCanPull;
	private boolean canFinish;
	private int boundaryPly;
	
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
		boundaryPly = mTouchSlop * 2;
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
    	final int currentY = (int) ev.getY();
    	
    	switch(action) {
    	case MotionEvent.ACTION_DOWN:
    		mStartX = currentX;
    		mStartY = currentY;
    		if(mScroller.isFinished()) {
    			mTouchState = STATE_IDLE;
    			mCanPull = canPull(currentX, currentY);
    		}else {
    			mTouchState = STATE_SETTLING;
    			mCanPull = false;
    		}
    		
    		break;
    	case MotionEvent.ACTION_MOVE:
    		if(mCanPull) {
	    		final int deltaX = currentX - mStartX;
	    		final int deltaY = currentY - mStartY;
	    		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	    		if(distance > mTouchSlop) {
	    			mTouchState = STATE_DRAGGING;
	    		}
    		}
    		break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = STATE_IDLE;
			break;
    	}
    	return mTouchState == STATE_DRAGGING;
    }
    
    private int getStatusBarH() {
    	Rect frame = new Rect();
    	Activity act = (Activity) getContext();
    	act.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    	return frame.top;
    }
    
    private boolean canPull(int x, int y) {
    	if(mStatusBarH == 0) {
    		mStatusBarH = getStatusBarH();
    	}
    	final int rightB = getWidth() - boundaryPly;
    	final int bottomB = getHeight() - boundaryPly;
    	
    	if((x < boundaryPly && y > mStatusBarH) //left boundary
    			|| (x > rightB && y > mStatusBarH)//right boundary
    			|| (y > bottomB)) {//bottom boundary
    		return true;
    	}
    	return false;
    }
    
    @SuppressLint("ClickableViewAccessibility") 
    public boolean onTouchEvent(MotionEvent event) {
    	final int action = event.getAction();
    	final int currentX = (int) event.getX();
    	final int currentY = (int) event.getY();
    	
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
    		mDeltaX = mStartX - currentX;
    		mDeltaY = mStartY - currentY;
    		scrollTo(mDeltaX, mDeltaY);
    		double distance = Math.sqrt(mDeltaX * mDeltaX + mDeltaY * mDeltaY);
    		if(distance > getWidth() / 3) {
    			canFinish = true;
    			getChildAt(0).setBackgroundResource(R.drawable.translate_bg);
    		}else {
    			canFinish = false;
    			getChildAt(0).setBackgroundResource(R.drawable.bg);
    		}
    		break;
    	case MotionEvent.ACTION_UP:
			final VelocityTracker velocityTracker = mVelocityTracker;
			velocityTracker.computeCurrentVelocity(1000);
			int velocityX = (int) velocityTracker.getXVelocity();
			int velocityY = (int) velocityTracker.getYVelocity();
    		Log.i("peter", "velocityX=" + velocityX);
    		Log.i("peter", "velocityY=" + velocityY);
			int deltaY, deltaX;
    		if(canFinish || Math.abs(velocityX) > VELOCITY_BOUNDRY || (Math.abs(velocityY) > VELOCITY_BOUNDRY)) {
    			mFinish = true;
    			if(mDeltaY > 0) {//手指向上，内容向上，窗口向下
    				deltaY = getHeight() - getScrollY();
    				if(mDeltaX > 0) {//手指向左，内容向左，窗口向右
    					deltaX = getWidth() - getScrollX();
    				}else {//向右
    					deltaX = -(getWidth() + getScrollX());
    				}
    			}else {//向下
    				deltaY = -(getHeight() + getScrollY());
    				if(mDeltaX > 0) {//向左
    					deltaX = getWidth() - getScrollX();
    				}else {//向左
    					deltaX = -(getWidth() + getScrollX());
    				}
    			}
    		}else {
    			deltaY = -getScrollY();
    			deltaX = -getScrollX();
    		}
    		mScroller.startScroll(getScrollX(), getScrollY(), deltaX, deltaY, mAnimTime);
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