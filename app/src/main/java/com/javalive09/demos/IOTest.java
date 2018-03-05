package com.javalive09.demos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.javalive09.codebag.CodeBag;
import com.javalive09.codebag.annotation.Tester;
import com.javalive09.codebag.annotation.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


@Tester(name = "IO读写")
public class IOTest {

    @Test(name = "context.getFilesDir()写文件 \n 目录:/data/data/<package name>/files")
    public void app_file_write() {
        String transferFile = "file.txt";
        File dir = CodeBag.context().getFilesDir();
        File requestFile = new File(dir, transferFile);
        writeToFile("filexxxx", requestFile);
        CodeBag.showText("dir =" + requestFile.getAbsolutePath());
    }

    @Test(name = "context.getFilesDir()读文件 \n 目录:/data/data/<package name>/files")
    public void app_read_file() {
        String transferFile = "file.txt";
        File dir = CodeBag.context().getFilesDir();
        File requestFile = new File(dir, transferFile);
        String str = readFromFile(requestFile);
        CodeBag.showText(transferFile + ":" + str);
    }

    @Test(name = "context.getCacheDir()写文件\n 目录:/data/data/<package name>/cache")
    public void app_write_cache() {
        String transferFile = "cache.txt";
        File dir = CodeBag.context().getCacheDir();
        File requestFile = new File(dir, transferFile);
        writeToFile("cachexxxx", requestFile);
        CodeBag.showText("dir =" + requestFile.getAbsolutePath());
    }

    @Test(name = "context.getCacheDir()读文件\n 目录:/data/data/<package name>/cache")
    public void app_read_cache() {
        String transferFile = "cache.txt";
        File dir = CodeBag.context().getCacheDir();
        File requestFile = new File(dir, transferFile);
        String str = readFromFile(requestFile);
        CodeBag.showText(transferFile + ":" + str);
    }

    @Test(name = "context.openFileOutput()写文件 \n目录:/data/data/<package name>/files")
    public void app_write_openfile() {
        writeToFile("write_openfilexxxx");
        CodeBag.showText("str :" + "write_openfilexxxx");
    }

    @Test(name = "context.openFileOutput()读文件 \n目录:/data/data/<package name>/files")
    public void read_openfile() {
        String str = readFromFile();
        CodeBag.showText("file :" + str);
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

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    CodeBag.context().openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() {
        String ret = "";
        try {
            InputStream inputStream = CodeBag.context().openFileInput("config.txt");

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

    @Test(name = "context.getExternalFilesDir()写文件\n 目录:SDCard/Android/data/<package name>/files")
    public void sdcard_write_file() {
        if (!isExternalStorageWritable()) {
            return;
        }
        String transferFile = "file.txt";
        File extDir = CodeBag.context().getExternalFilesDir(null);
        File requestFile = new File(extDir, transferFile);
        requestFile.setReadable(true, false);
        writeToFile("filexxx", requestFile);
        CodeBag.showText("dir=" + requestFile.getAbsolutePath());
    }

    @Test(name = "context.getExternalFilesDir()读文件\n 目录:SDCard/Android/data/<package name>/files")
    public void sdcard_read_file_str() {
        if (!isExternalStorageReadable()) {
            return;
        }
        String transferFile = "file.txt";
        File extDir = CodeBag.context().getExternalFilesDir(null);
        File requestFile = new File(extDir, transferFile);
        String str = readFromFile(requestFile);
        CodeBag.showText("file str =" + str);
    }

    @Test(name = "context.getExternalCacheDir()写文件\n 目录:SDCard/Android/data/<package name>/cache")
    public void sdcard_write_cache() {
        if (!isExternalStorageWritable()) {
            return;
        }
        String transferFile = "cache.txt";
        File extDir = CodeBag.context().getExternalCacheDir();
        File requestFile = new File(extDir, transferFile);
        requestFile.setReadable(true, false);
        writeToFile("cachexxx", requestFile);
        CodeBag.showText("dir=" + requestFile.getAbsolutePath());
    }

    @Test(name = "context.getExternalCacheDir()读文件 \n目录:SDCard/Android/data/<package name>/cache")
    public void sdcard_read_cache_str() {
        if (!isExternalStorageReadable()) {
            return;
        }
        String transferFile = "cache.txt";
        File extDir = CodeBag.context().getExternalCacheDir();
        File requestFile = new File(extDir, transferFile);
        String str = readFromFile(requestFile);
        CodeBag.showText("file str =" + str);
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

    @Test(name = "activity.getSharedPreferences(name)写xml \n目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_activity_set() {
        SharedPreferences sp = CodeBag.context().getSharedPreferences("getSharedPreferences1", Context.MODE_PRIVATE);
        sp.edit().putString("peter1", "12345").apply();
    }

    @Test(name = "activity.getSharedPreferences(name)读xml \n目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_activity_get() {
        SharedPreferences sp = CodeBag.context().getSharedPreferences("getSharedPreferences1", Context.MODE_PRIVATE);
        String str = sp.getString("peter1", "");
        CodeBag.showText(str);
    }

    @Test(name = "applicationContext.getSharedPreferences(name)写xml \n目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_application_set() {
        SharedPreferences sp = CodeBag
                .context().getApplicationContext().getSharedPreferences("getSharedPreferences2", Context.MODE_PRIVATE);
        sp.edit().putString("peter2", "12345").apply();
    }

    @Test(name = "applicationContext.getSharedPreferences(name)读xml\n 目录:/data/data/<package name>/shared_prefs/name.xml")
    public void sp_activity_get2() {
        SharedPreferences sp = CodeBag
                .context().getApplicationContext().getSharedPreferences("getSharedPreferences2", Context.MODE_PRIVATE);
        String str = sp.getString("peter2", "");
        CodeBag.showText(str);
    }

    @Test(name = "context.getPreferences()写xml \n目录:/data/data/<package name>/shared_prefs/名字为activity类全路径名去掉包名")
    public void sp_activity_innermethod_set() {
        SharedPreferences sp = CodeBag.context().getPreferences(Context.MODE_PRIVATE);
        String name = CodeBag.context().getLocalClassName();
        sp.edit().putString("peter3", "6789").apply();
    }

    @Test(name = "context.getPreferences()读xml \n目录:/data/data/<package name>/shared_prefs/名字为activity类全路径名去掉包名")
    public void sp_activity_innermethod_get() {
        SharedPreferences sp = CodeBag.context().getPreferences(Context.MODE_PRIVATE);
        String str = sp.getString("peter3", "");
        CodeBag.showText(str);
    }

    @Test(name = "PreferenceManager.getDefaultSharedPreferences写xml \n/data/data/com.package.name/shared_prefs/com.package.name_preferences.xml")
    public void sp_manager_set() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(CodeBag.context());
        sp.edit().putString("peter34", "0000").apply();
    }

    @Test(name = "PreferenceManager.getDefaultSharedPreferences读xml\n /data/data/com.package.name/shared_prefs/com.package.name_preferences.xml")
    public void sp_manager_get() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(CodeBag.context());
        String str = sp.getString("peter34", "");
        CodeBag.showText(str);
    }

}
