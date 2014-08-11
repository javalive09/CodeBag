package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author zhangrui-ms
 *
 */
public class CakeProgressBar extends ImageView {

	public static final int DEFAUTL_SPEED = 1;
	public static final int DELAY = 2;
	private int mSpeed = DEFAUTL_SPEED;
	private Paint mPaint;
	private RectF mRectIn;
	private RectF mRectOut;
	private float mAngle;
	private int mEndProgress;
	private int mProgressColor;
	private int mBackGroundColor;
	private int mPly;
	private AniminationListener mListener;
	private boolean mRoatingAnim;
	private int mDiameter;

	public CakeProgressBar(Context context) {
		this(context, 0, 0, DEFAUTL_SPEED);
	}

	public CakeProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(0, 0, DEFAUTL_SPEED);
	}

	/**
	 * @param context
	 * @param ply
	 *            圆环厚度
	 * @param diameter
	 *            圆环直径
	 */
	public CakeProgressBar(Context context, int diameter, int ply, int speed) {
		super(context);
		init(diameter, ply, speed);
	}

	private void init(int diameter, int ply, int speed) {
		mRectIn = new RectF();
		mRectOut = new RectF();
		mPaint = new Paint();
		mPaint.setStyle(Style.FILL);
		mPaint.setAntiAlias(true);
		setData(diameter, ply, speed);
	}

	public void setData(int diameter, int ply, int speed) {
		mDiameter = diameter;
		mPly = ply;
		mPaint.setStrokeWidth(ply);
		if (speed >= 1 && speed <= 11) {
			mSpeed = speed;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mDiameter, mDiameter);
		mRectIn.set(mPly / 2, mPly / 2, mDiameter + mPly / 2 - mPly, mDiameter
				+ mPly / 2 - mPly);
		mRectOut.set(0, 0, mDiameter, mDiameter);
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
		canvas.drawArc(mRectIn, 0, 360, false, mPaint);
		// draw progress
		mPaint.setColor(mProgressColor);
		canvas.drawArc(mRectOut, 270, mAngle, true, mPaint);
	}

	public void setProgress(int progress) {
		mAngle = 360 / 100f * progress;
		invalidate();
	}

	public void startAnimination(int endProgress) {
		mRoatingAnim = true;
		mEndProgress = endProgress;
		mHandler.sendEmptyMessageDelayed(100, DELAY);
		if (mListener != null) {
			mListener.start();
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		mRoatingAnim = false;
		super.onDetachedFromWindow();
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
			Log.i("peter", "anim =" + mRoatingAnim + "  progress = " + msg.what);
			if (mRoatingAnim) {
				if (msg.what <= mEndProgress) {
					setProgress(mEndProgress);
					if (mListener != null) {
						mListener.end();
					}
				} else if (msg.what > mEndProgress) {
					msg.what -= mSpeed;
					setProgress(msg.what);
					mHandler.sendEmptyMessageDelayed(msg.what, DELAY);
				}
			}
		}

	};

	public interface AniminationListener {
		public void start();

		public void end();
	}

}
