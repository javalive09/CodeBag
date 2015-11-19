package com.codebag.code.mycode.cleanmaster.cleanmasteranim_wave;


import android.view.LayoutInflater;
import android.view.View;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.utils.DisplayUtil;

public class Invoker extends MyCode {

	public Invoker(InovkedViewActivity context) {
		super(context);
	}
	
	@Entry
	public void showViewCakeProgressBar() {
		int mPly = DisplayUtil.dip2px(getActivity(), 4);
		CakeProgressBar c = new CakeProgressBar(getActivity());
		c.setData(500, mPly, 0);
		c.setColor(0xE624a0ff, 0x19000000);
		c.setProgress(40);
		showView(c);
	}
	
	@Entry
	public void showViewCakeProgressBar_xml() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View bar = inflater.inflate(R.layout.cakeprogressbar, null);
		CakeProgressBar c = (CakeProgressBar) bar.findViewById(R.id.cakebar);
		int mPly = DisplayUtil.dip2px(getActivity(), 4);
		c.setData(500, mPly, 0);
		c.setColor(0xE624a0ff, 0x19000000);
		c.setProgress(40);
		showView(bar);
	}
	
	@Entry
	public void animCakeProgressBar() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View bar = inflater.inflate(R.layout.cakeprogressbar, null);
		CakeProgressBar c = (CakeProgressBar) bar.findViewById(R.id.cakebar);
		int mPly = DisplayUtil.dip2px(getActivity(), 4);
		c.setData(500, mPly, 1);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(40);
		showView(bar);
	}
	
	@Entry
	public void animCakeProgressBar_xml() {
		int mPly = DisplayUtil.dip2px(getActivity(), 4);
		CakeProgressBar c = new CakeProgressBar(getActivity());
		c.setData(500, mPly, 3);
		c.setColor(0xE624a0ff, 0x19000000);
		c.startAnimination(40);
		showView(c);
	}
	
	@Entry
	public void animWaveView() {
		WaveView v = new WaveView(getActivity());
		v.startAnim(50);
		showView(v);
	}
	
}
