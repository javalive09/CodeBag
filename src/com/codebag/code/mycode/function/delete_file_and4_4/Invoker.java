package com.codebag.code.mycode.function.delete_file_and4_4;

import android.content.Context;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;
import com.codebag.bag.Log;

public class Invoker extends CaseListView {

//	String path = "/storage/extSdCard/system.txt";//G4
	String path = "/storage/ext_sd/360Download/system.txt";//htc
//	String path = "/storage/sdcard1/system.txt";//honor
	
	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void DeleteFile() {
		boolean result = MediaFileUtil.deleteFile(getContext().getContentResolver(), path);
		Log.addLog(this, "result=" + result);
	}
	
	

}
