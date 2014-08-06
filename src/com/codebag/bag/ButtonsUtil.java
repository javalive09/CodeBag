package com.codebag.bag;

import com.codebag.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class ButtonsUtil{

	private View mButtons; 
	
	public ButtonsUtil(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		mButtons= inflater.inflate(R.layout.buttonsview, null);
		Button bt1 = (Button) mButtons.findViewById(R.id.bt1);
		Button bt2 = (Button) mButtons.findViewById(R.id.bt2);
		Button bt3 = (Button) mButtons.findViewById(R.id.bt3);
		Button bt4 = (Button) mButtons.findViewById(R.id.bt4);
		Button bt5 = (Button) mButtons.findViewById(R.id.bt5);
		Button bt6 = (Button) mButtons.findViewById(R.id.bt6);

		bt1.setOnClickListener(listener);
		bt2.setOnClickListener(listener);
		bt3.setOnClickListener(listener);
		bt4.setOnClickListener(listener);
		bt5.setOnClickListener(listener);
		bt6.setOnClickListener(listener);
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1 , -1);
		params.gravity = Gravity.CENTER;
	}
	
	public View getButtons() {
		return mButtons;
	}

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.bt1:
				bt1Click();
				break;
			case R.id.bt2:
				bt2Click();
				break;
			case R.id.bt3:
				bt3Click();
				break;
			case R.id.bt4:
				bt4Click();
				break;
			case R.id.bt5:
				bt5Click();
				break;
			case R.id.bt6:
				bt6Click();
			}
		}
	};

	protected void bt1Click() {

	}

	protected void bt2Click() {

	}

	protected void bt3Click() {

	}

	protected void bt4Click() {

	}

	protected void bt5Click() {

	}
	
	protected void bt6Click() {
		
	}

}
