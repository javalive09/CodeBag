package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

/**
 * 
 * 居中显示的圆形进度
 * 
 * @author zhangrui-ms
 *
 */
public class RingView extends View {
	private Paint mPaint; 
	private RectF mRect;
	private float mAngle = 0;
	private int mEndProgress;
	private int mProgressColor;
	private int mBackGroundColor;
	private int mDiameter;
	private int mPly;
	private AniminationListener mListener;
	
	public RingView(Context context) {
		this(context, 0, 0);
	}
	
	/**
	 * @param context
	 * @param ply		圆环厚度
	 * @param diameter	圆环直径
	 */
	public RingView(Context context, int ply, int diameter) {
		super(context);
		init(ply, diameter);
	}
	
	private void init(int ply, int diameter) {
		mPly = ply;
		mDiameter = diameter - mPly;
		mRect = new RectF();
		mPaint = new Paint();
		mPaint.setStrokeWidth(ply);
		mPaint.setStyle(Style.STROKE);
		mPaint.setAntiAlias(true);
	}
	
	public void setData(int ply, int diameter) {
		mPly = ply;
		mPaint.setStrokeWidth(ply);
		mDiameter = diameter - mPly;
		Log.i("peter", "d=" + mDiameter);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		int startX = (width - mDiameter) / 2;
		int startY = (height - mDiameter) / 2;
		mRect.set(startX + mPly/2, startY + mPly/2, startX + mDiameter + mPly/2 - mPly, startY + mDiameter + mPly/2 - mPly);
	}
	
	public void setColor(int progressColor, int backGroundColor) {
		mProgressColor = progressColor;
		mBackGroundColor = backGroundColor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		//参考线
//		mPaint.setColor(Color.RED);
//		canvas.drawLine(startX, mDiameter/2 + startY, mDiameter + startX, mDiameter/2 + startY, mPaint);
		
		//draw background
		mPaint.setColor(mBackGroundColor);
		canvas.drawArc(mRect, 0, 360, false, mPaint);
		
		//draw progress
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
		if(mListener != null) {
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
			if(msg.what > mEndProgress) {
				msg.what--;
				setProgress(msg.what);
				sendEmptyMessageDelayed(msg.what, 30);
			}else if(msg.what == mEndProgress) {
				if(mListener != null) {
					mListener.end();
				}
			}
		}
		
	};
	
	public interface AniminationListener {
		public void start();
		public void end();
	}
}
