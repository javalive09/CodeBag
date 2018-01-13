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

import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;
import com.javalive09.codebag.PlayerActivity;
import com.javalive09.demos.drawable.TextDrawable;

import java.util.Random;

@Player(name = "Drawable")
public class DrawableLauncher {

    @Play(name = "clipDrawable 剪切类drawable")
    public void showClipDrawable() {
        android.graphics.Bitmap b = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.bitmap);
        BitmapDrawable d = new BitmapDrawable(PlayerActivity.context().getResources(), b);
        ClipDrawable cd = new ClipDrawable(d, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setBackgroundDrawable(cd);
        cd.setLevel(0);

        ValueAnimator va = ValueAnimator.ofInt(0, 10000);
        va.setDuration(1000);
        va.addUpdateListener(animation -> {
            int level = (Integer) animation.getAnimatedValue();
            cd.setLevel(level);
        });
        PlayerActivity.context().showView(iv);
        iv.post(va::start);
    }

    @Play(name = "clipDrawable xml 剪切类drawable")
    public void showClipDrawable_xml() {
        ClipDrawable cd = (ClipDrawable) PlayerActivity.context().getResources().getDrawable(R.drawable.clip_drawable);
        ImageView iv = new ImageView(PlayerActivity.context());
        ValueAnimator va = ValueAnimator.ofInt(0, 10000);
        va.setDuration(1000);
        va.addUpdateListener(animation -> {
            int level = (Integer) animation.getAnimatedValue();
            cd.setLevel(level);
        });
        iv.setBackgroundDrawable(cd);
        PlayerActivity.context().showView(iv);
        iv.post(va::start);
    }

    @Play(name = "TextDrawable 显示text的Drawable")
    public void showTextDrawable() {
        TextDrawable drawable = new TextDrawable(PlayerActivity.context());
        drawable.setText(1, 20);
        ImageView imageView = new ImageView(PlayerActivity.context());
        imageView.setBackgroundDrawable(drawable);
        PlayerActivity.context().showView(imageView);
        Random r = new Random();
        imageView.setOnClickListener((v) -> drawable.setText(r.nextInt(100), 20));
    }

    public void showAnimationDrawable() {
        AnimationDrawable ad = new AnimationDrawable();
        android.graphics.Bitmap bitmap0 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi0);
        android.graphics.Bitmap bitmap1 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi1);
        android.graphics.Bitmap bitmap2 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi2);
        android.graphics.Bitmap bitmap3 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi3);
        android.graphics.Bitmap bitmap4 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi4);
        android.graphics.Bitmap bitmap5 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi5);

        ad.addFrame(new BitmapDrawable(bitmap0), 200);
        ad.addFrame(new BitmapDrawable(bitmap1), 200);
        ad.addFrame(new BitmapDrawable(bitmap2), 200);
        ad.addFrame(new BitmapDrawable(bitmap3), 200);
        ad.addFrame(new BitmapDrawable(bitmap4), 200);
        ad.addFrame(new BitmapDrawable(bitmap5), 200);

        ad.setOneShot(false);
        FrameLayout fl = new FrameLayout(PlayerActivity.context());
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setBackgroundDrawable(ad);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        fl.addView(iv, params);
        fl.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(fl);
        ad.start();
    }

    @Play
    public void showAnimationDrawable_xml() {
        AnimationDrawable ad = (AnimationDrawable) PlayerActivity.context().getResources().getDrawable(R.drawable.animation_drawable);

        ad.setOneShot(false);
        FrameLayout fl = new FrameLayout(PlayerActivity.context());
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setBackgroundDrawable(ad);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        fl.addView(iv, params);
        fl.setBackgroundColor(Color.WHITE);
        PlayerActivity.context().showView(fl);
        ad.start();
    }

    @Play
    public void showLevelListDrawable_xml() {
        View v = PlayerActivity.context().showView(R.layout.drawable_level_list);
        ImageView iv = v.findViewById(R.id.image);
        LevelListDrawable ad = (LevelListDrawable) iv.getBackground();
        v.findViewById(R.id.next).setOnClickListener(v1 -> {
            int level = ad.getLevel();
            level++;
            if (level < 6) {
                ad.setLevel(level);
            } else {
                ad.setLevel(0);
            }
        });
    }

    @Play
    public void showLevelListDrawable() {
        android.graphics.Bitmap bitmap0 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi0);
        android.graphics.Bitmap bitmap1 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi1);
        android.graphics.Bitmap bitmap2 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi2);
        android.graphics.Bitmap bitmap3 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi3);
        android.graphics.Bitmap bitmap4 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi4);
        android.graphics.Bitmap bitmap5 = BitmapFactory.decodeResource(PlayerActivity.context().getResources(), R.drawable.wifi5);

        LevelListDrawable ad = new LevelListDrawable();
        ad.addLevel(0, 0, new BitmapDrawable(bitmap0));
        ad.addLevel(1, 1, new BitmapDrawable(bitmap1));
        ad.addLevel(2, 2, new BitmapDrawable(bitmap2));
        ad.addLevel(3, 3, new BitmapDrawable(bitmap3));
        ad.addLevel(4, 4, new BitmapDrawable(bitmap4));
        ad.addLevel(5, 5, new BitmapDrawable(bitmap5));

        FrameLayout fl = new FrameLayout(PlayerActivity.context());
        ImageView iv = new ImageView(PlayerActivity.context());
        iv.setBackgroundDrawable(ad);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        fl.addView(iv, params);

        Button start = new Button(PlayerActivity.context());
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

        PlayerActivity.context().showView(fl);
    }

    @Play(name = "InsetDrawable 嵌入Drawable")
    public void showInsetDrawable() {
        ImageView imageView = new ImageView(PlayerActivity.context());
        imageView.setBackgroundResource(R.drawable.insetdrawable);
        PlayerActivity.context().showView(imageView);
    }

    @Play(name = "LayerList 图片累加")
    public void showLayerlist() {
        TextView tv = new TextView(PlayerActivity.context());
        tv.setText("kajd;fkaj;dfkj");
        tv.setBackgroundResource(R.drawable.layer_list);
        PlayerActivity.context().showView(tv);
    }

    @Play(name = "TransitionDrawable 过渡drawable")
    public void showTransitionDrawable() {
        View v = View.inflate(PlayerActivity.context(), R.layout.drawable_transition, null);
        ImageView iv = v.findViewById(R.id.image);
        final TransitionDrawable trans = (TransitionDrawable) iv.getDrawable();
        v.setOnClickListener(v1 -> trans.reverseTransition(2000));
        PlayerActivity.context().showView(v);
    }

    @Play(name = "ScaleDrawable 缩放drawable")
    public void showScaleDrawable() {
        View v = View.inflate(PlayerActivity.context(), R.layout.drawable_scale, null);
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

        PlayerActivity.context().showView(v);
    }

    @Play(name = "综合类Shape图形 shape使用必须关闭硬件加速")
    public void showNormalShape() {
        PlayerActivity.context().showView(R.layout.shape);//shape使用必须关闭硬件加速
    }

    @Play(name = "stroke 线图 shape使用必须关闭硬件加速")
    public void showLineShape() {
        PlayerActivity.context().showView(R.layout.line);//shape使用必须关闭硬件加速
    }

    @Play(name = "GradientShape 渐变图形 shape使用必须关闭硬件加速")
    public void showGradientShape() {
        FrameLayout fl = new FrameLayout(PlayerActivity.context());
        fl.setBackgroundResource(R.drawable.gradient);
        PlayerActivity.context().showView(fl);
    }


}