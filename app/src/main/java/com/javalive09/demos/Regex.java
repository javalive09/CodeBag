package com.javalive09.demos;


import android.util.Patterns;

import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;
import com.javalive09.codebag.CaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Player(name = "正则匹配")
public class Regex {

    @Play(name = "matcher")
    public void matcher() {
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
            CaseActivity.showText(d + " ");
        } else {
            CaseActivity.showText("not find");
        }
    }

    @Play(name = "regex验证IP地址")
    public void ip() {
        String regex = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";

        Pattern pattern = Pattern.compile(regex);
        String str1 = "192.168.1.1";
        String str2 = "127.0.0.1";
        boolean str1Result = pattern.matcher(str1).matches();
        boolean str2Result = pattern.matcher(str2).matches();
        CaseActivity.addText(str1 + "match ip =" + str1Result);
        CaseActivity.addText(str2 + "match ip =" + str2Result);
        CaseActivity.showAddedText();
    }

    @Play(name = "number验证")
    public void number() {
        Pattern pattern = Pattern.compile("[0-9]*");
        String str1 = "123";
        String str2 = "A";
        boolean str1Result = pattern.matcher(str1).matches();
        boolean str2Result = pattern.matcher(str2).matches();
        CaseActivity.addText(str1 + "match number =" + str1Result);
        CaseActivity.addText(str2 + "match number =" + str2Result);
        CaseActivity.showAddedText();
    }

    @Play(name = "Patterns.IP_ADDRESS 验证ip地址")
    public void ipAddress() {
        Matcher matcher1 = Patterns.IP_ADDRESS.matcher("localhost");
        Matcher matcher2 = Patterns.IP_ADDRESS.matcher(null);
        Matcher matcher3 = Patterns.IP_ADDRESS.matcher("192.168.0.1");
        CaseActivity.showText("localhost match =" + matcher1.matches());
        CaseActivity.showText("null match =" + matcher2.matches());
        CaseActivity.showText("192.168.0.1 match =" + matcher3.matches());
    }

}
