package com.codebag.code.mycode.view.drawable.drawablecontainer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.widget.Button;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;
import com.codebag.code.mycode.view.MultiViews;
import com.codebag.code.mycode.view.MultiViews.MyAdapter;

/**
 * 实际上的状态分类：
 * 
 *  status------enable------pressed
 *  	   	| 			|---selected
 * 		   	|			|---focused
 *  	   	|---disable
 *  
 *  pressed状态触发点:  主动：调用此方法setPress(true);  被动：手指按下屏幕控件(action_down时触发)
 *  selected状态触发点: 主动：调用setSelected(true);     被动：无
 *  focused状态触发点:  
 *  	主动：	1. setFocusable(true);
 *				2. setFocusableInTouchMode(true);
 *				3. requestFocus();
 *  	被动：     满足触发前提的情况下，手指按下屏幕控件(action_up时触发)
 *  
 *  focused的前提是必须满足：1.focusable 2.focusableInTouchModel
 *  focusableInTouchModel的引入是为了满足触摸屏幕对焦点获取的需要。因为焦点本质上是为了按键，轨迹球等的定位。
 *  而触摸屏默认是不需要显示焦点的，点哪就是哪。所以如果触摸屏幕如果强行想获得焦点那么就必须设置focusableInTouchModel。
 *  
 *  注意点：StateListDrawable的状态设置范围要从小到大
 */			
public class StateListDrawable_ extends CaseListView implements OnClickListener{

	StateListDrawable sd = new StateListDrawable();
	Button show = null;
	
	public StateListDrawable_(Context context) {
		super(context);
		sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, new ColorDrawable(Color.RED));
		sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_selected}, new ColorDrawable(Color.YELLOW));
		sd.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_focused}, new ColorDrawable(Color.BLUE));		
		sd.addState(new int[]{-android.R.attr.state_enabled}, new ColorDrawable(Color.DKGRAY));
		sd.addState(new int[]{}, new ColorDrawable(Color.GREEN));
		
	}
	
	@Entry
	public void show() {
		MultiViews views = new MultiViews(getContext());
		views.setAdapter(new MyAdapter() {
			Button b = null;
			@Override
			public View getView(int position) {
				switch(position) {
				case 0:
					show = new Button(getContext()) {

						@Override
						public boolean onTouchEvent(MotionEvent event) {
							boolean focus = hasFocus();
							Log.addLog(this, "isFocusable = " + isFocusable() + "; isFocusableInTouchMode = " + isFocusableInTouchMode());
							boolean result = super.onTouchEvent(event);
							focus = hasFocus();
							return result;
						}
						
					};
					
					show.setOnClickListener(StateListDrawable_.this);
					show.setText("___show___");
					show.setBackgroundDrawable(sd);
					b = show;
					break;
				case 1:
					b = new Button(getContext());
					b.setText("enable");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 2:
					b = new Button(getContext());
					b.setText("disable");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 3:
					b = new Button(getContext());
					b.setText("focused");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 4:
					b = new Button(getContext());
					b.setText("lose focused");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 5:
					b = new Button(getContext());
					b.setText("selected");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				case 6:
					b = new Button(getContext());
					b.setText("disSelected");
					b.setOnClickListener(StateListDrawable_.this);
					break;
				}
				return b;
			}
			
			@Override
			public int getCount() {
				return 7;
			}
		});
		views.setBackgroundColor(Color.WHITE);
		showView(views);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case 0 :
			Log.addLog(this, "show");
			break;
		case 1:
			show.setEnabled(true);
			break;
		case 2:
			show.setEnabled(false);
			break;
		case 3:
			show.setFocusable(true);
			show.setFocusableInTouchMode(true);
			show.requestFocus();
			break;
		case 4:
			v.setFocusable(true);
			v.setFocusableInTouchMode(true);
			v.requestFocus();
			break;
		case 5:
			show.setSelected(true);
			break;
		case 6:
			show.setSelected(false);
			break;
		}
	}

}
