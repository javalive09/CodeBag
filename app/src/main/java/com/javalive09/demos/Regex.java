package com.javalive09.demos;


import android.util.Patterns;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Code(name = "正则匹配")
public class Regex {

    @Run(name = "matcher")
    public void matcher(CodeActivity activity) {
        String str = "1008 bytes from 61.135.169.125: icmp_seq=1 ttl=57 time=5.13 ms";
        String regx = "time=.*ms";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String mat = matcher.group();
            int start = mat.indexOf("=") + 1;
            int end = mat.indexOf(" ");
            String time = mat.substring(start, end);
            Double d = Double.valueOf(time);
            activity.showText(d + " ");
        } else {
            activity.showText("not find");
        }
    }

    @Run(name = "regex验证IP地址")
    public void ip(CodeActivity activity) {
        String regex = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";

        Pattern pattern = Pattern.compile(regex);
        String str1 = "192.168.1.1";
        String str2 = "127.0.0.1";
        boolean str1Result = pattern.matcher(str1).matches();
        boolean str2Result = pattern.matcher(str2).matches();
        String str = "";
        str += str1 + "match ip =" + str1Result + "\n";
        str += str2 + "match ip =" + str2Result;
        activity.showText(str);
    }

    @Run(name = "number验证")
    public void number(CodeActivity activity) {
        Pattern pattern = Pattern.compile("[0-9]*");
        String str1 = "123";
        String str2 = "A";
        boolean str1Result = pattern.matcher(str1).matches();
        boolean str2Result = pattern.matcher(str2).matches();
        String str = str1 + "match number =" + str1Result + "\n";
        str += str2 + "match number =" + str2Result;
        activity.showText(str);
    }

    @Run(name = "Patterns.IP_ADDRESS 验证ip地址")
    public void ipAddress(CodeActivity activity) {
        Matcher matcher1 = Patterns.IP_ADDRESS.matcher("localhost");
        Matcher matcher2 = Patterns.IP_ADDRESS.matcher(null);
        Matcher matcher3 = Patterns.IP_ADDRESS.matcher("192.168.0.1");
        String str = "localhost match =" + matcher1.matches();
        str += "null match =" + matcher2.matches();
        str += "192.168.0.1 match =" + matcher3.matches();
        activity.showText(str);
    }

}
