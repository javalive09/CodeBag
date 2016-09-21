package com.codebag.bag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import dalvik.system.DexFile;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

public class CodeBag extends Application {

    private static final boolean PRINT_NODE = false;
    private static Context mContext;
    private Node mRootNode;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context instance() {
        return mContext;
    }

    public Node init() {
        if (mRootNode == null) {
            mRootNode = getCodeNode();
            if (PRINT_NODE) {
                printNode(mRootNode);
            }
        }
        return mRootNode;
    }

    private Node getCodeNode() {
        String pkgName = getPackageName();
        Node rootNode = new Node(pkgName, Node.DIR);
        String apkDir = null;
        try {
            apkDir = getPackageManager().getApplicationInfo(pkgName, 0).sourceDir;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        DexFile dexFile;
        try {
            if(!TextUtils.isEmpty(apkDir)) {
                dexFile = new DexFile(apkDir);
                int startLen = pkgName.length() + 1;

                Enumeration<String> apkClassNames = dexFile.entries();
                while (apkClassNames.hasMoreElements()) {
                    String className = apkClassNames.nextElement();
                    if (className.startsWith(pkgName)
                            && !className.contains("$")
                            && !className.endsWith(".R")
                            && !className.contains("BuildConfig")) {
                        String fileName = className.substring(startLen);
                        String[] strings = fileName.split("\\.");
                        loadCodeBagNode(className, strings, 0, rootNode);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    private void printNode(Node node) {
        LogUtil.i("nodeName=" + node.name);
        LogUtil.i("className=" + node.className);
        if (node.mSubNodeList != null) {
            for (int i = 0; i < node.mSubNodeList.size(); i++) {
                printNode(node.mSubNodeList.get(i));
            }
        }
    }

    /**
     * @param className   class全路径名
     * @param strings        class全路径名除去根路径，剩下的字符以“.”为划分记号，划分成的数组
     * @param index       游标在strings数组中的位置
     * @param currentNode 当前节点（作为父节点）
     */
    private void loadCodeBagNode(String className, String[] strings, int index, Node currentNode) {

        if (index > strings.length - 1) {
            return;
        }
        String nodeName = strings[index];
        if (index == strings.length - 1) {//数组的最后一个元素为class
            getSubNode(nodeName, Node.CLASS, className, currentNode);
        } else {//数组中其他元素为目录
            Node subNode = getSubNode(nodeName, Node.DIR, className, currentNode);
            index++;
            loadCodeBagNode(className, strings, index, subNode);
        }

    }

    /**
     * @param nodeName    子节点名字（游标所在数组的元素名字）--- 是区分各个子节点的关键字
     * @param type        子节点类型（目录/类）
     * @param className   子节点全名（目录一般为null，类才有）
     * @param currentNode 父节点
     * @return
     */
    private Node getSubNode(String nodeName, int type, String className, Node currentNode) {
        if (currentNode.mSubNodeList == null) {//创建子节点列表
            currentNode.mSubNodeList = new ArrayList<>();
        } else {
            for (Node n : currentNode.mSubNodeList) {//父节点有子节点列表，则遍历一下
                if (n.name.equals(nodeName)) {
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


}
