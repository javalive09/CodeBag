package com.javalive09.codebag;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 *
 * Created by peter on 16/9/21.
 *
 */
public abstract class Entry {

    private DetailActivity mActivity = null;

    protected final DetailActivity getActivity() {
        return mActivity;
    }

    protected final Context getApplicationContext() {
        return mActivity.getApplicationContext();
    }

    protected final void showTxt(String text) {
        showTxt(text, null);
    }

    protected final void showTxt(String text, DetailActivity.ActivityCallback mActivityCallback) {
        showView(R.layout.dialog_code_textview, mActivityCallback);
        TextView textView = (TextView)findViewById(R.id.code_text);
        textView.setText(text);
    }

    protected final View showView(int resId) {
        return showView(resId, null);
    }

    protected final View showView(int resId, DetailActivity.ActivityCallback mActivityCallback) {
        if(mActivity == null) {
            throw new RuntimeException("can not invoke this method in constructor!");
        }else {
            mActivity.setmActivityCallback(mActivityCallback);
            return mActivity.showMethodView(resId);
        }
    }

    protected final View showView(View view){
        return showView(view, null);
    }

    protected final View showView(View view, DetailActivity.ActivityCallback mActivityCallback) {
        if(mActivity == null) {
            throw new RuntimeException("can not invoke this method in constructor!");
        }else {
            mActivity.setmActivityCallback(mActivityCallback);
            return mActivity.showMethodView(view);
        }
    }

    protected final View findViewById(int id) {
        return mActivity.findViewById(id);
    }


}
