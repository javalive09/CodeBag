package com.codebag.bag;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;

import dalvik.system.DexFile;
import android.app.Application;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.View;

public class CodeBag extends Application {

	public static final String ROOT_DIR = "com.codebag.code.mycode";
	public static final int FILE = 0;
	public static final int DIR = 1;
	private View mCurrentMethodView = null;
	private Node mCurrentNode = null;
	private boolean mHashInit = false;
	private Node mRootNode = new Node(ROOT_DIR, Node.DIR);
	private LinkedList<MainActivity> mActContainer = new LinkedList<MainActivity>();
	
	
	@Override
	public void onCreate() {
//		Debug.startMethodTracing("mytrace"); 
	}

	public void init() {
		
		if(mHashInit) {
			return;
		}
		
		String pkgName = getPackageName();
		String apkDir = null;
		try {
			apkDir = getPackageManager().getApplicationInfo(pkgName, 0).sourceDir;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		Log.i("~peter", "apkDir = " + apkDir);
		Log.i("~peter", "pkgName = " + pkgName);

		DexFile dexFile = null;
		try {
			String prefix = ROOT_DIR;
			int startLen = prefix.length() + 1;
			dexFile = new DexFile(apkDir);
			Enumeration<String> apkClassNames = dexFile.entries();
			while (apkClassNames.hasMoreElements()) {
				String className = apkClassNames.nextElement();
				
				if (className.endsWith(".R")) {
	                continue;
	            }
				
				if(className != null && className.startsWith(prefix) & className.indexOf("$") < 0) {
					
					String fileName = className.substring(startLen);
					String[] strs = fileName.split("\\.");
					
					loadNode(className, strs, 0, mRootNode);
					
					Log.i("~peter", "fileName = " + fileName);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		printNode(mRootNode);
		
		mHashInit = true;
	}
	
	public void addActivity(MainActivity act) {
		mActContainer.add(act);
	}
	
	public void removeActivity(MainActivity act) {
		mActContainer.remove(act);
	}
	
	public void exit() {
		for(MainActivity act : mActContainer) {
			act.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	public Node getRootNode() {
		return mRootNode;
	}
	
	public View getCurrentMethodView() {
		return mCurrentMethodView;
	}
	
	public void setCurrentMethodView(View view) {
		mCurrentMethodView = view;
	}
	
	public Node getCurrentNode() {
		return mCurrentNode;
	}
	
	public void setCurrentNode(Node node) {
		mCurrentNode = node;
	}
	
	private void printNode(Node node) {
		Log.i("!peter", "name=" + node.mName);
		Log.i("!peter", "fullname=" + node.mFullName);
		
		if(node.mSubNodeList != null) {
	
			for(int i = 0; i < node.mSubNodeList.size(); i++) {
				printNode(node.mSubNodeList.get(i));
			}
		}
	}
	
	private void loadNode(String className, String[] strs, int index, Node currentNode) {
		
		if(index > strs.length - 1) {
			return;
		}
		String nodeName = strs[index];
		if(index == strs.length - 1) {
			getSubNode(nodeName, Node.CLASS, className, currentNode);
		}else {
			Node subNode = getSubNode(nodeName, Node.DIR, null, currentNode);
			index++;
			loadNode(className, strs, index, subNode);
		}
		
	}
	
	private Node getSubNode(String nodeName, int type, String fullName, Node currentNode) {
		if(currentNode.mSubNodeList == null) {
			currentNode.mSubNodeList = new ArrayList<Node>();
			return createSubNode(nodeName, type, fullName, currentNode);
		}else {
			for(Node n : currentNode.mSubNodeList) {
				if(n.mName.equals(nodeName)) {
					return n;
				}
			}
			return createSubNode(nodeName, type, fullName, currentNode);
		}
	}
	
	private Node createSubNode(String nodeName, int type, String fullName, Node currentNode) {
		Node node = new Node();
		node.mName = nodeName;
		node.mFullName = fullName;
		node.mType = type;
		currentNode.mSubNodeList.add(node);
		return node;
	}
	
	public static class Node implements Serializable{
		private static final long serialVersionUID = 7667945539869687742L;
		public static final int DIR = 0;
		public static final int CLASS = 1;
		public static final int METHOD = 2;
		
		public int mType;
		public String mName;
		public String mFullName;
		public ArrayList<Node> mSubNodeList;
		
		public Node() {
			
		}
		
		public Node(String name, int type) {
			mName = name;
			mType = type;
		}
		
	}

}
