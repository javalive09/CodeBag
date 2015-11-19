package com.codebag.code.mycode.view.toast;

import android.graphics.Color;
import android.graphics.Rect;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;
import com.codebag.code.mycode.utils.DisplayMetricsUtil;
import com.codebag.code.mycode.utils.DisplayUtil;
import com.codebag.code.mycode.utils.Log;

public class MToast extends MyCode {


	public MToast(InovkedViewActivity act) {
		super(act);
		
		Toast toast = Toast.makeText(getActivity(), "6666666666666!", Toast.LENGTH_LONG);
	}
	
	@Entry
	public void showSuperToast() {
//		SuperToast.makeText(getActivity(), "6666666666666!", SuperToast.LENGTH_LONG).show();
//		Toast.makeText(getActivity(), "6666666666666!", Toast.LENGTH_LONG).show();
//		
		SuperToast toast = SuperToast.makeText(getActivity(), "", SuperToast.LENGTH_LONG);
		View view = View.inflate(getActivity(), R.layout.toast_force_close, null);
		TextView tv = (TextView) view.findViewById(R.id.toast_text);
		String str1 = "点击强制停止按钮完成操作";
		String str2 = "点击结束运行按钮完成操作";
//		SpannableStringBuilder style=new SpannableStringBuilder(str1);   
		SpannableString ss = new SpannableString(str1);
		ss.setSpan(new ForegroundColorSpan(Color.parseColor("#24a0ff")),2,6,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);     //设置指定位置文字的颜色  
		tv.setText(ss);
		toast.setDuration(10*1000);
		
		int yOffset = DisplayUtil.dip2px(getActivity(), 50);
		
		toast.setGravity(Gravity.TOP, 0, yOffset);
		toast.setView(view);
		toast.show();
	}
	
	@Entry
	public void getStatusBarHeight() {
		Rect rect = new Rect();

		Window win = getActivity().getWindow();

		win.getDecorView().getWindowVisibleDisplayFrame(rect);

		int statusBarHeight = rect.top;

		int contentViewTop = win.findViewById(Window.ID_ANDROID_CONTENT).getTop();

		Log.addLog("peter", this, "statusBarHeight = " + statusBarHeight); 
		Log.addLog("peter", this, "contentViewTop = " + contentViewTop); 
	}
	

	@Entry
	public void showSuperToast3() {
		Toast toast = Toast.makeText(getActivity(), "6666666666666!", Toast.LENGTH_SHORT);
		SuperToast3 st3 = new SuperToast3(toast, 100*1000);
		st3.show();
	}

}
