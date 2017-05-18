package com.javalive09.sample.view.drawable.drawPNGfile;

import android.view.View;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoke extends Entry {

	public void PNG_File() {
		View v = View.inflate(getActivity(), R.layout.drawable_bitmap_file, null);
		showView(v);
	}
	
	public void NINE_PATCH_PNG_File() {
		View v = View.inflate(getActivity(), R.layout.drawable_nine_patch_file, null);
		showView(v);
	}
	
	
	
}
