package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.DisplayUtil;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void showView() {
		WaterWaveView v = new WaterWaveView(getContext());
//		v.setBackgroundColor(Color.BLUE);
//		v.setWaterColor(Color.CYAN);
//		v.setAmplitude(20);
//		v.setWaterLevel(8);
//		v.startAnimation();
		
		v.setVisibility(View.VISIBLE);
        v.setWaterLevel(5);
        v.setWaterAlpha(0.08f);
//        v.setWaterColor(Color.BLUE);
        v.setAmplitude(5.0f);
        v.setWaterFrequent(0.063f);
        v.startAnimation();
		
		showView(v);
	}
	
	@Entry
	public void showView2() {
		
		FrameLayout out = new FrameLayout(getContext());
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(204, 204);
		FloatIconView v = new FloatIconView(getContext());
		v.updateMemoryUsage(80);
		v.toLeft();
		out.addView(v, params);
		
		showView(out , params);
		
	}
	
	@Entry
	public void showView3() {
		int d = DisplayUtil.dip2px(getContext(), 110);
		
		int mPly = DisplayUtil.dip2px(getContext(), 6);
		CakeProgressBar c = new CakeProgressBar(getContext());
		c.setData(mPly, d);
		c.setBackgroundColor(Color.WHITE);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(30);
		showView(c);
	}
	

}
