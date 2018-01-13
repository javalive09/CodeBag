package com.javalive09.demos.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * 椭圆路径动画
 */
public class CircleView extends View implements ValueAnimator.AnimatorUpdateListener {

    private Paint paint;
    private Path path;
    private int angleFinal = 0;
    private RectF rect;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        path = new Path();
        rect = new RectF(50, 20, 400, 200);
        postDelayed(() -> {
            angleFinal = 360;
            animacion();
        }, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, 0, angleFinal, true, paint);
    }

    private void animacion() {
        ValueAnimator anim = ValueAnimator.ofInt(0, angleFinal);
        anim.setDuration(3000);
        anim.addUpdateListener(this);
        anim.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        path.reset();
        angleFinal = (Integer) animation.getAnimatedValue();
        ViewCompat.postInvalidateOnAnimation(this);
    }

}