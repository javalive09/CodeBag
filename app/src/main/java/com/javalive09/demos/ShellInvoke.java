package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;
import com.javalive09.codebag.CodeActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by peter on 2018/1/10.
 */

@Code(name = "shell 调用")
public class ShellInvoke {

    @Run(name = "保存log到file")
    public void log(CodeActivity activity) {
        String logFileStr = "codebag.log";
        File extDir = activity.getExternalCacheDir();
        File logFile = new File(extDir, logFileStr);
        if (logFile.exists()) {
            boolean suc = logFile.delete();
            Log.i("ShellInvoke-log", "suc=" + suc);
        }
        String path = logFile.getAbsolutePath();
        logFile(path);
    }

    @Run(name = "读取保存到file的log")
    public void readLogFile(CodeActivity activity) {
        String logFileStr = "codebag.log";
        File extDir = activity.getExternalCacheDir();
        File logFile = new File(extDir, logFileStr);
        String logStr = readFromFile(logFile);
        activity.showText(logStr);
    }

    @Run(name = "Home键")
    public void home() {
        key(3);
    }

    @Run(name = "back返回键")
    public void back() {
        key(4);
    }

    @Run(name = "加音量键")
    public void volumeUp() {
        key(24);
    }

    @Run(name = "减音量键")
    public void volumeDown() {
        key(25);
    }

    @Run(name = "菜单键")
    public void menu() {
        key(82);
    }

    @Run(name = "电话键")
    public void call() {
        key(5);
    }

    @Run(name = "照相机键")
    public void camera() {
        key(27);
    }

    @Run(name = "关机键")
    public void power() {
        key(26);
    }

    @Run(name = "静音键")
    public void mute() {
        key(91);
    }

    @Run(name = "水平滑动屏幕")
    public void swip() {
        swip(600, 600, 1000, 600);
    }

    @Run(name = "点击屏幕")
    public void tap() {
        tap(600, 600);
    }

    @Run(name = "滚动屏幕")
    public void roll() {
        roll(0, 900);
    }

    private void key(int key) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("keyevent");
            commands.add(key + "");
            cmd(commands);
        });
    }

    private void swip(int x1, int y1, int x2, int y2) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("swipe");
            commands.add(x1 + "");
            commands.add(y1 + "");
            commands.add(x2 + "");
            commands.add(y2 + "");
            cmd(commands);
        });
    }

    private void tap(int x1, int y1) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("tap");
            commands.add(x1 + "");
            commands.add(y1 + "");
            cmd(commands);
        });
    }

    private void roll(int x1, int y1) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("roll");
            commands.add(x1 + "");
            commands.add(y1 + "");
            cmd(commands);
        });
    }

    private void logFile(String path) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("logcat");
            commands.add("-v");
            commands.add("threadtime");
            commands.add("--pid");
            commands.add(String.valueOf(android.os.Process.myPid()));
            commands.add("-f");
            commands.add(path);
            commands.add("-r");
            commands.add("10240");//kbytes
            commands.add("-n");
            commands.add("1");
            commands.add("-s");
            commands.add("*:I");
            cmd(commands);
        });
    }

    /**
     * ArrayList<String> commands = new ArrayList<String>();
     * commands.add("cxdish");
     * commands.add("sendcmd");
     * commands.add("0xD308631E");
     * commands.add("0x00000142");
     * commands.add("0x0");
     *
     * @param commands
     *
     * @return
     */
    public static String cmd(ArrayList<String> commands) {
        ProcessBuilder pb = new ProcessBuilder(commands);
        StringBuilder stringBuffer = new StringBuilder();
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return stringBuffer.toString();
    }

    private String readFromFile(File extDir) {
        String ret = "";
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(extDir));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }
            bufferedReader.close();
            ret = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

}
