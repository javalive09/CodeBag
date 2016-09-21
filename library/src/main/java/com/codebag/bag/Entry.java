package com.codebag.bag;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;


public class Entry {

    private CodeBagActivity mActivity = null;

    protected CodeBagActivity getActivity() {
        return mActivity;
    }

    protected void showTxt(String text) {
        View view = showView(R.layout.dialog_code_textview, null, true);
        TextView textView = (TextView) view.findViewById(R.id.code_text);
        textView.setText(text);
    }

    protected View showView(int resId, DialogInterface.OnDismissListener listener, boolean touchOutside) {
        View view = View.inflate(mActivity, resId, null);
        showView(view, listener, touchOutside);
        return view;
    }

    protected View showView(View view, DialogInterface.OnDismissListener listener, boolean touchOutside) {
        AlertDialog dialog = new AlertDialog.Builder(mActivity, R.style.show_view_dialog).create();
        dialog.setCanceledOnTouchOutside(touchOutside);
        dialog.show();
        dialog.setContentView(view);
        if(listener != null) {
            dialog.setOnDismissListener(listener);
        }
        return view;
    }

}
