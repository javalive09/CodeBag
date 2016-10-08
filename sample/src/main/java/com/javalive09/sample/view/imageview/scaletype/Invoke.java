package com.javalive09.sample.view.imageview.scaletype;

import android.graphics.Color;
import android.view.Gravity;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

/**
 * fit 开头的和inside的type 都是不丢失图片内容的展示类型。
 * 
 * fit 和inside 的区别是 fit 一定是贴边适合效果， inside 不一定
 * 当大图片显示在小view时， fit_center 和 inside 效果一致
 * 
 * crop  type 是剪切原图的展示类型。
 * 
 * matrix 是用像素矩阵，从左上角开始显示。
 *
 */
public class Invoke extends Entry {

	public void show_Origin() {
		WebView wv = new WebView(getActivity());
		String url = "file:///android_asset/image_demo.jpg";
		wv.loadUrl(url);
		showView(wv);
	}

	/**
	 * 按图片的原来size居中显示(不进行压缩，放大处理)，当图片长/宽超过View的长/宽，则截取图片的居中部分显示， 原图剪切效果
	 */
	public void show_Big__ScaleType_CENTER() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setScaleType(ScaleType.CENTER);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv);
	}

	public void show_small__ScaleType_CENTER() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setScaleType(ScaleType.CENTER);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv);
	}

	/**
	 * 按比例扩大图片的size居中显示(放大处理)，使得图片长 (宽)等于或大于View的长(宽), 原图放大填充效果
	 */
	public void show_Big__ScaleType_CENTER_CROP() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setScaleType(ScaleType.CENTER_CROP);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv);
	}

	public void show_small__ScaleType_CENTER_CROP() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setScaleType(ScaleType.CENTER_CROP);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv);
	}

	/**
	 * 将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长(宽)等于或小于View的长(宽)， 原图压缩效果
	 */
	public void show_Big__ScaleType_CENTER_INSIDE() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setScaleType(ScaleType.CENTER_INSIDE);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv);
	}

	public void show_small__ScaleType_CENTER_INSIDE() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setScaleType(ScaleType.CENTER_INSIDE);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv);
	}

	/**
	 * 把图片按比例扩大(缩小)到View的宽度，居中显示， 原图安比例缩放，显示全部内容（不丢失内容信息）
	 */
	public void show_Big__ScaleType_FIT_CENTER() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv);
	}

	public void show_small__ScaleType_FIT_CENTER() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv );
	}

	/**
	 * 把图片按比例扩大(缩小)到View的宽度，显示在View的上／左部分位置，原图安比例缩放，显示全部内容（不丢失内容信息）
	 */
	public void show_Big__ScaleType_FIT_START() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setScaleType(ScaleType.FIT_START);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv );
	}

	public void show_small__ScaleType_FIT_START() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setScaleType(ScaleType.FIT_START);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv );
	}

	/**
	 * 把图片按比例扩大(缩小)到View的宽度，显示在View的下／右部分位置，原图安比例（填充view最小边）缩放，显示全部内容（不丢失内容信息）
	 */
	public void show_Big__ScaleType_FIT_END() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setBackgroundColor(Color.WHITE);
		iv.setScaleType(ScaleType.FIT_END);
		showView(iv );
	}

	public void show_small__ScaleType_FIT_END() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setScaleType(ScaleType.FIT_END);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv );
	}

	/**
	 * 把图片按照指定的大小在View中显示，原图填充放大
	 */
	public void show_Big__ScaleType_FIT_XY() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setBackgroundColor(Color.WHITE);
		iv.setScaleType(ScaleType.FIT_XY);
		showView(iv );
	}

	public void show_small__ScaleType_FIT_XY() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setBackgroundColor(Color.WHITE);
		iv.setScaleType(ScaleType.FIT_XY);
		showView(iv );
	}

	/**
	 * 用matrix来绘制
	 */
	public void show_Big__ScaleType_MATRIX() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.image_demo);
		iv.setScaleType(ScaleType.MATRIX);
		iv.setBackgroundColor(Color.WHITE);
		showView(iv );
	}

	public void show_small__ScaleType_MATRIX() {
		ImageView iv = new ImageView(getActivity());
		iv.setImageResource(R.drawable.vimeo_button);
		iv.setBackgroundColor(Color.WHITE);
		iv.setScaleType(ScaleType.MATRIX);
		showView(iv );
	}

}
