package com.javalive09.demos.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.javalive09.demos.R;


/**
 * 可动态改变数字的drawable
 * <p>
 * Created by peter on 2017/7/11.
 */

public class TextDrawable extends Drawable {

    private String text;
    private final Paint paint;
    private final Context context;
    private Drawable vectorDrawable;
    private RectF rectF;
    private Rect textBounds;

    public TextDrawable(Context context) {
        this.context = context;
        this.paint = new Paint();
        this.textBounds = new Rect();
        vectorDrawable = context.getResources().getDrawable(R.drawable.ic_multi);
    }

    public void setText(int text, int spValue) {
        this.text = text + "";
        paint.setColor(context.getResources().getColor(R.color.tint_color));
        int size = sp2px(context, spValue);

        paint.setTextSize(size);
        paint.setAntiAlias(true);

        paint.setFakeBoldText(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);

        paint.getTextBounds(this.text, 0, this.text.length(), new Rect());
        invalidateSelf();
    }

    private int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Bitmap bitmap = getBitmap(vectorDrawable);
        canvas.drawBitmap(bitmap, null, rectF, paint);

        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, rectF.centerX(), rectF.centerY() + (textBounds.height() >> 1) , paint);
    }

    private static Bitmap getBitmap(Drawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        rectF = new RectF(left, top, right, bottom);
    }

    @Override
    public int getIntrinsicWidth() {
        return vectorDrawable.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return vectorDrawable.getIntrinsicHeight();
    }

}

