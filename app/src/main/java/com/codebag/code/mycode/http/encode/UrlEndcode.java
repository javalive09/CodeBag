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
	String url2 = "type=%E6%B5%8B%E8%AF%95%E5%8C%85&log=%E6%89%93%E5%8D%B0%E6%97%A5%E5%BF%97&branch=develop&mail=792387725%40qq.com%2C";
	String url3 = "http://192.168.230.119:1999/package/?mix=%E6%AD%A3%E5%B8%B8%E6%89%93%E5%8C%85&type=%E6%B5%8B%E8%AF%95%E5%8C%85&log=%E6%89%93%E5%8D%B0%E6%97%A5%E5%BF%97&branch=rl_p_v1.1.0_c_v1.1.0&mail=792387725%40qq.com%2C";
	
	public UrlEndcode(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void urldeCode() {
		try {
			String decodeUrl = URLDecoder.decode(url3, Charset.defaultCharset().name());
			Log.i("peter", "decodeUrl = " + decodeUrl);
			showTxt("decodeUrl = " + decodeUrl);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
