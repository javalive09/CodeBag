package com.javalive09.demos;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.javalive09.codebag.CodeBag;
import com.javalive09.annotation.Test;
import com.javalive09.annotation.Tester;
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
@Tester(name = "View")
public class ViewLauncher {

    @Test
    public void showXferView() {
        View view = new Xfermodes.SampleView(CodeBag.context());
        CodeBag.showView(view);
    }

    @Test
    public void showXferClipView() {
        View view = new Xfermodes_clip.SampleView(CodeBag.context());
        CodeBag.showView(view);
    }

    @Test(name = "可自动缩放的连接线")
    public void showCustomLine() {
        CodeBag.showView(R.layout.custom_line);
    }

    @Test
    public void showArcView() {
        CodeBag.showView(new ArcView(CodeBag.context()));
    }

    @Test
    public void showArcSampleView() {
        CodeBag.showView(new SampleView(CodeBag.context()));
    }

    @Test(name = "显示超大地图")
    public void showSuperBigImage() {
        WebView wv = new WebView(CodeBag.context());
        wv.loadUrl("file:///android_asset/world.jpg");
        CodeBag.showView(wv);
    }

    @Test(name = "多个GrideView嵌套")
    public void showMultiGrideView() {
        View view = CodeBag.showView(R.layout.multigridview);
        GridView game = view.findViewById(R.id.game);

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.card_danager_memory);//添加图像资源的ID
            map.put("ItemText", "NO." + String.valueOf(i));//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(CodeBag.context(), //没什么解释
                lstImageItem,//数据来源
                R.layout.grid_item,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[] {"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage, R.id.ItemText});

        game.setAdapter(saImageItems);

        GridView video = view.findViewById(R.id.video);

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem2 = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.card_danager_memory);//添加图像资源的ID
            map.put("ItemText", "NO." + String.valueOf(i));//按序号做ItemText
            lstImageItem2.add(map);
        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems2 = new SimpleAdapter(CodeBag.context(), //没什么解释
                lstImageItem2,//数据来源
                R.layout.grid_item,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[] {"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.ItemImage, R.id.ItemText});

        video.setAdapter(saImageItems2);

    }

    @Test(name = "ScaleType.CENTER\n按图片的原来size居中显示(不进行压缩，放大处理)，当图片长/宽超过View的长/宽，则截取图片的居中部分显示， 原图剪切效果")
    public void show_Big__ScaleType_CENTER() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_CENTER() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test(name = "ScaleType.CENTER_CROP\n按比例扩大图片的size居中显示(放大处理)，使得图片长 (宽)等于或大于View的长(宽), 原图放大填充效果")
    public void show_Big__ScaleType_CENTER_CROP() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_CENTER_CROP() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test(name = "ScaleType.CENTER_INSIDE\n将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长(宽)等于或小于View的长(宽)， 原图压缩效果")
    public void show_Big__ScaleType_CENTER_INSIDE() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_CENTER_INSIDE() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test(name = "ScaleType.FIT_CENTER 把图片按比例扩大(缩小)到View的宽度，居中显示， 原图安比例缩放，显示全部内容（不丢失内容信息）")
    public void show_Big__ScaleType_FIT_CENTER() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_FIT_CENTER() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test(name = "ScaleType.FIT_START\n把图片按比例扩大(缩小)到View的宽度，显示在View的上／左部分位置，原图安比例缩放，显示全部内容（不丢失内容信息）")
    public void show_Big__ScaleType_FIT_START() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.FIT_START);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_FIT_START() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_START);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test(name = "ScaleType.FIT_END\n把图片按比例扩大(缩小)到View的宽度，显示在View的下／右部分位置，原图安比例（填充view最小边）缩放，显示全部内容（不丢失内容信息)")
    public void show_Big__ScaleType_FIT_END() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_END);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_FIT_END() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_END);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test(name = "ScaleType.FIT_XY\n把图片按照指定的大小在View中显示，原图填充放大 ")
    public void show_Big__ScaleType_FIT_XY() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_FIT_XY() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        CodeBag.showView(iv);
    }

    @Test(name = "ScaleType.MATRIX 用matrix来绘制")
    public void show_Big__ScaleType_MATRIX() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        iv.setBackgroundColor(Color.WHITE);
        CodeBag.showView(iv);
    }

    @Test
    public void show_small__ScaleType_MATRIX() {
        ImageView iv = new ImageView(CodeBag.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        CodeBag.showView(iv);
    }

    @Test
    public void showEmojiX() {
        CodeBag.showText(R.string.x);
    }

    @Test
    public void showEmojiY() {
        CodeBag.showText(R.string.v);
    }

    @Test
    public void showShader_r0() {
        TextView tv = new TextView(CodeBag.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0f, 10, 10, Color.BLUE);//radius 是羽化半径
        tv.setGravity(Gravity.CENTER);
        CodeBag.showView(tv);
    }

    @Test
    public void showShader_r0_5() {
        TextView tv = new TextView(CodeBag.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0.5f, 0, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        CodeBag.showView(tv);
    }

    @Test
    public void showShader_r0_1() {
        TextView tv = new TextView(CodeBag.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0.1f, 10, 0, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        CodeBag.showView(tv);
    }

    @Test
    public void showShader_r1() {
        TextView tv = new TextView(CodeBag.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(1, 10, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        CodeBag.showView(tv);
    }

    @Test
    public void showShader_r5() {
        TextView tv = new TextView(CodeBag.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(5, 10, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        CodeBag.showView(tv);
    }

    @Test(name = "时间选择器")
    public void showNumPicker() {
        View root = CodeBag.showView(R.layout.time_picker_layout);
        NumPicker picker = root.findViewById(R.id.picker);
        NumPicker.Adapter adapter = new NumPicker.Adapter(CodeBag.context());
        picker.setAdapter(adapter);

        picker.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    int value = adapter.getValue();
                    CodeBag.toastShort(value + "");
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }

    @Test(name = "轮子时间选择器")
    public void showWheelPicker() {
        View root = CodeBag.showView(R.layout.wheelpicker);
        WheelPicker wheelPicker = root.findViewById(R.id.main_wheel_left);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 61; i++) {
            data.add(String.format(Locale.getDefault(), "%02d", i));
        }
        wheelPicker.setData(data);
        wheelPicker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                //                CodeBag.toastShort(String.valueOf(data));
                CodeBag.toastShort(Integer.valueOf(String.valueOf(data)) + "");
            }
        });
    }

    @Test(name = "使用action打开activity")
    public void startActivity() {
        final Intent intent = new Intent("duershow.settings.OTA");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CodeBag.context().startActivity(intent);
    }

    @Test(name = "从哪个文件包取资源")
    public void getFolderName() {
        CodeBag.showText(CodeBag.context().getResources().getString(R.string.who_are_you));
    }

    @Test(name = "action启动setting bluetooth")
    public void launchSetting() {
        Intent intent = new Intent("duershow.settings.BLUETOOTH_SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CodeBag.context().startActivity(intent);

    }

    @Test(name = "启动setting主页")
    public void launchSetting2() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CodeBag.context().startActivity(intent);
    }

    @Test(name = "启动duer os setting主页")
    public void launchSetting3() {
        Intent intent = new Intent("duershow.settings.SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CodeBag.context().startActivity(intent);

    }

    @Test(name = "显示矢量图")
    public void showVector() {
        ImageView imageView = new ImageView(CodeBag.context());
        imageView.setImageResource(R.drawable.ic_light);
        CodeBag.showView(imageView);
    }

    @Test(name = "获取勿扰模式")
    public void showZenMode() {
        String ZEN_MODE = "zen_mode";
        //        Settings.Global.getInt(mContext.getContentResolver(), Settings.Global.ZEN_MODE, 0);
        int result = Settings.Global.getInt(CodeBag.context().getContentResolver(), ZEN_MODE, 0);
        CodeBag.showText("result =" + result);

    }

    @Test(name = "显示阴影")
    public void showElevation() {
        //        ImageView imageView = new ImageView(CodeBag.context());
        //        imageView.setImageResource(R.drawable.ic_stop_carmea);
        //        imageView.setElevation(6.0f);
        CodeBag.showView(R.layout.elevator);
    }

}