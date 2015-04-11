package com.codebag.code.mycode.utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.gitlib.wheel.OnWheelScrollListener;
import com.codebag.code.mycode.gitlib.wheel.WheelView;
import com.codebag.code.mycode.gitlib.wheel.adapters.NumericWheelAdapter;
import com.codebag.code.mycode.utils.MultiViews.MyAdapter;

public class ColorUtils extends MyCode implements OnWheelScrollListener{

	WheelView wvAlpha1, wvAlpha2, wvRed1, wvRed2, wvGreen1, wvGreen2, wvBlue1, wvBlue2;
	TextView showView;
	
	public ColorUtils(MainActivity context) {
		super(context);
	}
	
	@Entry
	public void showColor() {

		MultiViews views = new MultiViews(getActivity(), 1);
		views.setAdapter(new MyAdapter() {
			
			@Override
			public View getView(int position) {
				View view = null;
				switch(position) {
				case 1:
					TextView v = new TextView(getActivity());
					v.setText("输入颜色（alpha, red, green, blue）");
					v.setGravity(Gravity.CENTER);
					v.setPadding(0, 20, 0, 20);
					view = v;
					break;
				case 2:
					LinearLayout ll = new LinearLayout(getActivity());
					ll.setOrientation(LinearLayout.HORIZONTAL);
					ll.setGravity(Gravity.CENTER);
					
					wvAlpha1 = new WheelView(getActivity());
					wvAlpha2 = new WheelView(getActivity());
					wvRed1 = new WheelView(getActivity());
					wvRed2 = new WheelView(getActivity());
					wvGreen1 = new WheelView(getActivity());
					wvGreen2 = new WheelView(getActivity());
					wvBlue1 = new WheelView(getActivity());
					wvBlue2 = new WheelView(getActivity());
					
					initWheel(wvAlpha1);
					initWheel(wvAlpha2);
					initWheel(wvRed1);
					initWheel(wvRed2);
					initWheel(wvGreen1);
					initWheel(wvGreen2);
					initWheel(wvBlue1);
					initWheel(wvBlue2);
					
					wvAlpha1.setCurrentItem(15);
					wvAlpha2.setCurrentItem(15);
					
					ll.addView(wvAlpha1);
					ll.addView(wvAlpha2);
					
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					params.leftMargin = 20;
					ll.addView(wvRed1, params);
					ll.addView(wvRed2);
					
				    params =  new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					params.leftMargin = 20;
					ll.addView(wvGreen1, params);
					ll.addView(wvGreen2);
					
					params =  new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					params.leftMargin = 20;
					ll.addView(wvBlue1, params);
					ll.addView(wvBlue2);
					
					view = ll;
					break;
				case 0:
					showView = new TextView(getActivity());
					showView.setText("color show");
					showView.setGravity(Gravity.CENTER);
					view = showView;
					break;
				}
				return view;
			}
			
			@Override
			public int getCount() {
				return 3;
			}
		});
		
		views.setBackgroundColor(Color.WHITE);
		showView(views);
	
	}
	
	private void initWheel( WheelView wheel) {
		NumericWheelAdapter adapter = new NumericWheelAdapter(getActivity(), 0, 15, "%x");
		adapter.setTextSize(40);
        wheel.setViewAdapter(adapter);
        wheel.setCurrentItem(0);
        wheel.setCyclic(true);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
        wheel.addScrollingListener(this);
    }

	@Override
	public void onScrollingStarted(WheelView wheel) {}

	@Override
	public void onScrollingFinished(WheelView wheel) {
		refreshColor();
	}
	
	private void refreshColor() {
		
		try {
			String alpha1 = Integer.toHexString(wvAlpha1.getCurrentItem());
			String alpha2 = Integer.toHexString(wvAlpha2.getCurrentItem());
			String red1 = Integer.toHexString(wvRed1.getCurrentItem());
			String red2 = Integer.toHexString(wvRed2.getCurrentItem());
			String green1 = Integer.toHexString(wvGreen1.getCurrentItem());
			String green2 = Integer.toHexString(wvGreen2.getCurrentItem());
			String blue1 = Integer.toHexString(wvBlue1.getCurrentItem());
			String blue2 = Integer.toHexString(wvBlue2.getCurrentItem());
			int color = Color.parseColor("#" + alpha1 + alpha2 + red1 + red2 + green1 + green2 + blue1 + blue2);
			showView.setBackgroundColor(color);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Entry
	public void showAlpha() {
		final TextView hexView = new TextView(getActivity());
		hexView.setGravity(Gravity.CENTER);
		hexView.setTextSize(30);
		String textPrefix = "16进制值";
		hexView.setText(textPrefix);
		final EditText input = new EditText(getActivity());
		input.setHint("输入alpha 灰度值百分比 （0 ～ 100）");
		input.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				try {
					String textPrefix = "16进制值";
					hexView.setText(textPrefix);
					if(s != null) {
						String content = s.toString().trim();
						if(!TextUtils.isEmpty(content)) {
							int intContent = Integer.parseInt(content);
							int realContent = (int) (intContent / 100f * 255);
							content = Integer.toHexString(realContent);
							hexView.setText(textPrefix + ":" + content);
						}
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					Toast.makeText(getActivity(), "输入不合法", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		MultiViews views = new MultiViews(getActivity(), 1);
		views.setAdapter(new MyAdapter() {

			@Override
			public int getCount() {
				return 2;
			}

			@Override
			public View getView(int position) {
				View view = null;
				switch(position) {
				case 0:
					view = hexView;
					break;
				case 1:
					view = input;
					break;
				}
				return view;
			}
			
		});
		views.setBackgroundColor(Color.WHITE);
		showView(views);
	}
	
}
