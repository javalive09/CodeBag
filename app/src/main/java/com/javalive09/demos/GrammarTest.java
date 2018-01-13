package com.javalive09.demos;

import com.javalive09.codebag.Player;
import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Player(name = "语法")
public class GrammarTest {

    @Play(name = "可变参数String... str")
    public void varyParam() {
        String[] strings = new String[]{"1", "2", "3"};
        varyParamMethod(1, strings);
    }

    private void varyParamMethod(int a, String... str) {
        List<String> strings = Arrays.asList(str);
    }

    @Play(name = "泛型返回值<T>T")
    public void genericityMethod() {
        set("123", 1);
        set("456", "abc");
        try {
            int i = getValue("12");
            String j = getValue("456");
            PlayerActivity.context().showText(i + "" + j);
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
