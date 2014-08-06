package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;

import com.codebag.R;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.cleanmasterdemo.RingView.AniminationListener;
import com.codebag.code.mycode.utils.DisplayUtil;
import com.codebag.code.mycode.utils.Log;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void showCircle() {
		RingView view = new RingView(getContext());
		view.setData(80, 540);
		view.setColor(0xE624a0ff, 0x19000000);
		view.setBackgroundColor(Color.WHITE);
		view.setAniminationListener(new AniminationListener() {
			
			@Override
			public void start() {
				Log.addLog(this, "start ----");
			}
			
			@Override
			public void end() {
				Log.addLog(this, "end ----");
			}
		});
		view.startAnimination(0);
		
		showView(view);
	}
	
	@Entry
	public void showCardRingView() {
		CardRingView cv = new CardRingView(getContext());
		cv.setBackgroundColor(Color.WHITE);
		cv.setProgress(78);
		cv.setColor(0xE624a0ff, 0x19000000);
		int ply = DisplayUtil.dip2px(getContext(), 13);
		cv.setData(ply, 540);
		showView(cv);
	}
	
	@Entry
	public void showCleanDialProgress() {
		CleanDial clean = new CleanDial(getContext());
		clean.setDialMarkResource(R.drawable.memery);//
		clean.setRoatingBg(R.drawable.fan);
		clean.setSmallMarkResource(R.drawable.roket);
		clean.setProgress(85);
		showView(clean);
	}
	
	@Entry
	public void showDleanDialDialMark() {
		CleanDial clean = new CleanDial(getContext());
		clean.setDialMarkResource(R.drawable.memery);//
		showView(clean);
	}
	
	@Entry
	public void showDleanDialRoket() {
		CleanDial clean = new CleanDial(getContext());
		clean.showRoket(50);
		showView(clean);
	}
	
	@Entry
	public void showCleanDial() {
		
		FrameLayout f = new FrameLayout(getContext());
		FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(-2, -2);
		f.setBackgroundColor(Color.WHITE);
		
		CleanDial clean = new CleanDial(getContext());
		clean.setDialMarkResource(R.drawable.memery);//
		clean.setRoatingBg(R.drawable.fan);
		clean.setSmallMarkResource(R.drawable.roket);
		clean.start(30);
		
		f.addView(clean, p);
		showView(f);
	}

}
