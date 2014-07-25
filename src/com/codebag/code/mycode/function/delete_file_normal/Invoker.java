package com.codebag.code.mycode.function.delete_file_normal;

import java.io.File;

import android.content.Context;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

//	String path = "/storage/extSdCard/system.txt";//G4
	String path = "/storage/ext_sd/360Download/system.txt";//htc
//	String path = "/storage/sdcard1/system.txt";//honor
	
	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void deleteFile() {
		File file = new File(path);
		file.delete();
		
	}

}
