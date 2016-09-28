package com.javalive09.codebag;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by peter on 16/9/21.
 */
public class CodeBagActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private static final String GIT_HUB_HOME = "https://raw.githubusercontent.com/";
    private static final String NODE_NAME = "node";
    private static List<CodeBagActivity> mActContainer = new LinkedList<>();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActContainer.add(CodeBagActivity.this);
        Intent intent = getIntent();
        if (intent != null) {
            Node node = intent.getParcelableExtra(NODE_NAME);
            if(node == null) {//init
                CodeBag app = (CodeBag) getApplication();
                node = app.init();
            }
            initActionBar(node);
            initStatusBar();
            setContentView(R.layout.activity_main_view);
            mListView = (ListView) findViewById(R.id.lv);
            ListAdapter adapter = new ListAdapter(CodeBagActivity.this, node.mSubNodeList);
            mListView.setAdapter(adapter);
        }
    }

    private ArrayList<Node> getMethodNodes(Node parentNode) {
        ArrayList<Node> methodList = new ArrayList<>();
        try {
            String className = parentNode.className;
            Class<?> cls = Class.forName(className);
            String superClassName = cls.getSuperclass().getSimpleName();
            if(superClassName.equals(Entry.class.getSimpleName())) {
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
                    Intent intent = new Intent(CodeBagActivity.this, CodeBagActivity.class);
                    intent.putExtra(NODE_NAME, node);
                    startActivity(intent);
                    break;
                case Node.CLASS:
                    ArrayList<Node> nodeList = getMethodNodes(node);
                    if(nodeList != null && nodeList.size() > 0) {
                        node.mSubNodeList = getMethodNodes(node);
                        intent = new Intent(CodeBagActivity.this, CodeBagActivity.class);
                        intent.putExtra(NODE_NAME, node);
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
            mActivity.set(obj, CodeBagActivity.this);
            Method method = cls.getDeclaredMethod(methodName);
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRootUrl() {
        int ownerStrId = CodeBag.instance().getApplicationContext().getResources()
                .getIdentifier("git_owner", "string", CodeBag.instance().getPackageName());
        if(ownerStrId != 0) {
            int repoStrId = CodeBag.instance().getApplicationContext().getResources()
                    .getIdentifier("git_repo", "string", CodeBag.instance().getPackageName());
            if(repoStrId != 0) {
                int dirStrId = CodeBag.instance().getApplicationContext().getResources()
                        .getIdentifier("git_dir", "string", CodeBag.instance().getPackageName());
                if(dirStrId != 0) {
                    String owner = CodeBag.instance().getResources().getString(ownerStrId);
                    String repo = CodeBag.instance().getResources().getString(repoStrId);
                    String rootDir = CodeBag.instance().getResources().getString(dirStrId);
                    String url = GIT_HUB_HOME + owner + "/" + repo + "/master/" + rootDir + "/src/main/java/";
                    return url;
                }else {
                    Toast.makeText(CodeBagActivity.this, R.string.no_dir, Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(CodeBagActivity.this, R.string.no_repo, Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(CodeBagActivity.this, R.string.no_owner, Toast.LENGTH_LONG).show();
        }
        return "";
    }


    @Override
    public boolean onLongClick(View v) {
        int position = (Integer) v.getTag(R.id.main_item_pos);
        Node node = (Node) mListView.getAdapter().getItem(position);
        if(node.type == Node.CLASS) {//get code source
            final AlertDialog dialog = showAlertDialog(node.name + ".java", getString(R.string.loading));
            String path = node.className.replace(".", "/") + ".java";
            String url = getRootUrl();
            if(!TextUtils.isEmpty(url)) {
                final AsyncTask loading = getGitHubCode(dialog, getRootUrl() + path);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        loading.cancel(true);
                    }
                });
                return true;
            }
        }
        return false;
    }

    private AsyncTask getGitHubCode(final AlertDialog dialog, final String url) {
        return  new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(Void... params) {
                HttpURLConnection c = null;
                StringBuilder sb = new StringBuilder();
                try {
                    URL u = new URL(url);
                    c = (HttpURLConnection) u.openConnection();
                    c.setRequestMethod("GET");
                    c.setConnectTimeout(10 * 1000);
                    c.setReadTimeout(10 * 1000);
                    c.connect();
                    int status = c.getResponseCode();
                    if(status == 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                            sb.append("\n");
                        }
                        br.close();
                    }else {
                        showToast(R.string.no_code);
                    }
                } catch (java.net.SocketTimeoutException e) {
                    e.printStackTrace();
                    showToast(R.string.time_out);
                } catch (IOException e) {
                    e.printStackTrace();
                    showToast(R.string.io_exp);
                } finally {
                    if (c != null) {
                        try {
                            c.disconnect();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                return sb.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(!TextUtils.isEmpty(s)) {
                    dialog.setMessage(s);
                }
            }
        }.execute();
    }

    private void showToast(final int resStr) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CodeBagActivity.this, resStr, Toast.LENGTH_LONG).show();
            }
        });
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
        mActContainer.remove(CodeBagActivity.this);
    }

    private void exit() {
        for (CodeBagActivity act : mActContainer) {
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
        int id = item.getItemId();

        if(id == android.R.id.home) {
            finish();
        }else if(id == R.id.action_help) {
            showAlertDialog(getString(R.string.action_help),
                    getString(R.string.action_help_msg));
        }else if(id == R.id.action_about) {
            showAlertDialog(getString(R.string.action_about),
                    getString(R.string.action_about_msg));
        }else if(id == R.id.action_showlog) {
            showAlertDialog("log", LogUtil.getLog());
        }else if(id == R.id.action_clearlog) {
            LogUtil.clearLog();
        }else if(id == R.id.action_exit) {
            exit();
        }
        return super.onOptionsItemSelected(item);
    }

    public AlertDialog showAlertDialog(String title, String content) {
        AlertDialog dialog = new AlertDialog.Builder(CodeBagActivity.this,
                R.style.AppCompatAlertDialogStyle).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.show();
        return dialog;
    }


}
