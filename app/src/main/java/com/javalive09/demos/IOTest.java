package com.javalive09.demos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Code;
import com.javalive09.annotation.Run;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


@Code(name = "IO读写")
public class IOTest {

    @Run(name = "context.getFilesDir()写文件 \n 目录:/data/data/<package name>/files")
    public void app_file_write(CodeActivity activity) {
        String transferFile = "file.txt";
        File dir = activity.getFilesDir();
        File requestFile = new File(dir, transferFile);
        writeToFile("filexxxx", requestFile);
        activity.showText("dir =" + requestFile.getAbsolutePath());
    }

    @Run(name = "context.getFilesDir()读文件 \n 目录:/data/data/<package name>/files")
    public void app_read_file(CodeActivity activity) {
        String transferFile = "file.txt";
        File dir = activity.getFilesDir();
        File requestFile = new File(dir, transferFile);
        String str = readFromFile(requestFile);
        activity.showText(transferFile + ":" + str);
    }

    @Run(name = "context.getCacheDir()写文件\n 目录:/data/data/<package name>/cache")
    public void app_write_cache(CodeActivity activity) {
        String transferFile = "cache.txt";
        File dir = activity.getCacheDir();
        File requestFile = new File(dir, transferFile);
        writeToFile("cachexxxx", requestFile);
        activity.showText("dir =" + requestFile.getAbsolutePath());
    }

    @Run(name = "context.getCacheDir()读文件\n 目录:/data/data/<package name>/cache")
    public void app_read_cache(CodeActivity activity) {
        String transferFile = "cache.txt";
        File dir = activity.getCacheDir();
        File requestFile = new File(dir, transferFile);
        String str = readFromFile(requestFile);
        activity.showText(transferFile + ":" + str);
    }

    @Run(name = "context.openFileOutput()写文件 \n目录:/data/data/<package name>/files")
    public void app_write_openfile(CodeActivity activity) {
        writeToFile("write_openfilexxxx", activity);
        activity.showText("str :" + "write_openfilexxxx");
    }

    @Run(name = "context.openFileOutput()读文件 \n目录:/data/data/<package name>/files")
    public void read_openfile(CodeActivity activity) {
        String str = readFromFile(activity);
        activity.showText("file :" + str);
    }

    private void writeToFile(String data, File extDir) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(extDir));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void writeToFile(String data, CodeActivity activity) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    activity.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(CodeActivity activity) {
        String ret = "";
        try {
            InputStream inputStream = activity.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    @Run(name = "context.getExternalFilesDir()写文件\n 目录:SDCard/Android/data/<package name>/files")
    public void sdcard_write_file(CodeActivity activity) {
        if (!isExternalStorageWritable()) {
            return;
        }
        String transferFile = "file.txt";
        File extDir = activity.getExternalFilesDir(null);
        File requestFile = new File(extDir, transferFile);
        requestFile.setReadable(true, false);
        writeToFile("filexxx", requestFile);
        activity.showText("dir=" + requestFile.getAbsolutePath());
    }

    @Run(name = "context.getExternalFilesDir()读文件\n 目录:SDCard/Android/data/<package name>/files")
    public void sdcard_read_file_str(CodeActivity activity) {
        if (!isExternalStorageReadable()) {
            return;
        }
        String transferFile = "file.txt";
        File extDir = activity.getExternalFilesDir(null);
        File requestFile = new File(extDir, transferFile);
        String str = readFromFile(requestFile);
        activity.showText("file str =" + str);
    }

    @Run(name = "context.getExternalCacheDir()写文件\n 目录:SDCard/Android/data/<package name>/cache")
    public void sdcard_write_cache(CodeActivity activity) {
        if (!isExternalStorageWritable()) {
            return;
        }
        String transferFile = "cache.txt";
        File extDir = activity.getExternalCacheDir();
        File requestFile = new File(extDir, transferFile);
        requestFile.setReadable(true, false);
        writeToFile("cachexxx", requestFile);
        activity.showText("dir=" + requestFile.getAbsolutePath());
    }

    @Run(name = "context.getExternalCacheDir()读文件 \n目录:SDCard/Android/data/<package name>/cache")
    public void sdcard_read_cache_str(CodeActivity activity) {
        if (!isExternalStorageReadable()) {
            return;
        }
        String transferFile = "cache.txt";
        File extDir = activity.getExternalCacheDir();
        File requestFile = new File(extDir, transferFile);
        String str = readFromFile(requestFile);
        activity.showText("file str =" + str);
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    @Run(name = "activity.getSharedPreferences(name)写xml \n目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_activity_set(CodeActivity activity) {
        SharedPreferences sp = activity.getSharedPreferences("getSharedPreferences1", Context.MODE_PRIVATE);
        sp.edit().putString("peter1", "12345").apply();
    }

    @Run(name = "activity.getSharedPreferences(name)读xml \n目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_activity_get(CodeActivity activity) {
        SharedPreferences sp = activity.getSharedPreferences("getSharedPreferences1", Context.MODE_PRIVATE);
        String str = sp.getString("peter1", "");
        activity.showText(str);
    }

    @Run(name = "applicationContext.getSharedPreferences(name)写xml \n目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_application_set(CodeActivity activity) {
        SharedPreferences sp = activity.getApplicationContext().getSharedPreferences("getSharedPreferences2", Context
                .MODE_PRIVATE);
        sp.edit().putString("peter2", "12345").apply();
    }

    @Run(name = "applicationContext.getSharedPreferences(name)读xml\n 目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_activity_get2(CodeActivity activity) {
        SharedPreferences sp = activity.getApplicationContext().getSharedPreferences("getSharedPreferences2", Context
                .MODE_PRIVATE);
        String str = sp.getString("peter2", "");
        activity.showText(str);
    }

    @Run(name = "context.getPreferences()写xml \n目录:/data/data/<package name>/shared_prefs/名字为activity类全路径名去掉包名")
    public void sp_activity_innermethod_set(CodeActivity activity) {
        SharedPreferences sp = activity.getPreferences(Context.MODE_PRIVATE);
        String name = activity.getLocalClassName();
        sp.edit().putString("peter3", "6789").apply();
    }

    @Run(name = "context.getPreferences()读xml \n目录:/data/data/<package name>/shared_prefs/名字为activity类全路径名去掉包名")
    public void sp_activity_innermethod_get(CodeActivity activity) {
        SharedPreferences sp = activity.getPreferences(Context.MODE_PRIVATE);
        String str = sp.getString("peter3", "");
        activity.showText(str);
    }

    @Run(name = "PreferenceManager.getDefaultSharedPreferences写xml \n/data/data/com.package.name/shared_prefs/com.package.name_preferences.xml")
    public void sp_manager_set(CodeActivity activity) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        sp.edit().putString("peter34", "0000").apply();
    }

    @Run(name = "PreferenceManager.getDefaultSharedPreferences读xml\n /data/data/com.package.name/shared_prefs/com.package.name_preferences.xml")
    public void sp_manager_get(CodeActivity activity) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(activity);
        String str = sp.getString("peter34", "");
        activity.showText(str);
    }

}
