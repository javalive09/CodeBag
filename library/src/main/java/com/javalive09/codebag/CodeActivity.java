package com.javalive09.codebag;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

/**
 * CodeActivity 核心逻辑类
 */
public class CodeActivity extends Activity {

    private static final String CURRENT_NODE = "currentNode";
    private static final String CLASS_NAME = "className";
    private static final String METHOD_NAME = "methodName";
    private static final String SP_NAME = "mark";
    private static CodeNode rootNode;
    private CodeNode currentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(getApplicationContext());
        initView();
    }

    public static void launch(@NonNull Context context) {
        context.getApplicationContext().startActivity(new Intent(context, CodeActivity.class));
    }

    public void showText(@NonNull final String text) {
        setContentView(R.layout.textview_layout);
        ((TextView) (findViewById(R.id.content_text))).setText(text);
    }

    private void initData(Context context) {
        if (rootNode == null) {
            String pkgName = context.getPackageName();
            rootNode = new CodeNode(pkgName, CodeNode.DIR);
            CodeNodeLoader.getInstance().load(rootNode, getApplicationContext());
        } else {
            if (getIntent() != null) {
                Bundle bundle = getIntent().getBundleExtra(CURRENT_NODE);
                if (bundle != null) {
                    currentNode = bundle.getParcelable(CURRENT_NODE);
                }
            }
        }
        if (currentNode == null) {
            currentNode = rootNode;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setTitle(currentNode.toString());
        switch (currentNode.type) {
            case CodeNode.DIR:
            case CodeNode.CLASS:
                ListView listView = installListView();
                if (listView != null) {
                    autoClick(listView);
                }
                break;
            case CodeNode.METHOD:
                try {
                    invokeMethod(CodeActivity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //mark method
                saveMark(CLASS_NAME, currentNode.className);
                saveMark(METHOD_NAME, currentNode.name);

                //no view - finish
                FrameLayout content = findViewById(android.R.id.content);
                if (content.getChildCount() == 0) {
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveMark(CLASS_NAME, "");
        saveMark(METHOD_NAME, "");
    }

    private void autoClick(ListView listView) {
        String className = getMark(CLASS_NAME);
        String methodName = getMark(METHOD_NAME);
        int type = currentNode.type;
        for (int i = 0, count = currentNode.mSubNodeList.size(); i < count; i++) {
            final CodeNode node = currentNode.mSubNodeList.get(i);
            if (TextUtils.equals(node.className, className)) {
                switch (type) {
                    case CodeNode.DIR:
                        performClickListItem(listView, i);
                        break;
                    case CodeNode.CLASS:
                        if (TextUtils.equals(node.name, methodName)) {
                            performClickListItem(listView, i);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void performClickListItem(ListView listView, int i) {
        listView.setSelection(i);
        listView.performItemClick(listView.getAdapter().getView(i, null, null), i, listView.getAdapter().getItemId(i));
    }

    private String getMark(String key) {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        return sp.getString(key, "");
    }

    private void saveMark(String key, String value) {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    private ListView installListView() {
        ListView listView = null;
        if (currentNode.mSubNodeList != null) {
            setContentView(R.layout.listview_layout);
            ArrayAdapter<CodeNode> arrayAdapter = new ArrayAdapter<>(CodeActivity.this,
                    android.R.layout.simple_list_item_1, currentNode.mSubNodeList);
            listView = findViewById(R.id.list_content);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CodeNode node = currentNode.mSubNodeList.get(position);
                    switch (node.type) {
                        case CodeNode.DIR:
                        case CodeNode.METHOD:
                            startActivity(node);
                            break;
                        case CodeNode.CLASS:
                            boolean haveRunMethod = false;
                            try {
                                Class cls = Class.forName(node.className);
                                for (Method m : cls.getDeclaredMethods()) {
                                    if (m.isAnnotationPresent(Run.class) && Modifier.PUBLIC == m
                                            .getModifiers()) {
                                        String methodName = m.getName();
                                        CodeNodeLoader.getInstance()
                                                .createAndAddSubNode(node.className, methodName,
                                                        CodeNode.METHOD, node);
                                        haveRunMethod = true;
                                    }
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (haveRunMethod) {
                                startActivity(node);
                            }
                            break;
                        default:
                            break;
                    }
                }
            });
        }
        return listView;
    }

    private void invokeMethod(Class<?> parameterTypes) throws Exception {
        Object obj = getClassLoader().loadClass(currentNode.className).newInstance();
        Method method = obj.getClass().getDeclaredMethod(currentNode.name, parameterTypes);
        if (method != null) {
            method.setAccessible(true);
            method.invoke(obj, CodeActivity.this);
        }
    }

    private void startActivity(CodeNode node) {
        Intent intent = new Intent(CodeActivity.this, CodeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(CURRENT_NODE, node);
        intent.putExtra(CURRENT_NODE, bundle);
        startActivity(intent);
    }

}
