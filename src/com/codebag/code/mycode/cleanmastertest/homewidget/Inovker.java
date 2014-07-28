package com.codebag.code.mycode.cleanmastertest.homewidget;

import java.util.ArrayList;
import java.util.List;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

public class Inovker extends CaseListView {

	public Inovker(Context context) {
		super(context);
	}
	
	@Entry
	public void getLauncherPackageName() {
		String name = getLauncherPackageName(getContext());
		Log.addLog(this, name);
	}

	@Entry
	public void queryLauncherProvider() {
		ArrayList<Integer> list = readLauncherProviderWidget(getContext());
		if(list != null) {
			AppWidgetManager manager = AppWidgetManager.getInstance(getContext());
			for(int appWidgetId: list) {
				AppWidgetProviderInfo info = manager.getAppWidgetInfo(appWidgetId);
				Log.addLog(this, "info=" + info);
			}
		}
	}
	
	@Entry
	public void getProviders() {
		AppWidgetManager manager = AppWidgetManager.getInstance(getContext());
		List<AppWidgetProviderInfo> list = manager.getInstalledProviders();
		for(AppWidgetProviderInfo info : list) {
			Log.addLog(this, info.toString());
		}
	}
	
	/**
	 * 获取authority
	 * 
	 * @param context
	 * @param permission
	 * @return
	 */
	static String getAuthorityFromPermission(Context context, String permission) {
		if (permission == null)
			return null;
		List<PackageInfo> packs = context.getPackageManager()
				.getInstalledPackages(PackageManager.GET_PROVIDERS);
		if (packs != null) {
			for (PackageInfo pack : packs) {
				ProviderInfo[] providers = pack.providers;
				if (providers != null) {
					for (ProviderInfo provider : providers) {
						if (permission.equals(provider.readPermission))
							return provider.authority;
						if (permission.equals(provider.writePermission))
							return provider.authority;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 参考： http://blog.csdn.net/zyting_love/article/details/8289816
	 * 
	 * @param context
	 */
	public ArrayList<Integer> readLauncherProviderWidget(Context context) {
		
		final ContentResolver cr = context.getContentResolver();
		String AUTHORITY = getAuthorityFromPermission(context,
				"com.android.launcher.permission.READ_SETTINGS");
		if (AUTHORITY == null) {
			AUTHORITY = getAuthorityFromPermission(context,
					"com.android.launcher.permission.WRITE_SETTINGS");
		}
		if (AUTHORITY == null) {
			Log.addLog(this, "no authority");
			return null;
		}
		Log.addLog(this, "authority:" + AUTHORITY);

		
		ArrayList<Integer> idList = new ArrayList<Integer>();
		Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
				+ "/favorites?notify=true");

		Cursor c = cr.query(CONTENT_URI, null, "appWidgetId<>?",
				new String[] { "-1" }, null);
		if (c != null) {
			while (c.moveToNext()) {
				int id = c.getInt(c.getColumnIndex("_id"));
				// 快捷方式的名称
				String title = c.getString(c.getColumnIndex("title"));
				// 快捷方式启动的对象
				String intent = c.getString(c.getColumnIndex("intent"));

				int itemType = c.getInt(c.getColumnIndex("itemType"));
				int appWidgetId = c.getInt(c.getColumnIndex("appWidgetId"));
				int iconType = c.getInt(c.getColumnIndex("iconType"));
				// 快捷方式的图标
				String iconPackage = c.getString(c
						.getColumnIndex("iconPackage"));

				String log = "id=" + id + "|title=" + title + "|intent="
						+ intent + "]|itemType=" + itemType + "|appWidgetId="
						+ appWidgetId + "|iconType=" + "iconType =" + iconType
						+ "|iconPackage =" + iconPackage;
				idList.add(appWidgetId);
				
				Log.addLog(this, log);
			}

		}
		
		return idList;

	}

	/**
	 * 获取正在运行桌面包名（注：存在多个桌面时且未指定默认桌面时，该方法返回Null,使用时需处理这个情况）
	 */
	public static String getLauncherPackageName(Context context) {
		final Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		final ResolveInfo res = context.getPackageManager().resolveActivity(
				intent, 0);
		if (res.activityInfo == null) {
			// should not happen. A home is always installed, isn't it?
			return null;
		}
		if (res.activityInfo.packageName.equals("android")) {
			// 有多个桌面程序存在，且未指定默认项时；
			return null;
		} else {
			return res.activityInfo.packageName;
		}
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
