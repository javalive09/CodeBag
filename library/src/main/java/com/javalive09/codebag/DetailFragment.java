package com.javalive09.codebag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.javalive09.codebag.node.NodeItem;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * Created by peter on 2017/3/22.
 *
 */

public class DetailFragment extends Fragment {

    private NodeItem mNode;
    private ViewGroup rootView;
    private FragmentCallback fragmentCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (fragmentCallback != null) {
            fragmentCallback.onCreate();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_detail, container, false);
        mNode = getArguments().getParcelable(CodeBag.NODE_NAME);
        if (mNode != null) {
            invokeMethod(mNode);
        } else {
            getActivity().onBackPressed();
        }
        return rootView;
    }

    public void setFragmentCallback(FragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
    }

    private void invokeMethod(NodeItem node) {
        try {
            String methodName = node.text;
            String className = node.className;
            Class<?> cls = Class.forName(className);
            Constructor<?> con = cls.getConstructor();
            Object obj = con.newInstance();
            if (obj != null) {
                Field mActivity = cls.getSuperclass().getDeclaredField("fragment");
                mActivity.setAccessible(true);
                mActivity.set(obj, DetailFragment.this);
                Method method = cls.getDeclaredMethod(methodName);
                method.invoke(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            View hint = rootView.findViewById(R.id.hint);
            if (hint != null) {
                getActivity().onBackPressed();
            }
        }
    }

    public View showMethodView(View view) {
        rootView.removeAllViews();
        rootView.addView(view);
        return view;
    }

    public View showMethodView(int viewRes) {
        rootView.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        inflater.inflate(viewRes, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (fragmentCallback != null) {
            fragmentCallback.onDestroy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (fragmentCallback != null) {
            fragmentCallback.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public static class FragmentCallback {
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {}
        public void onCreate() {}
        public void onDestroy() {}
    }

}
