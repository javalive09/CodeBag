package com.codebag.code.mycode.xui.jackson;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Invoke extends MyCode {

	String json = "{\"result\":1,\"err\":null,\"provinces\":["
			+ "{\"pid\":1,\"pname\":\"全国\",\"spname\":\"全国基础资源包\",\"citys\":[{\"cid\":1,\"cname\":\"全国\",\"scname\":\"全国基础资源包\"}]},"
			+ "]}";
	
	public Invoke(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void show() {
		try {
			JSONObject jo = new JSONObject(json);
			Log.i("peter", jo.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
