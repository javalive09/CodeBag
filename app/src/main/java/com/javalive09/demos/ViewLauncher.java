package com.javalive09.demos;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;
import com.javalive09.codebag.PlayerActivity;
import com.javalive09.demos.view.ArcView;
import com.javalive09.demos.view.SampleView;
import com.javalive09.demos.view.Xfermodes;
import com.javalive09.demos.view.Xfermodes_clip;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * View launcher
 */
@Player(name = "View")
public class ViewLauncher {

    @Play
    public void showXferView() {
        View view = new Xfermodes.SampleView(PlayerActivity.context());
        PlayerActivity.context().showView(view);
    }

    @Play
    public void showXferClipView() {
        View view = new Xfermodes_clip.SampleView(PlayerActivity.context());
        PlayerActivity.context().showView(view);
    }

    @Play(name = "可自动缩放的连接线")
    public void showCustomLine() {
        PlayerActivity.context().showView(R.layout.custom_line);
    }

    @Play
    public void showArcView() {
        PlayerActivity.context().showView(new ArcView(PlayerActivity.context()));
    }

    @Play
    public void showArcSampleView() {
        PlayerActivity.context().showView(new SampleView(PlayerActivity.context()));
    }

    @Play(name = "显示超大地图")
    public void showSuperBigImage() {
        WebView wv = new WebView(PlayerActivity.context());
        wv.loadUrl("file:///android_asset/world.jpg");
        PlayerActivity.context().showView(wv);
    }

    @Play(name = "多个GrideView嵌套")
    public void showMultiGrideView() {
        View view = PlayerActivity.context().showView(R.layout.multigridview);
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
        SimpleAdapter saImageItems = new SimpleAdapter(PlayerActivity.context(), //没什么解释
                lstImageItem,//数据来源
                R.layout.grid_item,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage, R.id.ItemText});

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
        SimpleAdapter saImageItems2 = new SimpleAdapter(PlayerActivity.context(), //没什么解释
                lstImageItem2,//数据来源
                R.layout.grid_item,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage, R.id.ItemText});

        video.setAdapter(saImageItems2);

    }

    @Play(name = "ScaleType.CENTER\n按图片的原来size居中显示(不进行压缩，放大处理)，当图片长/宽超过View的长/宽，则截取图片的居中部分显示， 原图剪切效果")
    public void show_Big__ScaleType_CENTER() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_CENTER() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "ScaleType.CENTER_CROP\n按比例扩大图片的size居中显示(放大处理)，使得图片长 (宽)等于或大于View的长(宽), 原图放大填充效果")
    public void show_Big__ScaleType_CENTER_CROP() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_CENTER_CROP() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "ScaleType.CENTER_INSIDE\n将图片的内容完整居中显示，通过按比例缩小或原来的size使得图片长(宽)等于或小于View的长(宽)， 原图压缩效果")
    public void show_Big__ScaleType_CENTER_INSIDE() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_CENTER_INSIDE() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "ScaleType.FIT_CENTER 把图片按比例扩大(缩小)到View的宽度，居中显示， 原图安比例缩放，显示全部内容（不丢失内容信息）")
    public void show_Big__ScaleType_FIT_CENTER() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_FIT_CENTER() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "ScaleType.FIT_START\n把图片按比例扩大(缩小)到View的宽度，显示在View的上／左部分位置，原图安比例缩放，显示全部内容（不丢失内容信息）")
    public void show_Big__ScaleType_FIT_START() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.FIT_START);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_FIT_START() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_START);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "ScaleType.FIT_END\n把图片按比例扩大(缩小)到View的宽度，显示在View的下／右部分位置，原图安比例（填充view最小边）缩放，显示全部内容（不丢失内容信息)")
    public void show_Big__ScaleType_FIT_END() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_END);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_FIT_END() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setScaleType(ImageView.ScaleType.FIT_END);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "ScaleType.FIT_XY\n把图片按照指定的大小在View中显示，原图填充放大 ")
    public void show_Big__ScaleType_FIT_XY() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_FIT_XY() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        PlayerActivity.context().showView(iv);
    }

    @Play(name = "ScaleType.MATRIX 用matrix来绘制")
    public void show_Big__ScaleType_MATRIX() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.image_demo);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        iv.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void show_small__ScaleType_MATRIX() {
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setImageResource(R.drawable.vimeo_button);
        iv.setBackgroundColor(Color.WHITE);
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        PlayerActivity.context().showView(iv);
    }

    @Play
    public void showEmojiX() {
        PlayerActivity.context().showText(R.string.x);
    }

    @Play
    public void showEmojiY() {
        PlayerActivity.context().showText(R.string.v);
    }

    @Play
    public void showShader_r0() {
        TextView tv = new TextView(PlayerActivity.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0f, 10, 10, Color.BLUE);//radius 是羽化半径
        tv.setGravity(Gravity.CENTER);
        PlayerActivity.context().showView(tv);
    }

    @Play
    public void showShader_r0_5() {
        TextView tv = new TextView(PlayerActivity.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0.5f, 0, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        PlayerActivity.context().showView(tv);
    }

    @Play
    public void showShader_r0_1() {
        TextView tv = new TextView(PlayerActivity.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(0.1f, 10, 0, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        PlayerActivity.context().showView(tv);
    }

    @Play
    public void showShader_r1() {
        TextView tv = new TextView(PlayerActivity.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(1, 10, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        PlayerActivity.context().showView(tv);
    }

    @Play
    public void showShader_r5() {
        TextView tv = new TextView(PlayerActivity.context());
        tv.setText("123456 张瑞");
        tv.setTextSize(50);
        tv.setTextColor(Color.GRAY);
        tv.setBackgroundColor(Color.WHITE);
        tv.setShadowLayer(5, 10, 10, Color.BLUE);
        tv.setGravity(Gravity.CENTER);
        PlayerActivity.context().showView(tv);
    }

    @Play(name = "放大镜搜索动画")
    public void showSearchView() {
        View view = PlayerActivity.context().showView(R.layout.searchlayout);
        final View root = view.findViewById(R.id.root);
        final View search = view.findViewById(R.id.search);
        root.setVisibility(View.INVISIBLE);
        search.postDelayed(() -> {
            final int width = search.getWidth();
            final int rootH = root.getWidth();
            final int R = (rootH - width) / 2;
            ValueAnimator tValue = ValueAnimator.ofFloat(0, (float) (2.0f * Math.PI));
            tValue.setDuration(2000);
            tValue.setRepeatCount(ValueAnimator.INFINITE);
            tValue.setInterpolator(new LinearInterpolator());
            tValue.addUpdateListener(animation -> {
                // 圆的参数方程 x = R*sin(t) y = R*cos(t)
                float t = (Float) animation.getAnimatedValue();
                int x = (int) (R * Math.sin(t)) + R;
                int y = (int) (R * Math.cos(t)) + R;
                search.layout(x, y, x + width, y + width);
            });
            tValue.start();
            root.setVisibility(View.VISIBLE);
        }, 500);
    }

}