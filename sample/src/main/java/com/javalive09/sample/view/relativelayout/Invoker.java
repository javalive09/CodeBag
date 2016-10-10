package com.javalive09.sample.view.relativelayout;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoker extends Entry {

	public void show() {
		RelativeLayout mNum2 = new RelativeLayout(getActivity());
		mNum2.setBackgroundColor(Color.WHITE);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		
		TextView mNum = new TextView(getActivity());
		mNum.setTextSize(40);
		mNum.setTextColor(0xE624a0ff);
		mNum.setText("80");
		mNum.setId(R.id.one);
		mNum2.addView(mNum, params);
		
		RelativeLayout.LayoutParams paramsP = new RelativeLayout.LayoutParams(-2, -2);
		TextView percentSign = new TextView(getActivity());
		percentSign.setText("%");
		percentSign.setTextSize(40);
		percentSign.setTextColor(0xE624a0ff);
		paramsP.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		paramsP.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);  
		mNum2.addView(percentSign, paramsP);
		showView(mNum2);
	}
	
	public void show2() {
		RelativeLayout rl = new RelativeLayout(getActivity());  
	    Button btn1 = new Button(getActivity());  
	    btn1.setText("btn1 ----------------------");  
	    btn1.setId(R.id.one);
	      
	    RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams
	    		(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);  
	    lp1.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);  
	    // btn1 位于父 View 的顶部，在父 View 中水平居中  
	    rl.addView(btn1, lp1 );  
	     
	    Button btn2 = new Button(getActivity());  
	    btn2.setText("btn2 |\n|\n|\n|\n|\n|");  
	    btn2.setId(R.id.two);
	     
	    RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams
	    		(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    lp2.addRule(RelativeLayout.BELOW, R.id.one);
	    lp2.addRule(RelativeLayout.ALIGN_LEFT, R.id.one);
	    // btn2 位于 btn1 的下方、其左边和 btn1 的左边对齐  
	    rl.addView(btn2, lp2);  
	     
	    Button btn3 = new Button(getActivity());  
	    btn3.setText("btn3 |\n|\n|\n|\n|\n|");  
	    btn3.setId(R.id.three);
	     
	    RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams
	    		(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    lp3.addRule(RelativeLayout.BELOW, R.id.one);
	    lp3.addRule(RelativeLayout.RIGHT_OF, R.id.two);
	    lp3.addRule(RelativeLayout.ALIGN_RIGHT, R.id.one);
	    // btn3 位于 btn1 的下方、btn2 的右方且其右边和 btn1 的右边对齐（要扩充）  
	    rl.addView(btn3,lp3);  
	     
	    Button btn4 = new Button(getActivity());  
	    btn4.setText("btn4 --------------------------------------------");  
	    btn4.setId(R.id.four);
	     
	    RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams
	    		(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);  
	    lp4.addRule(RelativeLayout.BELOW, R.id.two);
	    lp4.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);  
	    // btn4 位于 btn2 的下方，在父 Veiw 中水平居中  
	    rl.addView(btn4,lp4);  
	    
	    showView(rl);
	}

}
