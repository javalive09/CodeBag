package com.javalive09.demos;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.javalive09.codebag.Player;
import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;

/**
 * Bitmap 测试
 */

public class Bitmap {

    @Play(name = "Bitmap.Config.ALPHA_8")
    public void alpha_8() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ALPHA_8, false);
        ImageView view = new ImageView(PlayerActivity.context());
        view.setImageBitmap(mBitmap);
        PlayerActivity.context().showView(view);
    }

    @Play(name = "Bitmap.Config.RGB_565")
    public void rgb_565() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.RGB_565, false);
        ImageView view = new ImageView(PlayerActivity.context());
        view.setImageBitmap(mBitmap);
        PlayerActivity.context().showView(view);
    }

    @Play(name = "Bitmap.Config.ARGB_4444")
    public void argb_4444() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ARGB_4444, false);
        ImageView view = new ImageView(PlayerActivity.context());
        view.setImageBitmap(mBitmap);
        PlayerActivity.context().showView(view);
    }

    @Play(name = "Bitmap.Config.ARGB_8888")
    public void argb_8888() {
        android.graphics.Bitmap mBitmap = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.bitmap);
        mBitmap = mBitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false);
        ImageView view = new ImageView(PlayerActivity.context());
        view.setImageBitmap(mBitmap);
        PlayerActivity.context().showView(view);
    }

    @Play(name = "按比例缩放Bitmap")
    public void drawScale() {
        int width = DisplayUtil.dip2px(PlayerActivity.context(), 5);
        int height = DisplayUtil.dip2px(PlayerActivity.context(), 5);

        ImageView iv = new ImageView(PlayerActivity.context());
        android.graphics.Bitmap src = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.mipmap.ic_launcher);
        android.graphics.Bitmap bm = android.graphics.Bitmap.createScaledBitmap(src, width, height, false);
        iv.setImageBitmap(bm);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "在Bitmap上绘线")
    public void drawLine() {
        int width = DisplayUtil.dip2px(PlayerActivity.context(), 54);
        int height = DisplayUtil.dip2px(PlayerActivity.context(), 58);

        android.graphics.Bitmap src = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.mipmap.ic_launcher);
        android.graphics.Bitmap bm = android.graphics.Bitmap.createScaledBitmap(src, width, height, false);
        android.graphics.Bitmap bitmapAltered = android.graphics.Bitmap.createBitmap(width, height, bm.getConfig());
        Canvas canvas = new Canvas(bitmapAltered);

        Paint paint = new Paint();
        paint.setDither(true);

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bm, 0, 0, paint);

        int paintW = DisplayUtil.dip2px(PlayerActivity.context(), 1);
        paint.setStrokeWidth(paintW);
        paint.setFilterBitmap(true);
        canvas.drawLine(paintW, width + paintW, width - paintW, width + paintW, paint);
        canvas.drawLine(paintW * 2, width + 3 * paintW, width - 2 * paintW, width + 3 * paintW, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageBitmap(bitmapAltered);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
        params.gravity = Gravity.TOP;
        PlayerActivity.context().showView(iv);
    }

}
