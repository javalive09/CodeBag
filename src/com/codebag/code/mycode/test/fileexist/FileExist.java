package com.codebag.code.mycode.test.fileexist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class FileExist extends MyCode {

	private static final String TAG = FileExist.class.getSimpleName();
	private static final String FILE_NAME = "peter_test.jpg";
	
	public FileExist(MainActivity act) {
		super(act);
	}
	
	/**
	 * file 在new的一瞬间就exist了，所以不能用文件是否存在来判断，文件的是否下载成功
	 */
	@Entry
	public void writeFile() {
		String sdPath = "/sdcard/peter123.jpg";
		Log.i(TAG, "sdCard path = " + sdPath);
		
		File mFile = new File(sdPath);
		
		try {
			
			File file = new File("/sdcard/peter_test.jpg");
			
			FileInputStream fi = new FileInputStream(file);
			
			FileOutputStream fo = new FileOutputStream(mFile);
			
			int data;
			
			Log.i(TAG, "test exist = " + file.exists());
			Log.i(TAG, "123 exist = " + mFile.exists());
			
			if((data = fi.read()) != -1) {
				fo.write(data);
				
				try {
					boolean exist = mFile.exists();
					Log.i(TAG, "123 exist = " + exist);
					Thread.sleep(5000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			fo.close();
			fi.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exist() {
		
	}

}
