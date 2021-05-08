package com.javalive09.demos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import org.joor.Reflect;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
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

    @Run
    public void ime(CodeActivity codeActivity) {
        codeActivity.startActivity(new Intent(codeActivity, APIActivity.class));
    }

    @Run
    public void toast(CodeActivity codeActivity) {
        Toast.makeText(codeActivity, "toast", Toast.LENGTH_LONG).show();

    }

    @Run
    public void systemuiLight(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.color_layout);
        View view = codeActivity.getWindow().getDecorView();
        codeActivity.getWindow().getDecorView().setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Run
    public void systemuiDark(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.color_layout);
        View view = codeActivity.getWindow().getDecorView();
        view.setSystemUiVisibility(view.getSystemUiVisibility());
    }

    @Run
    public void systemuiVisiable(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.color_layout);
        View view = codeActivity.getWindow().getDecorView();
        codeActivity.getWindow().getDecorView().setSystemUiVisibility(view.getSystemUiVisibility() | 0x00004000);
    }

    @Run
    public void systemuiHide(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.color_layout);
        View view = codeActivity.getWindow().getDecorView();
        codeActivity.getWindow().getDecorView().setSystemUiVisibility(view.getSystemUiVisibility() | ~0x00004000);
    }

    @Run
    public void showRemoteView(CodeActivity mContext) {
        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        String channel_id = "channel_fota";
        if (mNotificationManager != null) {
            String name = "fota";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(channel_id, name, importance);//生成channel
            // 2019/7/26 android P需要设置channel才能显示通知
            mNotificationManager.createNotificationChannel(channel);//添加channel
        }
        // 第一次进入取消进度通知栏，防止上次异常退出没关掉
        Notification mNotification = new Notification();
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.color_layout);
        rv.setImageViewResource(R.id.collapseActionView, R.drawable.bitmap);
        //必须要设置smallIcon
        mNotification = new Notification.Builder(mContext, channel_id)
                .setSmallIcon(R.drawable.ic_light)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(),
                        R.drawable.image_demo))
                .setContentTitle(mContext.getString(R.string.app_name))
                .setAutoCancel(true)
                .setCustomContentView(rv)
                .build();
        mNotification.extras = new Bundle();

