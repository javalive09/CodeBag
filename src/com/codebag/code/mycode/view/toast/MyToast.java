package com.codebag.code.mycode.view.toast;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class MyToast extends MyCode {

	
	SuperToast2 st2;
	
	public MyToast(MainActivity act) {
		super(act);
		
		Toast toast = Toast.makeText(getActivity(), "6666666666666!", Toast.LENGTH_LONG);
		st2 = new SuperToast2(toast);
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
		toast.setView(view);
		toast.show();
	}
	
	
	@Entry
	public void showSuperToast2() {
		st2.show();
	}
	
	@Entry
	public void hideSuperToast2() {
		st2.hide();
	}
	
	@Entry
	public void showSuperToast3() {
		Toast toast = Toast.makeText(getActivity(), "6666666666666!", Toast.LENGTH_SHORT);
		SuperToast3 st3 = new SuperToast3(toast, 100*1000);
		st3.show();
	}

}
