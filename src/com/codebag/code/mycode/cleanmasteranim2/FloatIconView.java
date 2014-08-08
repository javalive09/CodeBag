package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class FloatIconView extends ImageView implements OnPreDrawListener {

    private static final String TAG = "FloatIconView";
    private static final boolean DEBUG = false;

    private static final int MESSAGE_LIGHT = 0;
    private static final int MESSAGE_FADE = 1;
    private static final int DELAY_FADE = 3000;

    private FloatIconDrawable mDrawable;
    private LayoutParams mLayoutParams;

    private final Handler mFadeHandler = new Handler() {
    	@Override
		public void handleMessage(android.os.Message msg) {
    		if (msg.what == MESSAGE_LIGHT) {
    			if (mDrawable != null) {
    				mDrawable.setAlpha(255);
    			}

    		} else if (msg.what == MESSAGE_FADE) {
    			if (mDrawable != null) {
    				mDrawable.setAlpha(127);
    			}
    		}
    	};
    };

    public FloatIconView(Context context) {
        super(context);
        init(context);
    }

    public FloatIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloatIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        if (DEBUG) {
            Log.i(TAG, "init");
        }
        mDrawable = new FloatIconDrawable(context);
        mLayoutParams = (LayoutParams) getLayoutParams();
    }

    @Override
	public boolean onPreDraw() {
    	getViewTreeObserver().removeOnPreDrawListener(this);

    	mDrawable.scheduleWave();
    	return false;
	}

	@Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnPreDrawListener(this);

        setImageDrawable(mDrawable);
        
        lightSelf();
        fadeSelfAfter3s();
    }
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		
//		if (mDrawable != null) {
//			mDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
//		}
	}

    @Override
    protected void onDetachedFromWindow() {
    	super.onDetachedFromWindow();
    	mFadeHandler.removeMessages(DELAY_FADE);
    	mFadeHandler.removeMessages(MESSAGE_LIGHT);
    }

    protected void updateMemoryUsage(int percent) {
        mDrawable.setLevel(percent * 100);
    }

    public void fadeSelfAfter3s() {
    	mFadeHandler.removeMessages(MESSAGE_FADE);
    	mFadeHandler.sendEmptyMessageDelayed(MESSAGE_FADE, DELAY_FADE);
    }

    public void lightSelf() {
    	mFadeHandler.removeMessages(MESSAGE_FADE);
    	mFadeHandler.removeMessages(MESSAGE_LIGHT);
    	mFadeHandler.sendEmptyMessage(MESSAGE_LIGHT);
    }

    public void toLeft() {
        if (mLayoutParams == null) {
            mLayoutParams = (LayoutParams) getLayoutParams();
        }

        if (mLayoutParams != null) {
            mLayoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        }
    }

    public void toRight() {
        if (mLayoutParams == null) {
            mLayoutParams = (LayoutParams) getLayoutParams();
        }

        if (mLayoutParams != null) {
            mLayoutParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        }
    }
}
