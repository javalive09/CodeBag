package com.javalive09.sample.function.disablereceiver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

public class Invoker extends Entry {


	public void changeState(int status, String packageName, String componetName) {
		List<String> commands = new ArrayList<String>();
		commands.add("su");
		commands.add("|");
		commands.add("pm");
		switch(status) {
		case PackageManager.COMPONENT_ENABLED_STATE_ENABLED:
			commands.add("enable");
			break;
		case PackageManager.COMPONENT_ENABLED_STATE_DISABLED:
			commands.add("disable");
			break;
		}
		String totalName = packageName + "/." + componetName;
		commands.add(totalName);
		ProcessBuilder pb = new ProcessBuilder(commands);
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disableReceiver() {
		changeState(PackageManager.COMPONENT_ENABLED_STATE_DISABLED, "com.peter.appmanager", "BootCompletedReceiver");
	}
	
	public void enAbleReceiver() {
		changeState(PackageManager.COMPONENT_ENABLED_STATE_ENABLED, "com.peter.appmanager", "BootCompletedReceiver");
	}
	
	public void getReceiverState() {
		ComponentName component = new ComponentName("com.peter.appmanager", "com.peter.appmanager.BootCompletedReceiver");
		int status = getActivity().getPackageManager().getComponentEnabledSetting(component);
		switch(status) {
		case PackageManager.COMPONENT_ENABLED_STATE_ENABLED:
			LogUtil.i( "state = enable");
			break;
		case PackageManager.COMPONENT_ENABLED_STATE_DISABLED:
			LogUtil.i( "state = disable");
			break;
		}
	}

}
