package com.codebag.code.mycode.cleanmaster.progress;

import java.lang.ref.WeakReference;

import com.codebag.code.mycode.utils.DisplayUtil;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.FrameLayout;

public class CircleView extends View {

    private int mInnerDiameter;
    private int mOuterDiameter;
    private RectF mSectionRect;
    private int mProgress;
    private Paint mPaint;
    private static final int FREE_PROGRESS_COLOR = Color.parseColor("#19ffffff");
    private static final int PROGRESS_COLOR = Color.WHITE;
    private Handler mHandler;
    private int mDelay;
    private AniminationCallBack mCallBack;
    private boolean mEndAnim;

    public CircleView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mInnerDiameter = DisplayUtil.dip2px(context, 114);// 内直径
        mOuterDiameter = DisplayUtil.dip2px(context, 136);// 外直径
        int sectionW = DisplayUtil.dip2px(context, 2);// 小块宽
        int sectionH = DisplayUtil.dip2px(context, 11);// 小块高
        mSectionRect = new RectF(-sectionW / 2, -sectionH, sectionW / 2, 0);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(FREE_PROGRESS_COLOR); // Set default
        mHandler = new MyHandler(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mOuterDiameter, mOuterDiameter);
    }

    public void setAniminationCallBack(AniminationCallBack callBack) {
        mCallBack = callBack;
    }

    public void onDraw(Canvas canvas) {
        
        canvas.translate(mOuterDiameter / 2, mOuterDiameter / 2);

        float rotation = 360.0f / 100;

        for (int i = 0; i < 100; i++) {
            canvas.save();

            canvas.rotate(i * rotation);
            canvas.translate(0, -mInnerDiameter / 2);

            if (i <= mProgress) {
                mPaint.setColor(PROGRESS_COLOR);
            } else {
                mPaint.setColor(FREE_PROGRESS_COLOR);
            }

            canvas.drawRect(mSectionRect, mPaint);
            canvas.restore();
        }
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mEndAnim = true;
    }

    public void startAnim() {
        mHandler.sendEmptyMessage(0);
        mEndAnim = false;
        if (mCallBack != null) {
            mCallBack.start();
        }
    }

    public void setDuration(int dur) {
        mDelay = dur / 100;
    }

    private static class MyHandler extends Handler {
        private final WeakReference<CircleView> mOuter;

        MyHandler(CircleView outer) {
            mOuter = new WeakReference<CircleView>(outer);
        }

        @Override
        public void handleMessage(Message msg) {

            CircleView obj = mOuter.get();

            if (obj == null) {
                return;
            }

            obj.setProgress(msg.what);

            msg.what++;

            if (msg.what == 100) {
                if (obj.mCallBack != null) {
                    obj.mCallBack.end();
                }
                msg.what = 0;
            }
            if (!obj.mEndAnim) {
                obj.mHandler.sendEmptyMessageDelayed(msg.what, obj.mDelay);
            }
        }

    }

    public static interface AniminationCallBack {
        public void start();

        public void end();
    }

}
