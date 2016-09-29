package com.javalive09.codebag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

/**
 *
 * Created by peter on 16/9/21.
 *
 */
public class Entry {

    private CodeBagActivity mActivity = null;

    public CodeBagActivity getActivity() {
        return mActivity;
    }

    public void showTxt(String text) {
        showTxt(text, null, true);
    }

    public void showTxt(String text, DialogInterface.OnDismissListener listener, boolean touchOutsideCancel) {
        View view = showView(R.layout.dialog_code_textview, listener, touchOutsideCancel);
        TextView textView = (TextView) view.findViewById(R.id.code_text);
        textView.setText(text);
    }

    public View showView(int resId) {
        return showView(resId, null, true);
    }

    public View showView(View view) {
        return showView(view, null, true);
    }

    public View showView(int resId, DialogInterface.OnDismissListener listener, boolean touchOutsideCancel) {
        View view = View.inflate(mActivity, resId, null);
        showView(view, listener, touchOutsideCancel);
        return view;
    }

    public View showView(View view, DialogInterface.OnDismissListener listener, boolean touchOutsideCancel) {
        AlertDialog dialog = new AlertDialog.Builder(mActivity, R.style.show_view_dialog).create();
        dialog.setCanceledOnTouchOutside(touchOutsideCancel);
        dialog.show();
        dialog.setContentView(view);
        if(listener != null) {
            dialog.setOnDismissListener(listener);
        }
        return view;
    }

    protected void startActivity(Intent intent) {
        mActivity.startActivity(intent);
    }

    protected void startService(Intent intent) {
        mActivity.startService(intent);
    }

}
