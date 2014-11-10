package com.codebag.code.mycode.test.fileexist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.util.EncodingUtils;

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
	
	@Entry
	public void writeFile() {
		String sdPath = Environment.getExternalStorageDirectory().getPath();
		Log.i(TAG, "sdCard path = " + sdPath);
		
		File mFile = new File(sdPath);
		
		try {
			
			FileInputStream inputStream = new FileInputStream(mFile);
			
//			byte[] b = new byte[inputStream.available()];
//			
//			inputStream.read(b);
			
			File file = new File(getActivity().getFilesDir(),"peter.jpg");
			
			FileOutputStream fo = new FileOutputStream(file);
			
			byte[] b = new byte[1024];
			
			int data;
			
			if((data = inputStream.read()) != -1) {
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        //若该文件存在
        if (mFile.exists()) {
            Bitmap bitmap=BitmapFactory.decodeFile(sdPath);
        }
        
	}
	
	public void exist() {
		
	}

}
