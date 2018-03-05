package com.javalive09.demos;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.javalive09.codebag.CodeBag;
import com.javalive09.codebag.annotation.Test;

/**
 * Bitmap 测试
 */

public class Bitmap {

    @Test(name = "Bitmap.Config.ALPHA_8")
    public void alpha_8() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(CodeBag.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ALPHA_8, false);
        ImageView view = new ImageView(CodeBag.context());
        view.setImageBitmap(mBitmap);
        CodeBag.showView(view);
    }

    @Test(name = "Bitmap.Config.RGB_565")
    public void rgb_565() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(CodeBag.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.RGB_565, false);
        ImageView view = new ImageView(CodeBag.context());
        view.setImageBitmap(mBitmap);
        CodeBag.showView(view);
    }

    @Test(name = "Bitmap.Config.ARGB_4444")
    public void argb_4444() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(CodeBag.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ARGB_4444, false);
        ImageView view = new ImageView(CodeBag.context());
        view.setImageBitmap(mBitmap);
        CodeBag.showView(view);
    }

    @Test(name = "Bitmap.Config.ARGB_8888")
    public void argb_8888() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(CodeBag.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false);
        ImageView view = new ImageView(CodeBag.context());
        view.setImageBitmap(mBitmap);
        CodeBag.showView(view);
    }

    @Test(name = "按比例缩放Bitmap")
    public void drawScale() {
        int width = DisplayUtil.dip2px(CodeBag.context(), 5);
        int height = DisplayUtil.dip2px(CodeBag.context(), 5);

        ImageView iv = new ImageView(CodeBag.context());
        android.graphics.Bitmap src = BitmapFactory.decodeResource(CodeBag.context().getResources(), R.mipmap.ic_launcher);
        android.graphics.Bitmap bm = android.graphics.Bitmap.createScaledBitmap(src, width, height, false);
        iv.setImageBitmap(bm);
        CodeBag.showView(iv);
    }

    @Test(name = "在Bitmap上绘线")
    public void drawLine() {
        int width = DisplayUtil.dip2px(CodeBag.context(), 54);
        int height = DisplayUtil.dip2px(CodeBag.context(), 58);

        android.graphics.Bitmap src = BitmapFactory.decodeResource(CodeBag.context().getResources(), R.mipmap.ic_launcher);
        android.graphics.Bitmap bm = android.graphics.Bitmap.createScaledBitmap(src, width, height, false);
        android.graphics.Bitmap bitmapAltered = android.graphics.Bitmap.createBitmap(width, height, bm.getConfig());
        Canvas canvas = new Canvas(bitmapAltered);

        Paint paint = new Paint();
        paint.setDither(true);

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bm, 0, 0, paint);

        int paintW = DisplayUtil.dip2px(CodeBag.context(), 1);
        paint.setStrokeWidth(paintW);
        paint.setFilterBitmap(true);
        canvas.drawLine(paintW, width + paintW, width - paintW, width + paintW, paint);
        canvas.drawLine(paintW * 2, width + 3 * paintW, width - 2 * paintW, width + 3 * paintW, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageBitmap(bitmapAltered);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.gravity = Gravity.TOP;
        CodeBag.showView(iv);
    }

}
