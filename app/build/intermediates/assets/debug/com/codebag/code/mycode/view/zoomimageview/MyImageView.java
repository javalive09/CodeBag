package com.codebag.code.mycode.view.zoomimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MyImageView extends ImageView {
	static final int NONE = 0;
	// 拖动中
	static final int DRAG = 1;
	// 缩放中
	static final int ZOOM = 2;
	// 放大ing
	static final int BIGGER = 3;
	// 缩小ing
	static final int SMALLER = 4;
	// 关闭缩放动画
	static final int OPENSCALE = 1;
	// 关闭移动动画
	static final int OPENTRANS = 2;
	// 当前的事件
	private int mode = NONE;
	// 两触点距离
	private float beforeLenght;
	// 两触点距离
	private float afterLenght;
	// 缩放的比例 X Y方向都是这个值 越大缩放的越快
	private float scale = 0.06f;
	/* 处理拖动 变量 */
	private int screenW;
	private int screenH;
	private int start_x;
	private int start_y;
	private int stop_x;
	private int stop_y;
	private TranslateAnimation trans;
	/* Bitmap的宽高 */
	private int bmWidth;
	private int bmHeight;
	// 处理超出边界的动画
	private Bitmap bitmap;
	private float maxScale = 2.0f;
	private float minScale = 1.0f;
	// 记录初始宽度，用于缩放回弹
	private int startWidth = 0;
	private float piovtX = 0.5f;
	private float piovtY = 0.5f;
	// 默认开启所有动画
	private int AnimSwitch = OPENSCALE | OPENTRANS;

	/**
	 * 构造函数
	 * 
	 * @param context
	 *            相关上下文
	 * @param w
	 *            容器的宽
	 * @param h
	 *            容器的高
	 */
	public MyImageView(Context context, int w, int h) {
		super(context);
		this.setPadding(0, 0, 0, 0);
		screenW = w;
		screenH = h;
	}

	@Override
	public void setImageBitmap(Bitmap bm) {
		super.setImageBitmap(bm);
		// 重置startWidth
		startWidth = 0;
		bmWidth = bm.getWidth();
		bmHeight = bm.getHeight();
		if (bitmap != null && !bitmap.isRecycled())
			bitmap.recycle();
		bitmap = bm;
	}

	/**
	 * 释放ImageView的Bitmap
	 */
	public void recycle() {
		setImageBitmap(null);
		if (bitmap != null && !bitmap.isRecycled())
			bitmap.recycle();
	}

	/**
	 * 计算两点间的距离
	 */
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	private float[] center;

	/**
	 * 计算两点间的中心点
	 */
	private float[] centerPostion(MotionEvent event) {
		float[] center = new float[2];
		float x = event.getX(0);
		float y = event.getY(0);
		float x1 = event.getX(1);
		float y1 = event.getY(1);
		/* x,y分别的距离 */
		center[0] = Math.abs((x1 - x) / 2);
		center[1] = Math.abs((y1 - y) / 2);
		center[0] = Math.max(x, x1) - center[0];
		center[1] = Math.max(y, y1) - center[1];
		return center;
	}

	/**
	 * 设置ImageView大小等于显示的内容大小
	 */
	public void setRect() {
		float scale = Math.min((float) getWidth() / (float) bmWidth,
				(float) getHeight() / (float) bmHeight);
		int w = (int) ((float) bmWidth * scale) + 1;
		int h = (int) ((float) bmHeight * scale) + 1;
		// int t=(screenH-h)/2;
		// int l=(screenW-w)/2;
		int t = getTop();
		int l = getLeft();
		layout(l, t, l + w, t + h);
	}

	/**
	 * 处理各种移动回弹
	 * 
	 * @param disX
	 *            X的偏移
	 * @param disY
	 *            Y的偏移
	 */
	public void Rebound(int disX, int disY) {
		this.layout(getLeft() + disX, getTop() + disY, getLeft() + disX
				+ getWidth(), getTop() + disY + getHeight());
		if ((AnimSwitch & OPENTRANS) == 0)
			return;
		trans = new TranslateAnimation(-disX, 0, -disY, 0);
		trans.setInterpolator(new AccelerateInterpolator());
		trans.setDuration(300);
		this.startAnimation(trans);
	}

	/**
	 * 处理各种缩放回弹
	 */
	public boolean ReScale() {
		float scaleX = 1f;
		float scaleY = 1f;
		int width = getWidth();
		int height = getHeight();
		int l, t, r, b;
		if (center == null)
			return false;
		if (getWidth() > startWidth * maxScale) {
			while (getWidth() > startWidth * maxScale) {
				l = this.getLeft() + (int) (center[0] * this.getWidth());
				t = this.getTop() + (int) (center[1] * this.getHeight());
				r = this.getRight()
						- (int) ((scale - center[0]) * this.getWidth());
				b = this.getBottom()
						- (int) ((scale - center[1]) * this.getHeight());
				this.setFrame(l, t, r, b);
			}
			scaleX = (float) width / (float) getWidth();
			scaleY = (float) height / (float) getHeight();
		}
		if (getWidth() < startWidth * minScale) {
			while (getWidth() < startWidth * minScale) {
				l = this.getLeft() - (int) (center[0] * this.getWidth());
				t = this.getTop() - (int) (center[1] * this.getHeight());
				r = this.getRight()
						+ (int) ((scale - center[0]) * this.getWidth());
				b = this.getBottom()
						+ (int) ((scale - center[1]) * this.getHeight());
				this.setFrame(l, t, r, b);
			}
			scaleX = (float) width / (float) getWidth();
			scaleY = (float) height / (float) getHeight();
		}

		if (scaleX == 1f && scaleY == 1f)
			return false;
		if ((AnimSwitch & OPENSCALE) == 0) {
			setRect();
			onRebound();
			return true;
		}
		ScaleAnimation scaleanim = new ScaleAnimation(scaleX, 1f, scaleY, 1f,
				ScaleAnimation.RELATIVE_TO_SELF, piovtX,
				ScaleAnimation.RELATIVE_TO_SELF, piovtY);
		scaleanim.setDuration(300);
		scaleanim.setInterpolator(new AccelerateInterpolator());
		scaleanim.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation paramAnimation) {
			}

			@Override
			public void onAnimationRepeat(Animation paramAnimation) {
			}

			@Override
			public void onAnimationEnd(Animation paramAnimation) {
				setRect();
				onRebound();
			}

		});
		this.startAnimation(scaleanim);
		return true;
	}

	/**
	 * 处理超范围回弹
	 */
	private void onRebound() {
		/* 判断是否超出范围 并处理 */
		int disX = 0, disY = 0;
		if (getHeight() < screenH) {
			disY = (screenH - getHeight()) / 2 - getTop();
		}
		if (getWidth() < screenW) {
			disX = (screenW - getWidth()) / 2 - getLeft();
		}
		if (getHeight() >= screenH) {
			if (getTop() > 0)
				disY = -getTop();
			if (getBottom() < screenH)
				disY = screenH - getBottom();
		}
		if (getWidth() >= screenW) {
			if (getLeft() > 0)
				disX = -getLeft();
			if (getRight() < screenW)
				disX = screenW - getRight();
		}
		// 开始回弹
		Rebound(disX, disY);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		if (startWidth == 0) {
			startWidth = right - left;
			setRect();
			AnimSwitch = 0;
			onRebound();
			AnimSwitch = OPENSCALE | OPENTRANS;
		}
	}

	/**
	 * 处理触碰..
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			mode = DRAG;
			stop_x = (int) event.getRawX();
			stop_y = (int) event.getRawY();
			start_x = (int) event.getX();
			start_y = stop_y - this.getTop();
			if (event.getPointerCount() == 2)
				beforeLenght = spacing(event);
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			/** 下面三句用于计算缩放中心点位置 **/
			center = centerPostion(event);
			piovtX = center[0] / getWidth();
			piovtY = center[1] / getHeight();

			center[0] = (center[0] / getWidth()) * scale;
			center[1] = (center[1] / getHeight()) * scale;
			if (spacing(event) > 10f) {
				mode = ZOOM;
				beforeLenght = spacing(event);
			}
			break;
		case MotionEvent.ACTION_UP:
			mode = NONE;
			setRect();
			/* 判断是否超过缩放界限 */
			if (!ReScale())
				onRebound();
			break;
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;
		case MotionEvent.ACTION_MOVE:
			/* 处理拖动 */
			if (mode == DRAG) {
				this.setPosition(stop_x - start_x, stop_y - start_y, stop_x
						+ this.getWidth() - start_x,
						stop_y - start_y + this.getHeight());
				stop_x = (int) event.getRawX();
				stop_y = (int) event.getRawY();
			}
			/* 处理缩放 */
			else if (mode == ZOOM) {
				if (spacing(event) > 10f) {
					afterLenght = spacing(event);
					float gapLenght = afterLenght - beforeLenght;
					if (gapLenght == 0) {
						break;
					} else if (Math.abs(gapLenght) > 5f) {
						if (gapLenght > 0) {
							this.setScale(scale, BIGGER);
						} else {
							this.setScale(scale, SMALLER);
						}
						beforeLenght = afterLenght;
					}
				}
			}
			break;
		}
		return true;
	}

	/**
	 * 实现处理缩放
	 */
	private void setScale(float temp, int flag) {
		int l = 0, t = 0, r = 0, b = 0;
		if (flag == BIGGER) {
			l = this.getLeft() - (int) (center[0] * this.getWidth());
			t = this.getTop() - (int) (center[1] * this.getHeight());
			r = this.getRight() + (int) ((scale - center[0]) * this.getWidth());
			b = this.getBottom()
					+ (int) ((scale - center[1]) * this.getHeight());
		} else if (flag == SMALLER) {
			l = this.getLeft() + (int) (center[0] * this.getWidth());
			t = this.getTop() + (int) (center[1] * this.getHeight());
			r = this.getRight() - (int) ((scale - center[0]) * this.getWidth());
			b = this.getBottom()
					- (int) ((scale - center[1]) * this.getHeight());
		}
		this.setFrame(l, t, r, b);
	}

	/**
	 * 实现处理拖动
	 */
	private void setPosition(int left, int top, int right, int bottom) {
		this.layout(left, top, right, bottom);
	}
}