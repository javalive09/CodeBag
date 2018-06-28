package com.javalive09.demos;

import android.animation.ValueAnimator;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;
import com.javalive09.demos.drawable.TextDrawable;

import java.util.Random;

@Code(name = "Drawable")
public class DrawableLauncher {

    @Run(name = "clipDrawable 剪切类drawable")
    public void showClipDrawable(CodeActivity activity) {
        android.graphics.Bitmap b = BitmapFactory.decodeResource(activity.getResources(), R.drawable.bitmap);
        BitmapDrawable d = new BitmapDrawable(activity.getResources(), b);
        ClipDrawable cd = new ClipDrawable(d, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        ImageView iv = new ImageView(activity);
        iv.setBackgroundDrawable(cd);
        cd.setLevel(0);

        ValueAnimator va = ValueAnimator.ofInt(0, 10000);
        va.setDuration(1000);
        va.addUpdateListener(animation -> {
            int level = (Integer) animation.getAnimatedValue();
            cd.setLevel(level);
        });
        activity.setContentView(iv);
        iv.post(va::start);
    }

    @Run(name = "clipDrawable xml 剪切类drawable")
    public void showClipDrawable_xml(CodeActivity activity) {
        ClipDrawable cd = (ClipDrawable) activity.getResources().getDrawable(R.drawable.clip_drawable);
        ImageView iv = new ImageView(activity);
        ValueAnimator va = ValueAnimator.ofInt(0, 10000);
        va.setDuration(1000);
        va.addUpdateListener(animation -> {
            int level = (Integer) animation.getAnimatedValue();
            cd.setLevel(level);
        });
        iv.setBackgroundDrawable(cd);
        activity.setContentView(iv);
        iv.post(va::start);
    }

    @Run(name = "TextDrawable 显示text的Drawable")
    public void showTextDrawable(CodeActivity activity) {
        TextDrawable drawable = new TextDrawable(activity);
        drawable.setText(1, 20);
        ImageView imageView = new ImageView(activity);
        imageView.setBackgroundDrawable(drawable);
        activity.setContentView(imageView);
        Random r = new Random();
        imageView.setOnClickListener((v) -> drawable.setText(r.nextInt(100), 20));
    }

    public void showAnimationDrawable(CodeActivity activity) {
        AnimationDrawable ad = new AnimationDrawable();
        android.graphics.Bitmap bitmap0 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi0);
        android.graphics.Bitmap bitmap1 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi1);
        android.graphics.Bitmap bitmap2 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi2);
        android.graphics.Bitmap bitmap3 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi3);
        android.graphics.Bitmap bitmap4 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi4);
        android.graphics.Bitmap bitmap5 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi5);

        ad.addFrame(new BitmapDrawable(bitmap0), 200);
        ad.addFrame(new BitmapDrawable(bitmap1), 200);
        ad.addFrame(new BitmapDrawable(bitmap2), 200);
        ad.addFrame(new BitmapDrawable(bitmap3), 200);
        ad.addFrame(new BitmapDrawable(bitmap4), 200);
        ad.addFrame(new BitmapDrawable(bitmap5), 200);

        ad.setOneShot(false);
        FrameLayout fl = new FrameLayout(activity);
        ImageView iv = new ImageView(activity);
        iv.setBackgroundDrawable(ad);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        fl.addView(iv, params);
        fl.setBackgroundColor(Color.WHITE);
        activity.setContentView(fl);
        ad.start();
    }

    @Run
    public void showAnimationDrawable_xml(CodeActivity activity) {
        AnimationDrawable ad = (AnimationDrawable) activity.getResources().getDrawable(R.drawable.animation_drawable);

        ad.setOneShot(false);
        FrameLayout fl = new FrameLayout(activity);
        ImageView iv = new ImageView(activity);
        iv.setBackgroundDrawable(ad);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        fl.addView(iv, params);
        fl.setBackgroundColor(Color.WHITE);
        activity.setContentView(fl);
        ad.start();
    }

    @Run
    public void showLevelListDrawable_xml(CodeActivity activity) {
        activity.setContentView(R.layout.drawable_level_list);
        ImageView iv = activity.findViewById(R.id.image);
        LevelListDrawable ad = (LevelListDrawable) iv.getBackground();
        activity.findViewById(R.id.next).setOnClickListener(v1 -> {
            int level = ad.getLevel();
            level++;
            if (level < 6) {
                ad.setLevel(level);
            } else {
                ad.setLevel(0);
            }
        });
    }

    @Run
    public void showLevelListDrawable(CodeActivity activity) {
        android.graphics.Bitmap bitmap0 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi0);
        android.graphics.Bitmap bitmap1 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi1);
        android.graphics.Bitmap bitmap2 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi2);
        android.graphics.Bitmap bitmap3 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi3);
        android.graphics.Bitmap bitmap4 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi4);
        android.graphics.Bitmap bitmap5 = BitmapFactory.decodeResource(activity.getResources(), R.drawable.wifi5);

        LevelListDrawable ad = new LevelListDrawable();
        ad.addLevel(0, 0, new BitmapDrawable(bitmap0));
        ad.addLevel(1, 1, new BitmapDrawable(bitmap1));
        ad.addLevel(2, 2, new BitmapDrawable(bitmap2));
        ad.addLevel(3, 3, new BitmapDrawable(bitmap3));
        ad.addLevel(4, 4, new BitmapDrawable(bitmap4));
        ad.addLevel(5, 5, new BitmapDrawable(bitmap5));

        FrameLayout fl = new FrameLayout(activity);
        ImageView iv = new ImageView(activity);
        iv.setBackgroundDrawable(ad);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        fl.addView(iv, params);

        Button start = new Button(activity);
        start.setText("next");
        start.setOnClickListener(v -> {
            int level = ad.getLevel();
            level++;
            if (level < 6) {
                ad.setLevel(level);
            } else {
                ad.setLevel(0);
            }
        });

        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        fl.addView(start, params2);
        fl.setBackgroundColor(Color.WHITE);

        activity.setContentView(fl);
    }

    @Run(name = "InsetDrawable 嵌入Drawable")
    public void showInsetDrawable(CodeActivity activity) {
        ImageView imageView = new ImageView(activity);
        imageView.setBackgroundResource(R.drawable.insetdrawable);
        activity.setContentView(imageView);
    }

    @Run(name = "LayerList 图片累加")
    public void showLayerlist(CodeActivity activity) {
        TextView tv = new TextView(activity);
        tv.setText("kajd;fkaj;dfkj");
        tv.setBackgroundResource(R.drawable.layer_list);
        activity.setContentView(tv);
    }

    @Run(name = "TransitionDrawable 过渡drawable")
    public void showTransitionDrawable(CodeActivity activity) {
        View v = View.inflate(activity, R.layout.drawable_transition, null);
        ImageView iv = v.findViewById(R.id.image);
        final TransitionDrawable trans = (TransitionDrawable) iv.getDrawable();
        v.setOnClickListener(v1 -> trans.reverseTransition(2000));
        activity.setContentView(v);
    }

    @Run(name = "ScaleDrawable 缩放drawable")
    public void showScaleDrawable(CodeActivity activity) {
        View v = View.inflate(activity, R.layout.drawable_scale, null);
        ImageView iv = v.findViewById(R.id.image);
        final ScaleDrawable sd = (ScaleDrawable) iv.getBackground();
        sd.setLevel(10000);
        SeekBar seb = v.findViewById(R.id.seekbar);
        seb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                sd.setLevel(progress);
            }
        });

        activity.setContentView(v);
    }

    @Run(name = "综合类Shape图形 shape使用必须关闭硬件加速")
    public void showNormalShape(CodeActivity activity) {
        activity.setContentView(R.layout.shape);//shape使用必须关闭硬件加速
    }

    @Run(name = "stroke 线图 shape使用必须关闭硬件加速")
    public void showLineShape(CodeActivity activity) {
        activity.setContentView(R.layout.line);//shape使用必须关闭硬件加速
    }

    @Run(name = "GradientShape 渐变图形 shape使用必须关闭硬件加速")
    public void showGradientShape(CodeActivity activity) {
        FrameLayout fl = new FrameLayout(activity);
        fl.setBackgroundResource(R.drawable.gradient);
        activity.setContentView(fl);
    }


}