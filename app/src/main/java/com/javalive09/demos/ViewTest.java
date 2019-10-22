package com.javalive09.demos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;
import com.javalive09.demos.activity.PostActivity;
import com.javalive09.demos.view.AbcPicker;
import com.javalive09.demos.view.ArcView;
import com.javalive09.demos.view.NumPicker;
import com.javalive09.demos.view.SampleView;
import com.javalive09.demos.view.Xfermodes;
import com.javalive09.demos.view.Xfermodes_clip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * View launcher
 */
@Code(name = "View")
public class ViewTest {

    @Run
    public void showXferView(CodeActivity activity) {
        View view = new Xfermodes.SampleView(activity);
        activity.setContentView(view);
    }

    @Run
    public void showXferClipView(CodeActivity activity) {
        View view = new Xfermodes_clip.SampleView(activity);
        activity.setContentView(view);
    }

    @Run(name = "可自动缩放的连接线")
    public void showCustomLine(CodeActivity activity) {
        activity.setContentView(R.layout.custom_line);
    }

    @Run
    public void showArcView(CodeActivity activity) {
        activity.setContentView(new ArcView(activity));
    }

    @Run
    public void showArcSampleView(CodeActivity activity) {
        activity.setContentView(new SampleView(activity));
    }

    @Run(name = "显示超大地图")
    public void showSuperBigImage(CodeActivity activity) {
        WebView wv = new WebView(activity);
        wv.loadUrl("file:///android_asset/world.jpg");
        activity.setContentView(wv);
    }

