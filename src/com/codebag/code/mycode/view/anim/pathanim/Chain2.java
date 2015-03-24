package com.codebag.code.mycode.view.anim.pathanim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Region;
import android.widget.ImageView;

public class Chain2 extends ImageView {

	public Chain2(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.clipRect(30, 30, 700, 700, Region.Op.INTERSECT);
		super.onDraw(canvas);
		
	}
	
	

}
