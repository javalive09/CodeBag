package com.codebag.code.mycode.view.anim.pathanim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

public class CustomView extends View implements ValueAnimator.AnimatorUpdateListener {

    private Paint paint;
    private Path path;
    private int angleFinal = 360;
    private RectF rect;



    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        rect = new RectF(50, 20, 250, 200);
        animacion();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc (rect, 180, angleFinal, true, paint);

    }

    private void animacion(){
        ValueAnimator anim = ValueAnimator.ofInt( 0, angleFinal);
        anim.setDuration(1500);
        anim.addUpdateListener(this);
        anim.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        path.reset();
        angleFinal = (Integer)animation.getAnimatedValue();
        ViewCompat.postInvalidateOnAnimation(this);
    }
}