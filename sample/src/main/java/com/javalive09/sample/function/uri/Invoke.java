package com.javalive09.sample.function.uri;

import android.net.Uri;
import com.javalive09.codebag.Entry;

public class Invoke extends Entry {


	String path = "file:///storage/emulated/0/XUIP/App/xui_music.apk";
	
	public void invoke() {
		Uri uri = Uri.parse(path);
		String path = uri.getPath(); //  /storage/emulated/0/XUIP/App/xui_music.apk
	}
}
