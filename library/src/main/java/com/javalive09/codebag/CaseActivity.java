package com.javalive09.codebag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import dalvik.system.DexFile;

/**
 * CaseActivity 核心逻辑类
 */
public class CaseActivity extends AppCompatActivity {

    private static final String CURRENT_NODE = "current_node";
    private static HashMap<Class, CaseActivity> CASES = new HashMap<>();
    private StringBuilder stringBuilder;
    private static String PACKAGE_NAME;
    private static Node rootNode;
    private ViewGroup contentView;
    private Node currentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView = new FrameLayout(CaseActivity.this));
        initData(getApplicationContext());
        PACKAGE_NAME = getPackageName();
        try {
            if (currentNode != null && !TextUtils.isEmpty(currentNode.className)) {
                if (currentNode.mSubNodeList == null) {//method
                    Class clazz = Class.forName(currentNode.className);
                    CASES.put(clazz, CaseActivity.this);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            finish();
        }
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (currentNode.mSubNodeList == null) {//method
            Set<Class> classSet = CASES.keySet();
            for (Class clazz : classSet) {
                if (CASES.get(clazz) == this) {
                    CASES.remove(clazz);
                    break;
                }
            }
        }
    }

    public static View showView(@LayoutRes int resId) {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            return caseActivity.getLayoutInflater().inflate(resId, caseActivity.contentView);
        } else {
            return null;
        }
    }

    public static void showView(@NonNull final View view) {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            caseActivity.contentView.removeAllViews();
            caseActivity.contentView.addView(view);
        }
    }

    public static void showText(@NonNull final String text) {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            TextView textView = new TextView(caseActivity);
            textView.setText(text);
            showView(textView);
        }
    }

    public static void showText(@StringRes int resId) {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            showText(caseActivity.getString(resId));
        }
    }

    public static void addText(@NonNull String text) {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            if (caseActivity.stringBuilder == null) {
                caseActivity.stringBuilder = new StringBuilder();
            }
            caseActivity.stringBuilder.append(text).append("\n");
        }
    }

    public static void showAddedText() {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            if (caseActivity.stringBuilder == null) {
                caseActivity.stringBuilder = new StringBuilder();
            }
            showText(caseActivity.stringBuilder.toString());
        }
    }

    public static void toastLong(@NonNull String text) {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            Toast.makeText(caseActivity, text, Toast.LENGTH_LONG).show();
        }
    }

    public static void toastShort(@NonNull String text) {
        CaseActivity caseActivity = context();
        if (caseActivity != null) {
            Toast.makeText(caseActivity, text, Toast.LENGTH_SHORT).show();
        }
    }

    private void initData(Context context) {
        if (rootNode == null) {
            String pkgName = context.getPackageName();
            rootNode = new Node(pkgName, Node.DIR);
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
            } catch (PackageManager.NameNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
            if (getIntent() != null) {
                currentNode = (Node) getIntent().getSerializableExtra(CURRENT_NODE);
            }
        }
        if (currentNode == null) {
            currentNode = rootNode;
        }
    }

    private boolean isPlayClass(String className) {
        try {
            Class clazz = Class.forName(className);
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Play.class)) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void initView() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(currentNode.toString());
        }
        switch (currentNode.type) {
            case Node.DIR:
            case Node.CLASS:
                if (currentNode.mSubNodeList != null) {
                    ArrayAdapter<Node> arrayAdapter = new ArrayAdapter<>(CaseActivity.this,
                            android.R.layout.simple_list_item_1, currentNode.mSubNodeList);
                    ListView listView = new ListView(CaseActivity.this);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Node node = currentNode.mSubNodeList.get(position);
                            switch (node.type) {
                                case Node.DIR:
                                case Node.METHOD:
                                    startActivity(node);
                                    break;
                                case Node.CLASS:
                                    boolean addSuc = false;
                                    String classPointMethodName = null;
                                    try {
                                        Class cls = Class.forName(node.className);
                                        if (cls.isAnnotationPresent(Player.class)) {
                                            Player player = (Player) cls.getAnnotation(Player.class);
                                            classPointMethodName = player.point();
                                        }

                                        for (Method m : cls.getDeclaredMethods()) {
                                            if (m.isAnnotationPresent(Play.class) &&
                                                    Modifier.PUBLIC == m.getModifiers() &&
                                                    m.getParameterTypes().length == 0) {
                                                String methodName = m.getName();
                                                Node methodNode = createAndAddSubNode(node.className, methodName, Node.METHOD, node);
                                                addSuc = true;
                                                if (TextUtils.equals(methodName, classPointMethodName)) {
                                                    startActivity(methodNode);
                                                    return;
                                                }
                                            }
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    if (addSuc) {
                                        startActivity(node);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    });

                    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            Node node = currentNode.mSubNodeList.get(position);
                            if (node.type == Node.CLASS) {
                                final String path = node.className.replace(".", "/") + ".source";
                                final String title = node.className.replace(".", "/") + ".java";
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            InputStream inputStream = getAssets().open(path);
                                            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                                            byte[] bytes = new byte[4096];
                                            int len;
                                            while ((len = inputStream.read(bytes)) > 0) {
                                                byteStream.write(bytes, 0, len);
                                            }
                                            final String result = new String(byteStream.toByteArray(), "UTF8");
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    new AlertDialog.Builder(CaseActivity.this).setTitle(title).
                                                            setMessage(result).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                        }
                                                    }).show();
                                                }
                                            });
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                                return true;
                            }
                            return false;
                        }
                    });

                    contentView.addView(listView);
                }
                break;
            case Node.METHOD:
                try {
                    Class<?> cls = Class.forName(currentNode.className);
                    Object obj = getClassLoader().loadClass(currentNode.className).newInstance();
                    Method method = cls.getDeclaredMethod(currentNode.name);
                    method.invoke(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //no view - finish
                if (contentView.getChildCount() == 0) finish();
                break;
        }
    }

    private void startActivity(Node node) {
        Intent intent = new Intent(CaseActivity.this, CaseActivity.class);
        intent.putExtra(CURRENT_NODE, node);
        startActivity(intent);
    }

    /**
     * @param className   class全路径名
     * @param fileNames   class全路径名除去根路径，剩下的字符以“.”为划分记号，划分成的数组
     * @param index       游标在fileNames数组中的位置
     * @param currentNode 当前节点（作为父节点）
     */
    private void loadCodeBagNode(String className, String[] fileNames, int index, Node currentNode) {
        if (index > fileNames.length - 1) {
            return;
        }
        String nodeName = fileNames[index];
        if (index == fileNames.length - 1) {//数组的最后一个元素为class
            createAndAddSubNode(className, nodeName, Node.CLASS, currentNode);
        } else {//数组中其他元素为目录
            Node subNode = createAndAddSubNode(className, nodeName, Node.DIR, currentNode);
            index++;
            loadCodeBagNode(className, fileNames, index, subNode);
        }

    }

    /**
     * @param className   class全路径名
     * @param nodeName    子节点名字（游标所在数组的元素名字）--- 是区分各个子节点的关键字
     * @param type        子节点类型（目录/类）
     * @param currentNode 父节点
     * @return Node
     */
    private Node createAndAddSubNode(String className, String nodeName, int type, Node currentNode) {
        if (currentNode.mSubNodeList == null) {//创建子节点列表
            currentNode.mSubNodeList = new ArrayList<>();
        } else {
            for (Node n : currentNode.mSubNodeList) {//父节点有子节点列表，则遍历一下
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
     * @return 节点
     */
    private Node createSubNode(String className, String nodeName, int type, Node currentNode) {
        Node node = new Node(nodeName, type, className);
        currentNode.mSubNodeList.add(node);
        return node;
    }

    public static synchronized CaseActivity context() {
        try {
            throw new Exception("get sample");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            String[] lines = sStackTrace.split("\n");
            String name = "";
            for (String message : lines) {
                String playerActivityClassName = CaseActivity.class.getName();
                if (message.contains(PACKAGE_NAME) &&
                        !message.contains(playerActivityClassName)) {
                    Log.e("message >>>>> ", message);
                    int simpleClassNameStartIndex = message.indexOf("(") + 1;
                    int simpleClassNameEndIndex = message.indexOf(".java:");
                    String simpleClassName = message.substring(simpleClassNameStartIndex, simpleClassNameEndIndex);
                    int classStartIndex = message.indexOf(PACKAGE_NAME);
                    int classEndIndex = message.indexOf("." + simpleClassName);
                    name = message.substring(classStartIndex, classEndIndex) + "." + simpleClassName;
                    break;
                }
            }
            Log.e("name", name);
            Class clazz = null;
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            if (clazz != null) {
                CaseActivity playerActivity = CASES.get(clazz);
                if (playerActivity != null) {
                    return playerActivity;
                }
            }
        }
        return null;
    }

}
