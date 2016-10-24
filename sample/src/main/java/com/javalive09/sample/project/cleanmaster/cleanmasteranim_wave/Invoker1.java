package com.javalive09.sample.project.cleanmaster.cleanmasteranim_wave;


import android.view.LayoutInflater;
import android.view.View;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.utils.DisplayUtil;
import com.javalive09.sample.R;

public class Invoker1 extends Entry {

	public void showViewCakeProgressBar() {
		int mPly = DisplayUtil.dip2px(getViewActivity(), 4);
		CakeProgressBar c = new CakeProgressBar(getViewActivity());
		c.setData(500, mPly, 0);
		c.setColor(0xE624a0ff, 0x19000000);
		c.setProgress(40);
		showView(c);
	}
	
	public void showViewCakeProgressBar_xml() {
		LayoutInflater inflater = LayoutInflater.from(getViewActivity());
		View bar = inflater.inflate(R.layout.cakeprogressbar, null);
		CakeProgressBar c = (CakeProgressBar) bar.findViewById(R.id.cakebar);
		int mPly = DisplayUtil.dip2px(getViewActivity(), 4);
		c.setData(500, mPly, 0);
		c.setColor(0xE624a0ff, 0x19000000);
		c.setProgress(40);
		showView(bar);
	}
	
	public void animCakeProgressBar() {
		LayoutInflater inflater = LayoutInflater.from(getViewActivity());
		View bar = inflater.inflate(R.layout.cakeprogressbar, null);
		CakeProgressBar c = (CakeProgressBar) bar.findViewById(R.id.cakebar);
		int mPly = DisplayUtil.dip2px(getViewActivity(), 4);
		c.setData(500, mPly, 3);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(40);
		showView(bar);
	}
	
	public void animCakeProgressBar_xml() {
		int mPly = DisplayUtil.dip2px(getViewActivity(), 4);
		CakeProgressBar c = new CakeProgressBar(getViewActivity());
		c.setData(500, mPly, 3);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(40);
		showView(c);
	}

	
	public void animWaveView() {
		WaveView v = new WaveView(getViewActivity());
		v.startAnim(50);
		showView(v);
	}
	
	
	public void showWaveView() {
		WaveView v = new WaveView(getViewActivity());
		v.setProgress(50);
		showView(v);
	}
	
}
