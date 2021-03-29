package com.javalive09.demos.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Rational;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.demos.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * onResume 中post 和 onMeasure onLayout onDraw的顺序
 */
public class PostActivity extends CodeActivity {

    private static final String ACTION = "remoteAction";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        MyView view = new MyView(this);
        view.setBackgroundColor(Color.BLUE);
        view.setText("peter123");
        setContentView(view, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

//
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        Log.i("peter", "onCreate");

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                enterPicInPic4(new Rect(500, 80, 1500, 500));
//            }
//        });

        ResultReceiver resultReceiver = getIntent().getParcelableExtra("resultReceiver");
        addClickButton("send", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultReceiver != null) {
                    resultReceiver.send(new Random().nextInt(100), null);
                }
            }
        });
    }

    private void enterPicInPic4(Rect rect) {
        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
        Rational rational = new Rational(123456, 100000);
        builder.setAspectRatio(rational);
        builder.setSourceRectHint(rect);

        final ArrayList<RemoteAction> actions = new ArrayList<>();
        Intent intent = new Intent(ACTION);
        RemoteAction remoteAction = new RemoteAction(Icon.createWithResource(this, R.mipmap.ic_launcher_round),
                "标题", "描述",
                PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        actions.add(remoteAction);
        builder.setActions(actions);
        enterPictureInPictureMode(builder.build());
    }

    private boolean isTopActivity() {
        ActivityManager manager = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> pis = manager.getRunningAppProcesses();
        ActivityManager.RunningAppProcessInfo topAppProcess = pis.get(0);
        if (topAppProcess != null && topAppProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {

        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();



        Log.i("peter", "onRsume isTopActivity()=" + isTopActivity());
        Log.i("peter", "onRsume");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("peter", "onRsume post");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private static class MyView extends TextView {

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.i("peter", "onMeasure");
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            Log.i("peter", "onLayout");
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Log.i("peter", "onDraw");
        }
    }

}
