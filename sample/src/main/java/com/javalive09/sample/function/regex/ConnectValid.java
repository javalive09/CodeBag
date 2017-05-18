package com.javalive09.sample.function.regex;

import com.javalive09.codebag.Entry;

import java.util.regex.Pattern;

/**
 * Created by peter on 2016/10/19.
 */

public class ConnectValid extends Entry {

    public void test1() {
        String str = "box"+ "\t" + "351564067617225" + "\t"+ "HTC M8t 646" + "\t" + "60000";
        showTxt(str + "isvalid = " + isValidConnectStr(str));
    }

    public void test2() {
        String str = "box"+ "\t" + "dfdfdfdf" + "\t"+ "HTC M8t 646" + "\t" + "60000";
        showTxt(str + "isvalid = " + isValidConnectStr(str));
    }

    public void test3() {
        String str = "sdfksdlfkjsdlkfjsdlkfj";
        showTxt(str + "isvalid = " + isValidConnectStr(str));
    }

    private boolean isValidConnectStr(String str) {
        String regex = "^box\\t[1-9]\\d*\\t[\\s\\S]*?\\t[1-9]\\d*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

}
