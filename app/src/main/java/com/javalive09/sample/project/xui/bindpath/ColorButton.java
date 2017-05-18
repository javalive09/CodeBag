package com.javalive09.sample.project.xui.bindpath;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.javalive09.sample.R;

public class ColorButton extends FrameLayout {

	private Path mPath = new Path();
	private Paint mPaint = new Paint();
	private float mRadius;
	private float mMaxDiameter;
	private RadialGradient mRadialGradient;
	private int mRippleColor;
	private float mAlphaFactor;
	private float mDownX;
	private float mDownY;
	private TextView point;
	private TextView tv;
	
	public ColorButton(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {
		setBackgroundColor(Color.TRANSPARENT);
		inflate(context, R.layout.binding_point, this);
		tv = (TextView) findViewById(R.id.txt);
		point = (TextView) findViewById(R.id.point);
	}
	
	boolean end = false;
	
	public void endAnim() {
		end = true;
		mHandler.removeCallbacks(anim);
	}
	
	public void anim() {
		mHandler.postDelayed(anim, 500);
	}
	
    protected void onDetachedFromWindow() {
    	super.onDetachedFromWindow();
    	endAnim();
    }
	
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouchEvent(MotionEvent event) {
		if(isClickable()) {
			super.onTouchEvent(event);
			int action = event.getAction();
			switch(action) {
			case MotionEvent.ACTION_DOWN:
				mPaint.setColor(adjustAlpha(mRippleColor, 0.85f));
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				mPaint.setColor(adjustAlpha(mRippleColor, mAlphaFactor));
				invalidate();
			}
			return true;
		}
		return false;
	}
	
	String[] strs = new String[]{" ", ".  ",".. ","..."};
	
	Handler mHandler = new Handler(Looper.getMainLooper());
	
	int index = 0;
	
	Runnable anim = new Runnable() {
		
		@Override
		public void run() {
			if(end) {
				return;
			}
			index++;
			if(index == 4) {
				index = 0;
			}
			point.setText(strs[index]);
			anim();
		}
	};
	
	public TextView getTV() {
		return tv;
	}
	
	public TextView getPoint() {
		return point;
	}
	
	public float getDiameter() {
		return mMaxDiameter;
	}
	
	public void setRadius(final float radius) {
		mRadius = radius;
		if (mRadius > 0) {
			mRadialGradient = new RadialGradient(mDownX, mDownY, mRadius,
					adjustAlpha(mRippleColor, mAlphaFactor), mRippleColor,
					Shader.TileMode.MIRROR);
			mPaint.setShader(mRadialGradient);
		}
		invalidate();
	}
	
	public int adjustAlpha(int color, float factor) {
		int alpha = Math.round(Color.alpha(color) * factor);
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		return Color.argb(alpha, red, green, blue);
	}
	
	public int getRippleColor() {
		return mRippleColor;
	}
	
	public float getAlphaFactor() {
		return mAlphaFactor;
	}
	
	public void setRippleColor(int rippleColor, float alphaFactor) {
		mRippleColor = rippleColor;
		mAlphaFactor = alphaFactor;
		mPaint.setColor(adjustAlpha(mRippleColor, mAlphaFactor));
		postInvalidate();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mDownX = getMeasuredWidth() / 2;
		mDownY = getMeasuredHeight() / 2;
		float d = mDownX * mDownX + mDownY * mDownY;
		mRadius = (float) Math.sqrt(d);
		mMaxDiameter = mRadius *2;
	}

	@Override
	protected void onDraw(final Canvas canvas) {
		
		canvas.save(Canvas.CLIP_SAVE_FLAG);
		mPath.reset();
		mPath.addCircle(mDownX, mDownY, mRadius, Path.Direction.CW);
		canvas.clipPath(mPath);
		canvas.restore();
		canvas.drawCircle(mDownX, mDownY, mRadius, mPaint);
	}
	
}
