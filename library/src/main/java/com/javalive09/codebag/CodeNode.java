package com.javalive09.codebag;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

class CodeNode implements Parcelable {
    static final int DIR = 0;
    static final int CLASS = 1;
    static final int METHOD = 2;
    final int type;
    final String name;
    String className;
    ArrayList<CodeNode> mSubNodeList;

    CodeNode(String name, int type) {
        this.name = name;
        this.type = type;
    }

    CodeNode(String name, int type, String className) {
        this(name, type);
        this.className = className;
    }

    private CodeNode(Parcel in) {
        type = in.readInt();
        name = in.readString();
        className = in.readString();
        mSubNodeList = in.createTypedArrayList(CodeNode.CREATOR);
    }

    public static final Creator<CodeNode> CREATOR = new Creator<CodeNode>() {
        @Override
        public CodeNode createFromParcel(Parcel in) {
            return new CodeNode(in);
        }

        @Override
        public CodeNode[] newArray(int size) {
            return new CodeNode[size];
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
                nodeName = name;
                try {
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(Code.class)) {
                        Code code = clazz.getAnnotation(Code.class);
                        if (code != null) {
                            String classAnnotationName = code.name();
                            if (!TextUtils.isEmpty(classAnnotationName)) {
                                nodeName = classAnnotationName;
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case METHOD:
                String methodAnnotationName = "";
                try {
                    Class<?> clazz = Class.forName(className);
                    for (Method method : clazz.getMethods()) {
                        String methodName = method.getName();
                        if (TextUtils.equals(methodName, name) && method.isAnnotationPresent(Run.class)) {
                            Run run = method.getAnnotation(Run.class);
                            if (run != null) {
                                methodAnnotationName = run.name();
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