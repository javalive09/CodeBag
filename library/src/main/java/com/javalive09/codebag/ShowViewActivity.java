package com.javalive09.codebag;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

public class ShowViewActivity extends Activity {

    private Node mNode;
    private ActivityCallback mActivityCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_view);
        Intent intent = getIntent();
        if (intent != null) {
            mNode = intent.getParcelableExtra(CodeBag.NODE_NAME);
            if (mNode == null) {
                finish();
            }else {
                invokeMethod(mNode);
                if(mActivityCallback != null){
                    mActivityCallback.onCreate();
                }
            }
        }
    }

    private void invokeMethod(Node node) {
        try {
            String methodName = node.name;
            String className = node.className;
            Class<?> cls = Class.forName(className);
            Object obj = CodeBagActivity.mObjectList.get(className);
            if(obj != null) {
                Field mActivity = cls.getSuperclass().getDeclaredField("mActivity");
                mActivity.setAccessible(true);
                mActivity.set(obj, ShowViewActivity.this);
                Method method = cls.getDeclaredMethod(methodName);
                method.invoke(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            View hint = findViewById(R.id.hint);
            if(hint != null) {
                finish();
            }
        }
    }

    public View showMethodView(View view) {
        setContentView(view);
        return view;
    }

    public View showMethodView(int viewRes) {
        setContentView(viewRes);
        return getWindow().getDecorView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mActivityCallback != null) {
            mActivityCallback.onDestory();
        }
    }

    public void setmActivityCallback(ActivityCallback callback) {
        mActivityCallback = callback;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(mActivityCallback != null) {
            mActivityCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mActivityCallback != null) {
            mActivityCallback.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mActivityCallback != null) {
            mActivityCallback.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mActivityCallback != null) {
            mActivityCallback.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mActivityCallback != null) {
            mActivityCallback.onPause();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mActivityCallback != null) {
            mActivityCallback.onDetachedFromWindow();
        }
    }

    @Override
    public void onAttachedToWindow() {
        if(mActivityCallback != null) {
            mActivityCallback.onAttachedToWindow();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mActivityCallback != null) {
            mActivityCallback.onStop();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        finish();
    }

    public static class ActivityCallback{
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){}
        public void onActivityResult(int requestCode, int resultCode, Intent data){}
        public void onCreate() {}
        public void onStart() {}
        public void onResume() {}
        public void onPause() {}
        public void onStop() {}
        public void onDestory() {}
        public void onDetachedFromWindow() {}
        public void onAttachedToWindow() {}
    }

}
