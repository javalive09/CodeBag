package com.javalive09.sample.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * Created by peter on 2017/1/24.
 */

public class ShellUtil {


    /**
     *
     * ArrayList<String> commands = new ArrayList<String>();
     * commands.add("cxdish");
     * commands.add("sendcmd");
     * commands.add("0xD308631E");
     * commands.add("0x00000142");
     * commands.add("0x0");
     *
     * @param commands
     * @return
     */
    public static String cmd(ArrayList<String> commands) {
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
