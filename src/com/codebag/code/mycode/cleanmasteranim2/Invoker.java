package com.codebag.code.mycode.cleanmasteranim2;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.DisplayUtil;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void showViewCakeProgressBar() {
		int d = DisplayUtil.dip2px(getContext(), 110);
		
		int mPly = DisplayUtil.dip2px(getContext(), 6);
		CakeProgressBar c = new CakeProgressBar(getContext());
		c.setData(mPly, d, 2);
		c.setBackgroundColor(Color.WHITE);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(40);
		showView(c);
	}
	
	@Entry
	public void showWaveView() {
		WaveView v = new WaveView(getContext());
		v.startAnim(50);
		showView(v);
	}
	
	@Entry
	public void showCakeWaveView() {
		CakeWaveView v = new CakeWaveView(getContext());
		v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		v.setProgress(50);
		showView(v);
	}
	
	@Entry
	public void showSampleClip() {
		SampleView v = new SampleView(getContext());
		v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		showView(v);
	}

}
