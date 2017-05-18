package com.javalive09.sample.view.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.utils.DisplayUtil;
import com.javalive09.sample.R;

public class BitmapDrawLine extends Entry {

	public void drawScale() {
		int width = DisplayUtil.dip2px(getActivity(), 54);
		int height = DisplayUtil.dip2px(getActivity(), 58);

		ImageView iv = new ImageView(getActivity());
		Bitmap src = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher);
		Bitmap bm = Bitmap.createScaledBitmap(src, width, width, false);
		iv.setImageBitmap(bm);
		showView(iv);
	}

	public void drawLine() {
		int width = DisplayUtil.dip2px(getActivity(), 54);
		int height = DisplayUtil.dip2px(getActivity(), 58);

		Bitmap src = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher);
		Bitmap bm = Bitmap.createScaledBitmap(src, width, width, false);
		Bitmap bitmapAltered = Bitmap.createBitmap(width, height, bm.getConfig());
		Canvas canvas = new Canvas(bitmapAltered);
		
		Paint paint = new Paint();
		paint.setDither(true);

		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(bm, 0, 0, paint);

		int paintW = DisplayUtil.dip2px(getActivity(), 1);
		paint.setStrokeWidth(paintW);
		paint.setFilterBitmap(true);
		canvas.drawLine(paintW, width + paintW, width - paintW, width + paintW, paint);
		canvas.drawLine(paintW*2, width + 3*paintW, width - 2*paintW, width + 3*paintW, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		canvas.restore();

		ImageView iv = new ImageView(getActivity());
		iv.setImageBitmap(bitmapAltered);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
		params.gravity = Gravity.TOP;
		showView(iv);
	}

}
