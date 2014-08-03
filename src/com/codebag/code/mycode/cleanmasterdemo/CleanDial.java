package com.codebag.code.mycode.cleanmasterdemo;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CleanDial {

	private ImageView mDialMarkView;

	private ImageView mRoatingMarkView;

	private View mRoatingView;

	private TextView mNum;

	private RingView mRingView;
	
	private Context mContext;

	public CleanDial(Context context) {
		mContext = context;
		init(context);
	}
	
	private void init(Context context){
		mDialMarkView = new ImageView(context);
		mRoatingMarkView = new ImageView(context);
		mRoatingView = new View(context);
		mNum = new TextView(context);
//		mRingView = new RingView(context);
	}
	
	public CleanDial setDialMarkResource(int resid) {
		mDialMarkView.setBackgroundResource(resid);
		return this;
	} 
	

}
