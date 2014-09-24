package com.codebag.code.mycode.function.shortcut;

import android.content.Intent;
import android.os.Parcelable;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class ShortCut extends MyCode{

	public ShortCut(MainActivity act) {
		super(act);
	}
	
	/**
	 * 需要权限 <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
	 */
	@Entry
	public void createShortCut() {
		Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		//不允许重复创建
		shortcutintent.putExtra("duplicate", false);
		//名称
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "CodeBag");
		//图标
		Parcelable icon = Intent.ShortcutIconResource.fromContext(getActivity().getApplicationContext(), R.drawable.head);
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		//activity 入口
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(getActivity().getApplicationContext() , MainActivity.class));
		//广播
		getActivity().sendBroadcast(shortcutintent);
	}
	
}
