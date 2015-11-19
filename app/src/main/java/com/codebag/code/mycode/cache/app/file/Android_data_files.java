package com.codebag.code.mycode.cache.app.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.widget.Toast;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * getFilesDir();	 目录： /data/data/<package name>/files
 * openFileOutput(); 目录： /data/data/<package name>/files
 * getCacheDir();    目录： /data/data/<package name>/cache
 */

public class Android_data_files extends MyCode {

	public Android_data_files(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void write_file() {
		String transferFile = "file.txt";
		File dir = getActivity().getFilesDir();
		File requestFile = new File(dir, transferFile);
		requestFile.setReadable(true, false);
		writeToFile("filexxxx", requestFile);
		showTxt("dir =" + requestFile.getAbsolutePath());
	}
	
	@Entry
	public void read_file() {
		String transferFile = "file.txt";
		File dir = getActivity().getFilesDir();
		File requestFile = new File(dir, transferFile);
		String str = readFromFile(requestFile);
		showTxt(transferFile +":"+ str);
	}
	
	@Entry
	public void write_cache() {
		String transferFile = "cache.txt";
		File dir = getActivity().getCacheDir();
		File requestFile = new File(dir, transferFile);
		requestFile.setReadable(true, false);
		writeToFile("cachexxxx", requestFile);
		showTxt("dir =" + requestFile.getAbsolutePath());
	}
	
	@Entry
	public void read_cache() {
		String transferFile = "cache.txt";
		File dir = getActivity().getCacheDir();
		File requestFile = new File(dir, transferFile);
		String str = readFromFile(requestFile);
		showTxt(transferFile +":"+ str);
	}
	
	@Entry
	public void write_openfile() {
		writeToFile("write_openfilexxxx");
		showTxt( "str :"+ "write_openfilexxxx");
	}
	
	@Entry
	public void read_openfile() {
		String str = readFromFile();
		Toast.makeText(getActivity(), str, Toast.LENGTH_LONG).show();
		showTxt( "file :"+ str);
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
	
	private void writeToFile(String data) {
	    try {
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput("config.txt", Context.MODE_PRIVATE));
	        outputStreamWriter.write(data);
	        outputStreamWriter.close();
	    }
	    catch (IOException e) {
	    } 
	}

	private String readFromFile() {
	    String ret = "";
	    try {
	        InputStream inputStream = getActivity().openFileInput("config.txt");

	        if ( inputStream != null ) {
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String receiveString = "";
	            StringBuilder stringBuilder = new StringBuilder();

	            while ( (receiveString = bufferedReader.readLine()) != null ) {
	                stringBuilder.append(receiveString);
	            }

	            inputStream.close();
	            ret = stringBuilder.toString();
	        }
	    }
	    catch (FileNotFoundException e) {
	    } catch (IOException e) {
	    }
	    return ret;
	}

}
