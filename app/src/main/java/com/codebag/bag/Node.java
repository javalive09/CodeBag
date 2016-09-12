package com.codebag.bag;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable{
	private static final long serialVersionUID = 8820603100632668533L;
	public static final int DIR = 0;
	public static final int CLASS = 1;
	public static final int METHOD = 2;

	public int type;
	public String name;
	public String className;
	public ArrayList<Node> mSubNodeList;
	
	public Node(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	public Node(String name, int type, String className) {
		this(name, type);
		this.className = className;
	}

}