package com.codebag.code.mycode.http.uri;

import android.net.Uri;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}

	String path = "file:///storage/emulated/0/XUIP/App/xui_music.apk";
	
	@Entry
	public void invoke() {
		Uri uri = Uri.parse(path);
		String path = uri.getPath(); //  /storage/emulated/0/XUIP/App/xui_music.apk
	}
}
