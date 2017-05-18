package com.javalive09.sample.project.xui.bindpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

public class RectPathView extends View {

	Path path = new Path();
	Paint paint = new Paint();
	Point stroke_start = new Point(500, 0);
	Point stroke_one = new Point(500, 200);
	Point stroke_two = new Point(900, 200);
	Point stroke_two_ = new Point(100, 200);
	Point stroke_three = new Point(900, 300);
	Point stroke_three_ = new Point(100, 300);
	Point stroke_four = new Point(500, 300);
	Point stroke_five = new Point(500, 500);
	Point stroke = new Point();
	MyAnimListener mListener;
	int delta = 19;
	
	public RectPathView(Context context) {
		super(context);
		
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(4);
		paint.setAntiAlias(true);
	}
	
	public void anim() {
		stroke.set(stroke_start.x, stroke_start.y);
		path.moveTo(stroke_start.x, stroke_start.y);
		Message m = Message.obtain();
		m.obj = stroke;
		mHandler.sendMessage(m);
	}
	
	boolean end = false;
	
	public void endAnim() {
		end = true;
	}
	
    protected void onDetachedFromWindow() {
    	super.onDetachedFromWindow();
    	endAnim();
    }
	
	public void setPoint(Point start, Point one, Point two, Point two_, Point three, Point three_, Point four, Point five) {
		stroke_start = start;
		stroke_one = one;
		stroke_two = two;
		stroke_two_ = two_;
		stroke_three = three;
		stroke_three_ = three_;
		stroke_four = four;
		stroke_five = five;
	}
	
	Handler mHandler = new Handler(Looper.getMainLooper()){

		@Override
		public void handleMessage(Message msg) {
			if(end) {
				return;
			}
			
			super.handleMessage(msg);
			Point p = (Point) msg.obj;
			if(p.y >=  stroke_start.y && p.y < stroke_one.y) {//one
				p.y += delta;
				if(p.y > stroke_one.y) {
					p.y = stroke_one.y;
				}
				path.lineTo(p.x, p.y);
			}else if(p.y == stroke_one.y && p.x < stroke_two.x) {//two
				p.x += delta;
				if(p.x > stroke_two.x) {
					p.x = stroke_two.x;
				}
				int x = p.x;
				x = stroke_start.x * 2 - p.x;
				path.lineTo(x, p.y);
				path.lineTo(p.x, p.y);
			}else if(p.x == stroke_two.x && p.y < stroke_three.y) {//three
				p.y += delta;
				if(p.y > stroke_three.y) {
					p.y = stroke_three.y;
				}
				path.lineTo(p.x, p.y);
				
				path.moveTo(stroke_two_.x, stroke_two_.y);
				path.lineTo(stroke_two_.x, p.y);
				path.moveTo(p.x, p.y);
			}else if(p.y == stroke_three.y && p.x > stroke_four.x) {//four
				p.x -= delta;
				if(p.x < stroke_four.x) {
					p.x = stroke_four.x;
				}
				path.lineTo(p.x, p.y);
				path.moveTo(stroke_three_.x, p.y);
				
				int x = stroke_start.x * 2 - p.x;
				path.lineTo(x, p.y);
				path.moveTo(p.x, p.y);
			}else if(p.x == stroke_four.x && p.y < stroke_five.y) {
				p.y += delta;
				if(p.y > stroke_five.y) {
					p.y = stroke_five.y;
				}
				path.lineTo(p.x, p.y);
			}else {
				mListener.end();
				return;
			}
			
			Message m = Message.obtain();
			m.obj = p;
			mHandler.sendMessageDelayed(m, 1);
			
			postInvalidate();
		}
		
	};
	
	public void setAnimListener(MyAnimListener listener) {
		mListener = listener;
	}
	
	public static interface MyAnimListener{
		public void end();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}
	
}
