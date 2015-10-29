package com.codebag.bag;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import com.codebag.code.mycode.utils.Log;

import dalvik.system.DexFile;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class CodeBag extends Application implements Thread.UncaughtExceptionHandler{

	private static final String TAG = CodeBag.class.getSimpleName();
	private static final String META_DATA_TYPE = "appType";
	private static final String META_DATA_INFO = "appInfo";
	private static final String META_DATA_SOURCE_URL = "appSourceUrl";
	public static final String ROOT_DIR = "com.codebag.code";
	public static final String MYCODE_DIR = ROOT_DIR + ".mycode";
//	private LinkedList<InovkedViewActivity> mActContainer = new LinkedList<InovkedViewActivity>();
	private static final boolean PRINT_NODE = false;
	private boolean mHashInit = false;
	private Node mRootNode;
	
	@Override
	public void onCreate() {
//		Debug.startMethodTracing("mytrace"); 
//		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	public void init() {
		
		if(mHashInit) {
			return;
		}
		
		mRootNode = new Node(ROOT_DIR, Node.DIR);
		mRootNode.mSubNodeList = new ArrayList<Node>(2);
		
		//加入mycode节点
		Node myCodeNode = getMyCodeNode();
		mRootNode.mSubNodeList.add(myCodeNode);
		
		//加入appDemo节点
		Node appNode = getAppDemoNode();
		if(appNode != null) {
			mRootNode.mSubNodeList.add(appNode);
		}
		
		printNode(mRootNode);
		
		mHashInit = true;
	}

	private Node getMyCodeNode() {
		Node myCodeNode = new Node("mycode", Node.DIR);
		String pkgName = getPackageName();
		String apkDir = null;
		try {
			apkDir = getPackageManager().getApplicationInfo(pkgName, 0).sourceDir;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		Log.addLog(TAG, this, "apkDir = " + apkDir);
		Log.addLog(TAG, this, "pkgName = " + pkgName);

		DexFile dexFile = null;
		try {
			String prefix = MYCODE_DIR;
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
					
					loadCodeBagNode(className, strs, 0, myCodeNode);
					
					Log.addLog(TAG, this, "fileName = " + fileName);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return myCodeNode;
	}
	
//	public void addActivity(InovkedViewActivity act) {
//		mActContainer.add(act);
//	}
//	
//	public void removeActivity(InovkedViewActivity act) {
//		mActContainer.remove(act);
//	}
//	
//	public void exit() {
//		for(InovkedViewActivity act : mActContainer) {
//			act.finish();
//		}
//		android.os.Process.killProcess(android.os.Process.myPid());
//	}
	
	public Node getRootNode() {
		return mRootNode;
	}
	
	public Node getAppDemoNode() {
		Node appNode = new Node("open source demo", Node.APP);
		appNode.mSubNodeList = new ArrayList<Node>();		
		
    	PackageManager pm = getPackageManager();
		List<ApplicationInfo> appList = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		for(ApplicationInfo info : appList) {
		    try {
				ApplicationInfo appInfo = pm.getApplicationInfo(info.packageName, PackageManager.GET_META_DATA);
				if(appInfo.metaData != null) {
					
					String type = appInfo.metaData.getString(META_DATA_TYPE);
					
					if("codebag_appdemo".equals(type)) {
						String inf = appInfo.metaData.getString(META_DATA_INFO);
						String source = appInfo.metaData.getString(META_DATA_SOURCE_URL);
						Node node = new Node(appInfo.packageName, Node.APP);
						node.openSourceInfo = inf;
						node.openSourceUrl = source;
						appNode.mSubNodeList.add(node);
						Log.addLog(TAG, this, "packageName=" + info.packageName);
					}
				}
				
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(appNode.mSubNodeList.size() == 0) {
			return null;
		}
		return appNode;
	}
	
	private void printNode(Node node) {
		if(!PRINT_NODE) {
			return;
		}
		Log.addLog(TAG, this, "name=" + node.name);
		Log.addLog(TAG, this, "fullname=" + node.className);
		
		if(node.mSubNodeList != null) {
			for(int i = 0; i < node.mSubNodeList.size(); i++) {
				printNode(node.mSubNodeList.get(i));
			}
		}
	}
	
	/**
	 * @param className     class全路径名
	 * @param strs          class全路径名除去根路径，剩下的字符以“.”为划分记号，划分成的数组
	 * @param index		       游标在strs数组中的位置
	 * @param currentNode   当前节点（作为父节点）
	 */
	private void loadCodeBagNode(String className, String[] strs, int index, Node currentNode) {
		
		if(index > strs.length - 1) {
			return;
		}
		String nodeName = strs[index];
		if(index == strs.length - 1) {//数组的最后一个元素为class
			getSubNode(nodeName, Node.CLASS, className, currentNode);
		}else {//数组中其他元素为目录
			Node subNode = getSubNode(nodeName, Node.DIR, className, currentNode);
			index++;
			loadCodeBagNode(className, strs, index, subNode);
		}
		
	}
	
	/**
	 * @param nodeName    子节点名字（游标所在数组的元素名字）--- 是区分各个子节点的关键字
	 * @param type        子节点类型（目录/类）
	 * @param className    子节点全名（目录一般为null，类才有）
	 * @param currentNode 父节点
	 * @return
	 */
	private Node getSubNode(String nodeName, int type, String className, Node currentNode) {
		if(currentNode.mSubNodeList == null) {//创建子节点列表
			currentNode.mSubNodeList = new ArrayList<Node>();
		}else {
			for(Node n : currentNode.mSubNodeList) {//父节点有子节点列表，则遍历一下
				if(n.name.equals(nodeName)) {
					return n;
				}
			}
		}
		return createSubNode(nodeName, type, className, currentNode);
	}
	
	private Node createSubNode(String nodeName, int type, String className, Node currentNode) {
		Node node = new Node(nodeName, type, className);
		currentNode.mSubNodeList.add(node);
		return node;
	}
	
	public void setRootViewController(int status) {
		getSharedPreferences("rootview_controller", Context.MODE_PRIVATE).edit().putInt("status", status).commit();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		 Log.addLog(TAG, this, "uncaughtException");
		 Log.addLog(TAG, this, "uncaughtException" + "; path = " + Log.CRASH_TXT_PATH);
		 
		 try {
             //将crash log写入文件
             FileOutputStream fileOutputStream = new FileOutputStream(Log.CRASH_TXT_PATH, true);
             PrintStream printStream = new PrintStream(fileOutputStream);
             ex.printStackTrace(printStream);
             printStream.flush();
             printStream.close();
             fileOutputStream.close();
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
		 
		 System.exit(0);
	}
	
}
