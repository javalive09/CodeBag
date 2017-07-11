package com.javalive09.sample.view.drawable.custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.javalive09.sample.R;
import com.javalive09.sample.utils.DisplayUtil;
import com.javalive09.sample.utils.Utils;

/**
 * Created by peter on 2017/7/11.
 */

public class TextDrawable extends Drawable {

    private String text;
    private final Paint paint;
    private Context context;
    private Rect bounds = new Rect();

    public TextDrawable(Context context) {
        this.context = context;
        this.paint = new Paint();
    }

    public void setText(int text) {
        this.text = text + "";
        paint.setColor(context.getResources().getColor(R.color.tint_color));
        int size = DisplayUtil.sp2px(context, 13);

        paint.setTextSize(size);
        paint.setAntiAlias(true);

        paint.setFakeBoldText(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);

        paint.getTextBounds(this.text, 0, this.text.length(), bounds);
        invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Drawable vectorDrawable = context.getResources().getDrawable(R.drawable.ic_multi);
        Bitmap bitmap = getBitmap(vectorDrawable);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        int xPos = (bitmap.getWidth() / 2);
        int yPos = (int) ((bitmap.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
        canvas.drawText(text, xPos, yPos, paint);
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


}
