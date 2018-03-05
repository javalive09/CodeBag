package com.javalive09.codebag;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.javalive09.codebag.annotation.Test;
import com.javalive09.codebag.annotation.Tester;

class TesterNode implements Parcelable {
    static final int DIR = 0;
    static final int CLASS = 1;
    static final int METHOD = 2;
    int type;
    String name;
    String className;
    ArrayList<TesterNode> mSubNodeList;

    TesterNode(String name, int type) {
        this.name = name;
        this.type = type;
    }

    TesterNode(String name, int type, String className) {
        this(name, type);
        this.className = className;
    }

    protected TesterNode(Parcel in) {
        type = in.readInt();
        name = in.readString();
        className = in.readString();
        mSubNodeList = in.createTypedArrayList(TesterNode.CREATOR);
    }

    public static final Creator<TesterNode> CREATOR = new Creator<TesterNode>() {
        @Override
        public TesterNode createFromParcel(Parcel in) {
            return new TesterNode(in);
        }

        @Override
        public TesterNode[] newArray(int size) {
            return new TesterNode[size];
        }
    };

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
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(Tester.class)) {
                        Tester player = clazz.getAnnotation(Tester.class);

                        String pointMethodAnnotationName = player.point();
                        if (TextUtils.isEmpty(pointMethodAnnotationName)) {
                            classAnnotationName = player.name();
                        } else {
                            Method method = clazz.getDeclaredMethod(pointMethodAnnotationName);
                            Test play = method.getAnnotation(Test.class);
                            String methodPlayName = play.name();
                            if (TextUtils.isEmpty(methodPlayName)) {
                                classAnnotationName = method.getName();
                            } else {
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
                            if (method.isAnnotationPresent(Test.class)) {
                                Test play = method.getAnnotation(Test.class);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(name);
        dest.writeString(className);
        dest.writeTypedList(mSubNodeList);
    }
}