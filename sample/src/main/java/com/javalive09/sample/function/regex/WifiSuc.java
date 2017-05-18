package com.javalive09.sample.function.regex;

import com.javalive09.codebag.Entry;

import java.util.regex.Pattern;

/**
 * Created by peter on 2016/10/19.
 */

public class WifiSuc extends Entry{

    public void test1() {
        String str = "ST"+  "\t" + "WIFI_SUCCESS" + "\t" + "144444444";
        showTxt(str + ">" + isValid(str));
    }

    public void test2() {
        String str = "ST"+  "\t" + "WIFI_SUCCESS" + "\t" + "1";
        showTxt(str + ">" + isValid(str));
    }

    public void test3() {
        String str = "adfsddfsd";
        showTxt(str + ">" + isValid(str));
    }

    public boolean isValid(String str) {
        String regex = "^ST\\tWIFI_SUCCESS\\t[1-9]\\d*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

}
