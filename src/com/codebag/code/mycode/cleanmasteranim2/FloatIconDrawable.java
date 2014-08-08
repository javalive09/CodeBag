package com.codebag.code.mycode.cleanmasteranim2;

import com.codebag.R;
import com.codebag.code.mycode.utils.DisplayUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;


public class FloatIconDrawable extends Drawable {

	private static final int COLOR_NORMAL= 0xe61965ff;
	private static final int COLOR_NORMAL_TRANS = 0x731965ff;
	private static final int COLOR_CRITICAL = 0xe6fe5129;
	private static final int COLOR_CRITICAL_TRANS = 0x73fe5129;
	private static final int COLOR_TEST = 0xffff0000;

	private static final float ICON_PADDING_IN_DIP = 2.5f;

	private final Rect mRect = new Rect();
	private final Paint mWaterPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

	private final Drawable mImg;
	private final Rect mWaterRect;

	private final int mPadding;

	private final float mRadius;

	/** 水波相关参数BEGIN **/
	private static final float WAVE_FREQUENCY = 0.0125f;
	private static final float AMPLITUDE = 2.0f;
	private static final int FRAME_TIME = 1000 / 60; //60fps

	private final float mWaterRectCenterY;
	private float mAmplitude;
	private int mLoop;

	private boolean isWillDrawWave = false;
	/** 水波相关参数END **/


    private final Runnable waveRunnable = new Runnable() {

        @Override
        public void run() {
        	isWillDrawWave = true;
            invalidateSelf();
            scheduleSelf(waveRunnable, SystemClock.uptimeMillis() + FRAME_TIME);
        }

    };

	public FloatIconDrawable(Context context) {
		mImg = context.getResources().getDrawable(R.drawable.bluebg);

		final int imgWidth = mImg.getIntrinsicWidth();
		final int imgHeight = mImg.getIntrinsicHeight();
//		final int imgWidth = 540;
//		final int imgHeight = 540;

		mPadding = DisplayUtil.dip2px(context, ICON_PADDING_IN_DIP);
		
		mWaterRect = new Rect(mPadding, mPadding, imgWidth - mPadding, imgHeight - mPadding);
		

		mRadius = mWaterRect.width() / 2.0f;

		mWaterRectCenterY = mWaterRect.exactCenterY();

		mWaterPaint.setStrokeWidth(1f);

		setAmplitude(AMPLITUDE);
	}

    public final void scheduleWave() {
        scheduleSelf(waveRunnable, SystemClock.uptimeMillis());
    }

	private void drawIcon(Canvas canvas) {
		int level = getLevel();

		if (mAmplitude > 0) {
			mWaterPaint.setColor(level < 8500 ? COLOR_TEST : COLOR_TEST);
		} else {
			mWaterPaint.setColor(level < 8500 ? COLOR_TEST : COLOR_TEST);
		}

		final float height = getAccurateHeight(level);
		final float surface = mWaterRect.bottom - height;

		drawWave(canvas, surface);

		mImg.draw(canvas);
	}

	private void drawWave(Canvas canvas, float surface) {
		if (isWillDrawWave) {
    		isWillDrawWave = false;
    	} else if (mAmplitude > 0) {
    		return;
    	}

		if (mLoop >= 0x7FFFFF) {
            mLoop = 0;
        }
        mLoop++;

        for (int posX = mWaterRect.left; posX <= mWaterRect.right; posX++) {
        	float waveY = getWaveY(posX, surface);
        	float upCircleY = getUpCircleY(posX);
        	float belowCircleY = getBelowCircleY(posX);

        	if (waveY <= mWaterRectCenterY) {
        		if (waveY <= belowCircleY) {
        			canvas.drawLine(posX, belowCircleY, posX, Math.max(waveY, upCircleY), mWaterPaint);
        		}
        	} else {
        		if (waveY <= belowCircleY) {
        			canvas.drawLine(posX, belowCircleY, posX, waveY, mWaterPaint);
        		}
        	}
        }
	}

	private void setAmplitude(float amplitude) {
		this.mAmplitude = amplitude;
	}

	/**
	 * 根据level / 10000 对应的“水波区域面积相对于整个绘图区域面积的比例”
	 * 计算出正确的clipRect高度值
	 * @param level
	 * @return
	 */
	private float getAccurateHeight(int level) {
		final float circleArea = (float) (mRadius * mRadius * Math.PI);
		float target = level / 10000.0f;

		if (level > 5000) {
			target = 1 - target;
		}

		float minDelta = 1;
		float targetArgX = 0;

		float ratio;
		float delta;
		for (float x = 0; x < mRadius; x++) {
			ratio = (float) ((getSectorArea(x, mRadius) - getTriangleArea(x, mRadius)) / circleArea);
			delta = Math.abs(target - ratio);

			if (delta < minDelta) {
				targetArgX = x;
				minDelta = delta;
			}
		}

		if (level > 5000) {
			return 2 * mRadius - targetArgX;
		} else {
			return targetArgX;
		}

	}

	/**
	 * 获取扇形面积
	 * @param argX 参数X
	 * @param radius 半径
	 * @return
	 */
	private double getSectorArea(float argX, float radius) {
		return Math.acos((radius - argX) / radius) * radius * radius;
	}

	/**
	 * 获取三角形面积
	 * @param argX 参数X
	 * @param radius 半径
	 * @return
	 */
	private double getTriangleArea(float argX, float radius) {
		return Math.sqrt(2 * radius * argX - argX * argX) * (radius - argX);
	}

    /**
     * 获取对应位置的波浪纵坐标
     * @param posX 指定位置的横坐标
     * @param waterRectY 水平面的RectY
     * @return
     */
    private float getWaveY(int posX, float waterRectY) {
        return (float) (waterRectY - mAmplitude * Math.sin((posX + 2 * mLoop * mRadius * WAVE_FREQUENCY) * Math.PI / mRadius));
    }

    /**
     * 获取对应位置圆形背景上半部分的纵坐标
     * @param posX 制定位置横坐标
     * @return
     */
    private float getUpCircleY(int posX) {
    	return (float) (mWaterRect.top + mRadius - Math.sqrt(mRadius * mRadius - (posX - mWaterRect.left - mRadius) * (posX - mWaterRect.left - mRadius)));
    }

    /**
     * 获取对应位置圆形背景下半部分的纵坐标
     * @param posX 制定位置横坐标
     * @return
     */
    private float getBelowCircleY(int posX) {
    	return (float) (mWaterRect.top + mRadius + Math.sqrt(mRadius * mRadius - (posX - mWaterRect.left - mRadius) * (posX - mWaterRect.left - mRadius)));
    }



	@Override
	public void draw(Canvas canvas) {
		drawIcon(canvas);
	}

	@Override
	public void setAlpha(int alpha) {
		mImg.setAlpha(alpha);
		invalidateSelf();
		if (alpha == 255) {
			setAmplitude(AMPLITUDE);
			scheduleWave();
		} else {
			setAmplitude(0);
			unscheduleSelf(waveRunnable);
		}
	}

	@Override
	public void setColorFilter(ColorFilter cf) {

	}

	@Override
	public int getOpacity() {
		return 0;
	}

	@Override
	public int getIntrinsicWidth() {
		return mImg.getIntrinsicWidth();
	}

	@Override
	public int getIntrinsicHeight() {
		return mImg.getIntrinsicHeight();
	}

	@Override
	protected boolean onLevelChange(int level) {
		invalidateSelf();
		return true;
	}

	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);
		mRect.set(bounds);
		mImg.setBounds(mRect);
	}

}
