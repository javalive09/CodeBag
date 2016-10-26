package com.javalive09.sample.function.cache.sdcard.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.os.Environment;
import com.javalive09.codebag.Entry;

/**
 * 可以被清除的缓存目录
 * 
 * Context.getExternalFilesDir() 
 * 
 * Context.getExternalCacheDir() 对应设置里面的清除缓存
 * 
 * 这两个目录在程序卸载或设置里的清除数据后，会全被清除。覆盖安装不会被清除。
 *
 */
public class Android_data_files extends Entry {


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
	
	/**
	 * SDCard/Android/data/你的应用的包名/files/目录
	 * 
	 * 位置： SDCard/Android/data/com.codebag/files/file.txt
	 * 
	 */
	public void write_file() {
		if(!isExternalStorageWritable()) {
			return;
		}
		String transferFile = "file.txt";
		File extDir = getActivity().getExternalFilesDir(null);
		File requestFile = new File(extDir, transferFile);
		requestFile.setReadable(true, false);
		writeToFile("filexxx", requestFile);
		showTxt("dir=" + requestFile.getAbsolutePath());
	}
	
	public void read_file_str() {
		if(!isExternalStorageReadable()) {
			return;
		}
		String transferFile = "file.txt";
		File extDir = getActivity().getExternalFilesDir(null);
		File requestFile = new File(extDir, transferFile);
		String str = readFromFile(requestFile);
		showTxt("file str =" + str);
	}
	
	/**
	 * SDCard/Android/data/你的应用包名/cache/目录
	 * 
	 * 位置：SDCard/Android/data/com.codebag/cache/cache.txt
	 * 
	 */
	public void write_cache() {
		if(!isExternalStorageWritable()) {
			return;
		}
		String transferFile = "cache.txt";
		File extDir = getActivity().getExternalCacheDir();
		File requestFile = new File(extDir, transferFile);
		requestFile.setReadable(true, false);
		writeToFile("cachexxx", requestFile);
		showTxt("dir=" + requestFile.getAbsolutePath());
	}

	public void read_cache_str() {
		if(!isExternalStorageReadable()) {
			return;
		}
		String transferFile = "cache.txt";
		File extDir = getActivity().getExternalCacheDir();
		File requestFile = new File(extDir, transferFile);
		String str = readFromFile(requestFile);
		showTxt("file str =" + str);
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
