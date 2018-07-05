package com.javalive09.demos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;
import com.javalive09.demos.view.ArcView;
import com.javalive09.demos.view.NumPicker;
import com.javalive09.demos.view.SampleView;
import com.javalive09.demos.view.Xfermodes;
import com.javalive09.demos.view.Xfermodes_clip;

import java.util.ArrayList;
import java.util.Arrays;
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
        activity.showText(R.string.x);
    }

    @Run
    public void showEmojiY(CodeActivity activity) {
        activity.showText(R.string.v);
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
        picker.setMaxValue(60).setShowCount(5).setTextSize(50).setLoop(true).build();
        picker2.setMaxValue(24).setShowCount(5).setLoop(true).setTextSize(50).build();
    }

    @Run(name = "轮子时间选择器")
    public void showWheelPicker(CodeActivity activity) {
        activity.setContentView(R.layout.wheelpicker);
        WheelPicker wheelPicker = activity.findViewById(R.id.main_wheel_left);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 61; i++) {
            data.add(String.format(Locale.getDefault(), "%02d", i));
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
        //        Settings.Global.getInt(mContext.getContentResolver(), Settings.Global.ZEN_MODE, 0);
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

}