package com.javalive09.codebag;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import com.javalive09.annotation.Run;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import dalvik.system.DexFile;

/**
 * Tester node loader
 *
 * Created by peter on 2018/3/5.
 */

public class CodeNodeLoader {

    private CodeNodeLoader() {}

    private static class SingletonInstance {
        private static final CodeNodeLoader INSTANCE = new CodeNodeLoader();
    }

    static CodeNodeLoader getInstance() {
        return CodeNodeLoader.SingletonInstance.INSTANCE;
    }

    void load(CodeNode rootNode, Context context) {
        String pkgName = context.getPackageName();
        try {
            String apkDir = context.getPackageManager().getApplicationInfo(pkgName, 0).sourceDir;
            DexFile dexFile = new DexFile(apkDir);
            Enumeration<String> apkClassNames = dexFile.entries();
            while (apkClassNames.hasMoreElements()) {
                String className = apkClassNames.nextElement();
                if (className.startsWith(pkgName) && isPlayClass(className) & !className.contains("$") &
                        !className.endsWith(".R") & !className.contains("BuildConfig")) {
                    String fileName = className.substring(pkgName.length() + 1);
                    String[] fileNames = fileName.split("\\.");
                    loadCodeBagNode(className, fileNames, 0, rootNode);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean isPlayClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Run.class)) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param className   class全路径名
     * @param fileNames   class全路径名除去根路径，剩下的字符以“.”为划分记号，划分成的数组
     * @param index       游标在fileNames数组中的位置
     * @param currentNode 当前节点（作为父节点）
     */
    void loadCodeBagNode(String className, String[] fileNames, int index, CodeNode currentNode) {
        if (index > fileNames.length - 1) {
            return;
        }
        String nodeName = fileNames[index];
        if (index == fileNames.length - 1) {//数组的最后一个元素为class
            createAndAddSubNode(className, nodeName, CodeNode.CLASS, currentNode);
        } else {//数组中其他元素为目录
            CodeNode subNode = createAndAddSubNode(className, nodeName, CodeNode.DIR, currentNode);
            index++;
            loadCodeBagNode(className, fileNames, index, subNode);
        }

    }

    /**
     * @param className   class全路径名
     * @param nodeName    子节点名字（游标所在数组的元素名字）--- 是区分各个子节点的关键字
     * @param type        子节点类型（目录/类）
     * @param currentNode 父节点
     *
     * @return CodeNode
     */
    CodeNode createAndAddSubNode(String className, String nodeName, int type, CodeNode currentNode) {
        if (currentNode.mSubNodeList == null) {//创建子节点列表
            currentNode.mSubNodeList = new ArrayList<>();
        } else {
            for (CodeNode n : currentNode.mSubNodeList) {//父节点有子节点列表，则遍历一下
                if(TextUtils.equals(nodeName, n.name)) {
                    return n;
                }
            }
        }
        return createSubNode(className, nodeName, type, currentNode);
    }

    /**
     * @param className   class全路径名
     * @param nodeName    新建的节点Name
     * @param type        新建的节点类型
     * @param currentNode 当前节点
     *
     * @return 节点
     */
    private CodeNode createSubNode(String className, String nodeName, int type, CodeNode currentNode) {
        CodeNode node = new CodeNode(nodeName, type, className);
        currentNode.mSubNodeList.add(node);
        return node;
    }

}
