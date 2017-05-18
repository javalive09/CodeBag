package com.javalive09.sample.project.xui.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.javalive09.sample.R;

public class SearchView extends FrameLayout {

	Bitmap mRollingBitmap;
	int mRadius;
	int mOuterRadius;
	Paint mPaint;
	float mAngle;

	public SearchView(final Context context) {
		this(context, null);
	}

	public SearchView(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.roateView);
	}

	public SearchView(final Context context, final AttributeSet attrs,
			final int defStyle) {
		super(context, attrs, defStyle);
		final TypedArray attributes = context.obtainStyledAttributes(attrs,
				R.styleable.search, defStyle, 0);
		if (attributes != null) {
			try {
				setRadius(attributes.getDimensionPixelSize(R.styleable.search_search_radius, 0));
				setAngle(attributes.getFloat(R.styleable.search_rearch_angle, 0.0f));
				setRoateBitmap(attributes.getResourceId(R.styleable.search_rearch_res, R.drawable.magnifier));
			} finally {
				attributes.recycle();
			}
		}
		mPaint = new Paint();
		setBackgroundColor(Color.WHITE);
	}
	
	public void setRoateBitmap(int res) {
		mRollingBitmap = BitmapFactory.decodeResource(getResources(), res);
	}

	public void setRadius(int radius) {
		mRadius = radius;
	}
	
	public void setAngle(float angle) {
		mAngle = angle;
		invalidate();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mRollingCell = Math.max(mRollingBitmap.getWidth(), mRollingBitmap.getHeight());
		int cell = mRadius * 2 + mRollingCell;
		mOuterRadius = cell / 2;
		setMeasuredDimension(cell, cell);
	}

	Matrix mMatrix = new Matrix ();
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		canvas.translate(250, 50);
		int left = - mRollingBitmap.getWidth() / 2;
		int top = mOuterRadius / 2;
		canvas.save();
//		canvas.rotate(mAngle);
		Bitmap bitmap = getCurrentBitmap(-mAngle);
		canvas.drawBitmap(bitmap, left, top, mPaint);
		canvas.restore();
	}
	
	float[] matrixArray = new float[10];
	
	private Bitmap getCurrentBitmap(float mAngle) {
		int width = mRollingBitmap.getWidth();
		int height = mRollingBitmap.getHeight();
		Matrix matrix = mMatrix;
		matrix.reset();
		matrix.postRotate(mAngle, width/2, height/2);
		matrix.getValues(matrixArray);
		float a = 240.0f - width * matrixArray[0] - height * matrixArray[1];
		float b = 400.0f - width * matrixArray[3] - height * matrixArray[4];
		Log.i("a", String.valueOf(a));
		Log.i("b", String.valueOf(b));
		matrix.postTranslate(a, b);
		
		Bitmap newBitmap = Bitmap.createBitmap(mRollingBitmap, 0, 0, width, height, matrix, true);
		return newBitmap;
	}

}