//        mNotificationManager.cancelAll();
        int FOTA_FLAG = 1;
        mNotificationManager.notify(FOTA_FLAG, mNotification);

    }
    
    @Run
    public void tint_color_day_connect(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.tint_color);
        ImageView view = codeActivity.findViewById(R.id.image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setColorFilter(codeActivity.getColor(R.color.tint_color_day_connect), PorterDuff.Mode.SRC_IN);
        }
    }
    @Run
    public void tint_color_day_disconnect(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.tint_color);
        ImageView view = codeActivity.findViewById(R.id.image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setColorFilter(codeActivity.getColor(R.color.tint_color_day_disconnect), PorterDuff.Mode.SRC_IN);
        }
    }
    @Run
    public void tint_color_night_connect(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.tint_color);
        ImageView view = codeActivity.findViewById(R.id.image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setColorFilter(codeActivity.getColor(R.color.tint_color_night_connect), PorterDuff.Mode.SRC_IN);
        }
    }
    @Run
    public void tint_color_night_disconnect(CodeActivity codeActivity) {
        FrameLayout frameLayout = new FrameLayout(codeActivity) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                Log.i("peter", "onMeasure---------------");
            }
        };
        codeActivity.getLayoutInflater().inflate(R.layout.tint_color, frameLayout);
        codeActivity.setContentView(frameLayout);
        ImageView view = codeActivity.findViewById(R.id.image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setColorFilter(codeActivity.getColor(R.color.tint_color_night_disconnect), PorterDuff.Mode.SRC_IN);
        }
    }

    @Run
    public void Toast(CodeActivity codeActivity) {
        Toast.makeText(codeActivity, "toast", Toast.LENGTH_LONG).show();
    }

    @Run
    public void hideStatusBar(CodeActivity codeActivity) {
        codeActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        codeActivity.showText("hideStatusBar");


    }

    @Run
    public void showTempreture(CodeActivity codeActivity) {
        codeActivity.showText("12345" + "\n" + "50"+"\u2103");
    }

    @Run
    public void getVolumeList(CodeActivity codeActivity) {
        StorageManager storageManager = codeActivity.getSystemService(StorageManager.class);
        List<StorageVolume> list = Reflect.on(storageManager).call("getStorageVolumes").get();
        List<String> ps = new ArrayList<>();
        for (StorageVolume storageVolume : list) {
            String s = Reflect.on(storageVolume).call("getPath").get();
            ps.add(s);
        }
        codeActivity.showText(ps.toString());
    }

    private static boolean haveFlag(int flag, int mask) {
        return (flag&mask) != 0;
    }

    @Run
    public void getFlag(CodeActivity codeActivity) {
        int flag = 0x14000000;

        String text =  "Intent.FLAG_ACTIVITY_CLEAR_TASK= " + haveFlag(Intent.FLAG_ACTIVITY_CLEAR_TASK, flag) +
                "\nFLAG_ACTIVITY_BROUGHT_TO_FRONT= " + haveFlag(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT, flag) +
                "\nFLAG_ACTIVITY_CLEAR_TASK = " + haveFlag(Intent.FLAG_ACTIVITY_CLEAR_TASK, flag) +
                "\nFLAG_ACTIVITY_CLEAR_TOP =" + haveFlag(Intent.FLAG_ACTIVITY_CLEAR_TOP, flag) +
                "\nFLAG_ACTIVITY_NEW_TASK =" + haveFlag(Intent.FLAG_ACTIVITY_NEW_TASK, flag);

        codeActivity.showText(text);

    }

    @Run
    public void showDialog(CodeActivity codeActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(codeActivity).setTitle("alertDialog").setMessage("message");
        builder.show();
        codeActivity.showText("dialog");
    }

    @Run
    public void showPopUpWindow(CodeActivity codeActivity) {

        codeActivity.showText("bg");
        TextView textView = new TextView(codeActivity);
        textView.setText("popupWindow");
        textView.setBackgroundColor(Color.BLUE);
        codeActivity.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                PopupWindow popupWindow = new PopupWindow(codeActivity);
                popupWindow.setContentView(textView);
                popupWindow.showAtLocation(codeActivity.getWindow().getDecorView(), Gravity.NO_GRAVITY,100,100);
            }
        }, 2000);
    }

    public final static int FID_BEGIN = 1000;

    @Run
    public void getInteger(CodeActivity codeActivity) {
        codeActivity.showText("FID_BEGIN = " + getId("FID_BEGIN"));
    }


    private static int getId(String idName) {
        try {
            Field sidField = WTTest.class.getDeclaredField(idName);
            return sidField.getInt(WTTest.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Run
    public void testDisplay(CodeActivity codeActivity) {
        DisplayManager mDm = (DisplayManager) codeActivity.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDm.getDisplays();
        for(Display display : displays) {
            if(display.getDisplayId() != Display.DEFAULT_DISPLAY) {
                codeActivity.showText("" + display.getDisplayId());
                break;
            }
        }

    }

    @Run
    public void testNoHistory(CodeActivity c) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        ComponentName componentName = new ComponentName("com.wt.wt30demo", "com.wt.wt30demo.NoHistoryActivity");
        intent.setComponent(componentName);
        c.startActivity(intent);
    }

    @Run
    public void testStartLauncher(CodeActivity c) {
        Intent intent = new Intent().setComponent(new ComponentName("com.wt.launcher3_", "com.wt.launcher3_.MainActivity")).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }

    @Run(name = "peter22222222222222")
    public void run(CodeActivity activity) {
        Object o = new Object();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("peter", (String)o);
            }
        });
        activity.showText("111111");
    }

    @Run(name = "registerReceiver")
    public void run1(CodeActivity activity) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wt.test1");
        intentFilter.addAction("com.wt.test2");
        activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "com.wt.test1":
                        Intent i = new Intent("com.wt.peter");
                        activity.sendBroadcast(i);
                        break;
                    case "com.wt.test2":
                        i = new Intent("com.wt.peter1");
                        activity.sendOrderedBroadcast(i, null);
                        break;
                }
            }
        }, intentFilter);

        activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("peter", "registerReceiver>>>>>>>>>>>>>>>>>>>>>>");
            }
        }, new IntentFilter("com.wt.peter"));

        activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("peter", "registerReceiver1>>>>>>>>>>>>>>>>>>>>>>");
            }
        }, new IntentFilter("com.wt.peter1"));
    }
}
