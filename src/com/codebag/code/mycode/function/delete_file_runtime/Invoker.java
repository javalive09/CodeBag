package com.codebag.code.mycode.function.delete_file_runtime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public void commandLineDelete_SU() {
		
		List<String> commands = new ArrayList<String>();
		commands.add("su");
		commands.add("|");
		commands.add("rm");
		commands.add(path);
		
		ProcessBuilder pb = new ProcessBuilder(commands);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Entry
	public void commandLineDelete() {
		List<String> commands = new ArrayList<String>();
		commands.add("rm");
		commands.add(path);
		
		ProcessBuilder pb = new ProcessBuilder(commands);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
