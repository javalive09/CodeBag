package com.javalive09.demos;

import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by zhangrui on 20-6-6
 */
public class WTTest {

    @Run
    public void dayNightMode(CodeActivity codeActivity) {
        int currentNightMode = codeActivity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        UI.show(codeActivity,"dayNightMode ="  +currentNightMode);
    }

    @Run
    public void uiMode(CodeActivity codeActivity) {
        UiModeManager mUiModeManager = (UiModeManager)codeActivity.getSystemService(Context.UI_MODE_SERVICE);
        UI.show(codeActivity, "currentMode=" + mUiModeManager.getCurrentModeType(),
                "currentNightMode=" + mUiModeManager.getNightMode(),
                "noNightMode=" + UiModeManager.MODE_NIGHT_NO,
                "autoNightMode="+UiModeManager.MODE_NIGHT_AUTO,
                "yesNightMode="+UiModeManager.MODE_NIGHT_YES);

    }

    @Run
    public void setNightMode(CodeActivity codeActivity) {
        UiModeManager mUiModeManager = (UiModeManager)codeActivity.getSystemService(Context.UI_MODE_SERVICE);
        mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
        UI.show(codeActivity, "getNightMode=" + mUiModeManager.getNightMode());
    }
    @Run
    public void setNoNightMode(CodeActivity codeActivity) {
        UiModeManager mUiModeManager = (UiModeManager)codeActivity.getSystemService(Context.UI_MODE_SERVICE);
        mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
        UI.show(codeActivity, "getNightMode=" + mUiModeManager.getNightMode());
    }

    @Run
    public void notification(CodeActivity codeActivity) {
        codeActivity.startService(new Intent(codeActivity, MyService.class));
        codeActivity.showText("startService");
    }


    @Run
    public void systemDialog(CodeActivity activity) {

        if (!Settings.canDrawOverlays(activity)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + activity.getPackageName()));
            activity.startActivityForResult(intent, 10);
        } else {
            final Dialog mDialog = new Dialog(activity.getApplicationContext(), R.style.dialog);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(R.layout.dialog);
            Window window = mDialog.getWindow();
            if (window != null) {
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.AnimDialog);
                window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                window.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                window.addFlags(WindowManager.LayoutParams.FLAG_SPLIT_TOUCH);
                WindowManager manager = window.getWindowManager();
                DisplayMetrics dm = new DisplayMetrics();
                manager.getDefaultDisplay().getMetrics(dm);
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = dm.widthPixels;
                window.setAttributes(params);
                ((TextView) (mDialog.findViewById(R.id.title))).setText("设备将在180秒后自动进行升级");
                mDialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });

                mDialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
            }
            mDialog.show();
        }
    }
}
