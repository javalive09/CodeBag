package com.codebag.code.mycode.function.cpu;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class CPUFreqSetting extends MyCode {
	
	private final String cpuFreqPath = "/sys/devices/system/cpu/cpu0/cpufreq"; 

	public CPUFreqSetting(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
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
	
	@Entry
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
	
	@Entry
	public void setPowersave() {
		setGovernors("powersave");
	}
	
	@Entry
	public void setondemand() {
		setGovernors("ondemand");
	}
	
	@Entry
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
