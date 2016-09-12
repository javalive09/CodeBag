package com.codebag.bag;

import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.codebag.R;

public class Entry {

    private MainActivity mActivity = null;

    protected MainActivity getActivity() {
        return mActivity;
    }

    protected void showTxt(String text) {
        AlertDialog dialog = showView(R.layout.dialog_code_textview);
        TextView textView = (TextView) dialog.findViewById(R.id.code_text);
        textView.setText(text);
    }

    protected AlertDialog showView(int resId) {
        AlertDialog dialog = new AlertDialog.Builder(mActivity, R.style.show_view_dialog).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setContentView(resId);
        return dialog;
    }

    protected AlertDialog showView(View view) {
        AlertDialog dialog = new AlertDialog.Builder(mActivity, R.style.show_view_dialog).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.setContentView(view);
        return dialog;
    }

}
