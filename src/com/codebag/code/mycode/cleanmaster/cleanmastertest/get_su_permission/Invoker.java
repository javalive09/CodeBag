package com.codebag.code.mycode.cleanmaster.cleanmastertest.get_su_permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;

public class Invoker extends MyCode {

	public Invoker(MainActivity context) {
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
