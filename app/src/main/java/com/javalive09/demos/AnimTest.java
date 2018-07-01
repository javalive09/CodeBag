package com.javalive09.demos;

import android.animation.ValueAnimator;
import android.graphics.*;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;
import com.javalive09.demos.anim.ChainView;
import com.javalive09.demos.anim.CircleView;

/**
 * Created by peter on 2018/1/11.
 */

@Code(name = " Animation")
public class AnimTest {

    @Run(name = "锁链动画")
    public void launchAnimChain(CodeActivity activity) {
        ChainView chain = new ChainView(activity);
        chain.setBackgroundColor(Color.BLACK);
        ValueAnimator anim = ValueAnimator.ofInt(0, 900);
        anim.addUpdateListener(chain);
        anim.setDuration(3000);
        chain.postDelayed(anim::start, 1000);
        activity.setContentView(chain);
    }

    @Run(name = "圆形动画")
    public void launchAnimCircle(CodeActivity activity) {
        activity.setContentView(new CircleView(activity));
    }

    @Run(name = "放大镜搜索动画")
    public void showSearchView(CodeActivity activity) {
        activity.setContentView(R.layout.searchlayout);
        final View root = activity.findViewById(R.id.root);
        final View search = activity.findViewById(R.id.search);
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