    @Run(name = "多个GrideView嵌套")
    public void showMultiGrideView(CodeActivity activity) {
        activity.setContentView(R.layout.multigridview);
        GridView game = activity.findViewById(R.id.game);

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.card_danager_memory);//添加图像资源的ID
            map.put("ItemText", "NO." + String.valueOf(i));//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(activity, //没什么解释
                lstImageItem,//数据来源
                R.layout.grid_item,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[] {"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage, R.id.ItemText});

        game.setAdapter(saImageItems);

        GridView video = activity.findViewById(R.id.video);

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem2 = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.card_danager_memory);//添加图像资源的ID
            map.put("ItemText", "NO." + String.valueOf(i));//按序号做ItemText
            lstImageItem2.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems2 = new SimpleAdapter(activity, //没什么解释
                lstImageItem2,//数据来源
                R.layout.grid_item,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[] {"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage, R.id.ItemText});

        video.setAdapter(saImageItems2);

    }

    @Run(name = "ScaleType.CENTER\n按图片的原来size居中显示(不进行压缩，放大处理)，当图片长/宽超过View的长/宽，则截取图片的居中部分显示， 原图剪切效果")
    public void show_Big__ScaleType_CENTER(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_CENTER(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run(name = "ScaleType.CENTER_CROP\n按比例扩大图片的size居中显示(放大处理)，使得图片长 (宽)等于或大于View的长(宽), 原图放大填充效果")
    public void show_Big__ScaleType_CENTER_CROP(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_CENTER_CROP(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run(name = "ScaleType.CENTER_INSIDE\n将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长(宽)等于或小于View的长(宽)， 原图压缩效果")
    public void show_Big__ScaleType_CENTER_INSIDE(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_CENTER_INSIDE(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run(name = "ScaleType.FIT_CENTER 把图片按比例扩大(缩小)到View的宽度，居中显示， 原图安比例缩放，显示全部内容（不丢失内容信息）")
    public void show_Big__ScaleType_FIT_CENTER(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_FIT_CENTER(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run(name = "ScaleType.FIT_START\n把图片按比例扩大(缩小)到View的宽度，显示在View的上／左部分位置，原图安比例缩放，显示全部内容（不丢失内容信息）")
    public void show_Big__ScaleType_FIT_START(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.FIT_START);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_FIT_START(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_START);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run(name = "ScaleType.FIT_END\n把图片按比例扩大(缩小)到View的宽度，显示在View的下／右部分位置，原图安比例（填充view最小边）缩放，显示全部内容（不丢失内容信息)")
    public void show_Big__ScaleType_FIT_END(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_END);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_FIT_END(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_END);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run(name = "ScaleType.FIT_XY\n把图片按照指定的大小在View中显示，原图填充放大 ")
    public void show_Big__ScaleType_FIT_XY(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_FIT_XY(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        activity.setContentView(iv);
    }

    @Run(name = "ScaleType.MATRIX 用matrix来绘制")
    public void show_Big__ScaleType_MATRIX(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        iv.setBackgroundColor(Color.WHITE);
        activity.setContentView(iv);
    }

    @Run
    public void show_small__ScaleType_MATRIX(CodeActivity activity) {
        ImageView iv = new ImageView(activity);
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        activity.setContentView(iv);
    }

    @Run
    public void showEmojiX(CodeActivity activity) {
        activity.showText(activity.getString(R.string.x));
    }

    @Run
    public void showEmojiY(CodeActivity activity) {
        activity.showText(activity.getString(R.string.v));
    }

    @Run
    public void showShader_r0(CodeActivity activity) {
        TextView tv = new TextView(activity);
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0f, 10, 10, Color.BLUE);//radius 是羽化半径
        tv.setGravity(Gravity.CENTER);
        activity.setContentView(tv);
    }

    @Run
    public void showShader_r0_5(CodeActivity activity) {
        TextView tv = new TextView(activity);
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0.5f, 0, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        activity.setContentView(tv);
    }

    @Run
    public void showShader_r0_1(CodeActivity activity) {
        TextView tv = new TextView(activity);
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0.1f, 10, 0, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        activity.setContentView(tv);
    }

    @Run
    public void showShader_r1(CodeActivity activity) {
        TextView tv = new TextView(activity);
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(1, 10, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        activity.setContentView(tv);
    }

    @Run
    public void showShader_r5(CodeActivity activity) {
        TextView tv = new TextView(activity);
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(5, 10, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        activity.setContentView(tv);
    }

    @Run(name = "时间选择器")
    public void showNumPicker(CodeActivity activity) {
        activity.setContentView(R.layout.time_picker_layout);
        NumPicker picker = activity.findViewById(R.id.picker1);
        NumPicker picker2 = activity.findViewById(R.id.picker2);
        picker.setMaxValue(60).setShowCount(5).setTextSize(50).setLoop(true).setDefaultItemNum(15).build();
        picker2.setMaxValue(24).setShowCount(5).setLoop(false).setTextSize(50).setDefaultItemNum(15).build();
    }

    @Run(name = "ABC选择器")
    public void showABCPicker(CodeActivity activity) {
        activity.setContentView(R.layout.abc_picker_layout);
        AbcPicker picker = activity.findViewById(R.id.picker);
        picker.setShowCount(5).setTextSize(50).setDefaultItemNum('G').build();
    }

    @Run(name = "轮子时间选择器")
    public void showWheelPicker(CodeActivity activity) {
        activity.setContentView(R.layout.wheelpicker);
        WheelPicker wheelPicker = activity.findViewById(R.id.main_wheel_left);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 61; i++) {
            data.add(String.format(Locale.getDefault(), "%03d", i));
        }
        wheelPicker.setData(data);
    }

    @Run(name = "使用action打开activity")
    public void startActivity(CodeActivity activity) {
        final Intent intent = new Intent("duershow.settings.OTA");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Run(name = "从哪个文件包取资源")
    public void getFolderName(CodeActivity activity) {
        activity.showText(activity.getResources().getString(R.string.who_are_you));
    }

    @Run(name = "action启动setting bluetooth")
    public void launchSetting(CodeActivity activity) {
        Intent intent = new Intent("duershow.settings.BLUETOOTH_SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Run(name = "启动setting主页")
    public void launchSetting2(CodeActivity activity) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Run(name = "启动duer os setting主页")
    public void launchSetting3(CodeActivity activity) {
        Intent intent = new Intent("duershow.settings.SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Run(name = "显示矢量图")
    public void showVector(CodeActivity activity) {
        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.drawable.ic_light);
        activity.setContentView(imageView);
    }

    @Run(name = "获取勿扰模式")
    public void showZenMode(CodeActivity activity) {
        String ZEN_MODE = "zen_mode";
        //        SettingsTest.Global.getInt(mContext.getContentResolver(), SettingsTest.Global.ZEN_MODE, 0);
        int result = Settings.Global.getInt(activity.getContentResolver(), ZEN_MODE, 0);
        activity.showText("result =" + result);

    }

    @Run(name = "显示阴影")
    public void showElevation(CodeActivity activity) {
        //        ImageView imageView = new ImageView(CodeActivity.context());
        //        imageView.setImageResource(R.drawable.ic_stop_carmea);
        //        imageView.setElevation(6.0f);
        activity.setContentView(R.layout.elevator);
    }

    @Run(name = "============")
    public void showContentProviderCall(CodeActivity activity) {
        String SETTINGS_URI = "content://com.baidu.duer.duershowsettings.provider";
        String RESET = "reset";
        String RESULT = "result";
        Bundle bundle = activity.getContentResolver().call(
                Uri.parse(SETTINGS_URI), RESET, null, null);
        boolean result = bundle.getBoolean(RESULT);
        activity.showText("result = " + result);

    }

    @Run(name = "action open baidu webview")
    public void showWebview(CodeActivity activity) {
        Intent intent = new Intent("com.baidu.duer.action.OPEN_WEBVIEW");
        intent.putExtra(":settings:show_fragment_url", "https://www.baidu.com/");
        intent.putExtra(":settings:show_fragment_title", "百度");
        activity.startActivity(intent);

    }

    @Run(name = "测试亮屏")
    public void testLock(CodeActivity activity) {
        activity.showText("亮屏");
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            PowerManager pManager = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock mWakeLock = pManager.newWakeLock(PowerManager.FULL_WAKE_LOCK
                    | PowerManager.ACQUIRE_CAUSES_WAKEUP, "DND");
            mWakeLock.acquire();
            Log.e("peter", "acquire");
        }, 100 * 1000);

    }

    @Run(name = "setting息屏")
    public void testCloseScreen(CodeActivity activity) {
        try {
            activity.getContentResolver()
                    .call(Uri.parse("content://com.baidu.duer.duershowsettings.provider"), "closeScreen", null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run(name = "setting亮屏")
    public void testOpenScreen(CodeActivity activity) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            try {
                activity.getContentResolver()
                        .call(Uri.parse("content://com.baidu.duer.duershowsettings.provider"), "openScreen", null,
                                null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 30 * 1000);
    }

    @Run(name = "setting reset")
    public void testSetting(CodeActivity activity) {
        activity.getContentResolver().call(Uri.parse("content://com.baidu.duer.duershowsettings.provider"),
                "reset", null, null);
    }

    @Run(name = "打开video")
    public void testOpenVideo(CodeActivity activity) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        ComponentName componentName =
                new ComponentName("android.rk.RockVideoPlayer", "android.rk.RockVideoPlayer.RockVideoPlayer");
        intent.setComponent(componentName);
        if (isCallable(intent, activity)) {
            activity.startActivity(intent);
        }
    }

    private boolean isCallable(Intent intent, CodeActivity activity) {
        List<ResolveInfo> list =
                activity.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @Run(name = "获取Setting亮度")
    public void testSettingProvider(CodeActivity activity) {
        try {
            Bundle bundle = activity.getContentResolver()
                    .call(Uri.parse("content://com.baidu.duer.duershowsettings.provider"), "getBrightness", null, null);
            if (bundle != null) {
                long brightness = bundle.getLong("brightness", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run(name = "last-userActivityTime")
    public void testGetLastUserActivity(CodeActivity activity) {
        PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
    }

    @Run(name = "snack dialog")
    public void testsnackDialog(CodeActivity activity) {

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
                window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);//
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

    @Run(name = "test provider")
    public void testProvider(CodeActivity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bundle bundle = activity.getContentResolver()
                            .call(Uri.parse("content://com.peter.provider"), "closeScreen", null, null);
                    if (bundle != null) {
                        long brightness = bundle.getInt("test", 0);
                        Log.i("peter", "brightness:" + brightness);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Run(name = "测试ettingGlobal")
    public void testSettingGlobal(CodeActivity activity) {
        int str = Settings.Global.getInt(activity.getContentResolver(), "screenshot_enable", -9);
        activity.showText(str + "");
    }

    @Run(name = "测试获取ettingGlobal")
    public void testgetSettingGlobal(CodeActivity activity) {
        try {
            Settings.Global.putInt(activity.getContentResolver(), "screenshot_enable", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Run
    public void isAudioPlay(CodeActivity activity) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            try {

                final AudioManager am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
                boolean active = am.isMusicActive();
                Log.i("peter", "active=" + active);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 10 * 1000);
    }

    @Run
    public void systemUpTime(CodeActivity activity) {
        String str = SystemClock.uptimeMillis() + "";
        activity.showText(str);
    }

    @Run(name = "app info list")
    public void getInfo(CodeActivity activity) {
        activity.showText(getAppInfo(activity));
    }

    private String getAppInfo(CodeActivity activity) {
        @SuppressLint("WrongConstant")
        List<ApplicationInfo> apps = activity.getPackageManager().getInstalledApplications(
                PackageManager.GET_SIGNATURES);

        String content = "";
        for (ApplicationInfo info : apps) {
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                // 非系统应用
                content += "name=" + info.loadLabel(activity.getPackageManager()).toString() + " path=" + info
                        .sourceDir + "\n";
            }
        }

        return content;
    }

    @Run(name = "获取整分钟")
    public void getMinTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.SECOND, 0);
        long currentTime = calendar.getTimeInMillis();
        Log.d("peter", "currentTime = " + currentTime);
    }

    @Run
    public void writeSystemSettings(CodeActivity codeActivity) {
        boolean result = Settings.System.putString(codeActivity.getContentResolver(), "notification_sound", "peter"
                + ".txt");
        codeActivity.showText("result =" + result);
    }

    @Run
    public void readSystemSettings(CodeActivity codeActivity) {
        String result = Settings.System.getString(codeActivity.getContentResolver(), "notification_sound");
        codeActivity.showText("result =" + result);
    }

    @Run
    public void writeGlobalSettings(CodeActivity codeActivity) {
        boolean result = Settings.Global.putString(codeActivity.getContentResolver(), "desk_dock_sound", "123.txt");
        codeActivity.showText("result =" + result);
    }

    @Run
    public void readGlobalSettings(CodeActivity codeActivity) {
        String result = Settings.Global.getString(codeActivity.getContentResolver(), "desk_dock_sound");
        codeActivity.showText("result =" + result);
    }

    @Run
    public void writeSecureSettings(CodeActivity codeActivity) {
        boolean result = Settings.Secure.putString(codeActivity.getContentResolver(), "default_input_method", "peter");
        codeActivity.showText("result =" + result);
    }

    @Run
    public void readSecureSettings(CodeActivity codeActivity) {
        String result = Settings.Secure.getString(codeActivity.getContentResolver(), "default_input_method");
        codeActivity.showText("result =" + result);
    }


    @Run
    public void settingsRing(CodeActivity codeActivity) {
        codeActivity.startActivity(new Intent("duershow.settings.RING_TONE_PICKER"));
    }

    @Run
    public void smallestScreenWidthDp(CodeActivity codeActivity) {
        Configuration config = codeActivity.getResources().getConfiguration();
        int smallestScreenWidth = config.smallestScreenWidthDp;
        codeActivity.showText(smallestScreenWidth + "");
    }


    @Run
    public void showShape(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.shape_1_2);
    }

    @Run
    public void getAndroiodScreenProperty(CodeActivity codeActivity) {
        int sw = codeActivity.getResources().getConfiguration().smallestScreenWidthDp;
        codeActivity.showText("sw = " + sw);
    }

    @Run
    public void actionWebView(CodeActivity codeActivity) {
        Intent intent = new Intent("com.baidu.duer.action.CUSTOMER_WEB_VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("USE_HTML_TITLE", true);
        intent.putExtra("EXTRA_URL", "https://xiaodu.baidu.com/saiya/html/helpVideo.html");
        intent.putExtra("EXTRA_SHOW_TOOLBAR", true);
        codeActivity.startActivity(intent);
    }

    @Run
    public void edittext(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.edit);
    }

    @Run
    public void showDialogActivity(CodeActivity codeActivity) {
        TextView textView = new TextView(codeActivity);
        textView.setText("dddddddddd");
        codeActivity.setContentView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeActivity.startActivity(new Intent(codeActivity, APIActivity.class));
            }
        });
    }

    @Run
    public void showDialog(CodeActivity codeActivity) {
        TextView textView = new TextView(codeActivity);
        textView.setText("dddddddddd");
        codeActivity.setContentView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(codeActivity).setTitle("peter").setMessage("test").show();
            }
        });
    }

    private static Toast toast;
    @Run
    public void touchDelegate(CodeActivity codeActivity) {
        FrameLayout frameLayout = new FrameLayout(codeActivity);

        ImageView view = new ImageView(codeActivity);
        view.setImageResource(R.drawable.card_danager_memory);

        frameLayout.post(new Runnable() {
            @Override
            public void run() {
                frameLayout.setTouchDelegate(new TouchDelegate(new Rect(0, 0, 250, 250), view));
            }
        });

        frameLayout.addView(view, new FrameLayout.LayoutParams(100, 100));
        frameLayout.setBackgroundColor(Color.GREEN);
        codeActivity.setContentView(frameLayout);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(codeActivity, "click", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null) {
                    toast.cancel();
                }
                toast = Toast.makeText(codeActivity, "frame layout click", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Run
    public void startService(CodeActivity codeActivity) {
        codeActivity.startService(new Intent(codeActivity, MyService.class));
        codeActivity.showText("startService");
    }

    @Run
    public void bindService(CodeActivity codeActivity) {
        codeActivity.bindService(new Intent(codeActivity, MyService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    private static boolean click = false;

    @Run
    public void actionCancel(CodeActivity codeActivity) {

        click = false;
        FrameLayout frameLayout = new FrameLayout(codeActivity) {
            @Override
            public boolean onInterceptTouchEvent(MotionEvent ev) {
                Log.i("peter", "conInterceptTouchEvent:" + ev.toString());

                if(click) {
                    return true;
                }
                return super.onInterceptTouchEvent(ev);
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                Log.i("peter", "click:" + click + "father:" + event.toString());
                return true;
            }
        };

        ImageView imageView = new ImageView(codeActivity) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    click = false;
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            click = true;
                        }
                    }, 5000);
                }

                if(event.getAction() == MotionEvent.ACTION_CANCEL) {
                    Log.i("peter", "click:" + click + "child:" + event.toString());
                }

                Log.i("peter", "click:" + click + "child:" + event.toString());
                return true;
            }
        };
        imageView.setImageResource(R.drawable.card_danager_memory);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        frameLayout.addView(imageView, new FrameLayout.LayoutParams(100, 100));
        codeActivity.setContentView(frameLayout, new FrameLayout.LayoutParams(900, 900));

    }

    @Run
    public void dispatchTouchEvent(CodeActivity activity) {
        FrameLayout frameLayout = new FrameLayout(activity) {

        };

        ImageView imageView = new ImageView(activity) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            frameLayout.removeAllViews();
                        }
                    }, 5_000);
                }


                Log.i("peter", "onTouchEvent" + event.getAction());
                if(event.getAction() == MotionEvent.ACTION_CANCEL) {
                    Log.i("peter", "onTouchEvent" + event.getAction());
                }
                return true;
            }
        };

        imageView.setImageResource(R.drawable.card_danager_memory);

        frameLayout.setBackgroundColor(Color.GREEN);


        activity.setContentView(frameLayout, new FrameLayout.LayoutParams(900, 900));

    }

    @Run
    public void postActivity(CodeActivity codeActivity) {
        codeActivity.startActivity(new Intent(codeActivity, PostActivity.class));
    }

    @Run
    public void idleHandler(CodeActivity codeActivity) {
        HandlerThread handlerThread = new HandlerThread("idle");
        handlerThread.start();
        Handler h = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("peter", "handler//// = " + msg.what);
            }
        };
        h.sendEmptyMessage(3);
        h.sendEmptyMessage(4);
        h.sendEmptyMessage(5);

        MessageQueue queue = handlerThread.getLooper().getQueue();
        queue.addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.i("peter", "idle");
                return true;
            }
        });

        TextView textView = new TextView(codeActivity);
        textView.setText("ddddddddddddddd");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.sendEmptyMessage(3);
                h.sendEmptyMessage(4);
                h.sendEmptyMessage(5);
            }
        });
        codeActivity.setContentView(textView);
    }

    @Run
    public void measureTest(CodeActivity activity) {
        FrameLayout frameLayout = new FrameLayout(activity) {

            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }

        };

        ImageView imageView = new ImageView(activity);

        imageView.setImageResource(R.drawable.card_danager_memory);

        frameLayout.setBackgroundColor(Color.GRAY);

        frameLayout.addView(imageView, new FrameLayout.LayoutParams(100, 100));

        activity.setContentView(frameLayout, new FrameLayout.LayoutParams(900, 900));

    }

}