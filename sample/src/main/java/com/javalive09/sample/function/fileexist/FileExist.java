package com.javalive09.sample.function.fileexist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;

public class FileExist extends Entry {

	private static final String FILE_NAME = "peter_test.jpg";
	
	/**
	 * file 在new的一瞬间就exist了，所以不能用文件是否存在来判断，文件的是否下载成功
	 */
	public void writeFile() {
		String sdPath = Environment.getExternalStorageDirectory() + "/peter123.jpg";
		LogUtil.i("sdCard path = " + sdPath);
		
		File mFile = new File(sdPath);
		
		try {
			
			File file = new File(Environment.getExternalStorageDirectory().getPath() + "/peter_test.jpg");
			
			FileInputStream fi = new FileInputStream(file);
			
			FileOutputStream fo = new FileOutputStream(mFile);
			
			int data;

			Log.i("test exist = " + file.exists());
			Log.i("123 exist = " + mFile.exists());
			
			if((data = fi.read()) != -1) {
				fo.write(data);
				
				try {
					boolean exist = mFile.exists();
					Log.i( "123 exist = " + exist);
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
	
    public void getDebugConfigValuel() {
        String value = null;
            // 读取测试配置文件,方便测试
            String state = Environment.getExternalStorageState();
            String dir_ = Environment.getExternalStorageDirectory().toString();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(Environment.getExternalStorageDirectory(), "peter_dir");
				Log.i("dir = " + dir);
                
                if (!dir.exists()) {
                    dir.mkdirs();
                }
//                if (file.isFile() && file.canRead()) {

        }
    }

}
