package com.codebag.code.mycode.view.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Gravity;
import android.view.View;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class DrawArcs extends CaseListView {

	public DrawArcs(Context context) {
		super(context);
	}
	
	@Entry
	public void drawArc() {
		View v = new View(getContext()) {

			Paint paint = new Paint();
			RectF rf = new RectF(0, 0, 300, 300 );
			
			@Override
			protected void onDraw(Canvas canvas) {
				paint.setColor(Color.CYAN);
				paint.setAntiAlias(true);
				paint.setStyle(Paint.Style.FILL);
				
				canvas.drawArc(rf, 0, 360, false, paint);
				paint.setColor(Color.BLUE);
				paint.setAntiAlias(true);
				paint.setStyle(Paint.Style.FILL);
				
//				canvas.save();
				canvas.clipRect(0, 50, 300, 300);
				canvas.drawArc(rf, 0, 360, false, paint);
//				canvas.restore();
			}
			
		};
		
		showView(v);
	}
	
	@Entry
	public void showArcsSample() {
		showView(new SampleView(getContext()));
	}

}
