package com.javalive09.codebag;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

/**
 * CodeActivity 核心逻辑类
 */
public class CodeActivity extends Activity {

    private static final String CURRENT_NODE = "current_node";
    private static CodeNode rootNode;
    private CodeNode currentNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Light);
        initData(getApplicationContext());
        initView();
    }

    public static void launch(@NonNull Context context) {
        context.startActivity(new Intent(context, CodeActivity.class));
    }

    public void showText(@NonNull final String text) {
        TextView textView = new TextView(CodeActivity.this);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        textView.setVerticalScrollBarEnabled(true);
        textView.setText(text);
        setContentView(textView);
    }

    public void showText(@StringRes int resId) {
        showText(getString(resId));
    }

    public void toastLong(@NonNull String text) {
        Toast.makeText(CodeActivity.this, text, Toast.LENGTH_LONG).show();
    }

    public void toastShort(@NonNull String text) {
        Toast.makeText(CodeActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void initData(Context context) {
        if (rootNode == null) {
            String pkgName = context.getPackageName();
            rootNode = new CodeNode(pkgName, CodeNode.DIR);
            CodeNodeLoader.getInstance().load(rootNode, getApplicationContext());
        } else {
            if (getIntent() != null) {
                currentNode = getIntent().getParcelableExtra(CURRENT_NODE);
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
                if (currentNode.mSubNodeList != null) {
                    ArrayAdapter<CodeNode> arrayAdapter = new ArrayAdapter<>(CodeActivity.this,
                            android.R.layout.simple_list_item_1, currentNode.mSubNodeList);
                    ListView listView = new ListView(CodeActivity.this);
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
                                    boolean addSuc = false;
                                    String classPointMethodName = null;
                                    try {
                                        Class cls = Class.forName(node.className);
                                        if (cls.isAnnotationPresent(Code.class)) {
                                            Code player = (Code) cls.getAnnotation(Code.class);
                                            classPointMethodName = player.point();
                                        }

                                        for (Method m : cls.getDeclaredMethods()) {
                                            if (m.isAnnotationPresent(Run.class) &&
                                                    Modifier.PUBLIC == m.getModifiers()) {
                                                String methodName = m.getName();
                                                CodeNode methodNode = CodeNodeLoader.getInstance()
                                                        .createAndAddSubNode(node.className, methodName,
                                                                CodeNode.METHOD, node);
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
                    setContentView(listView);
                }
                break;
            case CodeNode.METHOD:
                try {
                    Class<?> cls = Class.forName(currentNode.className);
                    Object obj = getClassLoader().loadClass(currentNode.className).newInstance();
                    Method method = cls.getDeclaredMethod(currentNode.name, CodeActivity.class);
                    if (method != null) {
                        method.invoke(obj, CodeActivity.this);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //no view - finish
                FrameLayout content = findViewById(android.R.id.content);
                if (content.getChildCount() == 0) {
                    finish();
                }
                break;
        }
    }

    private void startActivity(CodeNode node) {
        Intent intent = new Intent(CodeActivity.this, CodeActivity.class);
        intent.putExtra(CURRENT_NODE, node);
        startActivity(intent);
    }

}
