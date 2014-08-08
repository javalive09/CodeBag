
package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

/**
 * @author zhangrui-ms
 *
 */
public class CakeProgressBar extends View {
	private Paint mPaint; 
	private RectF mRectIn;
	private RectF mRectOut;
	private float mAngle = 0;
	private int mEndProgress;
	private int mProgressColor;
	private int mBackGroundColor;
	private int mDiameter;
	private int mPly;
	private AniminationListener mListener;
	
	public CakeProgressBar(Context context) {
		this(context, 0, 0);
	}
	
	/**
	 * @param context
	 * @param ply		圆环厚度
	 * @param diameter	圆环直径
	 */
	public CakeProgressBar(Context context, int ply, int diameter) {
		super(context);
		init(ply, diameter);
	}
	
	private void init(int ply, int diameter) {
		mPly = ply;
		mDiameter = diameter - mPly;
		mRectIn = new RectF();
		mRectOut = new RectF();
		mPaint = new Paint();
		mPaint.setStrokeWidth(ply);
		mPaint.setStyle(Style.FILL);
		mPaint.setAntiAlias(true);
	}
	
	public void setData(int ply, int diameter) {
		mPly = ply;
		mPaint.setStrokeWidth(ply);
		mDiameter = diameter;
		Log.i("peter", "d=" + mDiameter);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(mDiameter, mDiameter);
		int width = mDiameter;
		int height = mDiameter;
		int startX = (width - mDiameter) / 2;
		int startY = (height - mDiameter) / 2;
		mRectIn.set(startX + mPly/2, startY + mPly/2, startX + mDiameter + mPly/2 - mPly, startY + mDiameter + mPly/2 - mPly);
		mRectOut.set(startX , startY, startX + mDiameter, startY + mDiameter);
	}
	
	public void setColor(int progressColor, int backGroundColor) {
		mProgressColor = progressColor;
		mBackGroundColor = backGroundColor;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		//draw background
		mPaint.setColor(mBackGroundColor);
		canvas.drawArc(mRectIn, 0, 360, false, mPaint);
		
		//draw progress
		mPaint.setColor(mProgressColor);
		canvas.drawArc(mRectOut, 270, mAngle, true, mPaint);
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
				sendEmptyMessage(msg.what);
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
