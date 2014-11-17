package com.codebag.bag.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalBoundaryPullView extends ViewGroup {

	private Scroller mScroller;
	private int mTouchSlop;
	private static final int STATE_IDLE = 0;
	private static final int STATE_DRAGGING = 1;
	private static final int STATE_SETTLING = 2;
	private static final int mAnimTime = 500;
	private int mTouchState = STATE_IDLE;
	private boolean mFinish;
	private int mStartX;
	private int mDeltaX;
	private boolean mCanPull;
	private boolean canFinish;
	private int boundaryPly;
	
	public HorizontalBoundaryPullView(Context context) {
		super(context);
		init();
	}
	
    public HorizontalBoundaryPullView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init();
    }

    private void init() {
		mScroller = new Scroller(getContext());
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
		boundaryPly = mTouchSlop * 3;
    }
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			int mScrollerX = mScroller.getCurrX();
			int mScrollerY = mScroller.getCurrY();
			scrollTo(mScrollerX, mScrollerY);
			invalidate();
		}else if(mFinish) {
			ViewGroup parent = (ViewGroup) getParent();
			if(parent.getChildCount() == 1) {
				Activity act = (Activity) getContext();
				act.onBackPressed();
			}else {
				parent.removeView(this);
			}
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
    			mCanPull = canPull(currentX);
    		}else {
    			mTouchState = STATE_SETTLING;
    			mCanPull = false;
    		}
    		break;
    	case MotionEvent.ACTION_MOVE:
    		if(mCanPull) {
    			final int deltaX = currentX - mStartX;
        		if(Math.abs(deltaX) > mTouchSlop) {
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
    
    private boolean canPull(int x) {
    	final int rightB = getWidth() - boundaryPly;
    	if((x < boundaryPly) //left boundary
    			|| (x > rightB)) {//right boundary 
    		return true;
    	}
    	return false;
    }
    
    @SuppressLint("ClickableViewAccessibility") 
    public boolean onTouchEvent(MotionEvent event) {
    	final int action = event.getAction();
    	final int currentX = (int) event.getX();
		
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
    		if(Math.abs(mDeltaX) > getWidth() / 3) {
    			canFinish = true;
    		}else {
    			canFinish = false;
    		}
    		break;
    	case MotionEvent.ACTION_UP:
			int deltaX;
    		if(canFinish) {
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