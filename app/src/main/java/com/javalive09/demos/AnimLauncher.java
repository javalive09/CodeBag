package com.javalive09.demos;

import android.animation.ValueAnimator;
import android.graphics.*;
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

import com.javalive09.codebag.Player;
import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;
import com.javalive09.demos.anim.ChainView;
import com.javalive09.demos.anim.CircleView;
import com.javalive09.demos.drawable.TextDrawable;

import java.util.Random;

/**
 * Created by peter on 2018/1/11.
 */

@Player(name = " Animation")
public class AnimLauncher {

    @Play(name = "锁链动画")
    public void launchAnimChain() {
        ChainView chain = new ChainView(PlayerActivity.context());
        chain.setBackgroundColor(Color.BLACK);
        ValueAnimator anim = ValueAnimator.ofInt(0, 900);
        anim.addUpdateListener(chain);
        anim.setDuration(3000);
        chain.postDelayed(anim::start, 1000);
        PlayerActivity.context().showView(chain);
    }

    @Play(name = "圆形动画")
    public void launchAnimCircle() {
        PlayerActivity.context().showView(new CircleView(PlayerActivity.context()));
    }

}
