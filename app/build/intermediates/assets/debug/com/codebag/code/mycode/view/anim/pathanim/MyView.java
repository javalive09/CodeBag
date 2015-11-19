package com.codebag.code.mycode.view.anim.pathanim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

class MyView extends View {

    int framesPerSecond = 60;
    long animationDuration = 10000; // 10 seconds

    Matrix matrix = new Matrix(); // transformation matrix

    Path path = new Path();       // your path
    Paint paint = new Paint();    // your paint

    long startTime;

    public MyView(Context context) {
        super(context);

        // start the animation:
        this.startTime = System.currentTimeMillis();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        path.moveTo(10, 50);   // THIS TRANSFORMATIONS TO BE ANIMATED!!!!!!!!
        path.lineTo(40, 50);
        path.moveTo(40, 50);
        path.lineTo(50, 40);
        this.postInvalidate(); 
    }

    @Override
    protected void onDraw(Canvas canvas) {

        long elapsedTime = System.currentTimeMillis() - startTime;

        matrix.postRotate(30 * elapsedTime/1000);        // rotate 30° every second
        matrix.postTranslate(100 * elapsedTime/1000, 0); // move 100 pixels to the right
        // other transformations...

        canvas.concat(matrix);        // call this before drawing on the canvas!!

        canvas.drawPath(path, paint); // draw on canvas

        if(elapsedTime < animationDuration)
            this.postInvalidateDelayed( 1000 / framesPerSecond);
    }

}