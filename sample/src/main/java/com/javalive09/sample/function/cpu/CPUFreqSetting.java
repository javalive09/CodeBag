package com.javalive09.sample.function.cpu;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

import com.javalive09.codebag.Entry;

public class CPUFreqSetting extends Entry {
	
	private final String cpuFreqPath = "/sys/devices/system/cpu/cpu0/cpufreq"; 

	public void commandLine_getCpuCurGovernor() {
		List<String> commands = new ArrayList<String>();
		commands.add("su");
		commands.add("|");
		commands.add("cat");
		commands.add(cpuFreqPath + "/scaling_governor");
		ProcessBuilder pb = new ProcessBuilder(commands);
		Process process = null;
		try {
			process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DataInputStream dis = new DataInputStream(process.getInputStream());
		
		String result = "";
		try {
			result = dis.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		TextView tv = new TextView(getActivity());
		tv.setText(result);
		showView(tv);
	}
	
	public void commandLine_getAllCpuGovernors() {
		List<String> commands = new ArrayList<String>();
		commands.add("su");
		commands.add("|");
		commands.add("cat");
		commands.add(cpuFreqPath + "/scaling_available_governors");
		ProcessBuilder pb = new ProcessBuilder(commands);
		Process process = null;
		try {
			process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DataInputStream dis = new DataInputStream(process.getInputStream());
		
		String result = "";
		try {
			result = dis.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		TextView tv = new TextView(getActivity());
		tv.setText(result);
		showView(tv);
	}
	
	public void setPowersave() {
		setGovernors("powersave");
	}
	
	public void setondemand() {
		setGovernors("ondemand");
	}
	
	public void setperformance() {
		setGovernors("performance");
	}
	
	/**
	 * @param governor
	 * 	"powersave"
	 * 	"ondemand"
	 * 	"performance"
	 */
	private void setGovernors(String governor) {
		List<String> commands = new ArrayList<String>();
		commands.add("su");
		commands.add("|");
		commands.add("echo");
		commands.add(governor);
		commands.add(">");
		commands.add(cpuFreqPath + "/scaling_governor");
		ProcessBuilder pb = new ProcessBuilder(commands);
		Process process = null;
		try {
			process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DataInputStream dis = new DataInputStream(process.getInputStream());
		
		String result = "";
		try {
			result = dis.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		TextView tv = new TextView(getActivity());
		tv.setText(result);
		showView(tv);
	}

}
