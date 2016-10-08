package com.javalive09.sample.view.gradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Gradient extends View {

	int mStartColor;
	int mEndColor;
	Paint mPaint;
	
	public Gradient(Context context) {
		super(context);
		mPaint = new Paint();
	}
	
	public void setGradient(int startColor, int endColor) {
		mStartColor = startColor;
		mEndColor = endColor;
	}
 
	@Override
	protected void onDraw(Canvas canvas) {
		drawGradient(canvas, mPaint, 0, 0, getMeasuredWidth(), getMeasuredHeight(), mStartColor, mEndColor);
	}
	
	private void drawGradient(Canvas canvas, Paint paint, int startX, int startY, int width, int height, int startColor, int endColor) {
		int startRed = (startColor >> 16) & 0xff;
		int endRed = (endColor >> 16) & 0xff;
		int startGreen = (startColor >> 8) & 0xff;
		int endGreen = (endColor >> 8) & 0xff;
		int startBlue = startColor & 0xff;
		int endBlue = endColor & 0xff;
		
		float deltaRed = (endRed - startRed) / (float)height;
		float deltaGreen = (endGreen - startGreen) / (float)height;
		float deltaBlue = (endBlue - startBlue) / (float)height;
		
		int red, green , blue;
		for(int i = 0; i < height; i++) { 
			red = (int) (startRed + deltaRed * i);
			green = (int) (startGreen + deltaGreen * i);
			blue = (int) (startBlue + deltaBlue * i);
			paint.setARGB(0xff, red, green, blue);
			canvas.drawRect(startX, startY + i, width, startY + i + 1, paint);
		}
		
	}
	
	

}
