package com.codebag.code.mycode.test.fileexist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class FileExist extends MyCode {

	private static final String TAG = FileExist.class.getSimpleName();
	private static final String FILE_NAME = "peter_test.jpg";
	
	public FileExist(InovkedViewActivity act) {
		super(act);
	}
	
	/**
	 * file 在new的一瞬间就exist了，所以不能用文件是否存在来判断，文件的是否下载成功
	 */
	@Entry
	public void writeFile() {
		String sdPath = Environment.getExternalStorageDirectory() + "/peter123.jpg";
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
	
	@Entry
    public void getDebugConfigValuel() {
        String value = null;
            // 读取测试配置文件,方便测试
            String state = Environment.getExternalStorageState();
            String dir_ = Environment.getExternalStorageDirectory().toString();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(Environment.getExternalStorageDirectory(), "peter_dir");
                Log.i("peter", "dir = " + dir);
                
                if (!dir.exists()) {
                    dir.mkdirs();
                }
//                if (file.isFile() && file.canRead()) {

        }
    }

}
