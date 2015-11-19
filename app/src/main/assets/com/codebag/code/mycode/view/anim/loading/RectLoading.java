package com.codebag.code.mycode.view.anim.loading;

import com.codebag.code.mycode.utils.DisplayUtil;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;



/**
 * 36 x 16
 * 
 * @author zhangrui-ms
 *
 */
public class RectLoading extends View {

    private Paint mPaint;
    
    public RectLoading(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Style.STROKE);
    }
    
    public RectLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(11, 11, 555, 555, mPaint);
        Log.i("peter", "ondraw");
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(900, 900);
    }

//	@Override
//	protected void onLayout(boolean changed, int left, int top, int right,
//			int bottom) {
//		layout(0, 0, 900, 900);
//	}
    
    

}
