package com.javalive09.sample.view.toast;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.javalive09.codebag.Entry;
import com.javalive09.sample.utils.DisplayUtil;
import com.javalive09.sample.R;

public class MToast extends Entry {
	
	public void showSuperToast() {
		SuperToast toast = SuperToast.makeText(getViewActivity(), "", SuperToast.LENGTH_SHORT);
		View view = View.inflate(getViewActivity(), R.layout.toast_force_close, null);
		TextView tv = (TextView) view.findViewById(R.id.toast_text);
		String str1 = "点击强制停止按钮完成操作";
		SpannableString ss = new SpannableString(str1);
		ss.setSpan(new ForegroundColorSpan(Color.parseColor("#24a0ff")),2,6,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);     //设置指定位置文字的颜色  
		tv.setText(ss);
		toast.setDuration(5 * 1000);
		
		int yOffset = DisplayUtil.dip2px(getViewActivity(), 50);
		
		toast.setGravity(Gravity.TOP, 0, yOffset);
		toast.setView(view);
		toast.show();
	}
	
	public void showLongTimeToast() {
		Toast toast = Toast.makeText(getViewActivity(), "6666666666666!", Toast.LENGTH_SHORT);
		LongTimeToast st3 = new LongTimeToast(toast, 5 * 1000);
		st3.show();
	}

}
