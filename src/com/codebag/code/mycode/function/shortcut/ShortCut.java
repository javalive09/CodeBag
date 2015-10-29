package com.codebag.code.mycode.function.shortcut;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class ShortCut extends MyCode{

	public ShortCut(InovkedViewActivity act) {
		super(act);
	}
	
	/**
	 * 需要权限 <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
	 */
	@Entry
	public void createShortCut() {
		Intent shortcutintent = new Intent();
		Intent launcherIntent = new Intent(getActivity().getApplicationContext() , InovkedViewActivity.class);
		
		//不允许重复创建
		shortcutintent.putExtra("duplicate", false);
		//名称
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "CodeBag");
		//图标
		Parcelable icon = Intent.ShortcutIconResource.fromContext(getActivity().getApplicationContext(), R.drawable.head);
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		
		shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

		shortcutintent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		
		//广播
		getActivity().sendBroadcast(shortcutintent);
	}
	
	@Entry
	public void createShortCut1() {
	    Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
	    intent.putExtra("duplicate", false);
	    intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, getBitmap());
	    intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(getActivity().getApplicationContext() , InovkedViewActivity.class));
	    intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "peter");
	    getActivity().sendBroadcast(intent);
	    
	}
	
	private Bitmap getBitmap() {
	    Bitmap photo = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.card_danager_memory);
	   
	   int iconSize = (int) getActivity().getResources().getDimension(android.R.dimen.app_icon_size);
        Bitmap icon = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(icon);
	    
	    Paint photoPaint = new Paint();
        photoPaint.setDither(true);
        photoPaint.setFilterBitmap(true);
        Rect src = new Rect(0,0, photo.getWidth(),photo.getHeight());
        Rect dst = new Rect(0,0, iconSize,iconSize);
        canvas.drawBitmap(photo, src, dst, photoPaint);
        
        return icon;
	}
	
	
}
