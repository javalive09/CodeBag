package com.codebag.code.mycode.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoke extends CaseListView {

	public Invoke(Context context) {
		super(context);
	}
	
	@Entry
	public void translate() {
		final Button iv = new Button(getContext()){
			 public void draw(Canvas canvas) {
				 canvas.drawColor(Color.BLUE);
				 canvas.translate(100, 0);
				 super.draw(canvas);
				 canvas.translate(-100, 0);
			 }
			 
		};
		iv.setText("click me .....");
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.scrollTo(500, 0);
			}
		});
		showView(iv, wrapContentParams(Gravity.CENTER));
	}
	
	@Entry
	public void translate2() {
		final ImageView iv = new ImageView(getContext()){
			public void draw(Canvas canvas) {
				canvas.drawColor(Color.BLUE);
				canvas.translate(100, 0);
				super.draw(canvas);
				canvas.translate(-100, 0);
			}
			
		};
		iv.setImageResource(R.drawable.ic_launcher);
		iv.setBackgroundColor(Color.GRAY);
		iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.scrollTo(50, 0);
			}
		});
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(200, 200);
		params.gravity = Gravity.CENTER;
		showView(iv, params);
	}
	
	
	

}
