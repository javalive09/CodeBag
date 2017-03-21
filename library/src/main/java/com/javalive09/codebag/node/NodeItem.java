package com.javalive09.codebag.node;

import android.os.Parcel;
import android.os.Parcelable;

import com.javalive09.codebag.R;

public class NodeItem implements Parcelable {

    public static final int DIR = R.string.ic_folder;
    public static final int CLASS = R.string.ic_drive_file;
    public static final int METHOD = R.string.ic_play_arrow;

    public int icon;
    public String text;
    public String className;
    public int type;

    public NodeItem(int icon, String text, String className) {
        this.icon = icon;
        this.text = text;
        this.className = className;
    }

    protected NodeItem(Parcel in) {
        icon = in.readInt();
        text = in.readString();
        className = in.readString();
        type = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(text);
        dest.writeString(className);
        dest.writeInt(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NodeItem> CREATOR = new Creator<NodeItem>() {
        @Override
        public NodeItem createFromParcel(Parcel in) {
            return new NodeItem(in);
        }

        @Override
        public NodeItem[] newArray(int size) {
            return new NodeItem[size];
        }
    };
}
