package com.codebag.code.mycode.uri;

import android.net.Uri;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
		super(act);
	}

	String path = "file:///storage/emulated/0/XUIP/App/xui_music.apk";
	
	@Entry
	public void invoke() {
		Uri uri = Uri.parse(path);
		String path = uri.getPath(); //  /storage/emulated/0/XUIP/App/xui_music.apk
	}
}
