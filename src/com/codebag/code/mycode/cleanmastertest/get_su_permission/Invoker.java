package com.codebag.code.mycode.cleanmastertest.get_su_permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

public class Invoker extends CaseListView {

	public Invoker(Context context) {
		super(context);
	}
	
	@Entry
	public void getSu() {
		List<String> commands = new ArrayList<String>();
		commands.add("su");
		runTime(commands);
	}
	
	private void runTime(List<String> commands) {
		ProcessBuilder pb = new ProcessBuilder(commands);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
