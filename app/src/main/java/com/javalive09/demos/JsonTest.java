package com.javalive09.demos;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by peter on 2018/12/4
 */
public class JsonTest {

    @Run
    public void getString(CodeActivity activity) throws Exception{
        String info = "{\"forceOTA\":\"true\"}";

        JSONObject jsonObject = new JSONObject(info);

        String force = jsonObject.optString("forceOTA");

        activity.showText(force);

    }

    @Run
    public void zhuanyi(CodeActivity activity) throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("abc", "abc");
        jsonObject.put("efg", "abc");
        jsonObject.put("ip", "http://111.11.111.111:8081/api/Android/");
        activity.showText(jsonObject.toString());
    }

    @Run
    public void buzhuanyi(CodeActivity activity) {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        jsonObject.addProperty("abc", "abc");
        jsonObject.addProperty("efg", "abc");
        jsonObject.addProperty("ip", "http://111.11.111.111:8081/api/Android/");
        activity.showText(jsonObject.toString());
    }


}
