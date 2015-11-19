package com.codebag.code.mycode.view.drawable.drawfile;

import android.view.View;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void bitmapFile() {
		View v = View.inflate(getActivity(), R.layout.drawable_bitmap_file, null);
		showView(v);
	}
	
	@Entry
	public void ninePatchFile() {
		View v = View.inflate(getActivity(), R.layout.drawable_nine_patch_file, null);
		showView(v);
	}
	
	
	
}
