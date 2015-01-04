package com.codebag.code.mycode.view.anim.loading;

import com.codebag.code.mycode.utils.DisplayUtil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;



/**
 * 36 x 16
 * 
 * @author zhangrui-ms
 *
 */
public class RectLoading extends View {

    private Paint mPaint;
    
    private int mCellWidth;
    
    private int [] colors = {};
    
    public RectLoading(Context context) {
        super(context);
        mPaint = new Paint();
        mCellWidth = DisplayUtil.dip2px(getContext(), 4);
    }
    
    public RectLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawRect(left, top, right, bottom, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(9 * mCellWidth, 4 * mCellWidth);
    }
    
    

}
