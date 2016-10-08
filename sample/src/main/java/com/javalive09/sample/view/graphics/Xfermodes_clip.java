package com.javalive09.sample.view.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;

public class Xfermodes_clip {

	// create a bitmap with a circle, used for the "dst" image
	static Bitmap makeDst(int w, int h) {
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//		Canvas c = new Canvas(bm);
		c.setBitmap(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

		p.setColor(0xFFFFCC44);
		c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
		return bm;
	}

	static Canvas c = new Canvas();
	
	// create a bitmap with a rect, used for the "src" image
	static Bitmap makeSrc(int w, int h) {
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//		Canvas c = new Canvas(bm);
		c.setBitmap(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

		p.setColor(0xFF66AAFF);
		c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
		return bm;
	}

	public static class SampleView extends View {
		private static final int W = 64;
		private static final int H = 64;

		private Bitmap mSrcB;
		private Bitmap mDstB;

		public SampleView(Context context) {
			super(context);

			mSrcB = makeSrc(W, H);
			mDstB = makeDst(W, H);

		}

		Paint paint = new Paint();
		PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
		
		protected void onDraw(Canvas canvas) {

			paint.setFilterBitmap(false);

            int x = 100;
            int y = 100;
			
            // draw the src/dst example into our offscreen bitmap
            canvas.saveLayer(x, y, x + W, y + H, null,
                                      Canvas.MATRIX_SAVE_FLAG |
                                      Canvas.CLIP_SAVE_FLAG |
                                      Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                                      Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                                      Canvas.CLIP_TO_LAYER_SAVE_FLAG);
			
            canvas.translate(x, y);
			canvas.drawBitmap(mDstB, 0, 0, paint);
			paint.setXfermode(mode);
			canvas.drawBitmap(mSrcB, 0, 0, paint);
			
			paint.setXfermode(null);

		}
	}
}