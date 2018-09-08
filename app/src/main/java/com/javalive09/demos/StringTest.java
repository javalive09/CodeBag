package com.javalive09.demos;

import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;
import com.javalive09.demos.utils.GzipUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

@Code(name = "String")
public class StringTest {

    @Run(name = "空格分隔字符串 33 19 * * *")
    public void splitSpace(CodeActivity activity) {
        String str = "33 19 * * *";
        String[] strings = str.split(" ");
        activity.showText(strings[0] + strings[1] + strings[2] + strings[3] + strings[4]);
    }

    @Run(name = "点分隔字符串 com.codebag.code.function.fragment.MyFragmentActivity")
    public void splitDot(CodeActivity activity) {
        String str = "com.codebag.code.function.fragment.MyFragmentActivity";
        String[] s = str.split("\\.");
        activity.showText("" + s[s.length - 1]);
    }

    @Run(name = "Tokenizer分隔字符串 com.codebag.code.function.fragment.MyFragmentActivity")
    public void splitByTokenizer(CodeActivity activity) {
        String str = "com.codebag.code.function.fragment.MyFragmentActivity";
        StringTokenizer token = new StringTokenizer(str, ".");
        String strings = "";
        while (token.hasMoreTokens()) {
            strings += token.nextToken();
        }
        activity.showText(strings);
    }

    public void encodeUrl(CodeActivity activity) {
        String url =
                "http://192.168.230.119:1999/package/?mix=%E6%AD%A3%E5%B8%B8%E6%89%93%E5%8C%85&type=%E6%B5%8B%E8%AF"
                        + "%95%E5%8C%85&log=%E6%89%93%E5%8D%B0%E6%97%A5%E5%BF%97&branch=rl_p_v1.1.0_c_v1.1.0&mail"
                        + "=792387725%40qq.com%2C";
        try {
            String decodeUrl = URLDecoder.decode(url, Charset.defaultCharset().name());
            String str = "url=" + url;
            str += "decodeUrl=" + decodeUrl;
            activity.showText(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * noxliff:XML Localization Interchange File Format
     * XML本地化数据交换格式。
     * 用于把XML中的一些变值作为变量，从而提高效率
     * <p>
     * android中主要用于动态的在本地资源中加入一些可以作为变量的字符。
     * <p>
     * %n$ms：代表输出的是字符串，n代表是第几个参数，设置m的值可以在输出之前放置空格
     * %n$md：代表输出的是整数，n代表是第几个参数，设置m的值可以在输出之前放置空格，也可以设为0m,在输出之前放置m个0
     * %n$mf：代表输出的是浮点数，n代表是第几个参数，设置m的值可以控制小数位数，如m=2.2时，输出格式为00.00
     * 也可简单写成：
     * %d   （表示整数）
     * %f    （表示浮点数）
     * %s   （表示字符串）
     * 参考：http://examples.javacodegeeks.com/core-java/lang/string/java-string-format-example/
     */
    public void format_string(CodeActivity activity) {
        String nameFormat = activity.getResources().getString(R.string.format_test_name);
        String name = String.format(nameFormat, "张瑞");
        TextView tv = new TextView(activity);
        tv.setBackgroundColor(Color.WHITE);
        tv.setText(name);
        activity.setContentView(tv);
    }

    public void format_string_string(CodeActivity activity) {
        String nameFormat = activity.getResources().getString(R.string.format_test_name_place);
        String name = String.format(nameFormat, "张瑞", "辽宁");
        TextView tv = new TextView(activity);
        tv.setBackgroundColor(Color.WHITE);
        tv.setText(name);
        activity.setContentView(tv);
    }

    public void format_int(CodeActivity activity) {
        String nameFormat = activity.getResources().getString(R.string.format_test_old);
        String name = String.format(nameFormat, 20);
        TextView tv = new TextView(activity);
        tv.setBackgroundColor(Color.WHITE);
        tv.setText(name);
        activity.setContentView(tv);
    }

    public void format_int_string(CodeActivity activity) {
        String nameFormat = activity.getResources().getString(R.string.format_test_name_place_old);
        String name = String.format(nameFormat, "张瑞", "辽宁", 20);
        TextView tv = new TextView(activity);
        tv.setBackgroundColor(Color.WHITE);
        tv.setText(name);
        activity.setContentView(tv);
    }

    /**
     * 日期格式化输出
     * https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     */
    @Run(name = "日期格式化输出 SimpleDateFormat")
    public void simpleDateFormat(CodeActivity activity) {
        SimpleDateFormat sdfZ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z", Locale.CHINA);
        Date date = new Date();
        activity.showText(sdfZ.format(date));
    }

    @Run(name = "sdcard apk file url")
    public void uri(CodeActivity activity) {
        String path = "file:///storage/emulated/0/XUIP/App/xui_music.apk";
        Uri uri = Uri.parse(path);
        String string = "uri : " + path;
        String realPath = uri.getPath(); //  /storage/emulated/0/XUIP/App/xui_music.apk
        string += "uri realPath : " + realPath;
        activity.showText(string);
    }

    @Run(name = "根据栈信息获取Class名字")
    public void getClassNameByString(CodeActivity activity) {
        String s = "\tat com.javalive09.demos.ThreadTest.timer(ThreadTest.java:126)";
        String packageName = "com.javalive09.demos";

        int simpleClassNameStartIndex = s.indexOf("(") + 1;
        int simpleClassNameEndIndex = s.indexOf(".java:");
        String simpleClassName = s.substring(simpleClassNameStartIndex, simpleClassNameEndIndex);

        int classStartIndex = s.indexOf(packageName);
        int classEndIndex = s.indexOf("." + simpleClassName + ".");
        String className = s.substring(classStartIndex, classEndIndex) + "." + simpleClassName;
        String str = simpleClassName;
        str += className;
        activity.showText(str);

    }

    private String str = "";

    @Run
    public void compress(CodeActivity activity) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("代码笔记：www.note52.com");
        }
        str = sb.toString();
        String s = GzipUtil.compress(str);
        Log.i("peter", "before compress:" + str.length());
        Log.i("peter", "compress:" + s.length());
    }

    @Run
    public void unCompress(CodeActivity activity) {
        String s = GzipUtil.uncompress(str);
        Log.i("peter", "unCompress:" + s.length());

    }

}

