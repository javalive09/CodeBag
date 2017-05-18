package com.javalive09.sample.view.graphics;

import com.javalive09.sample.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomLine extends View {

	private int width;
	private int cellW;
	private int cellCount;
	private int mPaddingLeft;
	private Paint mPaint;
	private int mPointR;
	private int mCellGap;
	private int mColor;
	
	public CustomLine(Context context, AttributeSet attrs) {
		this(context, attrs, R.attr.pointLine);
		init();
	}
	
	private void init() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(mColor);
	}
	
	public CustomLine(final Context context,
			final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
		
		final TypedArray attributes = context.obtainStyledAttributes(attrs,
				R.styleable.XuiPointLine, defStyle, 0);
		if (attributes != null) {
			try {
				mColor = attributes.getColor(R.styleable.XuiPointLine_point_line_color, Color.BLUE);
				mPointR = attributes.getDimensionPixelOffset(R.styleable.XuiPointLine_point_line_radius, 10);
				mCellGap = attributes.getDimensionPixelOffset(R.styleable.XuiPointLine_point_line_gap, 10);
			} finally {
				attributes.recycle();
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), mPointR * 2);
		width = getMeasuredWidth();
		cellW = mPointR * 2 + mCellGap;
		int color = Color.parseColor("#88a2d4");
		mPaint.setColor(color);
		mPaddingLeft = width % cellW;
		cellCount = width / cellW;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for(int i = 0; i< cellCount; i++) {
			int pointX = mPaddingLeft + cellW * (i + 1) - mPointR;
			canvas.drawCircle(pointX, mPointR, mPointR, mPaint);
		}
	}

	
	
}
