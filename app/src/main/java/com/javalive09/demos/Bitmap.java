package com.javalive09.demos;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;

/**
 * Bitmap 测试
 */

public class Bitmap {

    @Run(name = "Bitmap.Config.ALPHA_8")
    public void alpha_8(CodeActivity activity) {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ALPHA_8, false);
        ImageView view = new ImageView(activity);
        view.setImageBitmap(mBitmap);
        activity.setContentView(view);
    }

    @Run(name = "Bitmap.Config.RGB_565")
    public void rgb_565(CodeActivity activity) {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.RGB_565, false);
        ImageView view = new ImageView(activity);
        view.setImageBitmap(mBitmap);
        activity.setContentView(view);
    }

    @Run(name = "Bitmap.Config.ARGB_4444")
    public void argb_4444(CodeActivity activity) {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ARGB_4444, false);
        ImageView view = new ImageView(activity);
        view.setImageBitmap(mBitmap);
        activity.setContentView(view);
    }

    @Run(name = "Bitmap.Config.ARGB_8888")
    public void argb_8888(CodeActivity activity) {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false);
        ImageView view = new ImageView(activity);
        view.setImageBitmap(mBitmap);
        activity.setContentView(view);
    }

    @Run(name = "按比例缩放Bitmap")
    public void drawScale(CodeActivity activity) {
        int width = DisplayUtil.dip2px(activity, 5);
        int height = DisplayUtil.dip2px(activity, 5);

        ImageView iv = new ImageView(activity);
        android.graphics.Bitmap src = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        android.graphics.Bitmap bm = android.graphics.Bitmap.createScaledBitmap(src, width, height, false);
        iv.setImageBitmap(bm);
        activity.setContentView(iv);
    }

    @Run(name = "在Bitmap上绘线")
    public void drawLine(CodeActivity activity) {
        int width = DisplayUtil.dip2px(activity, 54);
        int height = DisplayUtil.dip2px(activity, 58);

        android.graphics.Bitmap src = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.ic_launcher);
        android.graphics.Bitmap bm = android.graphics.Bitmap.createScaledBitmap(src, width, height, false);
        android.graphics.Bitmap bitmapAltered = android.graphics.Bitmap.createBitmap(width, height, bm.getConfig());
        Canvas canvas = new Canvas(bitmapAltered);

        Paint paint = new Paint();
        paint.setDither(true);

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bm, 0, 0, paint);

        int paintW = DisplayUtil.dip2px(activity, 1);
        paint.setStrokeWidth(paintW);
        paint.setFilterBitmap(true);
        canvas.drawLine(paintW, width + paintW, width - paintW, width + paintW, paint);
        canvas.drawLine(paintW * 2, width + 3 * paintW, width - 2 * paintW, width + 3 * paintW, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        ImageView iv = new ImageView(activity);
        iv.setImageBitmap(bitmapAltered);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.gravity = Gravity.TOP;
        activity.setContentView(iv);
    }

}
