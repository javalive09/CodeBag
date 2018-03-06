package com.javalive09.codebag;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.javalive09.annotation.Constant;

import android.content.Context;

/**
 * Created by peter on 2018/3/5.
 */

public class TesterNodeLoader {

    private TesterNodeLoader() {}

    private static class SingletonInstance {
        private static final TesterNodeLoader INSTANCE = new TesterNodeLoader();
    }

    static TesterNodeLoader getInstance() {
        return TesterNodeLoader.SingletonInstance.INSTANCE;
    }

    void load(TesterNode rootNode, Context context) {
        String pkgName = context.getPackageName();
        try {
            Class<?> clazz = context.getClassLoader().loadClass(Constant.PACKAGE_NAME + "." + Constant.CLASS_NAME);
            Method method = clazz.getDeclaredMethod(Constant.METHOD_NAME);
            Object obj = clazz.newInstance();
            Object returnObj = method.invoke(obj);
            ArrayList arrayList = (ArrayList) returnObj;
            for (Object className : arrayList) {
                String fileName = className.toString().substring(pkgName.length() + 1);
                String[] fileNames = fileName.split("\\.");
                loadCodeBagNode(className.toString(), fileNames, 0, rootNode);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param className   class全路径名
     * @param fileNames   class全路径名除去根路径，剩下的字符以“.”为划分记号，划分成的数组
     * @param index       游标在fileNames数组中的位置
     * @param currentNode 当前节点（作为父节点）
     */
    void loadCodeBagNode(String className, String[] fileNames, int index, TesterNode currentNode) {
        if (index > fileNames.length - 1) {
            return;
        }
        String nodeName = fileNames[index];
        if (index == fileNames.length - 1) {//数组的最后一个元素为class
            createAndAddSubNode(className, nodeName, TesterNode.CLASS, currentNode);
        } else {//数组中其他元素为目录
            TesterNode subNode = createAndAddSubNode(className, nodeName, TesterNode.DIR, currentNode);
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
     * @return TesterNode
     */
    TesterNode createAndAddSubNode(String className, String nodeName, int type, TesterNode currentNode) {
        if (currentNode.mSubNodeList == null) {//创建子节点列表
            currentNode.mSubNodeList = new ArrayList<>();
        } else {
            for (TesterNode n : currentNode.mSubNodeList) {//父节点有子节点列表，则遍历一下
                if (n.name.equals(nodeName)) {
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
    private TesterNode createSubNode(String className, String nodeName, int type, TesterNode currentNode) {
        TesterNode node = new TesterNode(nodeName, type, className);
        currentNode.mSubNodeList.add(node);
        return node;
    }

}
