package com.javalive09.sample.project.cleanmaster.cleanmaster_test.get_su_permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.javalive09.codebag.Entry;

public class Invoker extends Entry {

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
