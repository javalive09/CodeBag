package com.javalive09.demos.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class ArcView extends View {

    Paint paint = new Paint();
    RectF rf = new RectF(0, 0, 300, 300);

    public ArcView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.CYAN);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawArc(rf, 0, 360, false, paint);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        canvas.clipRect(0, 50, 300, 300);
        canvas.drawArc(rf, 0, 360, false, paint);
    }
}