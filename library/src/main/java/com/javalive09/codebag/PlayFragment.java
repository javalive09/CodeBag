package com.javalive09.codebag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by peter on 2017/3/22.
 */

public class PlayFragment extends Fragment {

    private ViewGroup rootView;
    private ViewCallback viewCallback;
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
        if(viewCallback != null) {
            viewCallback.show();
        }else {
            getActivity().onBackPressed();
        }
        return rootView;
    }

    public void setFragmentCallback(FragmentCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
    }

    public void setViewCallback(ViewCallback viewCallback) {
        this.viewCallback = viewCallback;
    }

    public View showMethodView(View view) {
        rootView.removeAllViews();
        rootView.addView(view);
        return view;
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

    public interface ViewCallback{
        void show();
    }

}
