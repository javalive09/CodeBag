package com.javalive09.codebag.log;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/**
 *
 * Created by peter on 2017/3/20.
 *
 */

public class LoggerContainer extends FrameLayout {

    private LoggerView mLoggerView;
    private ScrollView mScrollView;

    public LoggerContainer(@NonNull Context context) {
        super(context);
        init();
    }

    public LoggerContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoggerContainer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScrollView = new ScrollView(getContext());
        ViewGroup.LayoutParams scrollParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mScrollView.setLayoutParams(scrollParams);

        mLoggerView = new LoggerView(getContext());
        ViewGroup.LayoutParams logParams = new ViewGroup.LayoutParams(scrollParams);
        logParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLoggerView.setLayoutParams(logParams);
        mLoggerView.setClickable(true);
        mLoggerView.setFocusable(true);
        mLoggerView.setTypeface(Typeface.MONOSPACE);

        // Want to set padding as 16 dips, setPadding takes pixels.  Hooray math!
        int paddingDips = 16;
        double scale = getResources().getDisplayMetrics().density;
        int paddingPixels = (int) ((paddingDips * (scale)) + .5);
        mLoggerView.setPadding(paddingPixels, paddingPixels, paddingPixels, paddingPixels);
        mLoggerView.setCompoundDrawablePadding(paddingPixels);
        mLoggerView.setGravity(Gravity.BOTTOM);
        mLoggerView.setTextAppearance(getContext(), android.R.style.TextAppearance_Holo_Medium);
        mScrollView.addView(mLoggerView);

        mLoggerView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });

        addView(mScrollView);
    }

}
