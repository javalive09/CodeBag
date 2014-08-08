package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class WaterWaveView extends View {

    private static final boolean DEBUG = false;
    private static final int INTERVAL = 60; // ms

    private Paint mForePaint = new Paint();
    private Handler mHandler;

    private long mRound = 0;
    private boolean mAnimating = false;
    private float mAmplitude = 10.0f;
    private float mFrequency = 0.033f;
    private float mWaterLevel = 0.5f;
    private int mWaterAlpha = 31; // ~= 12%
    private int mWaterColor = Color.WHITE;
    private float mWaterXstart = 0.0f;

    public WaterWaveView(Context context) {
        super(context);
        init(context);
    }

    public WaterWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        if (DEBUG) {
            Log.d("WaterWave", "create");
        }

        mForePaint.setStrokeWidth(1f);
        mForePaint.setColor(mWaterColor);
        mForePaint.setAlpha(mWaterAlpha);

        mHandler = new Handler(context.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    invalidate();
                    if (mAnimating) {
                        mHandler.sendEmptyMessageDelayed(0, INTERVAL);
                    }
                }
            }
        };
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (DEBUG) {
            Log.d("WaterWave", "onAttachedToWindow:");
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (DEBUG) {
            Log.d("WaterWave", "onDetachedFromWindow");
        }
        if (mHandler.hasMessages(0)) {
            mHandler.removeMessages(0);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (DEBUG) {
            Log.d("WaterWave", "finalize:");
        }
    }

    public void startAnimation() {
        if (!mAnimating) {
            mRound = 0;
            mAnimating = true;
            mHandler.sendEmptyMessage(0);
        }
    }

    public void stopAnimation() {
        if (mAnimating) {
            mAnimating = false;
        }
    }

    public boolean isAnimating() {
        return mAnimating;
    }

    /**
     * @param level
     *            水纹的位置，0 ～ 1.0，level是1.0，满水位；0.0，空水位。
     */
    public void setWaterLevel(float level) {
        mWaterLevel = level;
    }

    public void setWaterAlpha(float alpha) {
        mWaterAlpha = (int) (255 * alpha);
        mForePaint.setAlpha(mWaterAlpha);
    }

    /**
     * @param amplitude
     *            水纹振幅
     */
    public void setAmplitude(float amplitude) {
        mAmplitude = amplitude;
    }

    public void setWaterFrequent(float freq) {
        mFrequency = freq;
    }

    public void setWaterColor(int color) {
        mWaterColor = color;
        mForePaint.setColor(mWaterColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //        if (DEBUG) {
        //            Log.d("WaterWave", "round = " + String.valueOf(mRound) + " s:" + mCount);
        //        }

        int width = getWidth();
        int height = getHeight();

        if (!mAnimating || width == 0 || height == 0) {
            canvas.drawRect(0, height / 2, width, height, mForePaint);
        } else {
            if (mRound >= 0x7FFFFF) {
                mRound = 0;
            }
            mRound++;

            int y = 0, oldX = 0, oldY = 0;
            float waterHeight = height * (1 - mWaterLevel);
            int splitlineHeight = (int) (waterHeight + mAmplitude);

            // Draw rect
            canvas.drawRect(0, splitlineHeight, width, height, mForePaint);

            // Draw wave
            oldY = (int) (waterHeight - mAmplitude * Math.sin((0 + mRound * width * mFrequency) * 2 * Math.PI / width));
            for (int x = 0; x < width; x++) {
                y = (int) (waterHeight - mAmplitude * Math.sin((x + mRound * width * mFrequency) * 2 * Math.PI / width));

                canvas.drawLine(oldX, oldY, x, y, mForePaint);
                canvas.drawLine(x, y, x, splitlineHeight, mForePaint);

                oldX = x;
                oldY = y;
            }
        }
    }
}
