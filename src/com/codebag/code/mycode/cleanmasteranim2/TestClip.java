package com.codebag.code.mycode.cleanmasteranim2;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;

public class TestClip extends View {

    private Paint mPaint;
    private Path mPath;

	
	public TestClip(Context context) {
		super(context);
        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.RIGHT);

        mPath = new Path();
	}

	
    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 100, 100);
        
        canvas.drawColor(Color.WHITE);
        
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, mPaint);
        
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, mPaint);
        
        mPaint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 100, 30, mPaint);
    }
    
    @Override protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);            
        
        canvas.save();
        canvas.translate(10, 160);
        mPath.reset();
        canvas.clipPath(mPath); // makes the clip empty
        mPath.addCircle(50, 50, 50, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        drawScene(canvas);
        canvas.restore();
        
    }
	
}
