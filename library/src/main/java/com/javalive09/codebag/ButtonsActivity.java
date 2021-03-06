package com.javalive09.codebag;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class ButtonsActivity extends Activity {

    private LinearLayout mButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initButtonsView();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        installButtonsView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("peter", this.getClass().getName() + " onResume()");
    }

    private void initButtonsView() {
        mButtons = new LinearLayout(this);
        mButtons.setOrientation(LinearLayout.VERTICAL);
        mButtons.setBackgroundColor(Color.GRAY);
    }

    private void installButtonsView() {
        if (mButtons.getChildCount() == 0) {
            return;
        }
        final ScrollView scrollView = new ScrollView(this);
        FrameLayout.LayoutParams paramsButtons = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.addView(mButtons, paramsButtons);
        final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.topMargin = 100;
        params.bottomMargin = 100;
        params.gravity = Gravity.END;
        addContentView(scrollView, params);

        Button position = addClickButton("改变位置", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (params.gravity) {
                    case Gravity.START:
                        params.gravity = Gravity.END;
                        break;
                    case Gravity.END:
                        params.gravity = Gravity.START;
                        break;
                    default:
                        break;
                }
                scrollView.setLayoutParams(params);
            }});
        position.setTextColor(Color.RED);
    }

    public Button addClickButton(String name, View.OnClickListener listener) {
        return addClickButton(name, 30, listener);
    }

    public Button addClickButton(String name, int textSize, View.OnClickListener listener) {
        Button button = new Button(this);
        button.setText(name);
        button.setTextSize(textSize);
        button.setOnClickListener(listener);
        mButtons.addView(button);
        return button;
    }


}