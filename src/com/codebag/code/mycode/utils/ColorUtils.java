package com.codebag.code.mycode.utils;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.MultiViews.MyAdapter;

public class ColorUtils extends MyCode {

	public ColorUtils(MainActivity context) {
		super(context);
	}
	
	@Entry
	public void TestColor() {
		MultiViews views = new MultiViews(getActivity(), 2);
		views.setAdapter(new MyAdapter(){

			EditText et_argb = null;
			TextView tv_argb = null;
			
			@Override
			public int getCount() {
				return 3;
			}

			@Override
			public View getView(int position) {
				switch (position) {
				case 0:
					tv_argb = new TextView(getActivity());
					tv_argb.setText("α r g b");
					tv_argb.setGravity(Gravity.CENTER);
					return tv_argb;
				case 1:
					et_argb = new EditText(getActivity());
					et_argb.setEms(2);
					et_argb.setHint("16进制 a r g b值");
					et_argb.setBackgroundColor(Color.WHITE);
					return et_argb;
				case 2:
					Button bt = new Button(getActivity());
					bt.setGravity(Gravity.CENTER);
					bt.setText("Show Color");
					bt.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							try {
								String content = et_argb.getText().toString().trim();
								Long longStr = Long.parseLong(content.substring(2), 16);
								
								int color = longStr.intValue();  
								
								tv_argb.setBackgroundColor(color);
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(getActivity(), "输入错误", Toast.LENGTH_LONG).show();;
							}
						}
					});
					return bt;
				}
				return null;
			}
			
		});
		showView(views);
	}
	
	

}
