package com.codebag.code.mycode.cleanmasteranim_wave;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.codebag.R;
import com.codebag.bag.ButtonsUtil;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.cleanmasteranim_wave.CradCleanDial;
import com.codebag.code.mycode.utils.DisplayUtil;

public class Invoker3 extends CaseListView {
public CradCleanDial c;
	
	public Invoker3(Context context) {
		super(context);
		c = new CradCleanDial(context);
	}
	
	@Entry
	public void showButtons() {
		View buttons = new ButtonsUtil(getContext()){

			@Override
			protected void bt1Click() {
				super.bt1Click();
				showCakeProgress();
			}

			@Override
			protected void bt2Click() {
				super.bt2Click();
				showWaveProgress();
			}

			@Override
			protected void bt3Click() {
				super.bt3Click();
				showStorage();
			}

			@Override
			protected void bt4Click() {
				super.bt4Click();
				showMemery();
			}
			
			@Override
			protected void bt5Click() {
				super.bt4Click();
				showRocket();
			}

			@Override
			protected void bt6Click() {
				super.bt6Click();
				showM();
			}
			
		}.getButtons();
		
		FrameLayout fl = new FrameLayout(getContext());
		int d = DisplayUtil.dip2px(getContext(), 94);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(d, d);
		params.gravity = Gravity.CENTER;
		fl.addView(c, params);
		fl.addView(buttons, fillParentParams(Gravity.BOTTOM));
		fl.setBackgroundColor(Color.DKGRAY);
		showView(fl);
	}

	public void showM() {
		c.showMagnifier();
	}
	
	public void showCakeProgress() {
		c.setCakeProgress(60);
	}
	
	public void showWaveProgress() {
		c.setWaveProgress(60);
	}
	
	public void showRocket() {
		c.showRoket();
	}
	
	public void showMemery() {
		c.setDialMarkResource(R.drawable.card_danager_memory);
	}
	
	public void showStorage() {
		c.setDialMarkResource(R.drawable.card_danager_storage);
	}
	
	public void showSystem() {
		c.setDialMarkResource(R.drawable.card_danager_system);
	}
}
