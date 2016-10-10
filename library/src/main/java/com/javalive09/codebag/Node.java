package com.javalive09.codebag;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/**
 *
 * Created by peter on 16/9/21.
 *
 */
class Node implements Parcelable, Comparable<Node>{
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

	Node(Parcel in) {
		type = in.readInt();
		name = in.readString();
		className = in.readString();
		mSubNodeList = in.createTypedArrayList(Node.CREATOR);
	}

	public static final Creator<Node> CREATOR = new Creator<Node>() {
		@Override
		public Node createFromParcel(Parcel in) {
			return new Node(in);
		}

		@Override
		public Node[] newArray(int size) {
			return new Node[size];
		}
	};

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

	@Override
	public int compareTo(Node o) {
		return name.compareTo(o.name);
	}
}