package com.javalive09.demos;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

/**
 * Created by peter on 2019-08-09
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    Handler handler = new Handler();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createUpdateNotification("1", "我是渠道名字01");
        createUpdateNotification("2", "我是渠道名字02");
        createUpdateNotification("2", "我是渠道名字03");
        show("1", "我是渠道名字1");
        show("1", "我是渠道名字2");
        show("1", "我是渠道名字3");
        show("1", "我是渠道名字4");
        show("1", "我是渠道名字5");

        ResultReceiver resultReceiver = intent.getParcelableExtra("resultReceiver");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(resultReceiver != null) {
                    int i = new Random().nextInt(10000);
                    Log.i("peter", "send>>>>>>>start?>" + i);
                    try {
                        resultReceiver.send(i, null);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.i("peter", "send>>>>>>>end?>" + i);
                    handler.postDelayed(this, 2000);

                }
            }
        }, 2000);

        return super.onStartCommand(intent, flags, startId);
    }

    private void show(String id, String name) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this, id)
                    .setContentTitle("title have new messages")
                    .setContentText("content hahaha")
                    .setWhen(System.currentTimeMillis())
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.card_danager_memory))
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, id)
                    .setContentTitle("5 new messages")
                    .setContentText("hahaha")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true);
            notification = notificationBuilder.build();
        }
        notificationManager.notify(name.hashCode(), notification);
        startForeground(110, notification);
    }


    private void createChannel(int channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id 这个地方只要一直即可
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "notification channel";
            // 用户可以看到的通知渠道的描述
            String id = String.valueOf(channelId);
            String description = "notification description";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 自定义声音
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    private void createUpdateNotification(String msg,String content) {
        int OTA_NOTIFICATION_ID = 12345;
        createChannel(OTA_NOTIFICATION_ID);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this, String.valueOf(OTA_NOTIFICATION_ID))
                .setAutoCancel(true)
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle(msg)
                //设置通知内容
                .setContentText(content);
        Intent in = new Intent(MyService.this, MyService.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(MyService.this, 0, in, 0);
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify("codebag",OTA_NOTIFICATION_ID, notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("peter", "send>>>>>>>onDestroy>");
    }
}
