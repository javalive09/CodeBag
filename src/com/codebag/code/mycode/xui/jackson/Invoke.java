package com.codebag.code.mycode.xui.jackson;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.TextView;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	String json = "{\"result\":1,\"err\":null,\"provinces\":["
			+ "{\"pid\":1,\"pname\":\"全国\",\"spname\":\"全国基础资源包\",\"citys\":[{\"cid\":1,\"cname\":\"全国\",\"scname\":\"全国基础资源包\"}]},"
			+ "]}";
	
	public Invoke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void show() {
		showView(R.layout.jackson_layout);
		TextView raw = (TextView) findViewById(R.id.raw);
		raw.setText(json);
		
		try {
			JSONObject jo = new JSONObject(json);
			TextView eat = (TextView) findViewById(R.id.eat);
			eat.setText(jo.toString());
			Log.i("peter", jo.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
