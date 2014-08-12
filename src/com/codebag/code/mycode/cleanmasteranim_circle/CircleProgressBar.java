package com.codebag.code.mycode.cleanmasteranim_circle;

import com.codebag.code.mycode.utils.Log;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

/**
 * @author zhangrui-ms
 * 
 */
public class CircleProgressBar extends View {
	private Paint mPaint;
	private RectF mRect;
	private float mAngle = 0;
	private int mEndProgress;
	private int mProgressColor;
	private int mBackGroundColor;
	private int mDiameter;
	private int mPly;
	private AniminationListener mListener;
	public static final int DEFAUTL_SPEED = 1;
	private int mSpeed = DEFAUTL_SPEED;
	private boolean mCanAnim;

	public CircleProgressBar(Context context) {
		this(context, 0, 0, DEFAUTL_SPEED);
	}

	/**
	 * @param context
	 * @param ply  圆环厚度
	 * @param diameter   圆环直径
	 */
	public CircleProgressBar(Context context, int ply, int diameter, int speed) {
		super(context);
		init(ply, diameter, speed);
	}

	private void init(int ply, int diameter, int speed) {
		mRect = new RectF();
		mPaint = new Paint();
		mPaint.setStyle(Style.STROKE);
		mPaint.setAntiAlias(true);
		setData(ply, diameter, speed);
	}

	public void setData(int ply, int diameter, int speed) {
		mPly = ply;
		mPaint.setStrokeWidth(ply);
		mDiameter = diameter;
		if (speed > 1 || speed < 11) {
			mSpeed = speed;
		}
		Log.addLog(this, "d=" + mDiameter);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mDiameter, mDiameter);
		int width = mDiameter;
		int height = mDiameter;
		int startX = (width - mDiameter) / 2;
		int startY = (height - mDiameter) / 2;
		mRect.set(startX + mPly / 2, startY + mPly / 2, startX + mDiameter
				+ mPly / 2 - mPly, startY + mDiameter + mPly / 2 - mPly);
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		mCanAnim = true;
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mCanAnim = false;
	}

	public void setColor(int progressColor, int backGroundColor) {
		mProgressColor = progressColor;
		mBackGroundColor = backGroundColor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// draw background
		mPaint.setColor(mBackGroundColor);
		canvas.drawArc(mRect, 0, 360, false, mPaint);

		// draw progress
		mPaint.setColor(mProgressColor);
		canvas.drawArc(mRect, 270, mAngle, false, mPaint);
	}

	public void setProgress(int progress) {
		mAngle = 360 / 100f * progress;
		invalidate();
	}

	public void startAnimination(int endProgress) {
		mEndProgress = endProgress;
		mHandler.sendEmptyMessage(100);
		if (mListener != null) {
			mListener.start();
		}
	}

	/**
	 * 在#startAnimination之前调用
	 * 
	 * @param listener
	 */
	public void setAniminationListener(AniminationListener listener) {
		mListener = listener;
	}

	private Handler mHandler = new Handler(Looper.getMainLooper()) {

		@Override
		public void handleMessage(Message msg) {
			if (mCanAnim) {
				if (msg.what <= mEndProgress) {
					setProgress(mEndProgress);
					if (mListener != null) {
						mListener.end();
					}
				}else if (msg.what > mEndProgress) {
					msg.what -= mSpeed;
					setProgress(msg.what);
					sendEmptyMessage(msg.what);
				}
			}
		}

	};

	public interface AniminationListener {
		public void start();
		public void end();
	}

}
