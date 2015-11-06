package com.codebag.code.mycode.cleanmaster.cleanmastertest.homewidget;

import com.codebag.code.mycode.utils.Log;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class TimeWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.addLog("peter", this, "onUpdate()");
		
//		final int N = appWidgetIds.length;  
//        for (int i=0; i<N; i++) {  
//            int appWidgetId = appWidgetIds[i];  
//            updateAppWidget(context, appWidgetManager, appWidgetId);  
//        }
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	// 当一个Widgets删除时会被调用
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.addLog("peter", this, "onDeleted()");
		super.onDeleted(context, appWidgetIds);
	}

	// 第一次往桌面添加Widgets时会被调用，之后添加同类型Widgets不会被调用
	public void onEnabled(Context context) {
		Log.addLog("peter", this, "onEnabled()");
		context.startService(new Intent(context, TimerService.class));
	}

	// 从桌面上删除最后一个Widgets时会被调用
	public void onDisabled(Context context) {
		Log.addLog("peter", this, "onDisabled()");
		context.stopService(new Intent(context, TimerService.class));
	}
	
//	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,  
//            int appWidgetId) {
//		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//		String time = sdf.format(new Date());
//		RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.time_appwidget);
//		remoteView.setTextViewText(R.id.tvt, time);
//		appWidgetManager.updateAppWidget(appWidgetId, remoteView);  
//	}
}
