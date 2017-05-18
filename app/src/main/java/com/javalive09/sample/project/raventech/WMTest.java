package com.javalive09.sample.project.raventech;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.utils.ShellUtil;

import java.util.ArrayList;

/**
 * Created by peter on 2017/1/24.
 */

public class WMTest extends Entry {


    public void test_density() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("wm");
        cmd.add("density");
        String result = ShellUtil.cmd(cmd);
        showTxt(result);
    }

    public void test_size() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("wm");
        cmd.add("size");
        String result = ShellUtil.cmd(cmd);
        showTxt(result);
    }

    /**
     * wm density 999999
     */
    public void set_120() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("wm");
        cmd.add("density");
        cmd.add("120");
        String result = ShellUtil.cmd(cmd);
        showTxt(result);
    }

    /**
     * wm size 999999x1
     */
    public void test_800x480() {
        ArrayList<String> cmd = new ArrayList<>();
        cmd.add("wm");
        cmd.add("size");
        cmd.add("800x480");
        String result = ShellUtil.cmd(cmd);
        showTxt(result);
    }


}
