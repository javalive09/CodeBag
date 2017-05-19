package com.javalive09.sample.project.raventech;

import android.os.Environment;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by peter on 2016/12/6.
 */

public class shellTap extends Entry {

    public void tap() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("input");
        cmd.add("tap");
        cmd.add("200");
        cmd.add("200");
        exeCMD(cmd);
    }

    public void swipe() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("input");
        cmd.add("swipe");
        cmd.add("200"); //x
        cmd.add("200"); //y
        cmd.add("700"); //x1
        cmd.add("200"); //y1
        exeCMD(cmd);
    }

    public void keyevent() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("input");
        cmd.add("keyevent");
        cmd.add("4");
        exeCMD(cmd);
    }

    public void am() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("am");
        cmd.add("start");
        cmd.add("-a");
        cmd.add("android.intent.action.MAIN");
        exeCMD(cmd);
    }

    public void ping() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("ping");
        commands.add("-c");
        commands.add("5");
        commands.add("-s");
        commands.add("1000");
        commands.add("-i");
        commands.add("2");
        commands.add("www.baidu.com");
        String log = exeCMD(commands);
        Log.i("ping", log);
    }

    public void du() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("du");
        commands.add("-s");
        commands.add(Environment.getExternalStorageDirectory().getPath() + "/VST");
        String log = exeCMD(commands);
        Log.i("du", log);
    }

    public void reboot() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("reboot");
        String log = exeCMD(commands);
        Log.i("reboot", log);
    }

    public void cp() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("cp");
        String log = exeCMD(commands);
        Log.i("cp", log);
    }

    private static String exeCMD(ArrayList<String> commands) {
        ProcessBuilder pb = new ProcessBuilder(commands);
        StringBuffer stringBuffer = new StringBuffer();
        Process process = null;
        try {
            process = pb.start();
            process.waitFor();
            InputStream in = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s;
            while ((s = br.readLine()) != null) {
                stringBuffer.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(process != null) {
                process.destroy();
            }
        }
        return stringBuffer.toString();
    }

}
