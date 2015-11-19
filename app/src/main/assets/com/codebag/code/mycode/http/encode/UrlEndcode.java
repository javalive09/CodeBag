package com.codebag.code.mycode.http.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * url decode 解码
 */
public class UrlEndcode extends MyCode {

	String url = "http://123.56.131.79/singer/%E6%96%B0%E7%99%BD%E5%A8%98%E5%AD%90%E4%BC%A0%E5%A5%87.jpg";
	
	public UrlEndcode(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void urldeCode() {
		try {
			String decodeUrl = URLDecoder.decode(url, Charset.defaultCharset().name());
			Log.i("peter", "decodeUrl = " + decodeUrl);
			showTxt("decodeUrl = " + decodeUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}