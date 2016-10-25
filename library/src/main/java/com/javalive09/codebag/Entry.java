package com.javalive09.codebag;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 *
 * Created by peter on 16/9/21.
 *
 */
public class Entry {

    private ShowViewActivity mActivity = null;

    private Entry() {}

    public ShowViewActivity getViewActivity() {
        return mActivity;
    }

    public Context getApplicationContext() {
        return mActivity.getApplicationContext();
    }

    public void showTxt(String text) {
        showTxt(text, null);
    }

    public void showTxt(String text, ShowViewActivity.ActivityCallback mActivityCallback) {
        showView(R.layout.dialog_code_textview, mActivityCallback);
        TextView textView = (TextView)findViewById(R.id.code_text);
        textView.setText(text);
    }

    public View showView(int resId) {
        return showView(resId, null);
    }

    public View showView(int resId, ShowViewActivity.ActivityCallback mActivityCallback) {
        mActivity.setmActivityCallback(mActivityCallback);
        return mActivity.showMethodView(resId);
    }

    public View showView(View view){
        return showView(view, null);
    }

    public View showView(View view, ShowViewActivity.ActivityCallback mActivityCallback) {
        mActivity.setmActivityCallback(mActivityCallback);
        return mActivity.showMethodView(view);
    }

    public View findViewById(int id) {
        return mActivity.findViewById(id);
    }


}
