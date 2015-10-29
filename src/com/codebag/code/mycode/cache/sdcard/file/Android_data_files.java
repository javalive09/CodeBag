package com.codebag.code.mycode.cache.sdcard.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Environment;
import android.widget.Toast;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 可以被清除的缓存目录
 * 
 * Context.getExternalFilesDir() 
 * 
 * Context.getExternalCacheDir() 对应设置里面的清除缓存
 * 
 * 这两个目录在程序卸载或设置里的清除数据后，会全被清除。
 *
 */
public class Android_data_files extends MyCode {

	public Android_data_files(InovkedViewActivity act) {
		super(act);
	}
	
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	/**
	 * SDCard/Android/data/你的应用的包名/files/目录
	 * 
	 * 位置： SDCard/Android/data/com.codebag/files/file.txt
	 * 
	 */
	@Entry
	public void invoke_file() {
		if(!isExternalStorageWritable()) {
			return;
		}
		String transferFile = "file.txt";
		File extDir = getActivity().getExternalFilesDir(null);
		File requestFile = new File(extDir, transferFile);
		requestFile.setReadable(true, false);
		writeToFile("filexxx", requestFile);
	}
	
	@Entry
	public void get_file_str() {
		if(!isExternalStorageReadable()) {
			return;
		}
		String transferFile = "file.txt";
		File extDir = getActivity().getExternalFilesDir(null);
		File requestFile = new File(extDir, transferFile);
		String str = readFromFile(requestFile);
		Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * SDCard/Android/data/你的应用包名/cache/目录
	 * 
	 * 位置：SDCard/Android/data/com.codebag/cache/cache.txt
	 * 
	 */
	@Entry
	public void invoke_cache() {
		if(!isExternalStorageWritable()) {
			return;
		}
		String transferFile = "cache.txt";
		File extDir = getActivity().getExternalCacheDir();
		File requestFile = new File(extDir, transferFile);
		requestFile.setReadable(true, false);
		writeToFile("cachexxx", requestFile);
	}

	@Entry
	public void get_cache_str() {
		if(!isExternalStorageReadable()) {
			return;
		}
		String transferFile = "cache.txt";
		File extDir = getActivity().getExternalCacheDir();
		File requestFile = new File(extDir, transferFile);
		String str = readFromFile(requestFile);
		Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
	}
	
	private void writeToFile(String data, File extDir) {
	    try {
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(extDir));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }catch (IOException e) {} 
	}
	
	private String readFromFile(File extDir) {
	    String ret = "";
	    try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(extDir));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }
            bufferedReader.close();
	        ret = stringBuilder.toString();
	    }
	    
	    catch (FileNotFoundException e) {
	    }catch (IOException e) {}

	    return ret;
	} 

}
