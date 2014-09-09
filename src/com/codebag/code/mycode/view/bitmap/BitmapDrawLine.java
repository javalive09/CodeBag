package com.codebag.code.mycode.view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.DisplayUtil;

public class BitmapDrawLine extends CaseListView {

	int width = DisplayUtil.dip2px(getContext(), 54);
	int height = DisplayUtil.dip2px(getContext(), 58);

	public BitmapDrawLine(Context context) {
		super(context);
		width = DisplayUtil.dip2px(context, 54);
		height = DisplayUtil.dip2px(context, 58);
	}

	@Entry
	public void drawScale() {
		ImageView iv = new ImageView(getContext());
		Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.head);
		Bitmap bm = Bitmap.createScaledBitmap(src, width, width, false);
		iv.setImageBitmap(bm);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
		showView(iv, params);
	}

	@Entry
	public void drawLine() {
		
		Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.head);
		Bitmap bm = Bitmap.createScaledBitmap(src, width, width, false);
		Bitmap bitmapAltered = Bitmap.createBitmap(width, height, bm.getConfig());
		Canvas canvas = new Canvas(bitmapAltered);
		
		Paint paint = new Paint();
		paint.setDither(true);

		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(bm, 0, 0, paint);

		int paintW = DisplayUtil.dip2px(getContext(), 1);
		paint.setStrokeWidth(paintW);
		paint.setFilterBitmap(true);
		canvas.drawLine(paintW, width + paintW, width - paintW, width + paintW, paint);
		canvas.drawLine(paintW*2, width + 3*paintW, width - 2*paintW, width + 3*paintW, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();

		ImageView iv = new ImageView(getContext());
		iv.setImageBitmap(bitmapAltered);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
		params.gravity = Gravity.TOP;
		showView(iv, params);
	}

}
