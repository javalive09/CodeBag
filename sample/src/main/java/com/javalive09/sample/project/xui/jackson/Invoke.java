package com.javalive09.sample.project.xui.jackson;

import org.json.JSONException;
import org.json.JSONObject;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

public class Invoke extends Entry {

	String json = "{\"result\":1,\"err\":null,\"provinces\":["
			+ "{\"pid\":1,\"pname\":\"全国\",\"spname\":\"全国基础资源包\",\"citys\":[{\"cid\":1,\"cname\":\"全国\",\"scname\":\"全国基础资源包\"}]},"
			+ "]}";
	
	public void show() {
		try {
			showTxt(json);
			JSONObject jo = new JSONObject(json);
			Log.i(jo.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
