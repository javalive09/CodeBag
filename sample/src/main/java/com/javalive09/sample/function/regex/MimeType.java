package com.javalive09.sample.function.regex;

import com.javalive09.codebag.Entry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by peter on 2017/3/10.
 */

public class MimeType extends Entry {

    public static final String MIME_TYPE_IMAGE = "[0-9]*";
    public static final String content = "123";

    public void action() {
        showTxt("result = " + match(content, MIME_TYPE_IMAGE));
    }

    private boolean match(String content, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        boolean result = matcher.matches();
        return result;
    }
}
