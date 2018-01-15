package com.javalive09.demos;

import android.animation.ValueAnimator;
import android.graphics.*;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.javalive09.codebag.Player;
import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Play;
import com.javalive09.demos.anim.ChainView;
import com.javalive09.demos.anim.CircleView;

/**
 * Created by peter on 2018/1/11.
 */

@Player(name = " Animation")
public class AnimLauncher {

    @Play(name = "锁链动画")
    public void launchAnimChain() {
        ChainView chain = new ChainView(CaseActivity.context());
        chain.setBackgroundColor(Color.BLACK);
        ValueAnimator anim = ValueAnimator.ofInt(0, 900);
        anim.addUpdateListener(chain);
        anim.setDuration(3000);
        chain.postDelayed(anim::start, 1000);
        CaseActivity.showView(chain);
    }

    @Play(name = "圆形动画")
    public void launchAnimCircle() {
        CaseActivity.showView(new CircleView(CaseActivity.context()));
    }

    @Play(name = "放大镜搜索动画")
    public void showSearchView() {
        View view = CaseActivity.showView(R.layout.searchlayout);
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
