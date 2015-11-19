package com.codebag.code.mycode.utils;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codebag.R;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.gitlib.wheel.WheelView;
import com.codebag.code.mycode.gitlib.wheel.adapters.NumericWheelAdapter;
import com.codebag.code.mycode.utils.MultiViews.MyAdapter;

public class DisplayMetricsUtil extends MyCode{

	public DisplayMetricsUtil(InovkedViewActivity context) {
		super(context);
	}

	@Entry
	public void getDisplayMetrics() {
		MultiViews views = new MultiViews(getActivity(), 1);
		views.setAdapter(new MyAdapter() {
			TextView view = null;
			DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();
			
			@Override
			public View getView(int position) {
				switch(position) {
				case 0:
					view = new TextView(getActivity());
					view.setText("手机型号: " + android.os.Build.MODEL);
					break;
				case 1:
					view = new TextView(getActivity());
					view.setText("SDK版本号: " + android.os.Build.VERSION.SDK_INT);
					break;
				case 2:
					view = new TextView(getActivity());
					view.setText("固件版本: " + android.os.Build.VERSION.RELEASE);
					break;
				case 3:
					view = new TextView(getActivity());
					String resolution = dm.widthPixels +" x " + dm.heightPixels;
					view.setText("分辨率: " + resolution);
					break;
				case 4:
					view = new TextView(getActivity());
					view.setText("屏幕密度: " + dm.densityDpi);
					break;
				case 5:
					view = new TextView(getActivity());
					TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
					view.setText("IMEI: " + tm.getDeviceId());
					break;
				case 6:
				    view = new TextView(getActivity());
				    String str = getActivity().getString(R.string.values_marks);
				    view.setText("res 资源读取目录: " + str);
				    break;
				case 7:
					view = new TextView(getActivity());
					str = getActivity().getString(R.string.values_marks);
					view.setText("状态栏高度: " + getStatusBarHeight(getActivity()));
				}
				return view;
			}
			
			@Override
			public int getCount() {
				return 8;
			}
		});
		views.setBackgroundColor(Color.BLACK);
		showView(views);
	}
	
	private int getStatusBarHeight(Context context){ 
        Class<?> c = null; 
        Object obj = null; 
        Field field = null; 
        int x = 0, statusBarHeight = 0; 
        try { 
            c = Class.forName("com.android.internal.R$dimen"); 
            obj = c.newInstance(); 
            field = c.getField("status_bar_height"); 
            x = Integer.parseInt(field.get(obj).toString()); 
            statusBarHeight = context.getResources().getDimensionPixelSize(x);  
        } catch (Exception e1) { 
            e1.printStackTrace(); 
        }  
        return statusBarHeight; 
    }
	
	WheelView wv1, wv2, wv3, wv4;
	TextView dp, sp;
	
	@Entry
	public void getDiplayUnit() {
		MultiViews views = new MultiViews(getActivity(), 1);
		views.setAdapter(new MyAdapter() {
			
			@Override
			public View getView(int position) {
				View view = null;
				switch(position) {
				case 0:
					TextView v = new TextView(getActivity());
					v.setTextColor(Color.BLACK);
					v.setText("输入像素值（px）");
					v.setGravity(Gravity.CENTER);
					view = v;
					break;
				case 1:
					LinearLayout ll = new LinearLayout(getActivity());
					ll.setOrientation(LinearLayout.HORIZONTAL);
					ll.setGravity(Gravity.CENTER);
					wv1 = new WheelView(getActivity());
					wv2 = new WheelView(getActivity());
					wv3 = new WheelView(getActivity());
					wv4 = new WheelView(getActivity());
					initWheel(wv1);
					initWheel(wv2);
					initWheel(wv3);
					initWheel(wv4);
					ll.addView(wv1);
					ll.addView(wv2);
					ll.addView(wv3);
					ll.addView(wv4);
					view = ll;
					break;
				case 2:
					Button show  = new Button(getActivity());
					show.setText("转换");
					show.setOnClickListener(mListener);
					view = show;
					break;
				case 3:
					dp = new TextView(getActivity());
					dp.setTextColor(Color.BLACK);
					dp.setText("dp :" + DisplayUtil.px2dip(getActivity(), getPx()));
					dp.setGravity(Gravity.CENTER);
					view = dp;
					break;
				case 4:
					sp = new TextView(getActivity());
					sp.setTextColor(Color.BLACK);
					sp.setText("sp : " + DisplayUtil.px2sp(getActivity(), getPx()));
					sp.setGravity(Gravity.CENTER);
					view = sp;
					break;
				}
				return view;
			}
			
			@Override
			public int getCount() {
				return 5;
			}
		});
		
		views.setBackgroundColor(Color.WHITE);
		showView(views);
	}
	
	private OnClickListener mListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			dp.setText("dp :" + DisplayUtil.px2dip(getActivity(), getPx()));
			sp.setText("sp : " + DisplayUtil.px2sp(getActivity(), getPx()));
		}
	};
	
	private int getPx() {
		int a = wv1.getCurrentItem() * 1000;
		int b = wv2.getCurrentItem() * 100;
		int c = wv3.getCurrentItem() * 10;
		int d = wv4.getCurrentItem() * 1;
		return a + b + c + d;
	}
	
	private void initWheel( WheelView wheel) {
		NumericWheelAdapter adapter = new NumericWheelAdapter(getActivity(), 0, 9);
		adapter.setTextSize(40);
        wheel.setViewAdapter(adapter);
        wheel.setCurrentItem(0);
        wheel.setCyclic(true);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
    }
	
}
