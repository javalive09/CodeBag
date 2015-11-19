package com.codebag.code.mycode.view.imageview.zoomimageview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ZoomImageView extends ImageView {

	public ZoomImageView(Context context) {
		super(context);
		init();
	}

	private void init() {
		setOnTouchListener(new MulitPointTouchListener());
		setScaleType(ScaleType.CENTER);
	}

	class MulitPointTouchListener implements OnTouchListener {
		// These matrices will be used to move and zoom image
		Matrix matrix = new Matrix();
		Matrix savedMatrix = new Matrix();
		Matrix originalMatrix = null;

		// We can be in one of these 3 states
		static final int NONE = 0;
		static final int DRAG = 1;
		static final int ZOOM = 2;
		int mode = NONE;

		// Remember some things for zooming
		PointF start = new PointF();
		PointF mid = new PointF();
		float oldDist = 1f;

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			ImageView view = (ImageView) v;
			// Log.e("view_width",
			// view.getImageMatrix()..toString()+"*"+v.getWidth());
			// Dump touch event to log
			// dumpEvent(event);

			// Handle touch events here...
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:

				if (getScaleType() != ScaleType.MATRIX) {
					setScaleType(ScaleType.MATRIX);
				}

				matrix.set(view.getImageMatrix());
				savedMatrix.set(matrix);
				if(originalMatrix == null) {
					originalMatrix = new Matrix(matrix);
				}
				
				start.set(event.getX(), event.getY());
				// Log.d(TAG, "mode=DRAG");
				mode = DRAG;

				// Log.d(TAG, "mode=NONE");
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				oldDist = spacing(event);
				// Log.d(TAG, "oldDist=" + oldDist);
				if (oldDist > 10f) {
					savedMatrix.set(matrix);
					midPoint(mid, event);
					mode = ZOOM;
					// Log.d(TAG, "mode=ZOOM");
				}
				break;
			case MotionEvent.ACTION_UP:
				matrix.set(originalMatrix);
				mode = NONE;
				Log.i("peter", "MotionEvent.ACTION_UP");
			case MotionEvent.ACTION_POINTER_UP:
				mode = DRAG;
				Log.i("peter", "MotionEvent.ACTION_POINTER_UP");
				savedMatrix.set(matrix);
				Log.i("peter", "event.getPointerCount() =" + event.getPointerCount());
				if(event.getPointerCount() > 1) {
					
					int id = 1 - (event.getAction() & 0xff00) >> 8;
					start.set(event.getX(id), event.getY(id));
				}
				// Log.e("view.getWidth", view.getWidth() + "");
				// Log.e("view.getHeight", view.getHeight() + "");

				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == DRAG) {
					// ...
					matrix.set(savedMatrix);
					matrix.postTranslate(event.getX() - start.x, event.getY()
							- start.y);
				} else if (mode == ZOOM) {
					float newDist = spacing(event);
					// Log.d(TAG, "newDist=" + newDist);
					if (newDist > 10f) {
						matrix.set(savedMatrix);
						float scale = newDist / oldDist;
						matrix.postScale(scale, scale, mid.x, mid.y);
					}
				}
				break;
			}

			view.setImageMatrix(matrix);
			return true; // indicate event was handled
		}

		// private void dumpEvent(MotionEvent event) {
		// String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
		// "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
		// StringBuilder sb = new StringBuilder();
		// int action = event.getAction();
		// int actionCode = action & MotionEvent.ACTION_MASK;
		// sb.append("event ACTION_").append(names[actionCode]);
		// if (actionCode == MotionEvent.ACTION_POINTER_DOWN
		// || actionCode == MotionEvent.ACTION_POINTER_UP) {
		// sb.append("(pid ").append(
		// action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
		// sb.append(")");
		// }
		// sb.append("[");
		// for (int i = 0; i < event.getPointerCount(); i++) {
		// sb.append("#").append(i);
		// sb.append("(pid ").append(event.getPointerId(i));
		// sb.append(")=").append((int) event.getX(i));
		// sb.append(",").append((int) event.getY(i));
		// if (i + 1 < event.getPointerCount())
		// sb.append(";");
		// }
		// sb.append("]");
		// //Log.d(TAG, sb.toString());
		// }

		private float spacing(MotionEvent event) {
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return FloatMath.sqrt(x * x + y * y);
		}

		private void midPoint(PointF point, MotionEvent event) {
			float x = event.getX(0) + event.getX(1);
			float y = event.getY(0) + event.getY(1);
			point.set(x / 2, y / 2);
		}
	}

}
