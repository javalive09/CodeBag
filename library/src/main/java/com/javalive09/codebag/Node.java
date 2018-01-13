package com.javalive09.codebag;

import android.text.TextUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;

class Node implements Serializable {
    private static final long serialVersionUID = 8820603100632668533L;
    static final int DIR = 0;
    static final int CLASS = 1;
    static final int METHOD = 2;
    int type;
    String name;
    String className;
    ArrayList<Node> mSubNodeList;

    Node(String name, int type) {
        this.name = name;
        this.type = type;
    }

    Node(String name, int type, String className) {
        this(name, type);
        this.className = className;
    }

    @Override
    public String toString() {
        String nodeName = "";
        switch (type) {
            case DIR:
                nodeName = name;
                break;
            case CLASS:
                String classAnnotationName = "";
                try {
                    Class clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(Player.class)) {
                        Player player = (Player) clazz.getAnnotation(Player.class);

                        String pointMethodAnnotationName = player.point();
                        if (TextUtils.isEmpty(pointMethodAnnotationName)) {
                            classAnnotationName = player.name();
                        } else {
                            Method method = clazz.getDeclaredMethod(pointMethodAnnotationName);
                            Play play = method.getAnnotation(Play.class);
                            String methodPlayName = play.name();
                            if(TextUtils.isEmpty(methodPlayName)) {
                                classAnnotationName = method.getName();
                            }else {
                                classAnnotationName = methodPlayName;
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(classAnnotationName)) {
                    nodeName = name;
                } else {
                    nodeName = classAnnotationName;
                }
                break;
            case METHOD:
                String methodAnnotationName = "";
                try {
                    Class clazz = Class.forName(className);
                    for (Method method : clazz.getDeclaredMethods()) {
                        if (TextUtils.equals(method.getName(), name)) {
                            if (method.isAnnotationPresent(Play.class)) {
                                Play play = method.getAnnotation(Play.class);
                                methodAnnotationName = play.name();
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(methodAnnotationName)) {
                    nodeName = name;
                } else {
                    nodeName = methodAnnotationName;
                }
                break;
        }
        return nodeName;
    }
}