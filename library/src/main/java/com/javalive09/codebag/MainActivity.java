package com.javalive09.codebag;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.javalive09.codebag.logger.Log;
import com.javalive09.codebag.logger.LogFragment;
import com.javalive09.codebag.logger.LogWrapper;
import com.javalive09.codebag.logger.MessageOnlyLogFilter;
import com.javalive09.codebag.node.FileHolder;
import com.javalive09.codebag.node.NodeItem;
import com.unnamed.b.atv.model.TreeNode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 *
 * Created by peter on 16/9/21.
 *
 */
public class MainActivity extends AppCompatActivity {

    TreeFragment treeFragment = new TreeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initActionBar();
        initStatusBar();
        initTreeFragment();
        initLogFragment();
    }

    private void initTreeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, treeFragment).commit();
    }

    public void startDetailFragment(NodeItem item) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(CodeBag.NODE_NAME, item);
        detailFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.hide(treeFragment).add(R.id.content, detailFragment).commit();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void initLogFragment() {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());
        logFragment.getLogView().setTextAppearance(this, R.style.Log);
        logFragment.getLogView().setBackgroundColor(Color.WHITE);
    }

    public TreeNode getCodeNode() {
        String pkgName = getPackageName();
        TreeNode rootNode = TreeNode.root();
        String apkDir = null;
        try {
            apkDir = getPackageManager().getApplicationInfo(pkgName, 0).sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        DexFile dexFile;
        try {
            if (!TextUtils.isEmpty(apkDir)) {
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

    /**
     * @param className   class全路径名
     * @param strings     class全路径名除去根路径，剩下的字符以“.”为划分记号，划分成的数组
     * @param index       游标在strings数组中的位置
     * @param currentNode 当前节点（作为父节点）
     */
    private void loadCodeBagNode(String className, String[] strings, int index, TreeNode currentNode) {
        if (index > strings.length - 1) {
            return;
        }
        String subNodeName = strings[index];
        if (index == strings.length - 1) {//数组的最后一个元素为class
            getSubNode(subNodeName, NodeItem.CLASS, className, currentNode);
        } else {//数组中其他元素为目录
            TreeNode subNode = getSubNode(subNodeName, NodeItem.DIR, className, currentNode);
            index++;
            loadCodeBagNode(className, strings, index, subNode);
        }
    }

    /**
     * @param subNodeName 子节点名字（游标所在数组的元素名字）--- 是区分各个子节点的关键字
     * @param type        子节点类型（目录/类）
     * @param className   子节点全名（目录一般为null，类才有）
     * @param currentNode 父节点
     * @return Node
     */
    private TreeNode getSubNode(String subNodeName, int type, String className, TreeNode currentNode) {
        for (TreeNode n : currentNode.getChildren()) {//父节点有子节点列表，则遍历一下
            NodeItem value = (NodeItem) n.getValue();
            if (value.text.equals(subNodeName)) {
                return n;
            }
        }
        return createSubNode(subNodeName, type, className, currentNode);
    }

    private TreeNode createSubNode(String nodeName, int icon, String className, TreeNode currentNode) {
        TreeNode node = createTreeNode(icon, nodeName, className);

        //method
        if (icon == NodeItem.CLASS) {
            addMethodNodes(node);
        }

        currentNode.addChild(node);
        return node;
    }

    private void addMethodNodes(TreeNode parentNode) {
        NodeItem value = (NodeItem) parentNode.getValue();

        String className = value.className;
        if (!TextUtils.isEmpty(className) && className.contains(getPackageName())) {
            try {
                Class<?> cls = Class.forName(className);
                Class<?> superclass = cls.getSuperclass();
                if (superclass != null) {
                    String superClassName = superclass.getSimpleName();
                    if (superClassName.equals(Entry.class.getSimpleName())) {
                        Method[] methods = cls.getDeclaredMethods();
                        for (Method m : methods) {
                            if (Modifier.PUBLIC == m.getModifiers()
                                    && m.getParameterTypes().length == 0) {
                                String methodName = m.getName();
                                TreeNode node = createTreeNode(NodeItem.METHOD, methodName, className);
                                parentNode.addChild(node);
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private TreeNode createTreeNode(int icon, String nodeName, String className) {
        TreeNode node = new TreeNode(new NodeItem(icon, nodeName, className)).setViewHolder(new FileHolder(getApplicationContext()));
        node.setClickListener(listener);
        node.setLongClickListener(longClickListener);
        return node;
    }

    private TreeNode.TreeNodeClickListener listener = new TreeNode.TreeNodeClickListener() {

        @Override
        public void onClick(TreeNode node, Object value) {
            NodeItem item = (NodeItem) value;
            if (item.icon == NodeItem.METHOD) {
                startDetailFragment(item);
            }
        }
    };

    private TreeNode.TreeNodeLongClickListener longClickListener = new TreeNode.TreeNodeLongClickListener() {
        @Override
        public boolean onLongClick(TreeNode node, Object value) {

            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initStatusBar() {
        int ver = android.os.Build.VERSION.SDK_INT;
        if (ver >= 21) {
            new StatusBarApiInvoke(this).invoke();
        }
    }

    private String getRootUrl() {
        int ownerStrId = getResources().getIdentifier("git_owner", "string", getPackageName());
        if (ownerStrId != 0) {
            int repoStrId = getResources().getIdentifier("git_repo", "string", getPackageName());
            if (repoStrId != 0) {
                int dirStrId = getResources().getIdentifier("git_dir", "string", getPackageName());
                if (dirStrId != 0) {
                    String owner = getString(ownerStrId);
                    String repo = getString(repoStrId);
                    String rootDir = getString(dirStrId);
                    return CodeBag.GIT_HUB_HOME + owner + "/" + repo + "/master/" + rootDir + "/src/main/java/";
                } else {
                    Toast.makeText(MainActivity.this, R.string.no_dir, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this, R.string.no_repo, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, R.string.no_owner, Toast.LENGTH_LONG).show();
        }
        return "";
    }

    private static class StatusBarApiInvoke {
        Activity mAct;

        StatusBarApiInvoke(Activity act) {
            mAct = act;
        }

        @TargetApi(21)
        void invoke() {
            Window window = mAct.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(mAct.getResources().getColor(R.color.statusbar));
        }
    }

    private void initActionBar() {
        setOverflowShowingAlways();
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.codebag_name);
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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_help) {
            showAlertDialog(getString(R.string.action_help),
                    getString(R.string.action_help_msg));
        } else if (id == R.id.action_about) {
            showAlertDialog(getString(R.string.action_about),
                    getString(R.string.action_about_msg));
        } else if (id == R.id.action_showlog) {
            showAlertDialog("log", LogUtil.getLog());
        } else if (id == R.id.action_clearlog) {
            LogUtil.clearLog();
        } else if (id == R.id.action_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public AlertDialog showAlertDialog(String title, String content) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this,
                R.style.AppCompatAlertDialogStyle).create();
        dialog.setCanceledOnTouchOutside(true);
        View dialogView = View.inflate(MainActivity.this, R.layout.alertdialog_view, null);
        dialog.setView(dialogView);
        TextView titleView = (TextView) dialogView.findViewById(R.id.title);
        TextView contentView = (TextView) dialogView.findViewById(R.id.content);
        titleView.setText(title);
        contentView.setText(content);
        dialog.show();
        return dialog;
    }

}
