package com.javalive09.codebag;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Set;

import com.javalive09.annotation.Test;
import com.javalive09.annotation.Tester;

/**
 * CodeBag 核心逻辑类
 */
public class CodeBag extends Activity {

    private static final String CURRENT_NODE = "current_node";
    private static HashMap<Class, CodeBag> CASES = new HashMap<>();
    private StringBuilder stringBuilder;
    private static String PACKAGE_NAME;
    private static TesterNode rootNode;
    private ViewGroup contentView;
    private TesterNode currentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView = new FrameLayout(CodeBag.this));
        initData(getApplicationContext());
        PACKAGE_NAME = getPackageName();
        try {
            if (currentNode != null && !TextUtils.isEmpty(currentNode.className)) {
                if (currentNode.mSubNodeList == null) {//method
                    Class clazz = Class.forName(currentNode.className);
                    CASES.put(clazz, CodeBag.this);
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
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            return caseActivity.getLayoutInflater().inflate(resId, caseActivity.contentView);
        } else {
            return null;
        }
    }

    public static void showView(@NonNull final View view) {
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            caseActivity.contentView.removeAllViews();
            caseActivity.contentView.addView(view);
        }
    }

    public static void showText(@NonNull final String text) {
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            TextView textView = new TextView(caseActivity);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            textView.setVerticalScrollBarEnabled(true);
            textView.setText(text);
            showView(textView);
        }
    }

    public static void showText(@StringRes int resId) {
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            showText(caseActivity.getString(resId));
        }
    }

    public static void addText(@NonNull String text) {
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            if (caseActivity.stringBuilder == null) {
                caseActivity.stringBuilder = new StringBuilder();
            }
            caseActivity.stringBuilder.append(text).append("\n");
        }
    }

    public static void showAddedText() {
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            if (caseActivity.stringBuilder == null) {
                caseActivity.stringBuilder = new StringBuilder();
            }
            showText(caseActivity.stringBuilder.toString());
        }
    }

    public static void toastLong(@NonNull String text) {
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            Toast.makeText(caseActivity, text, Toast.LENGTH_LONG).show();
        }
    }

    public static void toastShort(@NonNull String text) {
        CodeBag caseActivity = context();
        if (caseActivity != null) {
            Toast.makeText(caseActivity, text, Toast.LENGTH_SHORT).show();
        }
    }

    private void initData(Context context) {
        if (rootNode == null) {
            String pkgName = context.getPackageName();
            rootNode = new TesterNode(pkgName, TesterNode.DIR);
            TesterNodeLoader.getInstance().load(rootNode, getApplicationContext());
        } else {
            if (getIntent() != null) {
                currentNode = getIntent().getParcelableExtra(CURRENT_NODE);
            }
        }
        if (currentNode == null) {
            currentNode = rootNode;
        }
    }

//    private boolean isPlayClass(String className) {
//        try {
//            Class clazz = Class.forName(className);
//            for (Method method : clazz.getDeclaredMethods()) {
//                if (method.isAnnotationPresent(Test.class)) {
//                    return true;
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    private void initView() {
        setTitle(currentNode.toString());
        switch (currentNode.type) {
            case TesterNode.DIR:
            case TesterNode.CLASS:
                if (currentNode.mSubNodeList != null) {
                    ArrayAdapter<TesterNode> arrayAdapter = new ArrayAdapter<>(CodeBag.this,
                            android.R.layout.simple_list_item_1, currentNode.mSubNodeList);
                    ListView listView = new ListView(CodeBag.this);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            TesterNode node = currentNode.mSubNodeList.get(position);
                            switch (node.type) {
                                case TesterNode.DIR:
                                case TesterNode.METHOD:
                                    startActivity(node);
                                    break;
                                case TesterNode.CLASS:
                                    boolean addSuc = false;
                                    String classPointMethodName = null;
                                    try {
                                        Class cls = Class.forName(node.className);
                                        if (cls.isAnnotationPresent(Tester.class)) {
                                            Tester player = (Tester) cls.getAnnotation(Tester.class);
                                            classPointMethodName = player.point();
                                        }

                                        for (Method m : cls.getDeclaredMethods()) {
                                            if (m.isAnnotationPresent(Test.class) &&
                                                    Modifier.PUBLIC == m.getModifiers() &&
                                                    m.getParameterTypes().length == 0) {
                                                String methodName = m.getName();
                                                TesterNode methodNode = TesterNodeLoader.getInstance()
                                                        .createAndAddSubNode(node.className, methodName,
                                                                TesterNode.METHOD, node);
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
                            TesterNode node = currentNode.mSubNodeList.get(position);
                            if (node.type == TesterNode.CLASS) {
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
                                                    new AlertDialog.Builder(CodeBag.this).setTitle(title).
                                                            setMessage(result).setPositiveButton("OK",
                                                            new DialogInterface.OnClickListener() {
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
            case TesterNode.METHOD:
                try {
                    Class<?> cls = Class.forName(currentNode.className);
                    Object obj = getClassLoader().loadClass(currentNode.className).newInstance();
                    Method method = cls.getDeclaredMethod(currentNode.name);
                    method.invoke(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //no view - finish
                if (contentView.getChildCount() == 0) {
                    finish();
                }
                break;
        }
    }

    private void startActivity(TesterNode node) {
        Intent intent = new Intent(CodeBag.this, CodeBag.class);
        intent.putExtra(CURRENT_NODE, node);
        startActivity(intent);
    }

    public static void Launch(Activity activity) {
        if (activity != null) {
            activity.startActivity(new Intent(activity, CodeBag.class));
        }
    }

    public static synchronized CodeBag context() {
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
                String playerActivityClassName = CodeBag.class.getName();
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
                CodeBag playerActivity = CASES.get(clazz);
                if (playerActivity != null) {
                    return playerActivity;
                }
            }
        }
        return null;
    }

}
