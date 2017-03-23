package com.javalive09.codebag;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by peter on 16/9/21.
 */
public abstract class Entry {

    private PlayFragment fragment = null;
    private EntryTreeActivity activity = null;

    protected final PlayFragment getFragment() {
        return fragment;
    }

    protected final Context getApplicationContext() {
        return activity.getApplicationContext();
    }

    protected final EntryTreeActivity getActivity() {
        return activity;
    }

    protected final void showTxt(String text) {
        showTxt(text, null);
    }

    protected final void showTxt(String text, PlayFragment.FragmentCallback callback) {
        if (fragment == null) {
            throw new RuntimeException("can not invoke this method in constructor!");
        } else {
            View rootView = showView(R.layout.dialog_code_textview, callback);
            TextView textView = (TextView) rootView.findViewById(R.id.code_text);
            textView.setText(text);
        }
    }

    protected final View showView(int resId) {
        return showView(resId, null);
    }

    protected final View showView(int resId, PlayFragment.FragmentCallback callback) {
        if (fragment == null) {
            throw new RuntimeException("can not invoke this method in constructor!");
        } else {
            fragment.setFragmentCallback(callback);
            return fragment.showMethodView(resId);
        }
    }

    protected final View showView(View view) {
        return showView(view, null);
    }

    protected final View showView(View view, PlayFragment.FragmentCallback callback) {
        if (fragment == null) {
            throw new RuntimeException("can not invoke this method in constructor!");
        } else {
            fragment.setFragmentCallback(callback);
            return fragment.showMethodView(view);
        }
    }

    protected final View findViewById(int id) {
        return fragment.findViewById(id);
    }


}
