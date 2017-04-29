package com.javalive09.codebag;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.javalive09.codebag.node.NodeItem;

/**
 * Created by peter on 16/9/21.
 */
public abstract class Entry {

    private TreeFragment treeFragment;
    private EntryTreeActivity activity = null;

    protected final Context getApplicationContext() {
        return activity.getApplicationContext();
    }

    protected final EntryTreeActivity getActivity() {
        return activity;
    }

    protected final void showTxt(String text) {
        showTxt(text, null);
    }

    protected final void showTxt(final String text, PlayFragment.FragmentCallback callback) {
        View view = showView(R.layout.dialog_code_textview, callback);
        TextView textView = (TextView) view;
        textView.setText(text);
    }

    protected final View showView(int resId) {
        return showView(resId, null);
    }

    protected final View showView(final int resId, PlayFragment.FragmentCallback callback) {
        final PlayFragment playFragment = new PlayFragment();
        playFragment.setFragmentCallback(callback);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view = inflater.inflate(resId, null, false);
        realShow(view, callback);
        return view;
    }

    protected final View showView(View view) {
        return showView(view, null);
    }

    protected final View showView(final View view, PlayFragment.FragmentCallback callback) {
        realShow(view, callback);
        return view;
    }

    private void realShow(final View view, PlayFragment.FragmentCallback callback) {
        final PlayFragment playFragment = new PlayFragment();
        playFragment.setFragmentCallback(callback);
        playFragment.setViewCallback(new PlayFragment.ViewCallback() {
            @Override
            public void show() {
                playFragment.showMethodView(view);
            }
        });
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
        ft.addToBackStack(null);
        ft.hide(treeFragment).add(R.id.content, playFragment).commit();
    }

}
