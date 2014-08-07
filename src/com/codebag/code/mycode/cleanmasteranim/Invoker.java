package com.codebag.code.mycode.cleanmasteranim;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.codebag.R;
import com.codebag.bag.ButtonsUtil;
import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public CradCleanDial c;
	
	public Invoker(Context context) {
		super(context);
		c = new CradCleanDial(context);
	}
	
	@Entry
	public void showButtons() {
		View buttons = new ButtonsUtil(getContext()){

			@Override
			protected void bt1Click() {
				super.bt1Click();
				showProgress();
			}

			@Override
			protected void bt2Click() {
				super.bt2Click();
				showMemery();
			}

			@Override
			protected void bt3Click() {
				super.bt3Click();
				showStorage();
			}

			@Override
			protected void bt4Click() {
				super.bt4Click();
				showBrush();
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
		fl.addView(c, fillParentParams(Gravity.CENTER));
		fl.addView(buttons, fillParentParams(Gravity.BOTTOM));
		fl.setBackgroundColor(Color.WHITE);
		showView(fl);
	}

	public void showM() {
		c.showMagnifier();
	}
	
	public void showProgress() {
		c.setProgress(80);
	}
	
	public void showBrush() {
		c.showBrush();
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
