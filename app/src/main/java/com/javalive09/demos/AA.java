package com.javalive09.demos;

import android.widget.SeekBar;
import android.widget.TextView;

import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import io.feeeei.circleseekbar.CircleSeekBar;

@Code(name = "时间控件 精细控制体验")
public class AA {

    @Run
    public void show(CodeActivity codeActivity) {
        codeActivity.setContentView(R.layout.progressbar);

        CircleSeekBar bar = codeActivity.findViewById(R.id.image);
        TextView tv = codeActivity.findViewById(R.id.tv);
        tv.setText("progress");

        bar.setOnSeekBarChangeListener(new CircleSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onChanged(CircleSeekBar seekbar, int curValue) {
                tv.setText("progress:" + seekbar.getCurProcess());
            }
        });

    }

}
