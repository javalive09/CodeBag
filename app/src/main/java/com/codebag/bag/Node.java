package com.codebag.bag;

import java.io.Serializable;
import java.util.ArrayList;


public class Node implements Serializable{
	private static final long serialVersionUID = 8820603100632668533L;
	public static final int DIR = 0;
	public static final int CLASS = 1;
	public static final int APP = 3;
	public static final int METHOD = 4;
	
	public int type = -1;
	public String name;
	public String className;
	public String fullName;//全名
	public ArrayList<Node> mSubNodeList;
	public float pointX;
	public float pointY;
	public String openSourceInfo;
	public String openSourceUrl;
	
	public Node(String name, int type) {
		this.name = name;
		this.type = type;
		fullName = name;
	}
	
	public Node(String name, int type, String className) {
		this(name, type);
		this.className = className;
		int index = className.indexOf(name);
		fullName = className.substring(0, index + name.length());
	}

}