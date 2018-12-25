package com.javalive09.demos;

import org.json.JSONObject;

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
}
