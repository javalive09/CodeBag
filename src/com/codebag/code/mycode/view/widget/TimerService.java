package com.codebag.code.mycode.view.widget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.codebag.R;

public class TimerService extends Service {
	private Timer timer;

	@Override
	public void onCreate() {
		super.onCreate();
		timer = new Timer();
		timer.schedule(new MyTimerTask(), 0, 1000);
	}

	private final class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			String time = sdf.format(new Date());
			
			// 获取Widgets管理器
			AppWidgetManager widgetManager = AppWidgetManager.getInstance(getApplicationContext());
			// widgetManager所操作的Widget对应的远程视图即当前Widget的layout文件
			RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.time_appwidget);
			remoteView.setTextViewText(R.id.tvt, "123456");
			// 当点击Widgets时触发的世界
//			remoteView.setOnClickPendingIntent(viewId, pendingIntent)
			ComponentName componentName = new ComponentName(
					getApplicationContext(), TimeWidgetProvider.class);
			widgetManager.updateAppWidget(componentName, remoteView);
		}
	}

	@Override
	public void onDestroy() {
		timer.cancel();
		timer = null;
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
