package com.codebag.code.mycode.view.widget;

import java.util.List;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

public class Inovker extends CaseListView {

	public Inovker(Context context) {
		super(context);
	}
	
	@Entry
	public void getEnableWidget() {
		Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_ENABLED);
		List<ResolveInfo> list = getContext().getPackageManager().queryBroadcastReceivers(intent, 64);
		for(ResolveInfo info : list) {
			String packageName = info.activityInfo.packageName;
			Log.addLog(this, "packageName =" + packageName);
		}
	}
	
	@Entry
	public void getUpdateWidget() {
		Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		List<ResolveInfo> list = getContext().getPackageManager().queryBroadcastReceivers(intent, 64);
		for(ResolveInfo info : list) {
			String packageName = info.activityInfo.packageName;
			Log.addLog(this, "packageName =" + packageName);
		}
	}
	@Entry
	public void getDeletedWidget() {
		Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_DELETED);
		List<ResolveInfo> list = getContext().getPackageManager().queryBroadcastReceivers(intent, 64);
		for(ResolveInfo info : list) {
			String packageName = info.activityInfo.packageName;
			Log.addLog(this, "packageName =" + packageName);
		}
	}
	@Entry
	public void getPickWidget() {
		Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_PICK);
		List<ResolveInfo> list = getContext().getPackageManager().queryBroadcastReceivers(intent, 64);
		for(ResolveInfo info : list) {
			String packageName = info.activityInfo.packageName;
			Log.addLog(this, "packageName =" + packageName);
		}
	}
	

}
