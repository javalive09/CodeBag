package com.javalive09.demos;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Code(name = "语法")
public class GrammarTest {

    @Run(name = "可变参数String... str")
    public void varyParam() {
        String[] strings = new String[]{"1", "2", "3"};
        varyParamMethod(1, strings);
    }

    private void varyParamMethod(int a, String... str) {
        List<String> strings = Arrays.asList(str);
    }

    @Run(name = "泛型返回值<T>T")
    public void genericityMethod(CodeActivity activity) {
        set("123", 1);
        set("456", "abc");
        try {
            int i = getValue("12");
            String j = getValue("456");
            activity.showText(i + "" + j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void set(String key, Object integer) {
        map.put(key, integer);
    }

    private final HashMap<String, Object> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    private <T> T getValue(String key) throws Exception {
        if (map != null) {
            Object object = map.get(key);
            if (object != null) {
                return (T) map.get(key);
            }
        }
        throw new Exception(getClass().getSimpleName() + " not found value by key = " + key + " !!!");
    }

}
