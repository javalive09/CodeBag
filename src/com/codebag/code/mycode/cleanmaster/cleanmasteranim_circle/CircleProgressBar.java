package com.codebag.code.mycode.cleanmaster.cleanmasteranim_circle;

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
	private Paint mInnerCirclePaint;
	private Paint mOuterCirclePaint;
	private RectF mInnerCircleRect;
	private RectF mOuterCircleRect;
	private float mAngle = 0;
	private int mEndProgress;
	private int mInnerCircleColor;
	private int mOuterCircleColor;
	private int mDiameter;
	private AniminationListener mListener;
	public static final int DEFAUTL_SPEED = 1;
	private int mSpeed = DEFAUTL_SPEED;
	private boolean mCanAnim;

	public CircleProgressBar(Context context) {
		this(context, 0, 0, 0, 0, DEFAUTL_SPEED);
	}

	/**
	 * @param context
	 * @param innerCirclePly 内圆厚度	
	 * @param outerCirclePly 外圆厚度
	 * @param innerDiameter  内圆直径
	 * @param outerCirclePly 外圆直径
	 * @param speed	速度
	 */
	public CircleProgressBar(Context context, int innerCirclePly, int outerCirclePly, 
			int innerCircleDiameter, int outerCircleDiameter, int speed) {
		super(context);
		init(innerCirclePly, outerCirclePly, innerCircleDiameter, outerCircleDiameter, speed);
	}

	private void init(int innerCirclePly, int outerCirclePly, int innerCircleDiameter, int outerCircleDiameter, int speed) {
		mInnerCircleRect = new RectF();
		mOuterCircleRect = new RectF();
		
		mInnerCirclePaint = new Paint();
		mInnerCirclePaint.setStyle(Style.STROKE);
		mInnerCirclePaint.setAntiAlias(true);
		
		mOuterCirclePaint = new Paint();
		mOuterCirclePaint.setStyle(Style.STROKE);
		mOuterCirclePaint.setAntiAlias(true);
		
		setData(innerCirclePly, outerCirclePly, innerCircleDiameter, outerCircleDiameter, speed);
	}

	public void setData(int innerCirclePly, int outerCirclePly, int innerCircleDiameter, int outerCircleDiameter, int speed) {
		
		mInnerCirclePaint.setStrokeWidth(innerCirclePly);
		mOuterCirclePaint.setStrokeWidth(outerCirclePly);
		int x = (outerCircleDiameter - innerCircleDiameter) / 2;
		int y = x;
		setRect(mInnerCircleRect, x, y, innerCirclePly, innerCircleDiameter);
		setRect(mOuterCircleRect, 0, 0, outerCirclePly, outerCircleDiameter);
		
		mDiameter = outerCircleDiameter;
		
		if (speed > 1 || speed < 11) {
			mSpeed = speed;
		}
	}
	
	public void setRect(RectF rect, int startX, int startY, int ply, int diameter) {
		rect.set(startX + ply/2, startY + ply/2, startX + diameter + ply/2 - ply, startY + diameter + ply/2 - ply);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mDiameter, mDiameter);
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

	public void setColor(int innerCircleColor, int outerCircleColor) {
		mInnerCircleColor = innerCircleColor;
		mOuterCircleColor = outerCircleColor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// draw innerCircle
		mInnerCirclePaint.setColor(mInnerCircleColor);
		canvas.drawArc(mInnerCircleRect, 0, 360, false, mInnerCirclePaint);

		// draw outerCircle
		mOuterCirclePaint.setColor(mOuterCircleColor);
		canvas.drawArc(mOuterCircleRect, 270, mAngle, false, mOuterCirclePaint);
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
