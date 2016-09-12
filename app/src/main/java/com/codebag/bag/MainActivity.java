package com.codebag.bag;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import com.codebag.R;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private static List<MainActivity> mActContainer = new LinkedList<>();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActContainer.add(MainActivity.this);
        Intent intent = getIntent();
        if (intent != null) {
            Node node = (Node) intent.getSerializableExtra("node");
            if(node == null) {//初始化
                CodeBag app = (CodeBag) getApplication();
                node = app.init();
            }
            initActionBar(node);
            initStatusBar();
            setContentView(R.layout.activity_main_view);
            mListView = (ListView) findViewById(R.id.lv);
            ListAdapter adapter = new ListAdapter(MainActivity.this, node.mSubNodeList);
            mListView.setAdapter(adapter);
        }
    }

    private ArrayList<Node> getMethodNodes(Node parentNode) {
        ArrayList<Node> methodList = new ArrayList<>();
        try {
            String className = parentNode.className;
            Class<?> cls = Class.forName(className);
            String superClassName = cls.getSuperclass().getSimpleName();
            if(superClassName.equals("Entry")) {
                Method[] methods = cls.getDeclaredMethods();
                for (Method m : methods) {
                    if (Modifier.PUBLIC == m.getModifiers()) {
                        String methodName = m.getName();
                        Node node = new Node(methodName, Node.METHOD, className);
                        methodList.add(node);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return methodList;
        }
    }

    private void initStatusBar() {
        int ver = android.os.Build.VERSION.SDK_INT;
        if (ver >= 21) {
            new StatusBarApiInvoke(this).invoke();
        }
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag(R.id.main_item_pos);
        Node node = (Node) mListView.getAdapter().getItem(position);
        if (node != null) {
            switch (node.type) {
                case Node.DIR:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtra("node", node);
                    startActivity(intent);
                    break;
                case Node.CLASS:
                    ArrayList<Node> nodeList = getMethodNodes(node);
                    if(nodeList != null && nodeList.size() > 0) {
                        node.mSubNodeList = getMethodNodes(node);
                        intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.putExtra("node", node);
                        startActivity(intent);
                    }
                    break;
                case Node.METHOD:
                    invokeMethod(node);
                    break;
                default:
                    break;
            }
        }
    }

    private void invokeMethod(Node node) {
        try {
            String methodName = node.name;
            String className = node.className;
            Class<?> cls = Class.forName(className);
            Constructor<?> con = cls.getConstructor();
            Object obj = con.newInstance();

            Field mActivity = cls.getSuperclass().getDeclaredField("mActivity");
            mActivity.setAccessible(true);
            mActivity.set(obj, MainActivity.this);
            Method method = cls.getDeclaredMethod(methodName);
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public static class StatusBarApiInvoke {
        Activity mAct;

        public StatusBarApiInvoke(Activity act) {
            mAct = act;
        }

        @TargetApi(21)
        public void invoke() {
            Window window = mAct.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(mAct.getResources().getColor(R.color.statusbar));
        }
    }

    private void initActionBar(Node node) {
        setOverflowShowingAlways();
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            String name = node.name;
            switch (node.type) {
                case Node.DIR:
                    break;
                case Node.CLASS:
                    name += ".java";
                    break;
                case Node.METHOD:
                    name += "( )";
                    break;
            }
            getSupportActionBar().setTitle(name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActContainer.remove(MainActivity.this);
    }

    private void exit() {
        for (MainActivity act : mActContainer) {
            act.finish();
        }
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_help:
                showAlertDialog(getString(R.string.action_help),
                        getString(R.string.action_help_msg));
                break;
            case R.id.action_about:
                showAlertDialog(getString(R.string.action_about),
                        getString(R.string.action_about_msg));
                break;

            case R.id.action_showlog:
                showAlertDialog("log", LogUtil.getLog());
                break;

            case R.id.action_clearlog:
                LogUtil.clearLog();
                break;

            case R.id.action_exit:
                exit();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialog(String title, String content) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this,
                R.style.AppCompatAlertDialogStyle).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.show();
    }


}
